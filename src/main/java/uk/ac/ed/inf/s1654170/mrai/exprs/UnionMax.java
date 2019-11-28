package uk.ac.ed.inf.s1654170.mrai.exprs;

public class UnionMax extends BinaryExpr {
	
	public UnionMax(RAExpr left, RAExpr right) {		
		super(left, right, Type.UNION_MAX);
	}

	@Override
	public String getConnective() {
		return Type.UNION_MAX.getConnective();
	}
}
