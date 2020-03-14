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

public class Project extends RAExpr {
	
	private List<String> attributes;
	private RAExpr expr;
	
	public Project(RAExpr expr, List<String> attr) {
		super(Type.PROJECT);
		Set<String> attrSet = new HashSet<>(attr);
		if (attrSet.size() != attr.size()) {
			//unordered - attribute names must be distinct
			throw new RuntimeException();
		}
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
		List<String> attr = new ArrayList<>(sigExpr.getAttributes());
		List<Column.Type> type = new ArrayList<>(sigExpr.getTypes()); 
		if (!attr.containsAll(attributes)) {
			//relation being projected over doesn't have the required attributes
			throw new SchemaException(SchemaException.ErrorMessage.PROJECT_ERROR.getErrorMessage());
		}
		for (int i = 0; i < attr.size(); i++) {
			if (!attributes.contains(attr.get(i))) {
				attr.remove(i);
				type.remove(i);
			}
		}
		return new BaseSignature(attr,type,sigExpr.isOrdered());
	}

	@Override
	public Table executeValid(Database db) {
		if (expr.executeValid(db).getBagEvaluation()) {
			//bag evaluation
			return TableOperations.Project(attributes, expr.executeValid(db));
		} else {
			//set evaluation
			return TableOperations.Eliminate(TableOperations.Project(attributes, expr.executeValid(db)));
		}
	}
}
