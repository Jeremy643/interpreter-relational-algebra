package jas.rai.conditions;

public class Equality extends Comparison {

	public Equality(Term left, Term right) {
		super(Type.EQUALITY, left, right);
	}

	@Override
	public String getComparisonOperator() {
		return Condition.EQUALITY;
	}
}
