package uk.ac.ed.inf.s1654170.mrai.evaluation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ed.inf.s1654170.mrai.exprs.*;
import uk.ac.ed.inf.s1654170.mrai.instance.*;
import uk.ac.ed.inf.s1654170.mrai.schema.*;

class OrderedTests {
	
	private static Database dbBags;
	private static Database dbSets;
	private static boolean ordered = true;
	private static boolean bags = true;
	
	@BeforeAll
	static void readData() throws IOException {
		dbBags = GetDataHelper.readData(ordered, bags);
		dbSets = GetDataHelper.readData(ordered, !bags);
	}

	@Test
	void testBaseBags() throws SchemaException {
		RAExpr e = new Base("Students");
		Signature studentSig = dbBags.getSchema().getSignature("Students");
		Table expected = new Table(studentSig, bags);
		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
		assertEquals(expected, e.execute(dbBags));
	}
	
	@Test
	void testBaseSets() throws SchemaException {
		RAExpr e = new Base("SportStudents");
		Signature sportStudentSig = dbSets.getSchema().getSignature("SportStudents");
		Table expected = new Table(sportStudentSig, !bags);
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Hamish", "18", "s6"));
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sarah", "15", "s4"));
		expected.add(Record.valueOf(sportStudentSig.getTypes(), "Sean", "14", "s3"));
		assertEquals(expected, e.execute(dbSets));
	}
}
