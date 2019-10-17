package jas.rai.exprs;

public abstract class BinaryExpr extends RAExpr {
	
	private RAExpr left;
	private RAExpr right;

	public BinaryExpr(RAExpr left, RAExpr right, Type type) {
		super(type);
		this.left = left;
		this.right = right;
	}
	
	public abstract String getConnective();
	
	@Override
	public String toString() {
		return String.format("(%s %s %s)", left.toString(), getConnective(), right.toString());
	}
}
