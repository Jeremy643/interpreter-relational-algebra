package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableOperations {
	
	private static Table sortRecords(Table t) {
		// sort table by first column
		Table sortedTable = new Table();
		
		List<String> id = new ArrayList<>();
		for (Record r : t) {
			id.add(r.get(0).toString());
		}
		
		List<String> sortedID = new ArrayList<>(id);
		
		Collections.sort(sortedID);
		for (String val : sortedID) {
			sortedTable.add(t.get(id.indexOf(val)));
		}
		
		return sortedTable;
	}

	public static Table Union(Table A, Table B) {
		Table sortedA = sortRecords(A);
		Table sortedB = sortRecords(B);
		
		Table table = new Table();
		
		for (Record rA : sortedA) {
			for (Record rB : sortedB) {
				if (rA.equals(rB)) {
					table.add(rA);
					sortedB.remove(rB);
					break;
				}
			}
		}
		
		return table;
	}
}
