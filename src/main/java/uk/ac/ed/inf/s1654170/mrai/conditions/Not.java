package uk.ac.ed.inf.s1654170.mrai.conditions;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Not extends Condition {
	
	public static final String NOT = "NOT";
	private Condition main;

	public Not(Condition main) {
		super(Type.NOT);
		this.main = main;
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
	
}
