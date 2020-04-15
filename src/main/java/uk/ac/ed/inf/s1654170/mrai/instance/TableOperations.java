package uk.ac.ed.inf.s1654170.mrai.instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
	
	private static Table matchOrder(Table A, Table B) {
		// swap the columns of B to match the order of columns in A
		List<String> attrA = new ArrayList<>(A.getSignature().getAttributes());
		List<String> attrB = new ArrayList<>(B.getSignature().getAttributes());
		List<Integer> indices = new ArrayList<>();
		List<Integer> indicesBU = new ArrayList<>();
		for (int i = 0; i < attrB.size(); i++) {
			// get the relevant position in B for each position 1 to A.size()
			int index = attrA.indexOf(attrB.get(i));
			if (index == i) {
				indices.add(-1);
				indicesBU.add(-1);
			} else {
				indices.add(index);
				indicesBU.add(index);
			}
		}
		for (int i = 0; i < B.size(); i++) {
			int counter = 0;
			indices.clear();
			for (int x : indicesBU) {
				indices.add(x);
			}
			for (int index : indices) {
				if (indices.contains(-1)) {
					if (index == -1 || index < counter) {
						/*
						 * index = -1 - the column in B is already in the right position
						 * index < counter - stops double swapping
						 */
						counter++;
						continue;
					} else {
						Collections.swap(B.get(i), counter, index);
					}
				} else {
					Collections.swap(B.get(i), counter, index);
					Collections.swap(indices, counter, index);
				}
				counter++;
			}
		}
		Signature sig = new BaseSignature(attrA, A.getSignature().getTypes(), A.getSignature().isOrdered());
		Table newB = new Table(sig, A.getBags());
		newB.addAll(B);
		return newB;
	}

	public static Table Union(Table tA, Table tB) {
		Table newB = new Table(tB.getSignature(), tB.getBags());
		for (Record r : tB) {
			newB.add((Record) r.clone());
		}
		if (!tA.getSignature().isOrdered()) {
			//unordered - swap columns in tB to match tA
			newB = matchOrder(tA, newB);
		}
		Table table = new Table(tA.getSignature(), tA.getBags());
		table.addAll(tA);
		table.addAll(newB);
		if (tA.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}

	public static Table UnionMax(Table tA, Table tB) {
		Table tempA = new Table(tA.getSignature(), tA.getBags());
		tempA.addAll(tA);
		Table tempB = new Table(tB.getSignature(), tB.getBags());
		for (Record r : tB) {
			tempB.add((Record) r.clone());
		}
		if (!tA.getSignature().isOrdered()) {
			//unordered - swap columns in tB to match tA
			tempB = matchOrder(tA, tempB);
		}
		Table sortedA = sortRecords(tempA);
		Table sortedB = sortRecords(tempB);

		ListIterator<Record> itA = sortedA.listIterator();
		ListIterator<Record> itB = sortedB.listIterator();

		Table table = new Table(tA.getSignature(), tA.getBags());

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
		if (tA.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}

	public static Table Difference(Table tA, Table tB) {
		Table tempA = new Table(tA.getSignature(), tA.getBags());
		tempA.addAll(tA);
		Table tempB = new Table(tB.getSignature(), tB.getBags());
		for (Record r : tB) {
			tempB.add((Record) r.clone());
		}
		if (!tA.getSignature().isOrdered()) {
			//unordered - swap columns in tB to match tA
			tempB = matchOrder(tA, tempB);
		}
		Table sortedA = sortRecords(tempA);
		Table sortedB = sortRecords(tempB);

		ListIterator<Record> itA = sortedA.listIterator();
		ListIterator<Record> itB = sortedB.listIterator();

		Table table = new Table(tA.getSignature(), tA.getBags());

		// contA = false, when itA has no more values
		boolean contA = itA.hasNext();
		// contB = false, when itB has no more values
		boolean contB = itB.hasNext();

		Record a = null;
		Record b = null;
		int comp = 0;
		boolean first = true;

		// continue only when A or B still hold values
		while (contA) {
			if (a == null && b == null && comp == 0) {
				if (contA) {
					a = itA.next();
				}
				if (contB) {
					b = itB.next();
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
				contB = itB.hasNext();
				continue;
			}

			// a and b equal
			if (comp == 0) {
				contA = itA.hasNext();
				contB = itB.hasNext();
			}
			first = false;
		}
		if (tA.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}

	public static Table Intersect(Table tA, Table tB) {
		Table tempA = new Table(tA.getSignature(), tA.getBags());
		tempA.addAll(tA);
		Table tempB = new Table(tB.getSignature(), tB.getBags());
		for (Record r : tB) {
			tempB.add((Record) r.clone());
		}
		if (!tA.getSignature().isOrdered()) {
			//unordered - swap columns in tB to match tA
			tempB = matchOrder(tA, tempB);
		}
		Table sortedA = sortRecords(tempA);
		Table sortedB = sortRecords(tempB);
		Table table = new Table(tA.getSignature(), tA.getBags());

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
		if (tA.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}

	public static Table Product(Table tA, Table tB) {
		List<String> attributes = new ArrayList<>(tA.getSignature().getAttributes());
		attributes.addAll(tB.getSignature().getAttributes());
		List<Column.Type> attributeTypes = new ArrayList<>(tA.getSignature().getTypes());
		attributeTypes.addAll(tB.getSignature().getTypes());

		Signature sig = new BaseSignature(attributes, attributeTypes, tA.getSignature().isOrdered());
		Table table = new Table(sig, tA.getBags());

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

				Record rNew = Record.valueOf(attributeTypes, values);
				table.add(rNew);
			}
		}
		if (tA.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}

	public static Table Eliminate(Table tA) {
		Table tempA = new Table(tA.getSignature(), tA.getBags());
		for (Record r : tA) {
			tempA.add((Record) r.clone());
		}
		Set<Record> tempASet = new HashSet<>(tempA);
		Table table = new Table(tA.getSignature(), tA.getBags());
		table.addAll(tempASet);
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

		Signature signature = new BaseSignature(attributesTable, typesA, A.getSignature().isOrdered());
		Table table = new Table(signature, A.getBags());

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

		Table table = new Table(signature, A.getBags());

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

	public static Table Select(Condition condition, Table A, Stack<Condition> binaryCondTypes) {
		Table tempA = new Table(A.getSignature(), A.getBags());
		tempA.addAll(A);
		Table sortedA = sortRecords(tempA);
		Table table = new Table(A.getSignature(), A.getBags());

		// gets comparisons, for example: [Age='16', ID='s001', Name!='Jane']
		List<Comparison> comparisons = condition.getComparisons();
		// gets condition types, for example: [EQUALITY, AND, NOT, EQUALITY, OR, INEQUALITY]
		List<Condition.Type> cond = condition.getConditionTypes();
		
		int counter = binaryCondTypes.size();
		Condition[] condTypes = new Condition[counter];
		for (int i = counter-1; i >= 0; i--) {
			condTypes[i] = binaryCondTypes.pop();
		}

		for (Record r : sortedA) {
			// valueComparisons holds the comparisons outcomes for record r
			Map<String,Boolean> conditionComp = new HashMap<>();
			for (int i = 0; i < comparisons.size(); i++) {
				Comparison c = comparisons.get(i);
				Condition.Type type = c.getType();
				List<Term> terms = c.getTerms();
				Term left = terms.get(0);
				Term right = terms.get(1);
				boolean comp = false;

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
							comp = r.get(index).toString().equals(left.getValue());
							conditionComp.put(c.toString(), comp);
						} else {
							// right is a float
							rVal = Float.valueOf(r.get(index).toString());
							termVal = Float.valueOf(left.getValue());
							comp = rVal.equals(termVal);
							conditionComp.put(c.toString(), comp);
						}
						break;
					case INEQUALITY:
						if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
							comp = !(r.get(index).toString().equals(left.getValue()));
							conditionComp.put(c.toString(), comp);
						} else {
							rVal = Float.valueOf(r.get(index).toString());
							termVal = Float.valueOf(left.getValue());
							comp = !(rVal.equals(termVal));
							conditionComp.put(c.toString(), comp);
						}
						break;
					case LESS:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(left.getValue());
						comp = rVal > termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case LESS_EQUAL:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(left.getValue());
						comp = rVal >= termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(left.getValue());
						comp = rVal < termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER_EQUAL:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(left.getValue());
						comp = rVal <= termVal;
						conditionComp.put(c.toString(), comp);
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
							comp = r.get(index).toString().equals(right.getValue());
							conditionComp.put(c.toString(), comp);
						} else {
							rVal = Float.valueOf(r.get(index).toString());
							termVal = Float.valueOf(right.getValue());
							comp = rVal.equals(termVal);
							conditionComp.put(c.toString(), comp);
						}
						break;
					case INEQUALITY:
						if (sortedA.getSignature().getTypes().get(index).equals(Type.STRING)) {
							comp = !(r.get(index).toString().equals(right.getValue()));
							conditionComp.put(c.toString(), comp);
						} else {
							rVal = Float.valueOf(r.get(index).toString());
							termVal = Float.valueOf(right.getValue());
							comp = !(rVal.equals(termVal));
							conditionComp.put(c.toString(), comp);
						}
						break;
					case LESS:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(right.getValue());
						comp = rVal < termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case LESS_EQUAL:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(right.getValue());
						comp = rVal <= termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(right.getValue());
						comp = rVal > termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER_EQUAL:
						rVal = Float.valueOf(r.get(index).toString());
						termVal = Float.valueOf(right.getValue());
						comp = rVal >= termVal;
						conditionComp.put(c.toString(), comp);
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
							comp = r.get(indexL).toString().equals(r.get(indexR).toString());
							conditionComp.put(c.toString(), comp);
						} else {
							rVal = Float.valueOf(r.get(indexL).toString());
							termVal = Float.valueOf(r.get(indexR).toString());
							comp = rVal.equals(termVal);
							conditionComp.put(c.toString(), comp);
						}
						break;
					case INEQUALITY:
						if (sortedA.getSignature().getTypes().get(indexL).equals(Type.STRING)) {
							comp = !(r.get(indexL).toString().equals(r.get(indexR).toString()));
							conditionComp.put(c.toString(), comp);
						} else {
							rVal = Float.valueOf(r.get(indexL).toString());
							termVal = Float.valueOf(r.get(indexR).toString());
							comp = !(rVal.equals(termVal));
							conditionComp.put(c.toString(), comp);
						}
						break;
					case LESS:
						rVal = Float.valueOf(r.get(indexL).toString());
						termVal = Float.valueOf(r.get(indexR).toString());
						comp = rVal < termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case LESS_EQUAL:
						rVal = Float.valueOf(r.get(indexL).toString());
						termVal = Float.valueOf(r.get(indexR).toString());
						comp = rVal <= termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER:
						rVal = Float.valueOf(r.get(indexL).toString());
						termVal = Float.valueOf(r.get(indexR).toString());
						comp = rVal > termVal;
						conditionComp.put(c.toString(), comp);
						break;
					case GREATER_EQUAL:
						rVal = Float.valueOf(r.get(indexL).toString());
						termVal = Float.valueOf(r.get(indexR).toString());
						comp = rVal >= termVal;
						conditionComp.put(c.toString(), comp);
						break;
					default:
						throw new RuntimeException("This is not a comparison operator");
					}
				}
			}
			
			if (cond.size() > 1) {
				Condition finalCond = null;
				for (Condition t : condTypes) {
					boolean left;
					boolean right;
					boolean result;
					finalCond = t;
					switch (t.getType()) {
					case AND:
						left = conditionComp.get(t.getCondition().get(0).toString());
						right = conditionComp.get(t.getCondition().get(1).toString());
						result = left && right;
						conditionComp.put(t.toString(), result);
						break;
					case OR:
						left = conditionComp.get(t.getCondition().get(0).toString());
						right = conditionComp.get(t.getCondition().get(1).toString());
						result = left || right;
						conditionComp.put(t.toString(), result);
						break;
					case NOT:
						right = conditionComp.get(t.getCondition().get(0).toString());
						result = !right;
						conditionComp.put(t.toString(), result);
						break;
					default:
						break;
					}
				}
				if (conditionComp.get(finalCond.toString())) {
					table.add(r);
				}
			} else {
				if (conditionComp.get(comparisons.get(0).toString())) {
					// add record to table if comparison true
					table.add(r);
				}
			}
			
			conditionComp.clear();
		}
		if (A.getBags()) {
			return table;
		} else {
			Set<Record> recordSet = new HashSet<>(table);
			table.clear();
			table.addAll(recordSet);
			return table;
		}
	}
}