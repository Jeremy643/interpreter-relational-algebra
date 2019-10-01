import java.util.ArrayList;

public class Rename extends RAExpr {
	private String attributes;
	private RAExpr relation;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Rename(String attributes, RAExpr relation) {
		this.attributes = attributes;
		this.relation = relation;
		setType(Type.RENAME);
		setAttr(this.attributes);
		storeExpr();
	}
	
	private void storeExpr() {
		subExpr.add(relation);
		formExpr(subExpr);
	}
}
