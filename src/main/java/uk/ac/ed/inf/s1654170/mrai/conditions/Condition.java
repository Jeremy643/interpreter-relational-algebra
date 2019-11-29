package uk.ac.ed.inf.s1654170.mrai.conditions;

import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class Condition {
	
	private static final String getName(int i) {
		return RelationalAlgebraLexer.VOCABULARY.getLiteralName(i).replace("'", "");
	}
	
	enum Type {
		EQUALITY		(getName(RelationalAlgebraLexer.EQUALITY)),
		INEQUALITY		(getName(RelationalAlgebraLexer.INEQUALITY)),
		LESS			(getName(RelationalAlgebraLexer.LESS)),
		LESS_EQUAL		(getName(RelationalAlgebraLexer.LESS_EQUAL)),
		GREATER			(getName(RelationalAlgebraLexer.GREATER)),
		GREATER_EQUAL	(getName(RelationalAlgebraLexer.GREATER_EQUAL)),
		AND				(getName(RelationalAlgebraLexer.AND)),
		OR				(getName(RelationalAlgebraLexer.OR)),
		NOT				(getName(RelationalAlgebraLexer.NOT));
		
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
