package uk.ac.ed.inf.s1654170.mrai.conditions;

import org.antlr.v4.runtime.Vocabulary;

import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class Condition {
	
	enum Type {
		EQUALITY(VOCAB.getLiteralName(RelationalAlgebraLexer.EQUALITY).replace("'", "")),
		INEQUALITY(VOCAB.getLiteralName(RelationalAlgebraLexer.INEQUALITY).replace("'", "")),
		LESS(VOCAB.getLiteralName(RelationalAlgebraLexer.LESS).replace("'", "")),
		LESS_EQUAL(VOCAB.getLiteralName(RelationalAlgebraLexer.LESS_EQUAL).replace("'", "")),
		GREATER(VOCAB.getLiteralName(RelationalAlgebraLexer.GREATER).replace("'", "")),
		GREATER_EQUAL(VOCAB.getLiteralName(RelationalAlgebraLexer.GREATER_EQUAL).replace("'", "")),
		AND(VOCAB.getLiteralName(RelationalAlgebraLexer.AND).replace("'", "")),
		OR(VOCAB.getLiteralName(RelationalAlgebraLexer.OR).replace("'", "")),
		NOT(VOCAB.getLiteralName(RelationalAlgebraLexer.NOT).replace("'", ""));
		
		private final String connective;

		Type(String connective) {
			this.connective = connective;
		}

		public String getConnective() {
			return connective;
		}
	}
	
	private Type type;
	private static final Vocabulary VOCAB = RelationalAlgebraLexer.VOCABULARY;
	
	protected Condition(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public abstract boolean validate(Signature sig);
}
