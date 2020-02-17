package uk.ac.ed.inf.s1654170.mrai.evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import uk.ac.ed.inf.s1654170.mrai.exprs.Base;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.exprs.Union;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class Evaluate {
	
	private static Database db;
	private static ArrayList<String> fileName = new ArrayList<>();
	
	private static void readData() throws IOException {
		File dirPath = new File(System.getProperty("user.dir"));
		File folder = new File(dirPath, "src/main/java/uk/ac/ed/inf/s1654170/mrai/evaluation");
		File[] listOfFiles = folder.listFiles();
		
		//ArrayList<String> fileName = new ArrayList<>();
		ArrayList<String> attributes = new ArrayList<>();
		ArrayList<String> attributeTypes = new ArrayList<>();
		
		Map<String,List<Record>> tables = new HashMap<>();
		for (File file : listOfFiles) {
			if (file.getName().equals("Evaluate.java")) {
				continue;
			}
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
		
		db = new Database(sch);
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
		
		/*for (File file : listOfFiles) {
			if (file.getName().equals("Evaluate.java")) {
				continue;
			} else {
				fileName.add(file.getName().replace(".csv", ""));
				String path = dirPath + "\\" + file.getName();
				BufferedReader csvReader = new BufferedReader(new FileReader(path));
				for (int i = 0; i < 2; i++) {
					if (i == 0) {
						attributes.add(csvReader.readLine());
					} else {
						attributeTypes.add(csvReader.readLine());
					}
				}
				csvReader.close();
			}
		}
		
		Schema sch = new Schema(fileName, attributes, attributeTypes);
		
		
		db = new Database(sch);
		
		for (File file : listOfFiles) {
			String name = file.getName().replace(".csv", "");
			
			if (name.equals("Evaluate.java")) {
				continue;
			} else {
				Table table = new Table(db.getSchema().getSignature(name));
				List<Column.Type> types = sch.getSignature(name).getTypes();
				
				String path = dirPath + "\\" + file.getName();
				BufferedReader csvReader = new BufferedReader(new FileReader(path));
				
				int x = 0;
				String row;
				while((row = csvReader.readLine()) != null) {
					if (x == 0 || x == 1) {
						x++;
					} else {
						String[] val = row.split(",");
						Record r = Record.valueOf(types, val);
						table.add(r);
					}
				}
				
				try {
					db.add(name, table);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				csvReader.close();
			}
		}*/
	}
	
	private static void evaluateValidation() throws SchemaException {
		// Evaluate the validation method on correct and incorrect input
		System.out.println();
		System.out.println("Evaluating the validation method.");
		System.out.println();

		for (String name : fileName) {
			if (!name.substring(0, 1).equals("_")) {
				System.out.println(String.format("%s: %s", name, db.getSchema().getSignature(name)));
			}
		}
		
		System.out.println();
		
		RAExpr correctUnion = new Union(new Base("Students"), new Base("SportStudents"));
		System.out.println(correctUnion.toString());
		System.out.println("Expected: true");
		System.out.println("Actual: " + correctUnion.validate(db.getSchema()));
		
		System.out.println();
		
		RAExpr incorrectUnion = new Union(new Base("Students"), new Base("Teachers"));
		System.out.println(incorrectUnion.toString());
		System.out.println("Expected: schema exception");
		System.out.println("Actual: " + incorrectUnion.validate(db.getSchema()));
	}
	
	private static void evaluateTableOperations() {
		System.out.println();
		System.out.println("Evaluating the different table operations.");
		System.out.println();
		
		for (String name : fileName) {
			if (!name.substring(0, 1).equals("_")) {
				System.out.println(String.format("%s: %s", name, db.getTable(name)));
			}
		}
		
		System.out.println();
		
		RAExpr correctUnion = new Union(new Base("Students"), new Base("SportStudents"));
		System.out.println(correctUnion.toString());
		System.out.println("Expected: " + db.getTable("_StudentsUnionSportStudents"));
		System.out.println("Actual: " + correctUnion.executeValid(db));
	}
	
	public static void runEvaluation() throws IOException, SchemaException {
		readData();

		evaluateValidation();
		evaluateTableOperations();
	}
}
