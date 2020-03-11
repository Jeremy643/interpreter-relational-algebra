package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class Schema {
	
	private Map<String,Signature> relations = new HashMap<>();
	
	public Schema(List<String> fileName, List<String> attributes, List<String> attributeTypes, boolean orderedColumns) {
		for (int i = 0; i < fileName.size(); i++) {
			String file = fileName.get(i);
			String attr = attributes.get(i);
			String attrType = attributeTypes.get(i);
			
			List<String> attribute = new ArrayList<>(Arrays.asList(attr.split(",")));
			List<String> tempType = new ArrayList<>(Arrays.asList(attrType.split(",")));
			List<Type> type = new ArrayList<>();
			
			for (String t : tempType) {
				try {
					type.add(Type.valueOf(t));
				} catch(Exception e) {
					throw e;
				}
			}
			
			Signature sig = new BaseSignature(attribute, type, orderedColumns);
			relations.put(file, sig);
		}
	}
	
	public Set<String> getRelations() {
		return relations.keySet();
	}
	
	public Signature getSignature(String relName) {
		return relations.get(relName);
	}
}
