package jas.rai.conditions;

public abstract class BinaryCondition extends Condition {

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
		return String.format("(%s) %s (%s)", left.toString(), getConnective(), right.toString());
	}
}
