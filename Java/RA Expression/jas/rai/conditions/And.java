package jas.rai.conditions;
public class And extends BinaryCondition {
	
	public And(Condition left, Condition right) {
		super(left, right, Type.AND);
	}
	
	@Override
	public String getConnective() {
		return AND;
	}
	
}
