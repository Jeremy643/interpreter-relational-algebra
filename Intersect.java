import java.util.ArrayList;
import java.util.Collections;

public class Intersect extends RAExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Intersect(RAExpr left, RAExpr right) {
		leftExpr = left;
		rightExpr = right;
		setType(Type.INTERSECT);
		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}
}
