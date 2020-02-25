package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

// Attributes in a signature are never repeated
// Attributes are a set (implemented as a list) that can be interpreted as
// -- ordered (hence the list), or
// -- unordered (in which case the order given by the list implementation is ignored)

public interface Signature {

	public boolean isOrdered();

	public List<String> getAttributes();

	public List<Type> getTypes();

	public Column get(int i);
}
