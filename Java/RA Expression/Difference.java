import java.util.ArrayList;
import java.util.Collections;

public class Difference extends BinaryExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Difference(RAExpr left, RAExpr right) {
		super(left, right, Type.DIFFERENCE);
//		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}

	@Override
	public String getConnective() {
		return "DIFFERENCE";
	}
}
