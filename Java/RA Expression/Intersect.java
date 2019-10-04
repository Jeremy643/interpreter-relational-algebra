import java.util.ArrayList;
import java.util.Collections;

public class Intersect extends BinaryExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Intersect(RAExpr left, RAExpr right) {
		super(left, right, Type.INTERSECT);
//		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}

	@Override
	public String getConnective() {
		return "INTERSECT";
	}
}
