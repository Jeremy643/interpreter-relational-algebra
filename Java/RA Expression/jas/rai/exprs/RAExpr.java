package jas.rai.exprs;

public abstract class RAExpr {
	
	public static final String ELIMINATE = "ELIMINATE";
	public static final String PROJECT = "PROJECT";
	public static final String RENAME = "RENAME";
	public static final String SELECT = "SELECT";
	
	enum Type {
		BASE, PROJECT, SELECT, PRODUCT, UNION, UNION_MAX, INTERSECT, DIFFERENCE, ELIMINATE, RENAME
	}

	private Type type;

	protected RAExpr(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
}
