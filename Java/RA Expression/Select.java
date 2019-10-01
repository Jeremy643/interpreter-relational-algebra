import java.util.ArrayList;

public class Select extends RAExpr {
	private String condition;
	private RAExpr expr;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Select(String condition, RAExpr expr) {
		this.condition = condition;
		this.expr = expr;
		setType(Type.SELECT);
		setCondition(this.condition);
		storeExpr();
	}
	
	private void storeExpr() {
		subExpr.add(expr);
		formExpr(subExpr);
	}
}
