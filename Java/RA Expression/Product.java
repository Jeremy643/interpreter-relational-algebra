import java.util.ArrayList;
import java.util.Collections;

public class Product extends BinaryExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Product(RAExpr left, RAExpr right) {
		super(left, right, Type.PRODUCT);
//		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}

	@Override
	public String getConnective() {
		return "CROSS";
	}
}
