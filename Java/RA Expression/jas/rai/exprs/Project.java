package jas.rai.exprs;

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
		return String.format("%s%s(%s)", Type.PROJECT.getConnective(), attributes.toString(), expr.toString());
	}
}
