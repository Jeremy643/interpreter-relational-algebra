package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;
import uk.ac.ed.inf.s1654170.mrai.instance.TableOperations;
import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Database;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Project extends RAExpr {
	
	private List<String> attributes;
	private RAExpr expr;
	
	public Project(RAExpr expr, List<String> attr) {
		super(Type.PROJECT);
		this.attributes = new ArrayList<String>(attr);
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		String attrList = String.join(",", attributes);
		return String.format("%s[%s](%s)", Type.PROJECT.getConnective(), attrList, expr.toString());
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		Signature sigExpr = expr.signature(s);
		
		List<String> attr;
		List<Column.Type> type;

		attr = new ArrayList<>(sigExpr.getAttributes());
		type = new ArrayList<>(sigExpr.getTypes());
		
		if (!attr.containsAll(attributes)) {
			throw new SchemaException(SchemaException.PROJECT_ERROR);
		} else {
			for (int i = 0; i < attr.size(); i++) {
				if (!attributes.contains(attr.get(i))) {
					attr.remove(i);
					type.remove(i);
				}
			}
			
			return new BaseSignature(attr,type);
		}
	}

	@Override
	public Table executeValid(Database db) {
		return TableOperations.Project(attributes, expr.executeValid(db));
	}
}
