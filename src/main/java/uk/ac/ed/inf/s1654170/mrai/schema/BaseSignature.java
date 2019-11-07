package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class BaseSignature implements Signature {
	
	List<String> names;
	List<Type> types;

	public BaseSignature(List<String> attr, List<Type> types) {
		if (attr.size() != types.size()) {
			throw new RuntimeException();
		}
		names = new ArrayList<String>(attr);
		types = new ArrayList<Type>(types);
	}
	
	@Override
	public List<String> getAttributes() {
		return new ArrayList<String>(names);
	}

	@Override
	public List<Type> getTypes() {
		return new ArrayList<Type>(types);
	}

	@Override
	public Column get(int i) {
		return new Column(names.get(i), types.get(i));
	}
}
