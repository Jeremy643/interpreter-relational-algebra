package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.parser.RelationalAlgebraLexer;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public abstract class RAExpr {
	
	private static final String getName(int i) {
		return RelationalAlgebraLexer.VOCABULARY.getLiteralName(i).replace("'", "");
	}

	enum Type {
		BASE		(""),
		PROJECT		(getName(RelationalAlgebraLexer.PROJECTION)),
		SELECT		(getName(RelationalAlgebraLexer.SELECT)),
		PRODUCT		(getName(RelationalAlgebraLexer.PRODUCT)),
		UNION		(getName(RelationalAlgebraLexer.UNION)),
		UNION_MAX	(getName(RelationalAlgebraLexer.UNION_MAX)),
		INTERSECT	(getName(RelationalAlgebraLexer.INTERSECTION)),
		DIFFERENCE	(getName(RelationalAlgebraLexer.DIFFERENCE)),
		ELIMINATE	(getName(RelationalAlgebraLexer.ELIMINATE)),
		RENAME		(getName(RelationalAlgebraLexer.RENAMING));

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
	
	public abstract Table execute(Database db); // rename to executeValid
	
//	public Table execute(Database db) throws SchemaException {
//		validate(db.getSchema());
//		return executeValid(db);
//	}
}
