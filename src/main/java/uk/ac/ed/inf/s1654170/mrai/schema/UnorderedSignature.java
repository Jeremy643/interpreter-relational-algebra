package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class UnorderedSignature implements Signature {
	
	private Set<String> names;
	private Set<Type> types;
	
	public UnorderedSignature(Set<String> attr, Set<Type> types) {
		if (attr.size() != types.size()) {
			throw new RuntimeException();
		}
		names = new HashSet<String>(attr);
		this.types = new HashSet<Type>(types);
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
		return null;
	}
	
}
