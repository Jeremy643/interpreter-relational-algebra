package uk.ac.ed.inf.s1654170.mrai.exprs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Rename extends RAExpr {
	
	public static final String MAP_SYMBOL = "->";
	
	private Map<String,String> attributes;
	private RAExpr relation;
	
	public Rename(RAExpr relation, Map<String,String> attributes) {
		super(Type.RENAME);
		this.relation = relation;
		this.attributes = new HashMap<String,String>(attributes);
	}
	
	@Override
	public String toString() {
		List<String> subst = new ArrayList<String>();
 		for (String k : attributes.keySet()) {
			subst.add(String.format("%s%s%s", k, MAP_SYMBOL, attributes.get(k)));
		}
 		return String.format("%s[%s](%s)", Type.RENAME.getConnective(), String.join(",",subst), relation);
	}

	@Override
	public Signature signature(Schema s) throws SchemaException {
		Signature sigRel = relation.signature(s);
		
		List<String> attr;
		List<Column.Type> type;

		attr = new ArrayList<>(sigRel.getAttributes());
		type = new ArrayList<>(sigRel.getTypes());
		
		List<String> oldName = new ArrayList<>(attributes.keySet());
		
		if (!attr.containsAll(oldName)) {
			throw new SchemaException(SchemaException.RENAME_ERROR);
		} else {
			for (int i = 0; i < attr.size(); i++) {
				if (oldName.contains(attr.get(i))) {
					attr.remove(i);
					attr.add(i, attributes.get(attr.get(i)));
				}
			}
			return new BaseSignature(attr,type);
		}
	}
}
