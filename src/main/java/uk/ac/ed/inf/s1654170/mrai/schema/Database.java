package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.HashMap;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;

public class Database {

	private Schema schema;
	private HashMap<String, Table> instance;

	public Database(Schema schema) {
		this.schema = schema;
		this.instance = new HashMap<>();
	}

	public void add(String n, Table t) throws Exception {
		add(n,t,false);
	}

	public void add(String n, Table t, boolean validate) throws Exception {
		if (schema.getRelations().contains(n) == false) {
			throw new Exception("No relation name " + n + " in schema");
		}
		if (validate == true) {
			if (validateTypes(n, t) ==  false) {
				throw new Exception("Types do not match");
			}
		}
		// Consider throwing an exception if mapping for "n" already present
		instance.put(n, t);
	}

	private boolean validateTypes(String n, Table t) {
		boolean valid = true;
		Signature sig = schema.getSignature(n);
		List<Column.Type> types = sig.getTypes();
		for (Record r : t) {
			List<Column.Type> dv = r.getTypes();
			if (types.equals(dv) == false) {
				valid = false;
			}
		}
		return valid;
	}
}
