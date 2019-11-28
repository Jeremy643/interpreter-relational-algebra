package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static Signature concat(Signature left, Signature right) {
		List<String> names = new ArrayList<>(left.getAttributes());
		names.addAll(right.getAttributes());
		
		List<Column.Type> types = new ArrayList<>(left.getTypes());
		types.addAll(right.getTypes());

		return new BaseSignature(names, types);
	}
}
