package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Base extends RAExpr {
	
	private String name;
	
	public Base(String name) {
		super(Type.BASE);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public Signature signature(Schema s) {
		return s.getSignature(name);
	}

	@Override
	public Table execute(Database db) {
		return db.getTable(name);
	}
}
