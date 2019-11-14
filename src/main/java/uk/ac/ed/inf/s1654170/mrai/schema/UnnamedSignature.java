package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class UnnamedSignature implements Signature {
	
	private List<Type> types;
	
	public UnnamedSignature(List<Type> types) {
		this.types = types;
	}

	@Override
	public List<String> getAttributes() {
		return null;
	}

	@Override
	public List<Type> getTypes() {
		return new ArrayList<Type>(types);
	}

	@Override
	public Column get(int i) {
		return new Column(String.valueOf(i), types.get(i));
	}

}
