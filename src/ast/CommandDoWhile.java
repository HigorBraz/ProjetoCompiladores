package ast;

import java.util.ArrayList;

public class CommandDoWhile extends AbstractCommand{

	private String condition;
	private ArrayList<AbstractCommand> listaEnquanto;
	
	
	public CommandDoWhile(String condition, ArrayList<AbstractCommand> le) {
		this.condition = condition;
		this.listaEnquanto = le;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("		do {\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generateJavaCode()+"\n");
		}
		str.append("		}\n");
		str.append("		while ("+condition+");\n");
		return str.toString();
	}
	
	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		str.append("while True:\n");
		for (AbstractCommand cmd: listaEnquanto) {
			str.append("	"+cmd.generatePythonCode()+"\n");
		}
		str.append("	if not "+condition+":\n");
		str.append("		break\n");
		return str.toString();
	}

	@Override
	public String toString() {
		return "CommandDoWhile [condition=" + condition + ", listaEnquanto=" + listaEnquanto + "]";
	}
	
	
}
