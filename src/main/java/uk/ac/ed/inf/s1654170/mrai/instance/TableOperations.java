package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uk.ac.ed.inf.s1654170.mrai.schema.Column;

public class TableOperations {
	
	static class RecordSortingComparator implements Comparator<Record> {

		@Override
		public int compare(Record A, Record B) {
			List<Integer> comp = new ArrayList<>();
			for (int i = 0; i < A.size(); i++) {
				comp.add(A.get(i).toString().compareTo(B.get(i).toString()));
			}
			
			for (int i = 0; i < comp.size(); i++) {
				if (comp.get(i) == 0) {
					continue;
				} else {
					return comp.get(i);
				}
			}
			
			// we return here if A and B are exactly the same
			return 0;
		}
		
	}
	
	public static Table sortRecords(Table t) {
		Table sortedTable = t;
		Collections.sort(sortedTable, new RecordSortingComparator());
		return sortedTable;
	}

	public static Table Union(Table A, Table B) {
		Table table = new Table();
		
		table.addAll(A);
		table.addAll(B);
		
		return table;
	}
	
	public static Table Difference(Table A, Table B) {
		Table table = new Table();
		
		for (Record rA : A) {
			if (!B.contains(rA)) {
				table.add(rA);
			} else {
				B.remove(rA);
			}
		}
		
		return table;
	}
	
	public static Table Intersect(Table A, Table B) {
	    Table table = new Table();
	    
	    for (Record rA : A) {
            for (Record rB : B) {
                if (rA.equals(rB)) {
                    table.add(rA);
                    B.remove(rB);
                    break;
                }
            }
        }
	    
	    return table;
	}
	
	public static Table Product(Table A, Table B) {
	    Table table = new Table();
	    
	    List<Column.Type> types = new ArrayList<>();
        types.addAll(A.get(0).getTypes());
        types.addAll(B.get(0).getTypes());
	    
	    for (Record rA : A) {
	        for (Record rB : B) {
	            String[] values = new String[rA.size() + rB.size()];
	            int index = 0;
	            
	            for (int i = 0; i < rA.size(); i++) {
	                values[index] = rA.get(i).toString();
	                index++;
	            }
	            
	            for (int i = 0; i < rB.size(); i++) {
	                values[index] = rB.get(i).toString();
                    index++;
                }
	            
	            Record rNew = Record.valueOf(types, values);
	            table.add(rNew);
	        }
	    }
	    
	    return table;
	}
}
