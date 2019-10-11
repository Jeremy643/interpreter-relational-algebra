package jas.rai.exprs;

public class Union extends BinaryExpr {
	
	public Union(RAExpr left, RAExpr right) {
		super(left, right, Type.UNION);
	}

	@Override
	public String getConnective() {
		return UNION;
	}
}
