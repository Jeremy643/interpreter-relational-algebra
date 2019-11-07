package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public interface Signature {

	public List<String> getAttributes();
	
	//public String getAttribute(int k);
	
	//public int[] getPositions(String attr);
	
	public List<Type> getTypes();
	
	//public Type getType(int i);
		
	public Column get(int i);
}
