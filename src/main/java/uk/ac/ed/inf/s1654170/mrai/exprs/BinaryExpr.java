package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
import uk.ac.ed.inf.s1654170.mrai.schema.Utils;

public abstract class BinaryExpr extends RAExpr {

	private RAExpr left;
	private RAExpr right;
	private Type type;

	public BinaryExpr(RAExpr left, RAExpr right, Type type) {
		super(type);
		this.left = left;
		this.right = right;
		this.type = type;
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
		if (l.isOrdered() && r.isOrdered()) {
			if (this.getType() == RAExpr.Type.PRODUCT) {
				return Utils.concat(l, r);
			}

			//check that both signatures share the same attributes
			if (!l.getAttributes().equals(r.getAttributes()) || !l.getTypes().equals(r.getTypes())) {
				throw new SchemaException(SchemaException.NO_SAME_ATTR);
			} else {
				return left.signature(s);
			}
		} else {
			if (this.getType() == RAExpr.Type.PRODUCT) {
				return Utils.concat(l, r);
			}
			
			Set<String> lSetAttr = new HashSet<>(l.getAttributes());
			Set<String> rSetAttr = new HashSet<>(r.getAttributes());
			
			//check that both signatures share the same attributes
			if (!lSetAttr.equals(rSetAttr) || !l.getTypes().equals(r.getTypes())) {
				throw new SchemaException(SchemaException.NO_SAME_ATTR);
			} else {
				return left.signature(s);
			}
		}
	}

	@Override
	public Table executeValid(Database db) {
		switch (type) {
		case UNION:
			return TableOperations.Union(left.executeValid(db), right.executeValid(db));
		case UNION_MAX:
			return TableOperations.UnionMax(left.executeValid(db), right.executeValid(db));
		case PRODUCT:
			return TableOperations.Product(left.executeValid(db), right.executeValid(db));
		case INTERSECT:
			return TableOperations.Intersect(left.executeValid(db), right.executeValid(db));
		case DIFFERENCE:
			return TableOperations.Difference(left.executeValid(db), right.executeValid(db));
		default:
			throw new RuntimeException("Unknown binary operation");
		}
	}
}
