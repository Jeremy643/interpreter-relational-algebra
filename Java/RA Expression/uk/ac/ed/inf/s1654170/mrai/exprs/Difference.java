package uk.ac.ed.inf.s1654170.mrai.exprs;

public class Difference extends BinaryExpr {
	
	public Difference(RAExpr left, RAExpr right) {
		super(left, right, Type.DIFFERENCE);
	}

	@Override
	public String getConnective() {
		return Type.DIFFERENCE.getConnective();
	}
}
