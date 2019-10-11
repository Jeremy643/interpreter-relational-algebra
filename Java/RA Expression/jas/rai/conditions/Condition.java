package jas.rai.conditions;

public abstract class Condition {

	enum Type {TERM, EQUALITY, INEQUALITY, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL, AND, OR, NOT}
	
	private Type type;
	
	protected Condition(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
