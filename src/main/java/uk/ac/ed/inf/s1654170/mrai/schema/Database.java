package uk.ac.ed.inf.s1654170.mrai.schema;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.instance.Table;

public class Database {
	
	private Schema schema;
	private List<Table> instances;
	
	public Database(Schema schema, ArrayList<Table> instances) {
		this.schema = schema;
		this.instances = new ArrayList<>(instances);
	}
	
	public void addInstance(Table instance) {
		instances.add(instance);
	}

}
