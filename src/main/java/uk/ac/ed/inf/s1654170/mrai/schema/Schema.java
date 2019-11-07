package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.Map;
import java.util.Set;

public class Schema {
	
	private Map<String,Signature> relations;
	
//	public Schema(Map<String,HashMap<String,Type>> relations) {
//		this.relations = new HashMap<>(relations);
//	}
	
	public Set<String> getRelations() {
		return relations.keySet();
	}
	
	public Signature getSignature(String relName) {
		return relations.get(relName);
	}
}
