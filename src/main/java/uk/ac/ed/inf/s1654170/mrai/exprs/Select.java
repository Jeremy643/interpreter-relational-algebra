package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Select extends RAExpr {
	
	private Condition condition;
	private RAExpr expr;
	
	public Select(Condition condition, RAExpr expr) {
		super(Type.SELECT);
		this.condition = condition;
		this.expr = expr;
	}

	public Condition getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s](%s)", Type.SELECT.getConnective(), condition, expr);
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		Signature sig = expr.signature(s);
		
		if (!condition.validate(sig)) {
			throw new SchemaException(SchemaException.SELECT_ERROR);
		} else {
			return sig;
		}
	}

	@Override
	public Table execute(Database db) {
		return TableOperations.Select(condition, expr.execute(db));
	}
}
