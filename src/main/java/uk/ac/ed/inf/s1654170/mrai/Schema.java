package uk.ac.ed.inf.s1654170.mrai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Schema {
	
	private Map<String,HashSet<String>> relations;
	private Map<String,HashMap<String,Type>> attributeTypes;
	
	enum Type {
		STR,
		NUM;
	}

	public Schema(Map<String,HashSet<String>> relations, Map<String,HashMap<String,Type>> attributeTypes) {
		this.relations = new HashMap<>(relations);
		this.attributeTypes = new HashMap<>(attributeTypes);
	}
	
	public Map<String,HashSet<String>> getRelations() {
		return relations;
	}
	
	public Map<String,HashMap<String,Type>> getAttributeTypes() {
		return attributeTypes;
	}
	
}
