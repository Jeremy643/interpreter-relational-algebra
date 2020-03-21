//package evaluation;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import uk.ac.ed.inf.s1654170.mrai.exprs.*;
//import uk.ac.ed.inf.s1654170.mrai.instance.*;
//import uk.ac.ed.inf.s1654170.mrai.schema.*;
//
//class OrderedTest {
//
//	private static Database dbBags;
//	private static Database dbSets;
//	private static boolean ordered = true;
//	private static boolean bags = true;
//
//	@BeforeAll
//	static void readData() throws IOException {
//		dbBags = GetDataHelper.readData(ordered, bags);
//		dbSets = GetDataHelper.readData(ordered, !bags);
//	}
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
//	void testUnionBags() throws SchemaException {
//		// Students <+> SportStudents
//		RAExpr e = new Union(new Base("Students"), new Base("SportStudents"));
//		Signature studentSig = dbBags.getSchema().getSignature("Students");
//		Table expected = new Table(studentSig);
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sean", "14", "s3"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//
//	@Test
//	void testUnionSets() throws SchemaException {
//		// Students <+> SportStudents
//		RAExpr e = new Union(new Base("Students"), new Base("SportStudents"));
//		Signature studentSig = dbSets.getSchema().getSignature("Students");
//		Table expected = new Table(studentSig);
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbSets));
//	}
//
//	@Test
//	void testUnionMaxBags() throws SchemaException {
//		// Students <U> SportStudents
//		RAExpr e = new UnionMax(new Base("Students"), new Base("SportStudents"));
//		Signature studentSig = dbBags.getSchema().getSignature("Students");
//		Table expected = new Table(studentSig);
//		expected.add(Record.valueOf(studentSig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Jane", "18", "s6"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(studentSig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//
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
//
//	@Test
//	void testProjectBags() throws SchemaException {
//		List<String> attr = new ArrayList<>();
//		attr.add("Name");
//		attr.add("Teach");
//		List<Column.Type> types = new ArrayList<>();
//		types.add(Column.Type.STRING);
//		types.add(Column.Type.STRING);
//		// <P>[Name,Teach](Teachers)
//		RAExpr e = new Project(new Base("Teachers"), attr);
//		Signature sig = new BaseSignature(attr, types, ordered);
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(types, "David", "s3"));
//		expected.add(Record.valueOf(types, "Tom", "s1"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//
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
//
//	@Test
//	void testDifferenceBags() throws SchemaException {
//		// SportStudents <D> Students
//		RAExpr e = new Difference(new Base("SportStudents"), new Base("Students"));
//		Signature sig = dbBags.getSchema().getSignature("SportStudents");
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(sig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(sig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//
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
//	
//	@Test
//	void testEliminateBags() throws SchemaException {
//		// <E>(SportStudents)
//		RAExpr e = new Eliminate(new Base("SportStudents"));
//		Signature sig = dbBags.getSchema().getSignature("SportStudents");
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Hamish", "18", "s6"));
//		expected.add(Record.valueOf(sig.getTypes(), "Sarah", "15", "s4"));
//		expected.add(Record.valueOf(sig.getTypes(), "Sean", "14", "s3"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
//	@Test
//	void testEliminateSets() throws SchemaException {
//		// <E>(Parents)
//		RAExpr e = new Eliminate(new Base("Parents"));
//		Signature sig = dbSets.getSchema().getSignature("Parents");
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2"));
//		assertEquals(expected, e.execute(dbSets));
//	}
//	
//	@Test
//	void testIntersectBags() throws SchemaException {
//		// Students <I> SportStudents
//		RAExpr e = new Intersect(new Base("Students"), new Base("SportStudents"));
//		Signature sig = dbBags.getSchema().getSignature("Students");
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Hamish", "18", "s6"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
//	@Test
//	void testIntersectSets() throws SchemaException {
//		// DetentionStudents <I> SportStudents
//		RAExpr e = new Intersect(new Base("DetentionStudents"), new Base("SportStudents"));
//		Signature sig = dbSets.getSchema().getSignature("DetentionStudents");
//		Table expected = new Table(sig);
//		assertEquals(expected, e.execute(dbSets));
//	}
//	
//	@Test
//	void testProductBags() throws SchemaException {
//		// Parents <X> City
//		RAExpr e = new Product(new Base("Parents"), new Base("City"));
//		Signature sig = Utils.concat(dbBags.getSchema().getSignature("Parents"), dbBags.getSchema().getSignature("City"));
//		Table expected = new Table(sig);
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Edinburgh"));
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "London"));
//		expected.add(Record.valueOf(sig.getTypes(), "Hugo", "3/09/88", "2", "Glasgow"));
//		assertEquals(expected, e.execute(dbBags));
//	}
//	
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
//	}
//}
