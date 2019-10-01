import java.util.ArrayList;
import java.util.Collections;

public class Difference extends RAExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Difference(RAExpr left, RAExpr right) {
		leftExpr = left;
		rightExpr = right;
		setType(Type.DIFFERENCE);
		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}
}
