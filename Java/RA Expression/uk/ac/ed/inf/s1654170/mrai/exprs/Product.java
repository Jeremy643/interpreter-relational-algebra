package uk.ac.ed.inf.s1654170.mrai.exprs;

public class Product extends BinaryExpr {
	
	public Product(RAExpr left, RAExpr right) {
		super(left, right, Type.PRODUCT);
	}

	@Override
	public String getConnective() {
		return Type.PRODUCT.getConnective();
	}
}
