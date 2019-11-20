package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Eliminate extends RAExpr {
	
	private RAExpr expr;
	
	public Eliminate(RAExpr expr) {
		super(Type.ELIMINATE);
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", Type.ELIMINATE.getConnective(), expr);
	}

	@Override
	public Signature signature(Schema s) {
		return expr.signature(s);
	}

	@Override
	public boolean validate(Schema schema) {
		Signature sig = expr.signature(schema);
		
		if (sig == null) {
			return false;
		} else {
			return true;
		}
	}
}
