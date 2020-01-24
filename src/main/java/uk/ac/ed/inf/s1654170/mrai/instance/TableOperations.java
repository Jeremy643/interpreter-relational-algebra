package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import uk.ac.ed.inf.s1654170.mrai.conditions.*;
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
    	
    	Table table = new Table(A.getSignature());
    	
    	// TODO: implement algorithm with pointers
		/* WHILE one > other DO output other & increment both
		 * IF equal SET i=0; j=0; curr = record
		 *     WHILE = DO j++ & output right & increment right
		 *     left++
		 *     WHILE left = curr DO i++; if (i>j) output left; j++
		 */
    	
    	if (!(itA.hasNext() && itB.hasNext())) {
    		throw new RuntimeException("Either one or both tables are empty");
    	}
    	
    	boolean cont = true;
    	Record a = itA.next();
    	Record b = itB.next();
    	do {
    		// contA = false, when itA has no more values
    		boolean contA = true;
    		// contB = false, when itB has no more values
    		boolean contB = true;
    		
    		int comp = a.compareTo(b);
    		System.out.println(String.format("OUTER: comp = %s", comp));
    		while (comp < 0) {
    			// left < right
    			table.add(a);
    			if (itA.hasNext()) {
    				// increment left
    				a = itA.next();
    			} else {
    				contA = false;
    				break;
    			}
    			comp = a.compareTo(b);
    			
    			System.out.println(String.format("INNER: a = %s, b = %s, comp = %s", a, b, comp));
    		}
    		while (comp > 0) {
    			// left > right
    			table.add(b);
    			if (itB.hasNext()) {
    				// increment right
    				b = itB.next();
    			} else {
    				contB = false;
    				break;
    			}
    			comp = a.compareTo(b);
    			
    			System.out.println(String.format("INNER: a = %s, b = %s, comp = %s", a, b, comp));
    		}
    		while (comp == 0) {
    			// left = right
    			// does adding b instead of a make a difference?
    			table.add(a);
    			if (itA.hasNext()) {
    				a = itA.next();
    			} else {
    				contA = false;
    			}
    			if (itB.hasNext()) {
    				b = itB.next();
    			} else {
    				contB = false;
    			}
    			if (!contA && !contB) {
    				break;
    			} else {
    				comp = a.compareTo(b);
    			}
    		}
    		
    		if (!contA && contB) {
    			// add remainder of itB
    			table.add(b);
    			while (itB.hasNext()) {
    				b = itB.next();
    				table.add(b);
    			}
    			contB = false;
    		}
    		
    		if (!contB && contA) {
    			// add remainder of itA
    			table.add(a);
    			while (itA.hasNext()) {
    				a = itA.next();
    				table.add(a);
    			}
    			contA = false;
    		}
    		
    		// if itA and itB are empty then finish
    		if (!(contA || contB)) {
    			cont = false;
    		}
    	} while (cont);

            return table;
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
    	Table sortedA = sortRecords(A);
    	Table table = new Table(A.getSignature());
    	
    	List<Comparison> comparisons = condition.getComparisons();
    	List<Condition.Type> cond = condition.getConditionTypes();
    	System.out.println(cond);
    	System.out.println(comparisons);
    	
    	for (Record r : sortedA) {
    		boolean[] valueComparisons = new boolean[comparisons.size()];
    		for (int i = 0; i < comparisons.size(); i++) {
    			Comparison c = comparisons.get(i);
    			Condition.Type type = c.getType();
    			List<Term> terms = c.getTerms();
    			Term left = terms.get(0);
    			Term right = terms.get(1);
    			if (left.isConstant()) {
    				// right must be attribute
    				int index = sortedA.getSignature().getAttributes().indexOf(right.getValue());
    				Float rVal;
    				Float termVal;
    				switch (type) {
    				case EQUALITY:
    					if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
    						valueComparisons[i] = r.get(index).toString().equals(left.getValue());
    					} else {
    						rVal = new Float(r.get(index).toString());
    						termVal = new Float(left.getValue());
    						valueComparisons[i] = rVal.equals(termVal);
    					}
    					break;
    				case INEQUALITY:
    					if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
    						valueComparisons[i] = !(r.get(index).toString().equals(left.getValue()));
    					} else {
    						rVal = new Float(r.get(index).toString());
    						termVal = new Float(left.getValue());
    						valueComparisons[i] = !(rVal.equals(termVal));
    					}
    					break;
    				case LESS:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(left.getValue());
						valueComparisons[i] = rVal < termVal;
    					break;
    				case LESS_EQUAL:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(left.getValue());
						valueComparisons[i] = rVal <= termVal;
    					break;
    				case GREATER:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(left.getValue());
						valueComparisons[i] = rVal > termVal;
    					break;
    				case GREATER_EQUAL:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(left.getValue());
						valueComparisons[i] = rVal >= termVal;
    					break;
    				default:
    					throw new RuntimeException("This is not a comparison operator");
    				}
    			} else if (right.isConstant()) {
    				// left must be attribute
    				int index = sortedA.getSignature().getAttributes().indexOf(left.getValue());
    				Float rVal;
    				Float termVal;
    				switch (type) {
    				case EQUALITY:
    					if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
    						valueComparisons[i] = r.get(index).toString().equals(right.getValue());
    					} else {
    						rVal = new Float(r.get(index).toString());
    						termVal = new Float(right.getValue());
    						valueComparisons[i] = rVal.equals(termVal);
    					}
    					break;
    				case INEQUALITY:
    					if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
    						valueComparisons[i] = !(r.get(index).toString().equals(right.getValue()));
    					} else {
    						rVal = new Float(r.get(index).toString());
    						termVal = new Float(right.getValue());
    						valueComparisons[i] = !(rVal.equals(termVal));
    					}
    					break;
    				case LESS:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(right.getValue());
						valueComparisons[i] = rVal < termVal;
    					break;
    				case LESS_EQUAL:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(right.getValue());
						valueComparisons[i] = rVal <= termVal;
    					break;
    				case GREATER:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(right.getValue());
						valueComparisons[i] = rVal > termVal;
    					break;
    				case GREATER_EQUAL:
    					rVal = new Float(r.get(index).toString());
						termVal = new Float(right.getValue());
						valueComparisons[i] = rVal >= termVal;
    					break;
    				default:
    					throw new RuntimeException("This is not a comparison operator");
    				}
    			} else {
    				// left and right are attributes
    				int indexL = sortedA.getSignature().getAttributes().indexOf(left.getValue());
    				int indexR = sortedA.getSignature().getAttributes().indexOf(right.getValue());
    				Float rVal;
    				Float termVal;
    				switch (type) {
    				case EQUALITY:
    					if (sortedA.getSignature().getTypes().get(indexL).equals(Type.STRING)) {
    						valueComparisons[i] = r.get(indexL).toString().equals(r.get(indexR));
    					} else {
    						rVal = new Float(r.get(indexL).toString());
    						termVal = new Float(r.get(indexR).toString());
    						valueComparisons[i] = rVal.equals(termVal);
    					}
    					break;
    				case INEQUALITY:
    					if (sortedA.getSignature().getTypes().get(indexL).equals(Type.STRING)) {
    						valueComparisons[i] = !(r.get(indexL).toString().equals(r.get(indexR)));
    					} else {
    						rVal = new Float(r.get(indexL).toString());
    						termVal = new Float(r.get(indexR).toString());
    						valueComparisons[i] = !(rVal.equals(termVal));
    					}
    					break;
    				case LESS:
    					rVal = new Float(r.get(indexL).toString());
						termVal = new Float(r.get(indexR).toString());
						valueComparisons[i] = rVal < termVal;
    					break;
    				case LESS_EQUAL:
    					rVal = new Float(r.get(indexL).toString());
						termVal = new Float(r.get(indexR).toString());
						valueComparisons[i] = rVal <= termVal;
    					break;
    				case GREATER:
    					rVal = new Float(r.get(indexL).toString());
						termVal = new Float(r.get(indexR).toString());
						valueComparisons[i] = rVal > termVal;
    					break;
    				case GREATER_EQUAL:
    					rVal = new Float(r.get(indexL).toString());
						termVal = new Float(r.get(indexR).toString());
						valueComparisons[i] = rVal >= termVal;
    					break;
    				default:
    					throw new RuntimeException("This is not a comparison operator");
    				}
    			}
    		}
    		
    		if (comparisons.size() > 1) {
    			List<String> values = new ArrayList<>();
    			int index = 0;
    			for (Condition.Type c : cond) {
    				if (c.equals(Condition.Type.AND) || c.equals(Condition.Type.OR) || c.equals(Condition.Type.NOT)) {
    					values.add(c.toString());
    				} else {
    					values.add(String.valueOf(valueComparisons[index]));
    					index++;
    				}
    			}
    			
    			int notCounter = 0;
    			index = 0;
    			List<String> newValues = new ArrayList<>();
    			for (String s : values) {
    				if (!(s.equals("true") || s.equals("false")) && Condition.Type.valueOf(s).equals(Condition.Type.NOT)) {
    					notCounter++;
    				} else {
    					if (s.equals("true") || s.equals("false")) {
    						boolean b = Boolean.valueOf(s);
    						if (notCounter % 2 == 1) {
    							b = !b;
    							newValues.add(String.valueOf(b));
    							notCounter = 0;
    							continue;
    						} else {
    							notCounter = 0;
    						}
    					}
    					newValues.add(s);
    				}
    				index++;
    			}
    			
    			boolean add = Boolean.valueOf(newValues.get(0));
    			for (int i = 0; i < newValues.size(); i++) {
    				String current = newValues.get(i);
    				if (!(current.equals("true") || current.equals("false"))) {
    					Condition.Type c = Condition.Type.valueOf(current);
    					switch (c) {
        				case AND:
        					add = add && Boolean.valueOf(newValues.get(i+1));
        					break;
        				case OR:
        					add = add || Boolean.valueOf(newValues.get(i+1));
        					break;
        				default:
        				    throw new RuntimeException("We should only get AND or OR");
        				}
    				}
    			}
    			
    			if (add) {
    				table.add(r);
    			}
    		} else {
    			if (valueComparisons[0]) {
    				table.add(r);
    			}
    		}
    	}
    	
    	return table;
    }
}
