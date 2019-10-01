import java.util.ArrayList;

public class Project extends RAExpr {
	private String attributes;
	private RAExpr expr;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Project(String attributes, RAExpr expr) {
		this.attributes = attributes;
		this.expr = expr;
		setType(Type.PROJECT);
		setAttr(this.attributes);
		storeExpr();
	}
	
	private void storeExpr() {
		subExpr.add(expr);
		formExpr(subExpr);
	}
}
