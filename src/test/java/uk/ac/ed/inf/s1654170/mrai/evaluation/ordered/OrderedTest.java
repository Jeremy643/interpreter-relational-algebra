//package uk.ac.ed.inf.s1654170.mrai.evaluation.ordered;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import uk.ac.ed.inf.s1654170.mrai.evaluation.GetDataHelper;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Base;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Difference;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Eliminate;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Intersect;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Product;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Project;
//import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
//import uk.ac.ed.inf.s1654170.mrai.exprs.Union;
//import uk.ac.ed.inf.s1654170.mrai.exprs.UnionMax;
//import uk.ac.ed.inf.s1654170.mrai.instance.Record;
//import uk.ac.ed.inf.s1654170.mrai.instance.Table;
//import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
//import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
//import uk.ac.ed.inf.s1654170.mrai.schema.Database;
//import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
//import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
//
//class OrderedTest {
//
//	private static Database dbBags;
//	private static Database dbSets;
//	private static boolean ordered = true;
//	private static boolean bags = true;
//	
//	private static Map<RAExpr,Table> resultMap = new HashMap<>();
//	
//	private Table getExpectedTable(String expr) throws IOException {
//		String expectedPath = System.getProperty("user.dir") + "/src/test/java/uk/ac/ed/inf/s1654170/mrai/evaluation/ordered/";
//		InputStream configStream = new FileInputStream(expectedPath + "expected.properties");
//		Properties prop = new Properties();
//		try {
//			prop.load(configStream);
//		} catch (Exception e) {
//			System.out.println("ERROR: " + e.getMessage());
//			System.exit(1);
//		}
//		
//		String file = expectedPath + String.valueOf(prop.getProperty(expr.replaceAll("\\s+", "")));
//		File resultFile = new File(file);
//		Reader in = new FileReader(resultFile);
//		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
//		
//		int index = 0;
//		List<Record> tableRecords = new ArrayList<>();
//		List<String> attributes = new ArrayList<>();
//		List<Type> types = new ArrayList<>();
//		for (CSVRecord record : records) {
//			int size = record.size();
//			switch (index) {
//				case 0:
//					for (int i = 0; i < size; i++) {
//						attributes.add(record.get(i).trim().replaceAll("\\s+", " "));
//					}
//					break;
//				case 1:
//					for (int i = 0; i < size; i++) {
//						types.add(Type.valueOf(record.get(i).trim()));
//					}
//					break;
//				default:
//					String[] values = new String[size];
//					for (int i = 0; i < size; i++) {
//						values[i] = record.get(i);
//					}
//					Record r = Record.valueOf(types, values);
//					tableRecords.add(r);
//					break;
//			}
//			index++;
//		}
//		Signature sig = new BaseSignature(attributes, types, ordered);
//		Table table = new Table(sig);
//		table.addAll(tableRecords);
//			
//		return table;
//	}
//
//	@BeforeAll
//	static void readData() throws IOException, SchemaException {
//		dbBags = GetDataHelper.readData(ordered, bags);
//		dbSets = GetDataHelper.readData(ordered, !bags);
//		
//		String path = System.getProperty("user.dir") + "/src/test/java/uk/ac/ed/inf/s1654170/mrai/evaluation/ordered/";
//		InputStream configStream = new FileInputStream(path + "expected.properties");
//		Properties prop = new Properties();
//		try {
//			prop.load(configStream);
//		} catch (Exception e) {
//			System.out.println("ERROR: " + e.getMessage());
//			System.exit(1);
//		}
//		for (Object o : prop.keySet()) {
//			String k = (String) o;
//			RAExpr e = RAExpr.parse(k);
//			Table t = Table.fromCSV(new File(path + "/" + prop.getProperty(k)));
//			resultMap.put(e, t);
//		}
//	}
//	
//	/*@Test
//	void test1() throws SchemaException {
//		for (RAExpr e : resultMap.keySet()) {
//			Table t = e.execute(dbSets);
//			assertEquals(resultMap.get(e), t);
//		}
//	}*/
//
//	@Test
//	void testBaseBags() throws SchemaException {
//		RAExpr e = new Base("Students");
//		Signature studentSig = dbBags.getSchema().getSignature("Students");
//		Table expected = new Table(studentSig);
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//
//	@Test
//	void testBaseSets() throws SchemaException {
//		RAExpr e = new Base("SportStudents");
//		Signature sportStudentSig = dbSets.getSchema().getSignature("SportStudents");
//		Table expected = new Table(sportStudentSig);
//		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbSets));
//	}
//
//	@Test
//	void testEliminateBags() throws SchemaException, IOException {
//		// <E>(SportStudents)
//		RAExpr e = new Eliminate(new Base("SportStudents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
//	@Test
//	void testEliminateSets() throws SchemaException, IOException {
//		// <E>(Parents)
//		RAExpr e = new Eliminate(new Base("Parents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbSets));
//	}
//
//	@Test
//	void testUnionBags() throws SchemaException, IOException {
//		// Students <+> SportStudents
//		RAExpr e = new Union(new Base("Students"), new Base("SportStudents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//
////	@Test
////	void testUnionSets() throws SchemaException, IOException {
////		// Students <+> DetentionStudents
////		RAExpr e = new Union(new Base("Students"), new Base("DetentionStudents"));
////		Table expected = getExpectedTable(e.toString());
////		assertEquals(expected, e.execute(dbSets));
////	}
//
//	@Test
//	void testUnionMaxBags() throws SchemaException, IOException {
//		// Students <U> SportStudents
//		RAExpr e = new UnionMax(new Base("Students"), new Base("SportStudents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//
////	@Test
////	void testUnionMaxSets() throws SchemaException {
////		// Students <U> SportStudents
////		RAExpr e = new UnionMax(new Base("Students"), new Base("SportStudents"));
////		Signature studentSig = dbSets.getSchema().getSignature("Students");
////		Table expected = new Table(studentSig);
////		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
////		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
////		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
////		expected.add(Record.valueOf(studentSig.getTypes(), "Sean", "14", "s3"));
////		assertEquals(expected, e.execute(dbSets));
////	}
//
//	@Test
//	void testProjectBags() throws SchemaException, IOException {
//		List<String> attr = new ArrayList<>();
//		attr.add("Name");
//		attr.add("Teach");
//		// <P>[Name,Teach](Teachers)
//		RAExpr e = new Project(new Base("Teachers"), attr);
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//
////	@Test
////	void testProjectSets() throws SchemaException {
////		List<String> attr = new ArrayList<>();
////		attr.add("Class");
////		List<Column.Type> types = new ArrayList<>();
////		types.add(Column.Type.STRING);
////		// <P>[Class](Students)
////		RAExpr e = new Project(new Base("Students"), attr);
////		Signature sig = new BaseSignature(attr, types, ordered);
////		Table expected = new Table(sig);
////		expected.add(Record.valueOf(types, "s6"));
////		assertEquals(expected, e.execute(dbSets));
////	}
//
//	@Test
//	void testDifferenceBags() throws SchemaException, IOException {
//		// SportStudents <D> Students
//		RAExpr e = new Difference(new Base("SportStudents"), new Base("Students"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//
////	@Test
////	void testDifferenceSets() throws SchemaException {
////		// SportStudents <D> Students
////		RAExpr e = new Difference(new Base("SportStudents"), new Base("Students"));
////		Signature sig = dbSets.getSchema().getSignature("SportStudents");
////		Table expected = new Table(sig);
////		expected.add(Record.valueOf(sig.getTypes(), "Sarah", "15", "s4"));
////		expected.add(Record.valueOf(sig.getTypes(), "Sean", "14", "s3"));
////		assertEquals(expected, e.execute(dbSets));
////	}
//	
//	@Test
//	void testIntersectBags() throws SchemaException, IOException {
//		// Students <I> SportStudents
//		RAExpr e = new Intersect(new Base("Students"), new Base("SportStudents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
////	@Test
////	void testIntersectSets() throws SchemaException {
////		// DetentionStudents <I> SportStudents
////		RAExpr e = new Intersect(new Base("DetentionStudents"), new Base("SportStudents"));
////		Signature sig = dbSets.getSchema().getSignature("DetentionStudents");
////		Table expected = new Table(sig);
////		assertEquals(expected, e.execute(dbSets));
////	}
//	
//	@Test
//	void testProductBags() throws SchemaException, IOException {
//		// Parents <X> City
//		RAExpr e = new Product(new Base("Parents"), new Base("City"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
////	@Test
////	void testProductSets() throws SchemaException {
////		// Parents <X> City
////		RAExpr e = new Product(new Base("Parents"), new Base("City"));
////		Signature sig = Utils.concat(dbSets.getSchema().getSignature("Parents"), dbSets.getSchema().getSignature("City"));
////		Table expected = new Table(sig);
////		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Edinburgh"));
////		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Glasgow"));
////		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "London"));
////		assertEquals(expected, e.execute(dbSets));
////	}
//}
