package uk.ac.ed.inf.s1654170.mrai.schema;

public class SchemaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ErrorMessage {
		BASE_ERROR							("The relation you entered does not seem to exist."),
		PROJECT_ERROR						("The attributes to be projected over do not appear in the relation."),
		RENAME_ERROR						("You have a mistake in the renaming of one or more of your attributes."),
		SELECT_ERROR						("The condition of the selection operation is not valid."),
		PRODUCT_ERROR						("You must have distinct attributes when performing a cartesian product."),
		ORDERED_SIGNATURE_ATTRIBUTE_ERROR	("Your relations do not share the same attributes."),
		ORDERED_SIGNATURE_TYPE_ERROR		("The types do not match."),
		UNORDERED_SIGNATURE_ERROR			("There is a mistake in your attributes."),
		NO_SAME_ATTR						("The input arguments must have the same attributes and types."),
		DUPLICATE_ATTR						("There are duplicate attributes."),
		WRONG_TYPES							("The attributes do not share the same types.");

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
