package jas.rai.conditions;

public class Greater extends Comparison {

	public Greater(Term left, Term right) {
		super(Type.GREATER, left, right);
	}

	@Override
	public String getComparisonOperator() {
		return Type.GREATER.getConnective();
	}
}
