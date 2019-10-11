package jas.rai.conditions;
public class Term extends Condition {

	private boolean constant;
	private String value;
	
	public Term(String value, boolean constant) {
		super(Type.TERM);
		this.value = value;
		this.constant = constant;
	}
	
	public boolean isConstant() {
		return constant;
	}
	
	@Override
	public String toString() {
		if (constant) {
			return String.format("'%s'", value);
		} else {
			return value;
		}
	}
	
}
