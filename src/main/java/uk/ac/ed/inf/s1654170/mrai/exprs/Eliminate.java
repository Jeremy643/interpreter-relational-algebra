package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Eliminate extends RAExpr {
	
	private RAExpr expr;
	
	public Eliminate(RAExpr expr) {
		super(Type.ELIMINATE);
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", Type.ELIMINATE.getConnective(), expr);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Eliminate) {
			if (this.expr.equals(((Eliminate) o).expr)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		return expr.signature(s);
	}

	@Override
	public Table executeValid(Database db) {
		return TableOperations.Eliminate(expr.executeValid(db));
	}
}
