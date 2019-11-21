package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.schema.*;

public abstract class RAExpr {

	enum Type {
		BASE(""),
		PROJECT("PROJ"),
		SELECT("SEL"),
		PRODUCT("PROD"),
		UNION("UPLUS"),
		UNION_MAX("UMAX"),
		INTERSECT("INTERSECT"),
		DIFFERENCE("DIFF"),
		ELIMINATE("ELIM"),
		RENAME("REN");

		private final String connective;

		Type(String connective) {
			this.connective = connective;
		}

		public String getConnective() {
			return connective;
		}
	}

	private Type type;

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
