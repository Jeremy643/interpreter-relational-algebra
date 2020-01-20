package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class Table extends ArrayList<Record> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2180789515268198297L;
	
	private Signature signature;
	
	public Table(Signature signature) {
		this.signature = signature;
	}
	
	public Signature getSignature() {
		return signature;
	}
	
	@Override
	public String toString() {
		List<String> attributes = new ArrayList<>(signature.getAttributes());
		String table = "";
		
		for (int i = 0; i < attributes.size(); i++) {
			if (i == 0) {
				table += String.format("| %s |", attributes.get(i));
			} else {
				if (i == attributes.size()-1) {
					table += String.format(" %s |\n", attributes.get(i));
				} else {
					table += String.format(" %s |", attributes.get(i));
				}
			}
		}
		
		for (Record r : this) {
			table += r;
		}
		
		return table;
	}
}
