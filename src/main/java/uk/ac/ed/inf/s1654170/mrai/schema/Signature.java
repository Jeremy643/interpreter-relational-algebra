package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public interface Signature {

	public boolean isOrdered();

	public List<String> getAttributes();

	public List<Type> getTypes();

	public Column get(int i);
}
