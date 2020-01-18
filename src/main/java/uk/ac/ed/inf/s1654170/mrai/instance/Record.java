package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class Record extends ArrayList<DataValue> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Record valueOf(Column.Type[] types, String... values) {
		if (types.length != values.length) {
			throw new RuntimeException();
		}
		Record r = new Record();
		for (int i=0; i < values.length; i++) {
			DataValue dv ;
			switch (types[i]) {
			case NUMBER:
				dv = new DataValue(Float.valueOf(values[i]));
				break;
			default:
				dv = new DataValue(values[i]);
			}
			r.add(dv);
		}
		return r;
	}
	
	public static Record valueOf(List<Column.Type> types, String... values) {
		Column.Type[] tArray = new Column.Type[types.size()];
		types.toArray(tArray);
		return Record.valueOf(tArray,values);
	}

	public List<Column.Type> getTypes() {
		List<Column.Type> types = new ArrayList<>();
		for (int i = 0; i < super.size(); i++) {
			types.add(super.get(i).getType());
		}

		return types;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Record && this.size() == ((Record) o).size()) {
			Record r = (Record) o;
			for (int i = 0; i < this.size(); i++) {
				if (!this.get(i).equals(r.get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String record = "";
		
		for (int i = 0; i < this.size(); i++) {
			if (i == 0) {
				record += String.format("| %s |", this.get(i));
			} else {
				if (i == this.size()-1) {
					record += String.format(" | %s |\n", this.get(i));
				} else {
					record += String.format(" %s", this.get(i));
				}
			}
		}
		
		return record;
	}
}
