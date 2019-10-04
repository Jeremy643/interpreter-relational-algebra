import java.util.ArrayList;
import java.util.Collections;

public class UnionMax extends BinaryExpr {
	private RAExpr leftExpr;
	private RAExpr rightExpr;
	
	private ArrayList<RAExpr> subExpr = new ArrayList<>();
	
	public UnionMax(RAExpr left, RAExpr right) {		
		super(left, right, Type.UNION_MAX);
//		storeExpr();
	}
	
	private void storeExpr() {
		Collections.addAll(subExpr, leftExpr, rightExpr);
		formExpr(subExpr);
	}

	@Override
	public String getConnective() {
		return "UMAX";
	}
}
