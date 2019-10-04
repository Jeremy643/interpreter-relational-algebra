import java.util.ArrayList;

public class Select extends RAExpr {
	
	private Condition condition;
	private RAExpr expr;
	
	ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Select(Condition condition, RAExpr expr) {
		super(Type.SELECT);
		this.condition = condition;
		this.expr = expr;
		storeExpr();
	}

	public Condition getCondition() {
		return condition;
	}

//	public void setCondition(String condition) {
//		this.condition = condition;
//	}

	private void storeExpr() {
		subExpr.add(expr);
		formExpr(subExpr);
	}
}
