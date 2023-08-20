package ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import datastructures.IsiSymbol;
import datastructures.IsiVariable;
import datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
	
	public void generateTarget() {
		generateJavaCode();
		generatePythonCode();
		
		
	}
	
	public void generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n\n");
		str.append("public class codigoJava{ \n");
		str.append("	public static void main(String args[]){ \n");
		str.append("		Scanner sc = new Scanner(System.in);\n");
		
		for (IsiSymbol symbol: varTable.getAll()){
			str.append(symbol.generateJavaCode()+"\n");
		}
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode()+"\n");
		}
		
		str.append("	}\n");
		str.append("}\n");
		
		try {
			FileWriter frJava = new FileWriter(new File("codigoJava.java"));
			frJava.write(str.toString());
			frJava.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void generatePythonCode() {
		StringBuilder str = new StringBuilder();
		
		for (IsiSymbol symbol: varTable.getAll()){
			str.append(symbol.generatePythonCode());
		}
		for (AbstractCommand command: comandos) {
			str.append(command.generatePythonCode()+"\n");
		}
		
		try {
			FileWriter frPython = new FileWriter(new File("codigoPython.py"));
			frPython.write(str.toString());
			frPython.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public IsiSymbolTable getVartable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable vartable) {
		this.varTable = vartable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
