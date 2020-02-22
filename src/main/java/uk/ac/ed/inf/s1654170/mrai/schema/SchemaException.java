package uk.ac.ed.inf.s1654170.mrai.schema;

public class SchemaException extends Exception {
	
	public static final String UNION_ERROR = "UNION ERROR: The arguments do not have the same set of attributes or types.";
	public static final String UNION_MAX_ERROR = "UNION MAX ERROR: The arguments do not have the same set of attributes or types.";
	public static final String DIFFERENCE_ERROR = "DIFFERENCE ERROR: The arguments do not have the same set of attributes or types.";
	public static final String INTERSECT_ERROR = "INTERSECT ERROR: The arguments do not have the same set of attributes or types.";
	public static final String PROJECT_ERROR = "PROJECT ERROR: The attributes to be projected over do not appear in the relation.";
	public static final String RENAME_ERROR = "RENAME ERROR: You have a mistake in the renaming of one or more of your attributes.";
	public static final String SELECT_ERROR = "SELECT ERROR: The condition is not valid.";
	
	public static final String UNORDERED_SIGNATURE_ERROR = "There are duplicate attributes.";
	
	public static final String NO_SAME_ATTR = "The input arguments must have the same attributes and types";
	
	public static final String INSERT_ERROR = "INSERT ERROR: You are trying to insert data into a relation that does not exist in the schema.";

	public SchemaException() {}
	
	public SchemaException(String msg) {
		super(msg);
	}
}
