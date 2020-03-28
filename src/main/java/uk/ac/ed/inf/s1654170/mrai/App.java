package uk.ac.ed.inf.s1654170.mrai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;

public class App {

	public static void main(String[] args) throws IOException, Exception {

		File folder = null;
		boolean orderedColumns = false;
		boolean unorderedColumns = false;
		boolean bagEvaluation = false;
		boolean setEvaluation = false;

		// ========================COMMAND-LINE OPTIONS - IMPORTANT========================

		Option help = new Option("h", "help", false, "print this help");
		Option config = new Option("c", "config", true, "path to configuration file");
		config.setArgName("PATH");
		Option dbPath = new Option("d", "data", true, "path to CSV files");
		dbPath.setArgName("PATH");
		Option ordered = new Option("o", "ordered", false, "ordered columns (default: --unordered)\nMutually exclusive with --unordered");
		Option unordered = new Option("u", "unordered", false, "unordered columns (DEFAULT)\nMutually exclusive with --ordered");
		Option bag = new Option("b", "bag", false, "bag evaluation (default: sets)\nMutually exclusive with --set");
		Option set = new Option("s", "set", false, "set evaluation (DEFAULT)\nMutually exclusive with --bag");

		Options options = new Options();
		options.addOption(help);
		options.addOption(config);
		options.addOption(dbPath);
		options.addOption(ordered);
		options.addOption(unordered);
		options.addOption(bag);
		options.addOption(set);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		try {
			CommandLine cmd = parser.parse(options, args);

			if (cmd.getOptions().length == 0) {
				formatter.printHelp("java -jar target/mrai-0.1-SNAPSHOT.jar", options);
				System.exit(0);
			}
			if (cmd.hasOption("h")) {
				formatter.printHelp("java -jar target/mrai-0.1-SNAPSHOT.jar", options);
				System.exit(0);
			}
			if (cmd.hasOption("c") && cmd.getOptions().length > 1) {
				//if config and other options are used - display message
				System.out.println("WARNING: By including other options with the configuration file this will cause the relevant values"
						+ " being held in the file to be ignored.");
			}
			if (!cmd.hasOption("c") && cmd.hasOption("d") && cmd.getOptions().length < 3) {
				// display warning message about defualt setting
				System.out.println("WARNING: The defualt setting is; columns=unordered, evaluation=sets.");
			}
			if ((cmd.hasOption("b") && cmd.hasOption("s")) || (cmd.hasOption("u") && cmd.hasOption("o"))) {
				//bags/sets and ordered/unordered are mutually exclusive - exit with error message
				System.out.println("ERROR: You have used mutually exclusive options.");
				System.exit(1);
			}
			// user must provide a config file or else use other relevant options
			if (cmd.hasOption("c")) {
				String configPath = cmd.getOptionValue("c");

				InputStream configStream = new FileInputStream(configPath);
				Properties prop = new Properties();
				try {
					prop.load(configStream);
				} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
					System.exit(1);
				}

				//validate data from config file
				folder = new File(prop.getProperty("data_path"));
				if (!folder.isDirectory()) {
					//path entered is not a valid directory
					System.out.println(String.format("ERROR: The following is not a valid directory path:\n\"%s\"", folder));
					System.exit(1);
				}
				String orderedConfig = prop.getProperty("ordered_columns");
				if (!orderedConfig.equals("yes") && !orderedConfig.equals("no")) {
					System.out.println(String.format("ERROR: ordered_columns should be a \"yes\" or a \"no\", not \"%s\".", orderedConfig));
					System.exit(1);
				}
				orderedColumns = orderedConfig.equals("yes") ? true : false;
				unorderedColumns = orderedColumns ? false : true;
				String bagConfig = prop.getProperty("bag_evaluation");
				if (!bagConfig.equals("yes") && !bagConfig.equals("no")) {
					System.out.println(String.format("ERROR: bag_evaluation should be a \"yes\" or a \"no\", not \"%s\".", bagConfig));
					System.exit(1);
				}
				bagEvaluation = bagConfig.equals("yes") ? true : false;
				setEvaluation = bagEvaluation ? false : true;
			}
			if (cmd.hasOption("d")) {
				folder = new File(cmd.getOptionValue("d"));
				if (!folder.isDirectory()) {
					//path entered is not a valid directory
					System.out.println(String.format("ERROR: The following is not a valid directory path:\n\"%s\"", folder));
					System.exit(1);
				}
			} else {
				if (!cmd.hasOption("c")) {
					// No database given - print error and exit
					System.out.println("ERROR: You must use specify a database either by a configuration file or by using the"
							+ " option \"-d\".");
					System.exit(1);
				}
			}
			if (cmd.hasOption("o")) {
				// columns are ordered - default
				orderedColumns = true;
				unorderedColumns = false;
			}
			if (cmd.hasOption("u") && !cmd.hasOption("o")) {
				// columns are unordered
				unorderedColumns = true;
			}
			if (orderedColumns && unorderedColumns) {
				// config file gives orderedColumns true and unord option used as well
				unorderedColumns = true;
				orderedColumns = false;
			}
			if (cmd.hasOption("b")) {
				// bag evaluation - default
				bagEvaluation = true;
				setEvaluation = false;
			}
			if (cmd.hasOption("s") && !cmd.hasOption("b")) {
				// set evaluation
				setEvaluation = true;
			}
			if (bagEvaluation && setEvaluation) {
				// config file gives bagEvaluation true and set option used as well
				setEvaluation = true;
				bagEvaluation = false;
			}
		} catch (ParseException e3) {
			System.out.println(e3.getMessage());
			formatter.printHelp("java -jar target/mrai-0.1-SNAPSHOT.jar", options);
			System.exit(1);
		}

