package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.Schema;

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
	
	public boolean validate(Schema schema) {
		return true;
	}
}
