package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static Signature concat(Signature left, Signature right) throws SchemaException {
		if (left.isOrdered() && right.isOrdered()) {
			List<String> names = new ArrayList<>(left.getAttributes());
			names.addAll(right.getAttributes());
			
			List<Column.Type> types = new ArrayList<>(left.getTypes());
			types.addAll(right.getTypes());

			return new BaseSignature(names, types, true);
		} else {
			for (String attr : right.getAttributes()) {
				if (left.getAttributes().contains(attr)) {
					throw new SchemaException(SchemaException.ErrorMessage.UNORDERED_SIGNATURE_ERROR.getErrorMessage());
				}
			}
			
			List<String> names = new ArrayList<>(left.getAttributes());
			names.addAll(right.getAttributes());
			
			List<Column.Type> types = new ArrayList<>(left.getTypes());
			types.addAll(right.getTypes());
			
			return new BaseSignature(names, types, false);
		}
	}
}
