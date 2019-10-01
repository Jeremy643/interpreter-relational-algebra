import java.util.ArrayList;
import java.util.Collections;

public class UnionMax extends RAExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public UnionMax(RAExpr left, RAExpr right) {
		leftExpr = left;
		rightExpr = right;
		setType(Type.UNION_MAX);
		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}
}
