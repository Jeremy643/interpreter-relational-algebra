package uk.ac.ed.inf.s1654170.mrai.conditions;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class And extends BinaryCondition {
	
	private Condition left;
	private Condition right;
	
	public And(Condition left, Condition right) {
		super(left, right, Type.AND);
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String getConnective() {
		return Type.AND.getConnective();
	}

	@Override
	public boolean validate(Signature sig) {
		if (left.validate(sig) && right.validate(sig)) {
			return true;
		} else {
			return false;
		}
	}
	
}
