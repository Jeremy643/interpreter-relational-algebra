import java.util.ArrayList;
import java.util.List;

public class Project extends RAExpr {
	private List<String> attributes;
	private RAExpr expr;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Project(RAExpr expr, List<String> attr) {
		super(Type.PROJECT);
		this.attributes = new ArrayList<String>(attr);
		this.expr = expr;
		storeExpr();
	}
	
	public String toString() {
		return String.format("PROJECT%s(%s)", attributes.toString(), expr.toString());
	}
	
	
	private void storeExpr() {
		subExpr.add(expr);
		formExpr(subExpr);
	}
}
