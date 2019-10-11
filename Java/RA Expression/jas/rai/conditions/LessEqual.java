package jas.rai.conditions;

public class LessEqual extends BinaryCondition {

	public LessEqual(Condition left, Condition right) {
		super(left, right, Type.LESS_EQUAL);
	}
	
	@Override
	public String getConnective() {
		return LESS_EQUAL;
	}
	
}
