package jas.rai.conditions;

public class Greater extends BinaryCondition {

	public Greater(Condition left, Condition right) {
		super(left, right, Type.GREATER);
	}
	
	@Override
	public String getConnective() {
		return GREATER;
	}
	
}
