package jas.rai.conditions;

public class GreaterEqual extends Comparison {

	public GreaterEqual(Term left, Term right) {
		super(Type.GREATER_EQUAL, left, right);
	}
	
	@Override
	public String getComparisonOperator() {
		return Type.GREATER_EQUAL.getConnective();
	}
	
}
