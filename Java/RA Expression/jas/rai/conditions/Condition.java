package jas.rai.conditions;

public abstract class Condition {
	
	public static final String EQUALITY = "=";
	public static final String INEQUALITY = "!=";
	public static final String LESS = "<";
	public static final String LESS_EQUAL = "<=";
	public static final String GREATER = ">";
	public static final String GREATER_EQUAL = ">=";

	enum Type {EQUALITY, INEQUALITY, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL, AND, OR, NOT}
	
	private Type type;
	
	protected Condition(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
