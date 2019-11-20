package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

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

	@Override
	public Signature signature(Schema s) {
		return expr.signature(s);
	}

	@Override
	public boolean validate(Schema schema) {
		Signature sig = expr.signature(schema);
		
		if (sig == null || !condition.validate(sig)) {
			return false;
		} else {
			return true;
		}
	}
}
