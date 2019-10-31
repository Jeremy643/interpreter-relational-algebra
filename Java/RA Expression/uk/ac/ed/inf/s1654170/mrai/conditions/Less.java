package uk.ac.ed.inf.s1654170.mrai.conditions;

public class Less extends Comparison {

	public Less(Term left, Term right) {
		super(Type.LESS, left, right);
	}
	
	@Override
	public String getComparisonOperator() {
		return Type.LESS.getConnective();
	}
	
}
