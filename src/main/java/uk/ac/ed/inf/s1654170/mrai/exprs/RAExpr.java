package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.schema.*;

import org.antlr.v4.runtime.Vocabulary;

import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;

public abstract class RAExpr {

	enum Type {
		BASE(""),
		PROJECT(VOCAB.getLiteralName(RelationalAlgebraLexer.PROJECTION).replace("'", "")),
		SELECT(VOCAB.getLiteralName(RelationalAlgebraLexer.SELECT).replace("'", "")),
		PRODUCT(VOCAB.getLiteralName(RelationalAlgebraLexer.PRODUCT).replace("'", "")),
		UNION(VOCAB.getLiteralName(RelationalAlgebraLexer.UNION).replace("'", "")),
		UNION_MAX(VOCAB.getLiteralName(RelationalAlgebraLexer.UNION_MAX).replace("'", "")),
		INTERSECT(VOCAB.getLiteralName(RelationalAlgebraLexer.INTERSECTION).replace("'", "")),
		DIFFERENCE(VOCAB.getLiteralName(RelationalAlgebraLexer.DIFFERENCE).replace("'", "")),
		ELIMINATE(VOCAB.getLiteralName(RelationalAlgebraLexer.ELIMINATE).replace("'", "")),
		RENAME(VOCAB.getLiteralName(RelationalAlgebraLexer.RENAMING).replace("'", ""));

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

	protected RAExpr(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	public abstract Signature signature(Schema s) throws SchemaException;
	
	public boolean validate(Schema schema) {
		try {
			Signature sign = signature(schema);
			if (sign != null) {
				return true;
			} else {
				return false;
			}
		} catch (SchemaException e) {
			return false;
		}
	}
}