		// ========================DATABASE DEFINE - IMPORTANT========================

		/*File[] listOfFiles = folder.listFiles();

		//make sure that there is at least one csv file
		int counter = 0;
		for (File f : listOfFiles) {
			String name = f.getName();
			if (name.endsWith(".csv")) {
				counter++;
			}
		}
		if (counter == 0) {
			System.out.println("ERROR: Your chosen directory does not contain enough csv files.");
			System.exit(1);
		}

		List<String> fileName = new ArrayList<>();
		List<String> attributes = new ArrayList<>();
		List<String> attributeTypes = new ArrayList<>();

		Map<String, List<Record>> tables = new HashMap<>();
		for (File file : listOfFiles) {
			if (!file.getName().endsWith(".csv")) {
				//if file not csv then ignore
				continue;
			}
			String name = file.getName().replace(".csv", "");  //.trim().replaceAll("\\s+", " ")
			fileName.add(name);
			
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

			int index = 0;
			//Collection<Record> tableRecords = bagEvaluation ? new ArrayList<>() : new HashSet<>();
			List<Record> tableRecords = new ArrayList<>();
			List<Type> types = new ArrayList<>();
			List<String> dupAttr = new ArrayList<>();
			recordLoop: for (CSVRecord record : records) {
				int size = record.size();
				switch (index) {
				case 0:
					String attr = "";
					for (int i = 0; i < size; i++) {
						dupAttr.add(record.get(i));
						if (i == size - 1) {
							attr += record.get(i);
						} else {
							attr += record.get(i) + ",";
						}
					}
					// check for duplicate attributes
					Set<String> dupSetAttr = new HashSet<>(dupAttr);
					if (dupAttr.size() != dupSetAttr.size()) {
						// display error and don't include in database
						fileName.remove(name);
						System.out.println(
								String.format("WARNING: The relation \"%s\" contains duplicate attributes and will"
										+ " therefore not be included in the database.", name));
						break recordLoop;
					}
					attributes.add(attr.trim().replaceAll("\\s+", " "));
					break;
				case 1:
					String type = "";
					for (int i = 0; i < size; i++) {
						if (i == size - 1) {
							type += record.get(i).trim();
						} else {
							type += record.get(i).trim() + ",";
						}
						types.add(Type.valueOf(record.get(i).trim()));
					}
					attributeTypes.add(type);
					break;
				default:
					String[] values = new String[size];
					for (int i = 0; i < size; i++) {
						values[i] = record.get(i);
					}
					Record r = Record.valueOf(types, values);
					if (!bagEvaluation) {
						if (!tableRecords.contains(r)) {
							tableRecords.add(r);
							break;
						} else {
							break;
						}
					}
					tableRecords.add(r);
					break;
				}
				index++;
			}
			tables.put(name, tableRecords);
		}

		Schema sch = new Schema(fileName, attributes, attributeTypes, orderedColumns);

		Database db = new Database(sch);
		for (String name : fileName) {
			Table table = new Table(sch.getSignature(name), bagEvaluation);
			table.addAll(tables.get(name));
			try {
				db.add(name, table);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}*/
		
		Database db = Database.fromCSV(folder, orderedColumns, bagEvaluation);

		// ========================PARSING AND EXECUTION - IMPORTANT========================
		
		Scanner sc = new Scanner(System.in);

