package datastructures;

public class IsiVariable extends IsiSymbol{
	public static final int INT    = 0;
	public static final int REAL   = 1;
	public static final int TEXT   = 2;
	
	private int type;
	private String value;
	private boolean used;

	public IsiVariable(String name, int type, String value, boolean used) {
		super(name);
		this.type = type;
		this.value = value;
		this.used = used;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
	@Override
	public String toString() {
		return "IsiVariable [name="+name+", type=" + type + ", value=" + value + ", used=" + used + "]";
	}

	public String generateJavaCode() {
		String str;
		
		if (type == INT) {
			str = "		int ";
		}
		else if (type == REAL) {
			str = "		double ";
		}
		else {
			str = "		String ";
		}
		
		return str + " " +super.name+ ";";
	}
	
	public String generatePythonCode() {
		return "";
	}
}
