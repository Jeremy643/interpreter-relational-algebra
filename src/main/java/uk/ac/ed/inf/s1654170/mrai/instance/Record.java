package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class Record extends ArrayList<DataValue> {

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
}
