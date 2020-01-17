package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;

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
	
	public int getAttributePosition(String attribute) {
		return signature.getAttributes().indexOf(attribute);
	}
}
