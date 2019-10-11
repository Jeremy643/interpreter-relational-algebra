package jas.rai.exprs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rename extends RAExpr {
	
	private HashMap<String,String> attributes;
	private RAExpr relation;
	
	public Rename(RAExpr relation, HashMap<String,String> attributes) {
		super(Type.RENAME);
		this.relation = relation;
		this.attributes = new HashMap<>(attributes);
	}
	
	@Override
	public String toString() {
		List<String> attribute = new ArrayList<>();
		// FIFO - display first rename first
		for (int i = attributes.size()-1; i >= 0; i--) {
			String key = String.valueOf(attributes.keySet().toArray()[i]);
			attribute.add(key + "/" + attributes.get(key));
		}
		
		return String.format("%s%s(%s)", RENAME, attribute.toString(), relation);
	}
	
}
