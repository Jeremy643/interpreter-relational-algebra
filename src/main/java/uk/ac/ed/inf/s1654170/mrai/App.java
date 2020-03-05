package uk.ac.ed.inf.s1654170.mrai;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import uk.ac.ed.inf.s1654170.mrai.conditions.And;
import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;
import uk.ac.ed.inf.s1654170.mrai.conditions.Equality;
import uk.ac.ed.inf.s1654170.mrai.conditions.Inequality;
import uk.ac.ed.inf.s1654170.mrai.conditions.Not;
import uk.ac.ed.inf.s1654170.mrai.conditions.Or;
import uk.ac.ed.inf.s1654170.mrai.conditions.Term;
import uk.ac.ed.inf.s1654170.mrai.evaluation.Evaluate;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.parser.BuildExpr;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraParser;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;


public class App {

	public static void main(String[] args) throws IOException {
		
		
		
		
		//========================DATABASE DEFINE - IMPORTANT========================

		
		
		
		Scanner sc = new Scanner(System.in);

		File dirPath = new File(System.getProperty("user.dir"));
		File folder = new File(dirPath, "src/main/java/uk/ac/ed/inf/s1654170/mrai/data");
		File[] listOfFiles = folder.listFiles();

		List<String> fileName = new ArrayList<>();
		List<String> attributes = new ArrayList<>();
		List<String> attributeTypes = new ArrayList<>();

		Map<String,List<Record>> tables = new HashMap<>();
		for (File file : listOfFiles) {
			String name = file.getName().replace(".csv", "");
			fileName.add(name);

			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

			int index = 0;
			List<Record> tableRecords = new ArrayList<>();
			List<Type> types = new ArrayList<>();
			for (CSVRecord record : records) {
				int size = record.size();
				switch (index) {
				case 0:
					String attr = "";
					for (int i = 0; i < size; i++) {
						if (i == size-1) {
							attr += record.get(i);
						} else {
							attr += record.get(i) + ",";
						}
					}
					attributes.add(attr);
					break;
				case 1:
					String type = "";
					for (int i = 0; i < size; i++) {
						if (i == size-1) {
							type += record.get(i);
						} else {
							type += record.get(i) + ",";
						}
						types.add(Type.valueOf(record.get(i)));
					}
					attributeTypes.add(type);
					break;
				default:
					String[] values = new String[size];
					for (int i = 0; i < size; i++) {
						values[i] = record.get(i);
					}
					Record r = Record.valueOf(types, values);
					tableRecords.add(r);
					break;
				}
				tables.put(name, tableRecords);
				index++;
			}
		}

		Schema sch = new Schema(fileName, attributes, attributeTypes);

		Database db = new Database(sch);
		for (String name : fileName) {
			Table table = new Table(sch.getSignature(name));
			table.addAll(tables.get(name));
			System.out.println(table);
			try {
				db.add(name, table);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		try {
			Evaluate.runEvaluation();
		} catch (SchemaException e2) {
			e2.printStackTrace();
		}
		
		
		
		
		//========================OPERATION AND CONDITION - PLAY AROUND========================

		
		
		
		System.out.println(TableOperations.Union(db.getTable("Students"), db.getTable("SportStudents")));
		System.out.println(TableOperations.Difference(db.getTable("Students"), db.getTable("SportStudents")));

		System.out.println(db.getTable("P").equals(db.getTable("Q")));

		List<String> columns = new ArrayList<>();
		columns.add("Name");
		System.out.println(db.getTable("Students").getSignature().getAttributes());
		System.out.println(TableOperations.Project(columns, db.getTable("Students")));

		System.out.println();
		
		// Age='18'
		Condition c1 = new Equality(new Term("Age",false), new Term("18",true));
		// Age='15' && ID='s001'
		Condition c2 = new And(new Equality(new Term("Age",false), new Term("15",true)),
				new Equality(new Term("ID",false), new Term("s001",true)));
		// (Age='16' && ID='s001') || Name='Homer'
		Condition c3 = new Or(new And(new Equality(new Term("Age",false), new Term("16",true)),
				new Equality(new Term("ID",false), new Term("s001",true))),
				new Inequality(new Term("Name",false), new Term("Homer",true)));
		// (Age='16' && ~(ID='s001')) || Name='Jane'
		Condition c4 = new Or(new And(new Equality(new Term("Age",false), new Term("16",true)),
				new Not(new Equality(new Term("ID",false), new Term("s001",true)))),
				new Inequality(new Term("Name",false), new Term("Jane",true)));
		
		System.out.println(TableOperations.Select(c1, db.getTable("Students")));
		System.out.println(TableOperations.Select(c2, db.getTable("Students")));
		System.out.println(TableOperations.Select(c3, db.getTable("Students")));
		System.out.println(TableOperations.Select(c4, db.getTable("Students")));
		System.out.println();
		
		

		
		//========================PARSING AND EXECUTION - IMPORTANT========================

		
		
		
		while (true) {
			System.out.print("(mrai)=> ");
			String input = sc.nextLine();
			
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

			CharStream charStream = CharStreams.fromString(input);

			RelationalAlgebraLexer tl = new RelationalAlgebraLexer(charStream);
			CommonTokenStream commonTokenStream = new CommonTokenStream(tl);
			RelationalAlgebraParser tp = new RelationalAlgebraParser(commonTokenStream);

			ParseTree parseTree = tp.start();
			BuildExpr buildExpr = new BuildExpr();

			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(buildExpr, parseTree);
			
			RAExpr e;
			try {
				e = buildExpr.getExpr();
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
