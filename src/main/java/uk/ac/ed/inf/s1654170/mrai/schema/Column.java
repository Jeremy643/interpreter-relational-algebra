package uk.ac.ed.inf.s1654170.mrai.schema;

public class Column {
	
	public enum Type { STRING, NUMBER }
	
	String name;
	Type type;
	
	public Column(String name, Type type) {
		this.name = name;
		this.type = type;
	}
}
