package uk.ac.ed.inf.s1654170.mrai.conditions;

public abstract class Comparison extends Condition {

	private Term left;
	private Term right;

	public Comparison(Type type, Term left, Term right) {
		super(type);
		this.left = left;
		this.right = right;
	}

	public abstract String getComparisonOperator();

	@Override
	public String toString() {
		return String.format("%s%s%s", left, getComparisonOperator(), right);
	}
}
