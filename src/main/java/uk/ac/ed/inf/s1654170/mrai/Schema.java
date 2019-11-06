package uk.ac.ed.inf.s1654170.mrai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Schema {
	
	private Map<String,HashSet<String>> relations;
	private Map<String,Type> attributeTypes;
	
	enum Type {
		STR,
		NUM;
	}

	public Schema(Map<String,HashSet<String>> relations, Map<String,Type> types) {
		this.relations = new HashMap<>(relations);
		attributeTypes = new HashMap<>(types);
	}
	
	public Map<String,HashSet<String>> getRelations() {
		return relations;
	}
	
	public Map<String,Type> getAttributeTypes() {
		return attributeTypes;
	}
	
}
