package jas.rai.conditions;

public class Term  {

	private boolean constant;
	private String value;
	
	public Term(String value, boolean constant) {
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
