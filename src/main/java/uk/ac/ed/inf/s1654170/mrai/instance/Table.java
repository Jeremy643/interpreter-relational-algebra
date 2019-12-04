package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;

import uk.ac.ed.inf.s1654170.mrai.schema.Schema;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Table extends ArrayList<Record> {
	
	private String name;
	
	public Table(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
