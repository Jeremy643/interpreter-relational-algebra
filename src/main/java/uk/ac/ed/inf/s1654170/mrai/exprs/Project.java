package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.List;

public class Project extends RAExpr {
	
	private List<String> attributes;
	private RAExpr expr;
	
	public Project(RAExpr expr, List<String> attr) {
		super(Type.PROJECT);
		this.attributes = new ArrayList<String>(attr);
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		String attrList = String.join(",", attributes);
		return String.format("%s[%s](%s)", Type.PROJECT.getConnective(), attrList, expr.toString());
	}
}
