package ast;

import java.util.ArrayList;

public class CommandWhile extends AbstractCommand{

	private String condition;
	private ArrayList<AbstractCommand> listaEnquanto;
	
	
	public CommandWhile(String condition, ArrayList<AbstractCommand> le) {
		this.condition = condition;
		this.listaEnquanto = le;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("		while ("+condition+") {\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generateJavaCode());
		}
		str.append("		}\n");
		return str.toString();
	}
	
	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+"):\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generatePythonCode());
		}
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandWhile [condition=" + condition + ", listaEnquanto=" + listaEnquanto + "]";
	}


	
}