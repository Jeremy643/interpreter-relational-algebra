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

	public static Table Union(Table tA, Table tB) {
		Table table = new Table(tA.getSignature());
		table.addAll(tA);
		table.addAll(tB);
		return table;
	}

	public static Table UnionMax(Table tA, Table tB) {
		Table sortedA = sortRecords(tA);
		Table sortedB = sortRecords(tB);

		ListIterator<Record> itA = sortedA.listIterator();
		ListIterator<Record> itB = sortedB.listIterator();

		Table table = new Table(tA.getSignature());

		// contA = false, when itA has no more values
		boolean contA = itA.hasNext();
		// contB = false, when itB has no more values
		boolean contB = itB.hasNext();

		Record a = null;
		Record b = null;
		int comp = 0;
		boolean first = true;

		// continue only when A or B still hold values
		while (contA || contB) {
			if (a == null && b == null && comp == 0) {
				if (contA) {
					a = itA.next();
				}
				if (contB) {
					b = itB.next();
				}
			}
			
			// add the rest of table B to output
			if (!contA && contB) { // A empty, B non-empty
				table.add(b);
				if (itB.hasNext()) {
					contB = itB.hasNext();
					b = itB.next();
					continue;
				} else {
					contB = false;
					continue;
				}
			}

			// add the rest of table A to output
			if (!contB && contA) { // A non-empty, B empty
				table.add(a);
				if (itA.hasNext()) {
					contA = itA.hasNext();
					a = itA.next();
					continue;
				} else {
					contA = false;
					continue;
				}
			}

			// get next a if less than b
			if (comp < 0) {				
				a = itA.next();
			}
			// get next b if less than a
			if (comp > 0) {				
				b = itB.next();
			}
			// get next a and b if they're equal
			if (first == false && comp == 0) {
				a = itA.next();
				b = itB.next();
			}

			comp = a.compareTo(b);

			// a less than b
			if (comp < 0) {
				table.add(a);
				contA = itA.hasNext();
				continue;
			}

			// b less than a
			if (comp > 0) {
				table.add(b);
				contB = itB.hasNext();
				continue;
			}

			// a and b equal
			if (comp == 0) {
				table.add(a);
				contA = itA.hasNext();
				contB = itB.hasNext();
			}
			first = false;
		}
		return table;
	}

	public static Table Difference(Table tA, Table tB) {
		// TODO: better implementation N log(N)
		
		Table sortedA = sortRecords(tA);
		Table sortedB = sortRecords(tB);

		ListIterator<Record> itA = sortedA.listIterator();
		ListIterator<Record> itB = sortedB.listIterator();

		Table table = new Table(tA.getSignature());

		// contA = false, when itA has no more values
		boolean contA = itA.hasNext();
		// contB = false, when itB has no more values
		boolean contB = itB.hasNext();

		Record a = null;
		Record b = null;
		int comp = 0;

		// continue only when A still hold values
		while (contA) {
			// add the rest of table A to output
			if (!contB && contA) { // A non-empty, B empty
				if (itA.hasNext()) {
					a = itA.next();
					table.add(a);
					contA = itA.hasNext();
					continue;
				} else {
					table.add(a);
					contA = false;
					continue;
				}
			}

			// get next a if less than b
			if (comp < 0) {				
				a = itA.next();
			}
			// get next b if less than a
			if (comp > 0) {				
				b = itB.next();
			}
			// get next a and b if they're equal
			if (comp == 0) {
				a = itA.next();
				b = itB.next();
			}

			comp = a.compareTo(b);

			// a less than b
			if (comp < 0) {
				table.add(a);
				contA = itA.hasNext();
				continue;
			}

			// b less than a
			if (comp > 0) {
				//table.add(b);
				contB = itB.hasNext();
				continue;
			}

			// a and b equal
			if (comp == 0) {
				//table.add(a);
				contA = itA.hasNext();
				contB = itB.hasNext();
			}
		}
		return table;
		
		/*Table table = new Table(tA.getSignature());

		Table tempB = new Table(tB.getSignature());
		tempB.addAll(tB);

		for (Record rA : tA) {
			if (!tempB.contains(rA)) {
				table.add(rA);
			} else {
				tempB.remove(rA);
			}
		}
		return table;*/
	}

	public static Table Intersect(Table tA, Table tB) {
		//return Difference(tA,Difference(tA,tB));

		Table sortedA = sortRecords(tA);
		Table sortedB = sortRecords(tB);
		Table table = new Table(tA.getSignature());

		ListIterator<Record> itA = sortedA.listIterator();
		ListIterator<Record> itB = sortedB.listIterator();

		// contA = false, when itA has no more values
		boolean contA = itA.hasNext();
		// contB = false, when itB has no more values
		boolean contB = itB.hasNext();

		Record a = null;
		Record b = null;
		int comp = 0;

		// continue only when A and B still hold values
		while (contA && contB) {
			// get next a if less than b
			if (comp < 0) {				
				a = itA.next();
			}
			// get next b if less than a
			if (comp > 0) {				
				b = itB.next();
			}
			// get next a and b if they're equal
			if (comp == 0) {
				a = itA.next();
				b = itB.next();
			}

			comp = a.compareTo(b);

			// a less than b
			if (comp < 0) {
				contA = itA.hasNext();
				continue;
			}

			// b less than a
			if (comp > 0) {
				contB = itB.hasNext();
				continue;
			}

			// a and b equal
			if (comp == 0) {
				table.add(a);
				contA = itA.hasNext();
				contB = itB.hasNext();
			}
		}

		return table;
	}

	public static Table Product(Table tA, Table tB) {
		List<String> attributes = new ArrayList<>(tA.getSignature().getAttributes());
		attributes.addAll(tB.getSignature().getAttributes());
		List<Type> attributeTypes = new ArrayList<>(tA.getSignature().getTypes());
		attributeTypes.addAll(tB.getSignature().getTypes());

		Signature sig = new BaseSignature(attributes, attributeTypes);
		Table table = new Table(sig);

		List<Column.Type> types = new ArrayList<>();
		types.addAll(tA.getSignature().getTypes());
		types.addAll(tB.getSignature().getTypes());

		for (Record rA : tA) {
			for (Record rB : tB) {
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

	public static Table Eliminate(Table tA) {
		Table sortedA = sortRecords(tA);
		Table table = new Table(tA.getSignature());
		Record prev = null;
		for (Record curr : sortedA) {
			if (curr.equals(prev)) {
				continue;
			}
			table.add(curr);
			prev = curr;
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
		// get the types of the attributes to be projected over
		for (String col : columns) {
			int index = attributesA.indexOf(col);
			indexColumns.add(index);
			types.add(typesA.get(index));
		}
		// create a signature for the resulting relation
		Signature signature = new BaseSignature(columns, types, A.getSignature().isOrdered());

		Table table = new Table(signature);

		// get the values associated with the relevant attributes
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

		// gets comparisons; [Age='16', ID='s001', Name!='Jane']
		List<Comparison> comparisons = condition.getComparisons();
		// gets condition types; [EQUALITY, AND, NOT, EQUALITY, OR, INEQUALITY]
		List<Condition.Type> cond = condition.getConditionTypes();
		System.out.println(cond);
		System.out.println(comparisons);

		for (Record r : sortedA) {
			// valueComparisons holds the comparisons outcomes for record r
			boolean[] valueComparisons = new boolean[comparisons.size()];
			for (int i = 0; i < comparisons.size(); i++) {
				Comparison c = comparisons.get(i);
				Condition.Type type = c.getType();
				List<Term> terms = c.getTerms();
				Term left = terms.get(0);
				Term right = terms.get(1);

				// perform comparison between left and right terms
				if (left.isConstant()) { // right must be attribute
					// index that right appears in table
					int index = sortedA.getSignature().getAttributes().indexOf(right.getValue());
					Float rVal;
					Float termVal;
					switch (type) {
					case EQUALITY:
						if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) { 
							// right has type String
							valueComparisons[i] = r.get(index).toString().equals(left.getValue());
						} else {
							// right is a float
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
				} else if (right.isConstant()) { // left must be attribute
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
				} else { // left and right are attributes
					int indexL = sortedA.getSignature().getAttributes().indexOf(left.getValue());
					int indexR = sortedA.getSignature().getAttributes().indexOf(right.getValue());
					Float rVal;
					Float termVal;
					switch (type) {
					case EQUALITY:
						if (sortedA.getSignature().getTypes().get(indexL).equals(Type.STRING)) {
							valueComparisons[i] = r.get(indexL).toString().equals(r.get(indexR).toString());
						} else {
							rVal = new Float(r.get(indexL).toString());
							termVal = new Float(r.get(indexR).toString());
							valueComparisons[i] = rVal.equals(termVal);
						}
						break;
					case INEQUALITY:
						if (sortedA.getSignature().getTypes().get(indexL).equals(Type.STRING)) {
							valueComparisons[i] = !(r.get(indexL).toString().equals(r.get(indexR).toString()));
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
				// deal with NOT before AND and OR
				for (String s : values) {
					// if s is NOT increase notCounter
					if (!(s.equals("true") || s.equals("false")) && Condition.Type.valueOf(s).equals(Condition.Type.NOT)) {
						notCounter++;
					} else {
						if (s.equals("true") || s.equals("false")) {
							boolean b = Boolean.valueOf(s);
							if (notCounter % 2 == 1) { // if NOT applied an odd number of times, flip value
								b = !b;
								newValues.add(String.valueOf(b));
								notCounter = 0;
								continue;
							} else { // NOT was applied either an even number of times or not at all
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
						// take values either side of current and do AND or OR
						if (c.equals(Condition.Type.AND)) {
							add = add && Boolean.valueOf(newValues.get(i+1));
						} else {
							add = add || Boolean.valueOf(newValues.get(i+1));
						}
					}
				}

				if (add) {
					table.add(r);
				}
			} else { // only one comparison made
				if (valueComparisons[0]) {
					// add record to table if comparison true
					table.add(r);
				}
			}
		}

		return table;
	}
}
