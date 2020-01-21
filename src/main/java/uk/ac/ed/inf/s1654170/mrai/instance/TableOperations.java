package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import uk.ac.ed.inf.s1654170.mrai.conditions.Condition;
import uk.ac.ed.inf.s1654170.mrai.schema.BaseSignature;
import uk.ac.ed.inf.s1654170.mrai.schema.Column;
import uk.ac.ed.inf.s1654170.mrai.schema.Column.Type;
import uk.ac.ed.inf.s1654170.mrai.schema.Signature;

public class TableOperations {

    private static Table sortRecords(Table t) {
        Collections.sort(t);
        return t;
    }

    public static Table Union(Table A, Table B) {
    	Table sortedA = sortRecords(A);
    	Table sortedB = sortRecords(B);
        Table table = new Table(A.getSignature());

        table.addAll(sortedA);
        table.addAll(sortedB);

        return table;
    }

    public static Table UnionMax(Table A, Table B) {
    	Table sortedA = sortRecords(A);
    	Table sortedB = sortRecords(B);
    	
    	ListIterator<Record> itA = sortedA.listIterator();
    	ListIterator<Record> itB = sortedB.listIterator();
    	
    	while (itA.hasNext() && itB.hasNext()) {
    		Record a = itA.next();
    		Record b = itB.next();
    		
    		// TODO: implement algorithm with pointers
    		/* WHILE one > other DO output other & increment both
    		 * IF equal SET i=0; j=0; curr = record
    		 *     WHILE = DO j++ & output right & increment right
    		 *     left++
    		 *     WHILE left = curr DO i++; if (i>j) output left; j++
    		 */
    	}
    	
    	Table table = new Table(A.getSignature());

        Bags recordOccurrencesA = new Bags();
        Bags recordOccurrencesB = new Bags();

        // Find the number of occurrences of each record in A
        for (Record rA : sortedA) {
            if (!recordOccurrencesA.containsKey(rA)) {
                recordOccurrencesA.put(rA, 1);
            } else {
            	//System.out.println(rA);
            	//System.out.println(recordOccurrencesA.get(rA));
                int oldVal = recordOccurrencesA.get(rA);
                //recordOccurrencesA.put(rA, oldVal+1);
                recordOccurrencesA.replace(rA, oldVal + 1);
                System.out.println(recordOccurrencesA);
            }
        }

        // Find the number of occurrences of each record in B
        for (Record rB : sortedB) {
            if (!recordOccurrencesB.containsKey(rB)) {
                recordOccurrencesB.put(rB, 1);
            } else {
                int oldVal = recordOccurrencesB.get(rB);
                recordOccurrencesB.replace(rB, oldVal + 1);
            }
        }

        for (Record r : recordOccurrencesA.keySet()) {
            int occurrencesA = recordOccurrencesA.get(r);
            int occurrencesB;

            if (recordOccurrencesB.containsKey(r)) {
                occurrencesB = recordOccurrencesB.get(r);
                recordOccurrencesB.remove(r);
            } else {
                occurrencesB = 0;
            }

            int occ = occurrencesA >= occurrencesB ? occurrencesA : occurrencesB;
            for (int i = 0; i < occ; i++) {
            	table.add(r);
            }
        }

        if (recordOccurrencesB.isEmpty()) {
            // B contained records that all appeared in A
            return table;
        } else {
            // add the remaining records in B to the table
            for (Record r : recordOccurrencesB.keySet()) {
                int occurrences = recordOccurrencesB.get(r);
                for (int i = 0; i < occurrences; i++) {
                    table.add(r);
                }
            }

            return table;
        }
    	
    	
        /*Table table = new Table(A.getSignature());

        Map<Record, Integer> recordOccurrencesA = new HashMap<>();
        Map<Record, Integer> recordOccurrencesB = new HashMap<>();

        // Find the number of occurrences of each record in A
        for (Record rA : A) {
            if (!recordOccurrencesA.containsKey(rA)) {
                recordOccurrencesA.put(rA, 1);
            } else {
                int oldVal = recordOccurrencesA.get(rA);
                recordOccurrencesA.replace(rA, oldVal + 1);
            }
        }

        // Find the number of occurrences of each record in B
        for (Record rB : B) {
            if (!recordOccurrencesB.containsKey(rB)) {
                recordOccurrencesB.put(rB, 1);
            } else {
                int oldVal = recordOccurrencesB.get(rB);
                recordOccurrencesB.replace(rB, oldVal + 1);
            }
        }

        for (Record r : recordOccurrencesA.keySet()) {
            int occurrencesA = recordOccurrencesA.get(r);
            int occurrencesB;

            if (recordOccurrencesB.containsKey(r)) {
                occurrencesB = recordOccurrencesB.get(r);
                recordOccurrencesB.remove(r);
            } else {
                occurrencesB = 0;
            }

            int occ = occurrencesA >= occurrencesB ? occurrencesA : occurrencesB;
            for (int i = 0; i < occ; i++) {
            	table.add(r);
            }
        }

        if (recordOccurrencesB.isEmpty()) {
            // B contained records that all appeared in A
            return table;
        } else {
            // add the remaining records in B to the table
            for (Record r : recordOccurrencesB.keySet()) {
                int occurrences = recordOccurrencesB.get(r);
                for (int i = 0; i < occurrences; i++) {
                    table.add(r);
                }
            }

            return table;
        }*/
    }

