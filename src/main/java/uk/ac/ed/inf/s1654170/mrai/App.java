package uk.ac.ed.inf.s1654170.mrai;

import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import uk.ac.ed.inf.s1654170.mrai.exprs.RAExpr;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.parser.BuildExpr;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraParser;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;

public class App {

	public static void main(String[] args) {

		Schema sch = new Schema("R:Name/STRING,Age/STRING;S:Name/STRING,Age/NUMBER;P:Name/STRING");
		Table tbl = new Table();
		List<Column.Type> types = sch.getSignature("R").getTypes();
		//Column.Type[] types = new Column.Type[] {Column.Type.STRING, Column.Type.NUMBER};
		Record r1 = Record.valueOf( types, "John", "20");
		Record r2 = Record.valueOf( types, "John", "20");
		tbl.add(r1);
		tbl.add(r2);
		System.out.println(r1.equals(r2));
		
		tbl.add(Record.valueOf( types, "Mary", "20"));
		tbl.add(Record.valueOf( types, "Mary", "20"));
		for (Record r : tbl) {
			System.out.println(r);
		}
		
		Database db = new Database(sch);
		try {
			db.add("R", tbl);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.exit(0);

		Scanner sc = new Scanner(System.in);
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

		System.out.print("Schema: ");
		//String schemaTest = "R:Name/STRING,Age/NUMBER;S:Name/STRING,Age/NUMBER;P:Name/STRING";
		Schema schema = new Schema(sc.nextLine());
		try {
			System.out.println(e.signature(schema));
		} catch (SchemaException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
		if (e.validate(schema)) {
			System.out.println("Valid!");
		} else {
			System.out.println("Not valid!");
		}

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
