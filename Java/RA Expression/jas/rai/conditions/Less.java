package jas.rai.conditions;

public class Less extends BinaryCondition {

	public Less(Condition left, Condition right) {
		super(left, right, Type.LESS);
	}
	
	@Override
	public String getConnective() {
		return LESS;
	}
	
}
