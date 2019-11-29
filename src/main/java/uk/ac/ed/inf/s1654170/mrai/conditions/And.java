package uk.ac.ed.inf.s1654170.mrai.conditions;

public class And extends BinaryCondition {
	
	public And(Condition left, Condition right) {
		super(left, right, Type.AND);
	}
	
	@Override
	public String getConnective() {
		return Type.AND.getConnective();
	}
}
