package uk.ac.ed.inf.s1654170.mrai.exprs;

import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
import uk.ac.ed.inf.s1654170.mrai.schema.Utils;

public abstract class BinaryExpr extends RAExpr {

	private RAExpr left;
	private RAExpr right;

	public BinaryExpr(RAExpr left, RAExpr right, Type type) {
		super(type);
		this.left = left;
		this.right = right;
	}

	public abstract String getConnective();

	@Override
	public String toString() {
		return String.format("(%s %s %s)", left.toString(), getConnective(), right.toString());
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		Signature l = left.signature(s);
		Signature r = right.signature(s);

		if (this.getType() == RAExpr.Type.PRODUCT) {
			return Utils.concat(l, r);
		}
		
		if (!l.getAttributes().equals(r.getAttributes()) || !l.getTypes().equals(r.getTypes())) {
			throw new SchemaException(SchemaException.NO_SAME_ATTR);
		} else {
			return left.signature(s);
		}
	}
}
