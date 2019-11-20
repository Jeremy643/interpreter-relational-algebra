package uk.ac.ed.inf.s1654170.mrai.conditions;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class Condition {
	
	enum Type {
		EQUALITY("="),
		INEQUALITY("!="),
		LESS("<"),
		LESS_EQUAL("<="),
		GREATER(">"),
		GREATER_EQUAL(">="),
		AND("AND"),
		OR("OR"),
		NOT("NOT");
		
		private final String connective;

		Type(String connective) {
			this.connective = connective;
		}

		public String getConnective() {
			return connective;
		}
	}
	
	private Type type;
	
	protected Condition(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public abstract boolean validate(Signature sig);
}
