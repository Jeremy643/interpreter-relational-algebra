package uk.ac.ed.inf.s1654170.mrai.conditions;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class Comparison extends Condition {

	private Term left;
	private Term right;

	public Comparison(Type type, Term left, Term right) {
		super(type);
		this.left = left;
		this.right = right;
	}

	public abstract String getComparisonOperator();

	@Override
	public String toString() {
		return String.format("%s%s%s", left, getComparisonOperator(), right);
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
