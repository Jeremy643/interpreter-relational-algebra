
public class Base extends RAExpr {
	private String name;
	
	public Base(String name) {
		this.name = name;
		setType(Type.BASE);
		setName(this.name);
		setBaseExpr();
	}
	
	public String getName() {
		return name;
	}
}
