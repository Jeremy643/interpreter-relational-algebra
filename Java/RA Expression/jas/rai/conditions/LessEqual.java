package jas.rai.conditions;

public class LessEqual extends Comparison {

	public LessEqual(Term left, Term right) {
		super(Type.LESS_EQUAL, left, right);
	}
	
	@Override
	public String getComparisonOperator() {
		return Type.LESS_EQUAL.getConnective();
	}
	
}
