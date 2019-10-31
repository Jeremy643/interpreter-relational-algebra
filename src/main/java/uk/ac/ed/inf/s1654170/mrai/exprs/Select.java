package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;

public class Select extends RAExpr {
	
	private Condition condition;
	private RAExpr expr;
	
	public Select(Condition condition, RAExpr expr) {
		super(Type.SELECT);
		this.condition = condition;
		this.expr = expr;
	}

	public Condition getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s](%s)", Type.SELECT.getConnective(), condition, expr);
	}
}
