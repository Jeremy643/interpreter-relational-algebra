package uk.ac.ed.inf.s1654170.mrai.table;

import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Table {
	
	private String name;
	private Schema schema;
	
	public Table(String name, Schema schema) {
		this.name = name;
		this.schema = schema;
	}
	
	public void insert(String record) throws SchemaException {
		Signature sig = schema.getSignature(name);
		if (sig == null) {
			throw new SchemaException(SchemaException.INSERT_ERROR);
		} else {
			String newRecord = record.replaceAll(" ", "");
			String[] dataEntry = newRecord.split(",");
			if (dataEntry.length != schema.getSignature(name).getAttributes().size()) {
				throw new SchemaException(SchemaException.INSERT_ERROR);
			} else {
				
			}
		}
	}
}
