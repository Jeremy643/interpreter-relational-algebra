package uk.ac.ed.inf.s1654170.mrai.conditions;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class GreaterEqual extends Comparison {
	
	private Term left;
	private Term right;

	public GreaterEqual(Term left, Term right) {
		super(Type.GREATER_EQUAL, left, right);
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String getComparisonOperator() {
		return Type.GREATER_EQUAL.getConnective();
	}

	@Override
	public boolean validate(Signature sig) {
		List<String> attr = new ArrayList<>(sig.getAttributes());

		if (left.isConstant() && right.isConstant()) {
			return false;
		}

		if ((!left.isConstant() && !attr.contains(left.toString()))
				|| (!right.isConstant() && !attr.contains(right.toString()))) {
			return false;
		} else {
			return true;
		}
	}
	
}
