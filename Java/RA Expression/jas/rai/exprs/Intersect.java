package jas.rai.exprs;

public class Intersect extends BinaryExpr {
	
	public Intersect(RAExpr left, RAExpr right) {
		super(left, right, Type.INTERSECT);
	}

	@Override
	public String getConnective() {
		return INTERSECT;
	}
}
