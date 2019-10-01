import java.util.ArrayList;
import java.util.Collections;

public class Product extends RAExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Product(RAExpr left, RAExpr right) {
		leftExpr = left;
		rightExpr = right;
		setType(Type.PRODUCT);
		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}
}
