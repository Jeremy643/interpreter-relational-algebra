package uk.ac.ed.inf.s1654170.mrai.conditions;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Not extends Condition {
	
	public static final String NOT = "NOT";
	private Condition main;

	public Not(Condition main) {
		super(Type.NOT);
		this.main = main;
	}
	
	public List<Condition> getCondition() {
		List<Condition> ans = new ArrayList<>();
		ans.add(main);
		return ans;
	}
	
	@Override
	public String toString() {
		return String.format("[%s %s]", NOT, main);
	}

	@Override
	public boolean validate(Signature sig) {
		if (main.validate(sig)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Comparison> getComparisons() {
		List<Comparison> comparisons = new ArrayList<>(main.getComparisons());
		return comparisons;
	}

	@Override
	public List<Type> getConditionTypes() {
		List<Type> conditionTypes = new ArrayList<>();
		conditionTypes.add(Type.NOT);
		conditionTypes.addAll(main.getConditionTypes());
		return conditionTypes;
	}
}
