package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.instance.DataValue;
import uk.ac.ed.inf.s1654170.mrai.instance.Record;
import uk.ac.ed.inf.s1654170.mrai.instance.Table;

public class Database {
	
	private Schema schema;
	private List<Table> instances;
	
	public Database(Schema schema, ArrayList<Table> instances) {
		this.schema = schema;
		this.instances = validate(instances);
	}
	
	private ArrayList<Table> validate(ArrayList<Table> instances) {
		boolean valid = true;
		for (Table t : instances) {
			if (schema.getRelations().contains(t.getName())) {
				Signature sig = schema.getSignature(t.getName());
				List<Column.Type> types = new ArrayList<>(sig.getTypes());
				for (Record r : t) {
					List<Column.Type> dv = r.getTypes();
					if (!types.containsAll(dv)) {
						valid = false;
					}
				}
			} else {
				valid = false;
				break;
			}
		}
		
		if (valid) {
			return new ArrayList<>(instances);
		} else {
			return null;
		}
	}
	
	public void addInstance(Table instance) {
		instances.add(instance);
	}

}
