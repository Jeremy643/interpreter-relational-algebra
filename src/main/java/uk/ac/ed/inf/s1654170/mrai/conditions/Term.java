package uk.ac.ed.inf.s1654170.mrai.conditions;

public class Term {

	private boolean constant;
	private String value;
	
	public Term(String value, boolean constant) {
		this.value = value;
		this.constant = constant;
	}
	
	public boolean isConstant() {
		return constant;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		if (constant) {
			return String.format("'%s'", value);
		} else {
			return value;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Term) {
			if (this.constant == ((Term) o).constant && this.value.equals(((Term) o).value)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
