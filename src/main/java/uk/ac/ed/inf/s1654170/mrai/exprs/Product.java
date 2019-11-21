package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
import uk.ac.ed.inf.s1654170.mrai.schema.UnnamedSignature;

public class Product extends BinaryExpr {
	
	private RAExpr left;
	private RAExpr right;
	
	public Product(RAExpr left, RAExpr right) {
		super(left, right, Type.PRODUCT);
		this.left = left;
		this.right = right;
	}

	@Override
	public String getConnective() {
		return Type.PRODUCT.getConnective();
	}

	@Override
	public Signature signature(Schema s) {
		Signature l = left.signature(s);
		Signature r = right.signature(s);
		
		List<Column.Type> types = new ArrayList<>();
		
		if (l == null || r == null) {
			return null;
		} else {
			types.addAll(l.getTypes());
			types.addAll(r.getTypes());
			
			return new UnnamedSignature(types);
		}
	}

	@Override
	public boolean validate(Schema schema) {
		Signature l = left.signature(schema);
		Signature r = right.signature(schema);
		
		if (l == null || r == null) {
			return false;
		} else {
			return true;
		}
	}
}
