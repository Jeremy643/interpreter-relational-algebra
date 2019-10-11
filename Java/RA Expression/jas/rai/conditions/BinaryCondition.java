package jas.rai.conditions;

public abstract class BinaryCondition extends Condition {
	
	public static final String EQUALITY = "=";
	public static final String INEQUALITY = "!=";
	public static final String LESS = "<";
	public static final String LESS_EQUAL = "<=";
	public static final String GREATER = ">";
	public static final String GREATER_EQUAL = ">=";
	public static final String AND = "AND";
	public static final String OR = "OR";

	private Condition left;
	private Condition right;

	public BinaryCondition (Condition left, Condition right, Type type) {
		super(type);
		this.left = left;
		this.right = right;
	}

	public abstract String getConnective();

	@Override
	public String toString() {
		return String.format("[%s %s %s]", left.toString(), getConnective(), right.toString());
	}
}
