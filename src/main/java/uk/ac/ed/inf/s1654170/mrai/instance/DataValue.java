package uk.ac.ed.inf.s1654170.mrai.instance;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;

public class DataValue implements Comparable<DataValue> {
	
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
	
	public Column.Type getType() {
		return type;
	}
	
	public String toString() {
		if (stringValue == null) {
			return String.valueOf(numberValue);
		} else {
			return stringValue;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DataValue) {
			DataValue dv = (DataValue) o;
			if (this.type == dv.type && this.type == Column.Type.STRING) {
				return this.stringValue.equals(dv.stringValue);
			} else {
				if (this.type == dv.type && this.type == Column.Type.NUMBER) {
					return this.numberValue.equals(dv.numberValue);
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(DataValue o) {
		if (this.type != o.getType()) {
			throw new RuntimeException("Cannot compare values of different type");
		}
		if (this.type == Type.NUMBER) {
			return this.numberValue.compareTo(o.numberValue);
		}
		if (this.type == Type.STRING) {
			return this.stringValue.compareTo(o.stringValue);
		}
		throw new RuntimeException("This should not be happening");
	}
}
