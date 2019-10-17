package jas.rai.exprs;

public abstract class RAExpr {

	enum Type {
		BASE (""),
		PROJECT ("PROJ"),
		SELECT ("SEL"),
		PRODUCT ("PROD"),
		UNION ("UPLUS"),
		UNION_MAX ("UMAX"),
		INTERSECT ("INTERSECT"),
		DIFFERENCE ("DIFF"),
		ELIMINATE ("ELIM"),
		RENAME ("REN");

		private final String connective;

		Type (String connective) {
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
}
