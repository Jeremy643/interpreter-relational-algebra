package uk.ac.ed.inf.s1654170.mrai.conditions;

public class Equality extends Comparison {

	public Equality(Term left, Term right) {
		super(Type.EQUALITY, left, right);
	}

	@Override
	public String getComparisonOperator() {
		return Type.EQUALITY.getConnective();
	}
}
