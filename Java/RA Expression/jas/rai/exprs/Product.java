package jas.rai.exprs;

public class Product extends BinaryExpr {
	
	public Product(RAExpr left, RAExpr right) {
		super(left, right, Type.PRODUCT);
	}

	@Override
	public String getConnective() {
		return PRODUCT;
	}
}
