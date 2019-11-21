package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class Union extends BinaryExpr {
	
	private RAExpr left;
	private RAExpr right;
	
	public Union(RAExpr left, RAExpr right) {
		super(left, right, Type.UNION);
		this.left = left;
		this.right = right;
	}

	@Override
	public String getConnective() {
		return Type.UNION.getConnective();
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		Signature l = left.signature(s);
		Signature r = right.signature(s);
		
		List<String> lAttr;
		List<Column.Type> lType;
		List<String> rAttr;
		List<Column.Type> rType;
		
		lAttr = new ArrayList<>(l.getAttributes());
		lType = new ArrayList<>(l.getTypes());
		rAttr = new ArrayList<>(r.getAttributes());
		rType = new ArrayList<>(r.getTypes());
		
		if (!lAttr.equals(rAttr) || !lType.equals(rType)) {
			throw new SchemaException("The arguments do not have the same set of attributes");
		} else {
			return left.signature(s);
		}
	}
}
