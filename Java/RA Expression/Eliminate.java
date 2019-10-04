import java.util.ArrayList;

public class Eliminate extends RAExpr {
	private RAExpr expr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Eliminate(RAExpr expr) {
		super(Type.ELIMINATE);
		this.expr = expr;
		storeExpr();
	}
	
	private void storeExpr() {
		subExpr.add(expr);
		formExpr(subExpr);
	}
}
