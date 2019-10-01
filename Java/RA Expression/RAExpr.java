import java.util.ArrayList;
import java.util.Collections;

abstract class RAExpr {
	enum Type {BASE, PROJECT, SELECT, PRODUCT, UNION, UNION_MAX,
		INTERSECT, DIFFERENCE, ELIMINATE, RENAME}
	
	private Type type;
	private String name;
	private String attribute;
	private String condition;
	private String expr = "";
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttr(String attr) {
		this.attribute = attr;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAttr() {
		return attribute;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setBaseExpr() {
		expr = name;
	}
	
	public void formExpr(ArrayList<RAExpr> subExpr) {
		// The operations that appear at the beginning of an expression
		ArrayList<Type> startOp = new ArrayList<>();
		Collections.addAll(startOp, Type.PROJECT, Type.SELECT, Type.ELIMINATE, Type.RENAME);
		
		// The operations that appear between two base relations
		ArrayList<Type> midOp = new ArrayList<>();
		Collections.addAll(midOp, Type.PRODUCT, Type.UNION, Type.UNION_MAX, Type.INTERSECT, Type.DIFFERENCE);
		
		for (RAExpr e : subExpr) {
			Type eType = e.type;
			int eIndex = subExpr.indexOf(e);
			
			// Puts the operation in the correct place in the expression
			if (startOp.contains(type) && eIndex == 0) {
				switch (type) {
					case PROJECT:
						expr += type.name() + " (" + attribute + ")";
						break;
					case SELECT:
						expr += type.name() + " (" + condition + ")";
						break;
					case RENAME:
						expr += type.name() + " (" + attribute + ")";
						break;
					default:
						expr += type.name();
				}
			}
			
			if (midOp.contains(type) && eIndex == 1) {
				expr += type.name();
			}
			
			// Brackets not included if e has type BASE, otherwise brackets are included
			switch (eType) {
				case BASE:
					if (eIndex < subExpr.size()-1) {
						expr += e.expr + " ";
					} else {
						expr += " " + e.expr;
					}
					break;
				default:
					if (eIndex < subExpr.size()-1) {
						expr += "(" + e.expr + ") ";
					} else {
						expr += " (" + e.expr + ")";
					}
			}
		}
	}
	
	public String printExpr() {
		return expr;
	}
}
