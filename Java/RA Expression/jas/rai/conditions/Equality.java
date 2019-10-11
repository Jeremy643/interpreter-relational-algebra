package jas.rai.conditions;
public class Equality extends BinaryCondition {
	
	public Equality(Condition left, Condition right) {
		super(left, right, Type.EQUALITY);
	}
	
	@Override
	public String getConnective() {
		return EQUALITY;
	}
}
