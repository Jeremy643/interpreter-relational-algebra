package uk.ac.ed.inf.s1654170.mrai.exprs;

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

//	@Override
//	public Signature getSignature() {
//		return null;
//	}

	@Override
	public boolean validate(Schema schema) {
		Signature sig = schema.getSignature(name);
		if (sig == null) {
			return false;
		} else {
			return true;
		}
	}
}
