package jas.rai.exprs;

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
}
