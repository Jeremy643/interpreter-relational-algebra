package uk.ac.ed.inf.s1654170.mrai;

import java.io.BufferedReader;
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

import uk.ac.ed.inf.s1654170.mrai.conditions.*;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.instance.*;
import uk.ac.ed.inf.s1654170.mrai.parser.*;
import uk.ac.ed.inf.s1654170.mrai.schema.*;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;


public class App {

	public static void main(String[] args) throws IOException {
				
		Scanner sc = new Scanner(System.in);

		File dirPath = new File(System.getProperty("user.dir"));
		File folder = new File(dirPath, "src/main/java/uk/ac/ed/inf/s1654170/mrai/data");
		File[] listOfFiles = folder.listFiles();
		
		List<String> fileName = new ArrayList<>();
		List<String> attributes = new ArrayList<>();
		List<String> attributeTypes = new ArrayList<>();
		
		/*for (File file : listOfFiles) {
			fileName.add(file.getName().replace(".csv", ""));
			String path = folder + File.separator + file.getName();
			BufferedReader csvReader = new BufferedReader(new FileReader(path));
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					attributes.add(csvReader.readLine());
				} else {
					attributeTypes.add(csvReader.readLine());
				}
			}
			csvReader.close();
		}*/
		
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
		
		/*for (File file : listOfFiles) {
			String name = file.getName().replace(".csv", "");
			
			Table table = new Table(db.getSchema().getSignature(name));
			List<Column.Type> types = sch.getSignature(name).getTypes();
			
			String path = folder + File.separator + file.getName();
			BufferedReader csvReader = new BufferedReader(new FileReader(path));
			
			int x = 0;
			String row;
			while((row = csvReader.readLine()) != null) {
				if (x == 0 || x == 1) {
					x++;
				} else {
					System.out.println(row);
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
		}*/

//		Table tableA = db.getTable("R");
//		Table tableB = db.getTable("S");
//		
//		for (Record r : tableA) {
//			for (Record s : tableB) {
//				int comp = r.compareTo(s);
//				String op;
//				if (comp == 0) {
//					op = "=";
//				} else if (comp > 0) {
//					op = ">";
//				} else {
//					op = "<";
//				}
//				System.out.println(String.format("%s %s %s", r, op, s));
//			}
//		}
		
		System.out.println(TableOperations.Union(db.getTable("Students"), db.getTable("SportStudents")));
		System.out.println(TableOperations.Difference(db.getTable("Students"), db.getTable("SportStudents")));
		//System.out.println(TableOperations.Intersect(db.getTable("Students"), db.getTable("SportStudents")));
		
		List<String> columns = new ArrayList<>();
		columns.add("Name");
		System.out.println(db.getTable("Students").getSignature().getAttributes());
		System.out.println(TableOperations.Project(columns, db.getTable("Students")));
		
//		System.out.println(TableOperations.UnionMax(db.getTable("R"), db.getTable("S")));
		
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
		System.out.println(TableOperations.Select(c4, db.getTable("Students")));
		
		/*Map<String,String> attrRename = new HashMap<>();
		attrRename.put("Name", "FullName");
		System.out.println(TableOperations.Rename(attrRename, db.getTable("Students")).getSignature().getAttributes());
		System.out.println(db.getTable("Students"));*/
		
		
		//Schema sch = new Schema("R:Name/STRING,Age/STRING;S:Name/STRING,Age/NUMBER;P:Name/STRING");
		//Table tbl = new Table();
		//List<Column.Type> types = sch.getSignature("R").getTypes();
		//Column.Type[] types = new Column.Type[] {Column.Type.STRING, Column.Type.NUMBER};
		//Record r1 = Record.valueOf( types, "John", "20");
		//Record r2 = Record.valueOf( types, "John", "20");
		//tbl.add(r1);
		//tbl.add(r2);
		//System.out.println(r1.equals(r2));
		
		//tbl.add(Record.valueOf( types, "Mary", "20"));
		//tbl.add(Record.valueOf( types, "Mary", "20"));
		//for (Record r : tbl) {
			//System.out.println(r);
		//}
		
		//Database db = new Database(sch);
//		try {
//			db.add("R", tbl);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		//System.exit(0);

		//Scanner sc = new Scanner(System.in);
		System.out.print("RA expression: ");
		String input = sc.nextLine();

		CharStream charStream = CharStreams.fromString(input);

		RelationalAlgebraLexer tl = new RelationalAlgebraLexer(charStream);
		CommonTokenStream commonTokenStream = new CommonTokenStream(tl);
		RelationalAlgebraParser tp = new RelationalAlgebraParser(commonTokenStream);

		ParseTree parseTree = tp.start();
		BuildExpr buildExpr = new BuildExpr();

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(buildExpr, parseTree);

		RAExpr e = buildExpr.getExpr();

		System.out.println(e);

		System.out.println();

		//System.out.print("Schema: ");
		//String schemaTest = "R:Name/STRING,Age/NUMBER;S:Name/STRING,Age/NUMBER;P:Name/STRING";
		//Schema schema = new Schema(sc.nextLine());
//		try {
//			System.out.println(e.signature(sch));
//		} catch (SchemaException se) {
//			System.out.println(se.getMessage());
//			se.printStackTrace();
//		}
//		if (e.validate(sch)) {
//			System.out.println("Valid!");
//			// Valid therefore execute expression
//			System.out.println(e.execute(db));
//		} else {
//			System.out.println("Not valid!");
//		}
		System.out.println(e.execute(db));

		sc.close();

		/*
		Base r = new Base("R");
		Base s = new Base("S");
		Base t = new Base("T");

		RAExpr e1 = new Union(r, s);
		System.out.println(e1);

		RAExpr e2 = new Intersect(e1, t);
		System.out.println(e2);

		List<String> attr = new ArrayList<String>();
		attr.add("A");
		attr.add("B");
		RAExpr e3 = new Project(t, attr);
		System.out.println(e3);

		HashMap<String, String> renameAttr = new HashMap<>();
		renameAttr.put("Name", "FullName");
		renameAttr.put("Surname", "LastName");
		RAExpr e4 = new Rename(s, renameAttr);
		System.out.println(e4);

		Condition c1 = new Equality(new Term("Name", false), new Term("Jill", true));
		RAExpr e5 = new Select(c1, t);
		System.out.println(e5);

		Condition c2 = new And(c1, new Greater(new Term("Height", false), new Term("160", true)));
		RAExpr e6 = new Select(c2, s);
		System.out.println(e6);
		 */
	}

}
