package uk.ac.ed.inf.s1654170.mrai.schema;

public class SchemaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ErrorMessage {
		UNION_ERROR					("UNION ERROR: The arguments do not have the same set of attributes or types."),
		UNION_MAX_ERROR				("UNION MAX ERROR: The arguments do not have the same set of attributes or types."),
		DIFFERENCE_ERROR			("DIFFERENCE ERROR: The arguments do not have the same set of attributes or types."),
		INTERSECT_ERROR				("INTERSECT ERROR: The arguments do not have the same set of attributes or types."),
		PROJECT_ERROR				("PROJECT ERROR: The attributes to be projected over do not appear in the relation."),
		RENAME_ERROR				("RENAME ERROR: You have a mistake in the renaming of one or more of your attributes."),
		SELECT_ERROR				("SELECT ERROR: The condition is not valid."),
		ORDERED_SIGNATURE_ERROR		("The types do not match."),
		UNORDERED_SIGNATURE_ERROR	("There are duplicate attributes."),
		NO_SAME_ATTR				("The input arguments must have the same attributes and types."),
		WRONG_TYPES					("The attributes do not share the same types."),
		INSERT_ERROR				("INSERT ERROR: You are trying to insert data into a relation that does not exist in the schema.");

		private final String errorMsg;
		
		ErrorMessage(String msg) {
			this.errorMsg = msg;
		}
		
		public String getErrorMessage() {
			return errorMsg;
		}
	}

	public SchemaException() {}
	
	public SchemaException(String msg) {
		super(msg);
	}
}
