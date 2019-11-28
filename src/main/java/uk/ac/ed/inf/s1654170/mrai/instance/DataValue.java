package uk.ac.ed.inf.s1654170.mrai.instance;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class DataValue {
	
	private Column.Type type;
	private final String stringValue;
	private final Float numberValue;

	public DataValue(String s) {
		this.type = Column.Type.STRING;
		this.stringValue = s;
		this.numberValue = null;
	}
	
	public DataValue(Float f) {
		this.type = Column.Type.NUMBER;
		this.stringValue = null;
		this.numberValue = f;
	}
}
