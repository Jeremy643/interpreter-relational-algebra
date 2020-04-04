package uk.ac.ed.inf.s1654170.mrai.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
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
	private static final String DATA_PATH = PATH + "data/";
	private static final String RESULT_PATH = PATH + "result/";
	
	private static Map<String, String> parsingMap = new HashMap<>();
	private static Map<RAExpr, Boolean> validationMap = new HashMap<>();
	private static Map<RAExpr, String> validationSchemaExceptionMap = new HashMap<>();
	private static Map<RAExpr, Table> operationMap = new HashMap<>();
	
	private static Map<String, String> getParsingMap(String propName) throws FileNotFoundException {
		InputStream configStream = new FileInputStream(RESULT_PATH + propName);
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
		InputStream configStream = new FileInputStream(RESULT_PATH + propName);
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
	
	private static Map<RAExpr, String> getValidationSchemaExceptionMap(String propName) throws FileNotFoundException {
		InputStream configStream = new FileInputStream(RESULT_PATH + propName);
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
			validationSchemaExceptionMap.put(e, prop.get(o).toString());
		}
		return validationSchemaExceptionMap;
	}
	
	private static Map<RAExpr, Table> getOperationMap(String propName, boolean ordered, boolean bags) throws SchemaException, IOException {
		operationMap.clear();
		InputStream configStream = new FileInputStream(RESULT_PATH + propName);
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
			Table t = Table.fromCSV(new File(RESULT_PATH + prop.getProperty(k)), ordered, bags);
			operationMap.put(e, t);
		}
		return operationMap;
	}

	@BeforeAll
	static void readData() throws IOException, SchemaException {
		File folder = new File(DATA_PATH);
		dbOrderedBags = Database.fromCSV(folder, ordered, bags);
		dbOrderedSets = Database.fromCSV(folder, ordered, !bags);
		dbUnorderedBags = Database.fromCSV(folder, !ordered, bags);
		dbUnorderedSets = Database.fromCSV(folder, !ordered, !bags);
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
	
	@Test()
	@Order(3)
	void testValidationSchemaException() throws SchemaException, IOException {
		System.out.println("\n==================== Test 3 - testValidationSchemaException() ====================");
		System.out.println("Testing that validation throws a schema exception for bad input.\n");
		String propName = "expectedValidationSchemaException.properties";
		validationSchemaExceptionMap = getValidationSchemaExceptionMap(propName);
		boolean allPassed = true;
		for (RAExpr e : validationSchemaExceptionMap.keySet()) {
			String expected = validationSchemaExceptionMap.get(e);
			SchemaException exception = assertThrows(SchemaException.class, () -> e.validate(dbOrderedBags.getSchema()));
			if (expected.equals(exception.getMessage())) {
				System.out.println(String.format("PASSED! - %s \"%s\"", e.toString(), exception.getMessage()));
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: \"%s\" Actual: \"%s\"", e.toString(), expected, exception.getMessage()));
			}
		}
		assertTrue(allPassed);
	}
	
	@Test
	@Order(4)
	void testOrdBagsTableOp() throws SchemaException, IOException {
		System.out.println("\n==================== Test 4 - testOrdBagsTableOp() ====================");
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
	
	@Test
	@Order(5)
	void testUnordBagsTableOp() throws SchemaException, IOException {
		System.out.println("\n==================== Test 5 - testUnordBagsTableOp() ====================");
		System.out.println("Testing the execution of table operations on unordered columns under bags.\n");
		String propName = "expectedUnordBagsTableOp.properties";
		operationMap = getOperationMap(propName, !ordered, bags);
		boolean allPassed = true;
		for (RAExpr e : operationMap.keySet()) {
			Table t = e.execute(dbUnorderedBags);
			if (operationMap.get(e).equals(t)) {
				System.out.println("PASSED! - " + e.toString());
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: %s Actual: %s", e.toString(), operationMap.get(e), t));
			}
		}
		assertTrue(allPassed);
	}
	
	@Test
	@Order(6)
	void testOrdSetsTableOp() throws SchemaException, IOException {
		System.out.println("\n==================== Test 6 - testOrdSetsTableOp() ====================");
		System.out.println("Testing the execution of table operations on ordered columns under sets.\n");
		String propName = "expectedOrdSetsTableOp.properties";
		operationMap = getOperationMap(propName, ordered, !bags);
		boolean allPassed = true;
		for (RAExpr e : operationMap.keySet()) {
			Table t = e.execute(dbOrderedSets);
			if (e.toString().equals("<P>[Name,Teach](Teachers)")) {
				Set<Record> setT = new HashSet<>(t);
				t.clear();
				t.addAll(setT);
			}
			if (operationMap.get(e).equals(t)) {
				System.out.println("PASSED! - " + e.toString());
			} else {
				allPassed = false;
				System.out.println(String.format("FAILED! - %s | Expected: %s Actual: %s", e.toString(), operationMap.get(e), t));
			}
		}
		assertTrue(allPassed);
	}
}
