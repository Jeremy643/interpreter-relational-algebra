package uk.ac.ed.inf.s1654170.mrai.exprs;

public class Union extends BinaryExpr {
	
	public Union(RAExpr left, RAExpr right) {
		super(left, right, Type.UNION);
	}

	@Override
	public String getConnective() {
		return Type.UNION.getConnective();
	}
}
