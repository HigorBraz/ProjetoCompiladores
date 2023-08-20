package datastructures;

public abstract class IsiSymbol {

	protected String name;
	
	public abstract String generateJavaCode();
	public abstract String generatePythonCode();
	
	public IsiSymbol(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "IsiSymbol [name=" + name + "]";
	}

	public abstract int getType();
	
	public abstract boolean isUsed();
	
	public abstract void setValue(String value);
	
	public abstract void setUsed(boolean used);
	
	public abstract String getValue();
}
