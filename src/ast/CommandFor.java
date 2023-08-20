package ast;

import java.util.ArrayList;

public class CommandFor extends AbstractCommand{

	private String condition;
	private ArrayList<AbstractCommand> listaEnquanto;
	private int min;
	private int max;
	
	
	public CommandFor(String condition, ArrayList<AbstractCommand> le) {
		this.condition = condition;
		this.listaEnquanto = le;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("		for (int "+condition+") {\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generateJavaCode());
		}
		str.append("		}\n");
		return str.toString();
	}
	
	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		str.append("for i in range(5):\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generatePythonCode());
		}
		return str.toString();
	}


	@Override
	public String toString() {
		return "CommandFor [condition=" + condition + ", listaEnquanto=" + listaEnquanto + "]";
	}
	
}