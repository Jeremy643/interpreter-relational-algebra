package uk.ac.ed.inf.s1654170.mrai.conditions;
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
	
}
