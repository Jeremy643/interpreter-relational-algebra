package uk.ac.ed.inf.s1654170.mrai.exprs;

public class Intersect extends BinaryExpr {

	public Intersect(RAExpr left, RAExpr right) {
		super(left, right, Type.INTERSECT);
	}

	@Override
	public String getConnective() {
		return Type.INTERSECT.getConnective();
	}
}