		System.out.println("A multimodal interpreter for relational algebra by Jeremy Scott.\n"
				+ "Type \"\\help\" for more information.");
		while (true) {
			System.out.print("(mrai)=> ");
			String input = sc.nextLine();

			if (input.toLowerCase().trim().equals("")) {
				continue;
			}
			if (input.toLowerCase().trim().equals("\\exit")) {
				break;
			}
			if (input.toLowerCase().trim().equals("\\schema")) {
				for (String name : db.getSchema().getRelations()) {
					String fmt = "%s: %s";
					System.out.println(String.format(fmt, name, db.getSchema().getSignature(name)));
				}
				continue;
			}
			if (input.toLowerCase().trim().equals("\\settings")) {
				// display configuration
				if (orderedColumns) {
					System.out.println("Columns = ordered");
				} else {
					System.out.println("Columns = unordered");
				}
				if (bagEvaluation) {
					System.out.println("Evaluation = bags");
				} else {
					System.out.println("Evaluation = sets");
				}
				continue;
			}
			if (input.toLowerCase().trim().equals("\\configure")) {
				// change the configuration of ordered/unordered or bags/sets
				if (orderedColumns) {
					System.out.println("Set columns... unordered?[y/n]");
					String ans = sc.nextLine();
					if (ans.toLowerCase().trim().equals("y")) {
						// set columns to unordered
						unorderedColumns = true;
						orderedColumns = false;
						//sch = new Schema(fileName, attributes, attributeTypes, orderedColumns);
						//db = new Database(sch);
						db = Database.fromCSV(folder, orderedColumns, bagEvaluation);
						/*for (String name : fileName) {
							Table table = new Table(sch.getSignature(name), bagEvaluation);
							table.addAll(tables.get(name));
							try {
								db.add(name, table);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}*/
					}
				} else {
					System.out.println("Set columns... ordered?[y/n]");
					String ans = sc.nextLine();
					if (ans.toLowerCase().trim().equals("y")) {
						// set columns to ordered
						orderedColumns = true;
						unorderedColumns = false;
						//sch = new Schema(fileName, attributes, attributeTypes, orderedColumns);
						//db = new Database(sch);
						db = Database.fromCSV(folder, orderedColumns, bagEvaluation);
						/*for (String name : fileName) {
							Table table = new Table(sch.getSignature(name), bagEvaluation);
							table.addAll(tables.get(name));
							try {
								db.add(name, table);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}*/
					}
				}
				if (bagEvaluation) {
					System.out.println("Set evaluation... sets?[y/n]");
					String ans = sc.nextLine();
					if (ans.toLowerCase().trim().equals("y")) {
						// change evaluation to sets
						setEvaluation = true;
						bagEvaluation = false;
						//db = new Database(sch);
						db = Database.fromCSV(folder, orderedColumns, bagEvaluation);
						/*for (String name : fileName) {
							Table table = new Table(sch.getSignature(name), bagEvaluation);
							table.addAll(tables.get(name));
							try {
								db.add(name, table);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}*/
					}
				} else {
					System.out.println("Set evaluation... bags?[y/n]");
					String ans = sc.nextLine();
					if (ans.toLowerCase().trim().equals("y")) {
						// change evaluation to bags
						setEvaluation = false;
						bagEvaluation = true;
						//db = new Database(sch);
						db = Database.fromCSV(folder, orderedColumns, bagEvaluation);
						/*for (String name : fileName) {
							Table table = new Table(sch.getSignature(name), bagEvaluation);
							table.addAll(tables.get(name));
							try {
								db.add(name, table);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}*/
					}
				}
				continue;
			}
			if (input.toLowerCase().trim().equals("\\help")) {
				try {
					File helpFile = new File("help.txt");
					Scanner helpScanner = new Scanner(helpFile);
					while (helpScanner.hasNext()) {
						System.out.println(helpScanner.nextLine());
					}
					helpScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("ERROR: Could not find the help text file.");
					System.exit(1);
				}
				continue;
			}

			RAExpr e;
			try {
				e = RAExpr.parse(input);
			} catch (Exception error) {
				System.out.println("ERROR: You entered something that isn't recognized.");
				continue;
			}

			try {
				Table t = e.execute(db);
				AsciiTable at = new AsciiTable();
				at.addRule();
				at.addRow(t.getSignature().getAttributes());
				at.addRule();
				for (Record r : t) {
					at.addRow(r);
					at.setTextAlignment(TextAlignment.CENTER);
					at.setPaddingLeft(1);
					at.setPaddingRight(1);
				}
				at.addRule();
				CWC_LongestLine cwc = new CWC_LongestLine();
				at.getRenderer().setCWC(cwc);
				System.out.println(at.render());
			} catch (SchemaException e1) {
				System.out.println("ERROR: " + e1.getMessage());
				continue;
			}
		}
		sc.close();
	}
}
