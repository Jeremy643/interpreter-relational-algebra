package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class Record extends ArrayList<DataValue> {
	
	public List<Column.Type> getTypes() {
		List<Column.Type> types = new ArrayList<>();
		for (int i = 0; i < super.size(); i++) {
			types.add(super.get(i).getType());
		}
		
		return types;
	}
}
