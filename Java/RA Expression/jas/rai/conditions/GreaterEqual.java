package jas.rai.conditions;

public class GreaterEqual extends BinaryCondition {

	public GreaterEqual(Condition left, Condition right) {
		super(left, right, Type.GREATER_EQUAL);
	}
	
	@Override
	public String getConnective() {
		return GREATER_EQUAL;
	}
	
}
