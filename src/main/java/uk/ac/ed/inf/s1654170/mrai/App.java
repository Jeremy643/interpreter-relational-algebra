package uk.ac.ed.inf.s1654170.mrai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import uk.ac.ed.inf.s1654170.mrai.conditions.*;
import uk.ac.ed.inf.s1654170.mrai.exprs.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		
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
