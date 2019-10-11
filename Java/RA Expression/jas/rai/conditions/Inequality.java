package jas.rai.conditions;

public class Inequality extends BinaryCondition {

	public Inequality(Condition left, Condition right) {
		super(left, right, Type.INEQUALITY);
	}
	
	@Override
	public String getConnective() {
		return INEQUALITY;
	}
	
}
