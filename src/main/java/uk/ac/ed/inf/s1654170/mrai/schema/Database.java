package uk.ac.ed.inf.s1654170.mrai.schema;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class Database {

	private Schema schema;
	private HashMap<String, Table> instance;

	public Database(Schema schema) {
		this.schema = schema;
		this.instance = new HashMap<>();
	}
	
	public Table getTable(String name) {
		return instance.get(name);
	}
	
	public Schema getSchema() {
		return schema;
	}

	public void add(String n, Table t) throws Exception {
		add(n,t,false);
	}

	public void add(String n, Table t, boolean validate) throws Exception {
		if (schema.getRelations().contains(n) == false) {
			throw new Exception("No relation name " + n + " in schema");
		}
		if (validate == true) {
			if (validateTypes(n, t) ==  false) {
				throw new Exception("Types do not match");
			}
		}
		// Consider throwing an exception if mapping for "n" already present
		instance.put(n, t);
	}

	private boolean validateTypes(String n, Table t) {
		boolean valid = true;
		Signature sig = schema.getSignature(n);
		List<Column.Type> types = sig.getTypes();
		for (Record r : t) {
			List<Column.Type> dv = r.getTypes();
			if (types.equals(dv) == false) {
				valid = false;
			}
		}
		return valid;
	}
	
	public static Database fromCSV(File folder) throws IOException {
		return fromCSV(folder, false, false);
	}
	
	public static Database fromCSV(File folder, boolean ordered, boolean bags) throws IOException {
		File[] listOfFiles = folder.listFiles();

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
			String name = file.getName().replace(".csv", "");
			fileName.add(name);
			
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);

			int index = 0;
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
					if (!bags) {
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

		Schema sch = new Schema(fileName, attributes, attributeTypes, ordered);

		Database db = new Database(sch);
		for (String name : fileName) {
			Table table = new Table(sch.getSignature(name), bags);
			table.addAll(tables.get(name));
			try {
				db.add(name, table);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return db;
	}
}
