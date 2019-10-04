import java.util.ArrayList;
import java.util.Collections;

public class Union extends BinaryExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public Union(RAExpr left, RAExpr right) {
		super(left, right, Type.UNION);
//		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}

	@Override
	public String getConnective() {
		return "UNION";
	}
}
