import java.util.ArrayList;
import java.util.Collections;

public class Union extends RAExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Union(RAExpr left, RAExpr right) {
		leftExpr = left;
		rightExpr = right;
		setType(Type.UNION);
		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}
}
