package uk.ac.ed.inf.s1654170.mrai.instance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
import uk.ac.ed.inf.s1654170.mrai.schema.SchemaException;

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
	public boolean equals(Object o) {
		if (o instanceof Table) {
			Table oTable = new Table(((Table) o).getSignature());
			oTable.addAll((Table) o);
			if (signature.isOrdered() && oTable.getSignature().isOrdered()) {
				//columns are ordered
				boolean sameAttr = signature.getAttributes().equals(oTable.getSignature().getAttributes());
				boolean sameType = signature.getTypes().equals(oTable.getSignature().getTypes());
				if (sameAttr && sameType && this.size() == oTable.size()) {
					//compare records
					for (int i = 0; i < this.size(); i++) {
						if (!this.get(i).equals(oTable.get(i))) {
							//does not equal
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else {
				//columns are unordered
				Set<String> thisSetAttr = new HashSet<>(this.getSignature().getAttributes());
				Set<Column.Type> thisSetType = new HashSet<>(this.getSignature().getTypes());
				Set<String> oTableSetAttr = new HashSet<>(oTable.getSignature().getAttributes());
				Set<Column.Type> oTableSetType = new HashSet<>(oTable.getSignature().getTypes());
				
				boolean sameSetAttr = thisSetAttr.equals(oTableSetAttr);
				boolean sameSetType = thisSetType.equals(oTableSetType);
				
				if (sameSetAttr && sameSetType && this.size() == oTable.size()) {
					//compare records
					for (int i = 0; i < this.size(); i++) {
						Record thisRecord = this.get(i);
						Record oTableRecord = oTable.get(i);
						if (!thisRecord.equals(oTableRecord)) {
							//does not equal
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	public static Table fromCSV(File f) throws SchemaException, IOException {
		return fromCSV(f, false, false);
	}
	
	public static Table fromCSV(File f, boolean ord, boolean bags) throws SchemaException, IOException {
		List<String> attributes = new ArrayList<>();
		List<String> attributeTypes = new ArrayList<>();
		Collection<Record> records = bags ? new ArrayList<>() : new HashSet<>();
		List<Type> types = new ArrayList<>();
		List<String> dupAttr = new ArrayList<>();

		Reader in = new FileReader(f);
		Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.parse(in);

		int index = 0;
		for (CSVRecord record : csvRecords) {
			int size = record.size();
			switch (index) {
			case 0:
				String attr = "";
				for (int i = 0; i < size; i++) {
					dupAttr.add(record.get(i));
					if (i == size - 1) {
						attr += record.get(i);
					} else {
						attr += record.get(i) + ",";
					}
				}
				// check for duplicate attributes
				Set<String> dupSetAttr = new HashSet<>(dupAttr);
				if (dupAttr.size() != dupSetAttr.size()) {
					throw new SchemaException(String.format("The table %s contains duplicate attributes", f.getPath()));
				}
				attributes.add(attr.trim().replaceAll("\\s+", " "));
				break;
			case 1:
				String type = "";
				for (int i = 0; i < size; i++) {
					if (i == size - 1) {
						type += record.get(i).trim();
					} else {
						type += record.get(i).trim() + ",";
					}
					types.add(Type.valueOf(record.get(i).trim()));
				}
				attributeTypes.add(type);
				break;
			default:
				String[] values = new String[size];
				for (int i = 0; i < size; i++) {
					values[i] = record.get(i);
				}
				Record r = Record.valueOf(types, values);
				records.add(r);
				break;
			}
			index++;
		}
		Table t = new Table(new BaseSignature(attributes,types,ord));
		t.addAll(records);
		return t;
	}
}
