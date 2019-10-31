package uk.ac.ed.inf.s1654170.mrai.exprs;

public class Base extends RAExpr {
	private String name;
	
	public Base(String name) {
		super(Type.BASE);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
