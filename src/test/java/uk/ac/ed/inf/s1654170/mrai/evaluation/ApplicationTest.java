package uk.ac.ed.inf.s1654170.mrai.evaluation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import uk.ac.ed.inf.s1654170.mrai.conditions.And;
import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;
import uk.ac.ed.inf.s1654170.mrai.conditions.Equality;
import uk.ac.ed.inf.s1654170.mrai.conditions.Greater;
import uk.ac.ed.inf.s1654170.mrai.conditions.Inequality;
import uk.ac.ed.inf.s1654170.mrai.conditions.Not;
import uk.ac.ed.inf.s1654170.mrai.conditions.Or;
import uk.ac.ed.inf.s1654170.mrai.conditions.Term;
import uk.ac.ed.inf.s1654170.mrai.exprs.Base;
import uk.ac.ed.inf.s1654170.mrai.exprs.Difference;
import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.exprs.Select;
import uk.ac.ed.inf.s1654170.mrai.exprs.Union;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTest {

	private static Database dbOrderedBags;
	private static Database dbOrderedSets;
	private static Database dbUnorderedBags;
	private static Database dbUnorderedSets;
	private static boolean ordered = true;
	private static boolean bags = true;
	
	private static final String PATH = System.getProperty("user.dir") + "/src/test/java/uk/ac/ed/inf/s1654170/mrai/evaluation/";
	private static final String ORDERED_PATH = PATH + "ordered/";
	private static final String UNORDERED_PATH = PATH + "unordered/";
	
	private static Map<RAExpr, Table> operationMap = new HashMap<>();
	private static Map<String, String> parsingMap = new HashMap<>();
	private static Map<RAExpr, Boolean> validationMap = new HashMap<>();
	
	private static Map<RAExpr, Table> getOperationMap(String propName, boolean ordered, boolean bags) throws SchemaException, IOException {
		operationMap.clear();
		String path = "";
		if (ordered) {
			path = ORDERED_PATH;
		} else {
			path = UNORDERED_PATH;
		}
		InputStream configStream = new FileInputStream(path + propName);
		Properties prop = new Properties();
		try {
			prop.load(configStream);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			System.exit(1);
		}
		for (Object o : prop.keySet()) {
			String k = (String) o;
			RAExpr e = RAExpr.parse(k);
			Table t = Table.fromCSV(new File(path + "/" + prop.getProperty(k)), ordered, bags);
			operationMap.put(e, t);
		}
		return operationMap;
	}
	
	private static Map<String, String> getParsingMap(String propName) throws FileNotFoundException {
		InputStream configStream = new FileInputStream(PATH + propName);
		Properties prop = new Properties();
		try {
			prop.load(configStream);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			System.exit(1);
		}
		for (Object o : prop.keySet()) {
			String k = (String) o;
			parsingMap.put(k, prop.get(o).toString());
		}
		return parsingMap;
	}
	
	private static Map<RAExpr, Boolean> getValidationMap(String propName) throws FileNotFoundException {
		InputStream configStream = new FileInputStream(ORDERED_PATH + propName);
		Properties prop = new Properties();
		try {
			prop.load(configStream);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			System.exit(1);
		}
		for (Object o : prop.keySet()) {
			String k = (String) o;
			RAExpr e = RAExpr.parse(k);
			validationMap.put(e, Boolean.valueOf(prop.get(o).toString()));
		}
		return validationMap;
	}

	@BeforeAll
	static void readData() throws IOException, SchemaException {
		File orderedFolder = new File(ORDERED_PATH);
		File unorderedFolder = new File(UNORDERED_PATH);
		dbOrderedBags = Database.fromCSV(orderedFolder, ordered, bags);
		dbOrderedSets = Database.fromCSV(orderedFolder, ordered, !bags);
		dbUnorderedBags = Database.fromCSV(unorderedFolder, !ordered, bags);
		dbUnorderedSets = Database.fromCSV(unorderedFolder, !ordered, !bags);
	}
	
	@Test
	@Order(1)
	void testParsing() throws SchemaException, IOException {
		System.out.println("\n==================== Test 1 - testParsing() ====================");
		System.out.println("Testing the parsing of input.\n");
		String propName = "expectedParsing.properties";
		parsingMap = getParsingMap(propName);
		boolean allPassed = true;
		for (String s : parsingMap.keySet()) {
			RAExpr e = RAExpr.parse(s);
			if (parsingMap.get(s).equals(e.toString())) {
				System.out.println("PASSED! - " + s);
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: %s Actual: %s", s, parsingMap.get(s), e));
			}
		}
		assertTrue(allPassed);
	}
	
	@Test
	@Order(2)
	void testValidation() throws SchemaException, IOException {
		System.out.println("\n==================== Test 2 - testValidation() ====================");
		System.out.println("Testing validation for ordered columns under bags.\n");
		String propName = "expectedValidationBags.properties";
		validationMap = getValidationMap(propName);
		boolean allPassed = true;
		for (RAExpr e : validationMap.keySet()) {
			boolean expected = validationMap.get(e);
			boolean actual = e.validate(dbOrderedBags.getSchema());
			if (expected == actual) {
				System.out.println("PASSED! - " + e.toString());
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: %s Actual: %s", e.toString(), expected, actual));
			}
		}
		assertTrue(allPassed);
	}
	
	@Test
	@Order(3)
	void testOrdBagsTableOp() throws SchemaException, IOException {
		System.out.println("\n==================== Test 3 - testOrdBagsTableOp() ====================");
		System.out.println("Testing the execution of table operations on ordered columns under bags.\n");
		String propName = "expectedOrdBagsTableOp.properties";
		operationMap = getOperationMap(propName, ordered, bags);
		boolean allPassed = true;
		for (RAExpr e : operationMap.keySet()) {
			Table t = e.execute(dbOrderedBags);
			if (operationMap.get(e).equals(t)) {
				System.out.println("PASSED! - " + e.toString());
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: %s Actual: %s", e.toString(), operationMap.get(e), t));
			}
		}
		assertTrue(allPassed);
	}

	/*@Test
	void testBaseBags() throws SchemaException {
		RAExpr e = new Base("Students");
		Signature studentSig = dbBags.getSchema().getSignature("Students");
		Table expected = new Table(studentSig);
		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
		assertEquals(expected, e.execute(dbBags));
	}

	@Test
	void testBaseSets() throws SchemaException {
		RAExpr e = new Base("SportStudents");
		Signature sportStudentSig = dbSets.getSchema().getSignature("SportStudents");
		Table expected = new Table(sportStudentSig);
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Hamish", "18", "s6"));
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sarah", "15", "s4"));
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sean", "14", "s3"));
		assertEquals(expected, e.execute(dbSets));
	}

	@Test
	void testEliminateBags() throws SchemaException, IOException {
		// <E>(SportStudents)
		RAExpr e = new Eliminate(new Base("SportStudents"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}
	
	@Test
	void testEliminateSets() throws SchemaException, IOException {
		// <E>(Parents)
		RAExpr e = new Eliminate(new Base("Parents"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbSets));
	}

	@Test
	void testUnionBags() throws SchemaException, IOException {
		// Students <+> SportStudents
		RAExpr e = new Union(new Base("Students"), new Base("SportStudents"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}

//	@Test
//	void testUnionSets() throws SchemaException, IOException {
//		// Students <+> DetentionStudents
//		RAExpr e = new Union(new Base("Students"), new Base("DetentionStudents"));
//		Table expected = getExpectedTable(e.toString());
//		assertEquals(expected, e.execute(dbSets));
//	}

	@Test
	void testUnionMaxBags() throws SchemaException, IOException {
		// Students <U> SportStudents
		RAExpr e = new UnionMax(new Base("Students"), new Base("SportStudents"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}

//	@Test
//	void testUnionMaxSets() throws SchemaException {
//		// Students <U> SportStudents
//		RAExpr e = new UnionMax(new Base("Students"), new Base("SportStudents"));
//		Signature studentSig = dbSets.getSchema().getSignature("Students");
//		Table expected = new Table(studentSig);
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbSets));
//	}

	@Test
	void testProjectBags() throws SchemaException, IOException {
		List<String> attr = new ArrayList<>();
		attr.add("Name");
		attr.add("Teach");
		// <P>[Name,Teach](Teachers)
		RAExpr e = new Project(new Base("Teachers"), attr);
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}

//	@Test
//	void testProjectSets() throws SchemaException {
//		List<String> attr = new ArrayList<>();
//		attr.add("Class");
//		List<Column.Type> types = new ArrayList<>();
//		types.add(Column.Type.STRING);
//		// <P>[Class](Students)
//		RAExpr e = new Project(new Base("Students"), attr);
//		Signature sig = new BaseSignature(attr, types, ordered);
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(types, "s6"));
//		assertEquals(expected, e.execute(dbSets));
//	}

	@Test
	void testDifferenceBags() throws SchemaException, IOException {
		// SportStudents <D> Students
		RAExpr e = new Difference(new Base("SportStudents"), new Base("Students"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}

//	@Test
//	void testDifferenceSets() throws SchemaException {
//		// SportStudents <D> Students
//		RAExpr e = new Difference(new Base("SportStudents"), new Base("Students"));
//		Signature sig = dbSets.getSchema().getSignature("SportStudents");
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(sig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbSets));
//	}
	
	@Test
	void testIntersectBags() throws SchemaException, IOException {
		// Students <I> SportStudents
		RAExpr e = new Intersect(new Base("Students"), new Base("SportStudents"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}
	
//	@Test
//	void testIntersectSets() throws SchemaException {
//		// DetentionStudents <I> SportStudents
//		RAExpr e = new Intersect(new Base("DetentionStudents"), new Base("SportStudents"));
//		Signature sig = dbSets.getSchema().getSignature("DetentionStudents");
//		Table expected = new Table(sig);
//		assertEquals(expected, e.execute(dbSets));
//	}
	
	@Test
	void testProductBags() throws SchemaException, IOException {
		// Parents <X> City
		RAExpr e = new Product(new Base("Parents"), new Base("City"));
		Table expected = getExpectedTable(e.toString());
		assertEquals(expected, e.execute(dbBags));
	}
	
//	@Test
//	void testProductSets() throws SchemaException {
//		// Parents <X> City
//		RAExpr e = new Product(new Base("Parents"), new Base("City"));
//		Signature sig = Utils.concat(dbSets.getSchema().getSignature("Parents"), dbSets.getSchema().getSignature("City"));
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Edinburgh"));
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Glasgow"));
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "London"));
//		assertEquals(expected, e.execute(dbSets));
//	}*/
}
