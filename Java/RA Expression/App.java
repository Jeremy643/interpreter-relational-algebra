import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Base r = new Base("R");
		Base s = new Base("S");
		Base t = new Base("T");
		RAExpr e1 = new Union(r,s);
		RAExpr e2 = new Intersect(e1,t);
		List<String> attr = new ArrayList<String>();
		attr.add("A");
		attr.add("B");
		System.out.println(new Project(e2, attr));
	}

}
