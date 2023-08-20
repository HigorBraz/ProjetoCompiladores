package ast;

import datastructures.IsiVariable;

public class CommandLeitura extends AbstractCommand{
	
	private String id;
	private IsiVariable var;
	
	public CommandLeitura(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}

	@Override
	public String generateJavaCode() {
		String str = "";
		if (var.getType()==IsiVariable.INT) {
			str = "nextInt();";
		}
		else if (var.getType()==IsiVariable.REAL) {
			str = "nextDouble();";
		}
		else {
			str = "nextLine();";
		}
		
		return "		"+id + " = sc."+str;
	}
	
	@Override
	public String generatePythonCode() {
		String str = "";
		if (var.getType()==IsiVariable.INT) {
			str = "int(input())";
		}
		else if (var.getType()==IsiVariable.REAL) {
			str = "double(input())";
		}
		else {
			str = "input()";
		}
		
		return id + " ="+str;
	}

	@Override
	public String toString() {
		return "CommandLeitura [id=" + id + "]";
	}
	
}
