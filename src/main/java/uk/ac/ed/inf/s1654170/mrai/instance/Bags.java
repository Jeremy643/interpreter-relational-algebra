package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.HashMap;

public class Bags extends HashMap<Record,Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean containsKey(Object key) {
		if (key instanceof Record) {
			for (Record r : this.keySet()) {
				if (r.equals(key)) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
	
	@Override
	public Integer get(Object o) {
		if (o instanceof Record) {
			Record r = (Record) o;
			ArrayList<Integer> values = new ArrayList<>();
			for (int val : this.values()) {
				values.add(val);
			}
			int index = 0;
			for (Record record : this.keySet()) {
				if (r.equals(record)) {
					return values.get(index);
				}
				index++;
			}
			return 0;
		} else {
			throw new RuntimeException("Key does not match correct type.");
		}
	}
	
	public void replace(Record key, int value) {
		Bags temp = new Bags();
		for (Record r : this.keySet()) {
			if (r.equals(key)) {
				temp.put(r, value);
			} else {
				temp.put(r, this.get(r));
			}
		}
	}
}
