package uk.ac.ed.inf.s1654170.mrai.conditions;

public class Inequality extends Comparison {

	public Inequality(Term left, Term right) {
		super(Type.INEQUALITY, left, right);
	}
	
	@Override
	public String getComparisonOperator() {
		return Type.INEQUALITY.getConnective();
	}
}
