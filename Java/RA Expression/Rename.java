import java.util.ArrayList;

public class Rename extends RAExpr {
	private String attributes;
	private RAExpr relation;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Rename(String attributes, RAExpr relation) {
		super(Type.RENAME);
		this.attributes = attributes;
		this.relation = relation;
		storeExpr();
	}
	
	private void storeExpr() {
		subExpr.add(relation);
		formExpr(subExpr);
	}
}
