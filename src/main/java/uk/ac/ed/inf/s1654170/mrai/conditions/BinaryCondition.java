package uk.ac.ed.inf.s1654170.mrai.conditions;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class BinaryCondition extends Condition {

	private Condition left;
	private Condition right;
	private Type type;

	public BinaryCondition (Condition left, Condition right, Type type) {
		super(type);
		this.type = type;
		this.left = left;
		this.right = right;
	}

	public abstract String getConnective();

	@Override
	public String toString() {
		return String.format("(%s) %s (%s)", left.toString(), getConnective(), right.toString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof BinaryCondition) {
			if (this.left.equals(((BinaryCondition) o).left) && this.right.equals(((BinaryCondition) o).right) &&
					this.type.equals(((BinaryCondition) o).type)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean validate(Signature sig) {
		if (left.validate(sig) && right.validate(sig)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Comparison> getComparisons() {
		List<Comparison> comparisons = new ArrayList<>(left.getComparisons());
		comparisons.addAll(right.getComparisons());
		return comparisons;
	}
	
	@Override
	public List<Type> getConditionTypes() {
		List<Type> conditionTypes = new ArrayList<>();
		conditionTypes.addAll(left.getConditionTypes());
		conditionTypes.add(type);
		conditionTypes.addAll(right.getConditionTypes());
		return conditionTypes;
	}
}
