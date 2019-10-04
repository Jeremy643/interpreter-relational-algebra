public abstract class Condition {

	enum Type {EQUALITY, AND, OR, NOT}
	
	private Type type;
	
	protected Condition (Type type) {
		this.type = type;
	}
}