    public static Table Difference(Table A, Table B) {
    	Table sortedA = sortRecords(A);
    	Table sortedB = sortRecords(B);
        Table table = new Table(A.getSignature());
        
        Table tempB = new Table(B.getSignature());
        tempB.addAll(sortedB);

        for (Record rA : sortedA) {
            if (!sortedB.contains(rA)) {
                table.add(rA);
            } else {
                tempB.remove(rA);
            }
        }

        return table;
    }

    public static Table Intersect(Table A, Table B) {
    	Table sortedA = sortRecords(A);
    	Table sortedB = sortRecords(B);
        Table table = new Table(A.getSignature());
        
        Table tempB = new Table(B.getSignature());
        tempB.addAll(sortedB);

        for (Record rA : sortedA) {
            for (Record rB : tempB) {
                if (rA.equals(rB)) {
                    table.add(rA);
                    tempB.remove(rB);
                    break;
                }
            }
        }

        return table;
    }

    public static Table Product(Table A, Table B) {
    	Table sortedA = sortRecords(A);
    	Table sortedB = sortRecords(B);
    	
    	List<String> attributes = new ArrayList<>(A.getSignature().getAttributes());
    	attributes.addAll(B.getSignature().getAttributes());
    	List<Type> attributeTypes = new ArrayList<>(A.getSignature().getTypes());
    	attributeTypes.addAll(B.getSignature().getTypes());
    	
    	Signature sig = new BaseSignature(attributes, attributeTypes);
        Table table = new Table(sig);

        List<Column.Type> types = new ArrayList<>();
        types.addAll(sortedA.get(0).getTypes());
        types.addAll(sortedB.get(0).getTypes());

        for (Record rA : sortedA) {
            for (Record rB : sortedB) {
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
    
    public static Table Eliminate(Table A) {
    	Table sortedA = sortRecords(A);
    	Table table = new Table(A.getSignature());
    	
    	for (Record r : sortedA) {
    		if (!table.contains(r)) {
    			table.add(r);
    		}
    	}
    	
    	return table;
    }
    
    public static Table Rename(Map<String,String> attributes, Table A) {
    	List<String> attributesA = new ArrayList<>(A.getSignature().getAttributes());
    	List<Type> typesA = new ArrayList<>(A.getSignature().getTypes());
    	
    	List<String> attributesTable = new ArrayList<>();
    	for (String attr : attributesA) {
    		if (attributes.containsKey(attr)) {
    			attributesTable.add(attributes.get(attr));
    		} else {
    			attributesTable.add(attr);
    		}
    	}
    	
    	Signature signature = new BaseSignature(attributesTable, typesA);
    	Table table = new Table(signature);
    	
    	table.addAll(A);
    	A = table;
    	
    	return A;
    }
    
    public static Table Project(List<String> columns, Table A) {
    	List<String> attributesA = new ArrayList<>(A.getSignature().getAttributes());
    	List<Type> typesA = new ArrayList<>(A.getSignature().getTypes());
    	
    	List<Type> types = new ArrayList<>();
    	List<Integer> indexColumns = new ArrayList<>();
    	for (String col : columns) {
    		int index = attributesA.indexOf(col);
    		indexColumns.add(index);
    		types.add(typesA.get(index));
    	}
    	Signature signature = new BaseSignature(columns, types);
    	
    	Table table = new Table(signature);
    	
    	for (Record r : A) {
    		int counter = 0;
    		String[] values = new String[columns.size()];
    		for (int i : indexColumns) {
    			values[counter] = r.get(i).toString();
    			counter++;
    		}
    		Record rNew = Record.valueOf(types, values);
    		table.add(rNew);
    	}
    	
    	return table;
    }
    
    public static Table Select(Condition condition, Table A) {
    	/*Table table = new Table();
    	
    	return table;*/
    	return null;
    }
}
