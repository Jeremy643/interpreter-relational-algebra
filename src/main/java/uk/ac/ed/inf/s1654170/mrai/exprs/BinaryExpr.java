package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
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
		
		//if one ordered and the other unordered, then throw runtime exception
		if ((l.isOrdered() && !r.isOrdered()) ||
				(!l.isOrdered() && r.isOrdered())) {
			throw new RuntimeException("One is ordered while the other is unordered.");
		}
		
		if (this.getType() == RAExpr.Type.PRODUCT) {
			for (String rAttr : r.getAttributes()) {
				if (l.getAttributes().contains(rAttr)) {
					throw new SchemaException(SchemaException.ErrorMessage.NO_SAME_ATTR.getErrorMessage());
				}
			}
			return Utils.concat(l, r);
		} else {
			//if type is union max/plus, intersect, difference
			if (l.isOrdered()) {
				List<Column.Type> lTypes = new ArrayList<>(l.getTypes());
				List<Column.Type> rTypes = new ArrayList<>(r.getTypes());
				if (lTypes.equals(rTypes)) {
					return new BaseSignature(l.getAttributes(), lTypes, true);
				} else {
					throw new SchemaException(SchemaException.ErrorMessage.ORDERED_SIGNATURE_ERROR.getErrorMessage());
				}
			} else {
				Set<String> lNames = new HashSet<>(l.getAttributes());
				Set<String> rNames = new HashSet<>(r.getAttributes());
				if (!lNames.equals(rNames)) {
					//left and right do not share the same attributes
					throw new SchemaException(SchemaException.ErrorMessage.UNORDERED_SIGNATURE_ERROR.getErrorMessage());
				}
				for (String lAttr : lNames) {
					//check attribute types match
					Column.Type lType = l.getTypes().get(l.getAttributes().indexOf(lAttr));
					Column.Type rType = r.getTypes().get(r.getAttributes().indexOf(lAttr));
					if (lType.equals(rType)) {
						continue;
					} else {
						throw new SchemaException(SchemaException.ErrorMessage.WRONG_TYPES.getErrorMessage());
					}
				}
				return new BaseSignature(l.getAttributes(), l.getTypes(), false);
			}
		}
	}

	@Override
	public Table executeValid(Database db) {
		if (left.executeValid(db).getBagEvaluation()) {
			//bag evaluation
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
		} else {
			//set evaluation
			switch (type) {
			case UNION:
				return TableOperations.Eliminate(TableOperations.Union(left.executeValid(db), right.executeValid(db)));
			case UNION_MAX:
				return TableOperations.Eliminate(TableOperations.UnionMax(left.executeValid(db), right.executeValid(db)));
			case PRODUCT:
				return TableOperations.Eliminate(TableOperations.Product(left.executeValid(db), right.executeValid(db)));
			case INTERSECT:
				return TableOperations.Eliminate(TableOperations.Intersect(left.executeValid(db), right.executeValid(db)));
			case DIFFERENCE:
				return TableOperations.Eliminate(TableOperations.Difference(left.executeValid(db), right.executeValid(db)));
			default:
				throw new RuntimeException("Unknown binary operation");
			}
		}
	}
}
