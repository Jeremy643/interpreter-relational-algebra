import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jas.rai.exprs.*;
import jas.rai.conditions.*;

public class App {

	public static void main(String[] args) {
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
	}

}
