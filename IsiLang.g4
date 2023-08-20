grammar IsiLang;

@header {
	import datastructures.IsiSymbol;
	import datastructures.IsiSymbolTable;
	import datastructures.IsiVariable;
	import exceptions.IsiSemanticException;
	import ast.AbstractCommand;
	import ast.CommandLeitura;
	import ast.CommandEscrita;
	import ast.CommandAtribuicao;
	import ast.CommandDecisao;
	import ast.CommandDoWhile;
	import ast.CommandWhile;
	import ast.CommandFor;
	import ast.IsiProgram;
	import java.util.ArrayList;
	import java.util.Stack;	
}

@members{
	private int _tipo;
	private int leftDT;
	private int rightDT;
	private int aux = 0;
	
	private String _varName;
	private String _varValue;
	private String _varTipo;
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprWhile;
	private String _oprlog;
	
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	
	private IsiProgram program = new IsiProgram();
	
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	
	private ArrayList<AbstractCommand> curThread;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse = new ArrayList<AbstractCommand>();
	private ArrayList<AbstractCommand> listaEnquanto;
	
	public void verificaID(String id) {
		if(!symbolTable.exists(id)) {
			throw new IsiSemanticException("Simbolo '" +id + "' não foi declarado!");
		}
	}
	
	public void verificaDeclID(String id) {
		if (!symbolTable.exists(_varName)) {
			symbolTable.add(symbol);
		}
		else {
			throw new IsiSemanticException("Simbolo '" +_varName + "' já foi declarado previamente!");
		}
	}
	
	public void exibeComandos() {
		for (AbstractCommand c: program.getComandos()) {
			System.out.println(c);
		}
	}
	
	public void generateCode() {
		program.generateTarget();
	}
	
	//retorna a string do type
	public String returnType(int type) {
		if (type == 0) {
			return "int";
		}
		
		if (type == 1) {
			return "real";
		}
		else {
			return "String";
		}
	}
	
	// Verifica se os types sao iguais
	public void verificaDT(int leftDT, int rightDT, String varName, int func) {
		// caso func == 1 trata int e real como sendo iguais
		if (func == 0) {
			if (leftDT != rightDT) {
				throw new IsiSemanticException("Tipo incorreto: esperado '"+returnType(leftDT)+"' mas '"+varName+"' possui tipo '"+returnType(rightDT)+"'");
			}	
		}
		else {
			if(leftDT == 0) {
				leftDT = 1;
			}
			if(rightDT == 0) {
				rightDT = 1;
			}
			if (leftDT != rightDT) {
				throw new IsiSemanticException("Tipo incorreto: esperado '"+returnType(leftDT)+"' mas '"+varName+"' possui tipo '"+returnType(rightDT)+"'");
			}
		}	
	}
	
	// verifica se a var possui valor diferente de null
	public void verificaValue(String id) {
		IsiVariable var = (IsiVariable)symbolTable.get(id);
		if (var.getValue() == null) {
			throw new IsiSemanticException("Variavel '"+id+"' não possui valor!");
		}
	}
	
	// verifica quais var nao estao sendo utilizadas
	public void notUsed() {
		ArrayList<IsiSymbol> lista = symbolTable.getAll();
		for(IsiSymbol var: lista) {
				if (!var.isUsed()) {
					System.out.println("A variavel '"+var.getName()+"' não está sendo utilizada!");
				}
			}
	}
	
	public String getOprLogico(String oprlog) {
		if (oprlog.equals("and")) {
			return "&&";
		}
		else {
			return "||";
		}
	}
}



prog : 'programa' bloco 'fimprog.' 
		{
			program.setVarTable(symbolTable);
			program.setComandos(stack.pop());
			notUsed();
		}
		;

bloco : {
			curThread = new ArrayList<AbstractCommand>();
			stack.push(curThread);
		}
		(cmd)+
		;

declaravar : tipo
			 ID { 
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue, false);
					verificaDeclID(_varName);
				} 
			 (VIR
			 ID { 
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue, false);
					verificaDeclID(_varName);
				 } 
			 )* 
			 PF
			 ;

tipo : 'int'  	{ _tipo = IsiVariable.INT; } 
	| 'real'    { _tipo = IsiVariable.REAL; } 
	| 'String' 	{ _tipo = IsiVariable.TEXT; }
	;

cmd : cmdleitura | cmdescrita | cmdattrib | cmdif | cmddo | cmdwhile | cmddecl | cmdfor ;

cmddecl : (declaravar)+ ;

cmdleitura : 'leia' AP
					ID 
						{ 
							verificaID(_input.LT(-1).getText());
					 	 	_readID = _input.LT(-1).getText();
					   	}
					FP
					SC 
					   {
					   		symbolTable.get(_readID).setValue(" ");
					   		symbolTable.get(_readID).setUsed(true); 
					   		IsiVariable var = (IsiVariable)symbolTable.get(_readID);
					    	CommandLeitura cmd = new CommandLeitura(_readID, var);
					    	stack.peek().add(cmd);
					   }
					; 

cmdescrita : 'escreva' AP
					   ( ID 
					   		{
					   			verificaID(_input.LT(-1).getText());
					   	 		_writeID = _input.LT(-1).getText();
					   	 		symbolTable.get(_writeID).setUsed(true);
					   		}
					   	 |	TEXTO { _writeID = _input.LT(-1).getText(); }
					   	 |	number { _writeID = _input.LT(-1).getText(); }
					   )
					   FP 
					   SC 
					   		{ 
					   			CommandEscrita cmd = new CommandEscrita(_writeID);
					   			stack.peek().add(cmd);
					   		}
					   ;

cmdattrib : ID 
				{
					verificaID(_input.LT(-1).getText()); 
			  		_exprID = _input.LT(-1).getText();
			  		leftDT = symbolTable.get(_exprID).getType();
			  		symbolTable.get(_exprID).setUsed(true);
				} 
			ATTR 	{_exprContent = ""; }
			expr 
			SC 
				{
					symbolTable.get(_exprID).setValue(_exprContent);
					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
					stack.peek().add(cmd);
				}
			;

cmdif: 'if' AP 		{_exprContent = ""; }
		    expr	
		    OPREL	{ _exprContent += _input.LT(-1).getText(); }
		    expr
		    (
		  	OPLOG		
		  			{
		  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
		  			}
		  	expr
		  	OPREL		{ _exprContent += _input.LT(-1).getText(); }
		  	expr
		  	)*
		    FP 
		    ACH 
		  			{
		  				curThread = new ArrayList<AbstractCommand>();
		  				stack.push(curThread);
		  			}
		    (cmd)+ 
		    FCH 	{ listaTrue = stack.pop(); }
	        ('else' 
	  	    ACH 
					{
						curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					}
			(cmd)+ 
			FCH		{ listaFalse = stack.pop(); }
			)? 
					{
						CommandDecisao cmd = new CommandDecisao(_exprContent, listaTrue, listaFalse);
						stack.peek().add(cmd);
					}
			;


cmddo : 	'do' 
			ACH 
				{
					curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
				}
			(cmd)+ 
			FCH 
			'while'
			AP		{ _exprContent = ""; }
		  	expr
		  	OPREL	{ _exprContent += _input.LT(-1).getText(); }
		  	expr
		  	(
		  	OPLOG		
		  			{
		  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
		  			}
		  	expr
		  	OPREL		{ _exprContent += _input.LT(-1).getText(); }
		  	expr
		  	)*
		  	FP
		  	SC
		  			{   	
		  				listaEnquanto = stack.pop();
		  				CommandDoWhile cmd = new CommandDoWhile(_exprContent, listaEnquanto);
						stack.peek().add(cmd);
		  			}
		  	;
		  	
cmdwhile :	'while'
			AP			
					{ 
					aux = 1;
					_exprContent = "";
					}
		  	expr
		  	OPREL		{ _exprContent += _input.LT(-1).getText(); }
		  	expr
		  	(
		  	OPLOG		
		  			{
		  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
		  			}
		  	expr
		  	OPREL		{ _exprContent += _input.LT(-1).getText(); }
		  	expr
		  	)*
		  	FP
		  	ACH
		  		{
					curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
                    _exprWhile = _exprContent;
				}
		  	(cmd)+ 
		  	FCH
		  		{   
		  			listaEnquanto = stack.pop();
		  			CommandWhile cmd = new CommandWhile(_exprWhile, listaEnquanto);
					stack.peek().add(cmd);
					aux = 0;
		  		}
			;
			
cmdfor : 'for'
		 AP		
		 		{ 
		 		_exprContent = ""; 
		 		_tipo = 0;
		 		leftDT = 0;
				curThread = new ArrayList<AbstractCommand>(); 
           		 stack.push(curThread);
		 		}
		 ID		
		 		{ 
		 		_varName = _input.LT(-1).getText();
		 		_exprContent += _varName;
		 		if (symbolTable.exists(_input.LT(-1).getText())) {
		 			throw new IsiSemanticException("Simbolo " +_varName + " nao foi declarado!");
		 		}
		 	
		 		symbol = new IsiVariable(_varName, _tipo, _varName, true);
				symbolTable.add(symbol);  
		 		}
		 ATTR		{ _exprContent += "="; }
		 number		{ _exprContent += _input.LT(-1).getText(); } 
		 SC			{ _exprContent += ";"; }
		 expr	
		 OPREL 		{ _exprContent += _input.LT(-1).getText(); }
		 expr
		 SC			{ _exprContent += ";"; }
		 ID		
		 			{ 
		 				_exprContent += _input.LT(-1).getText();
		 				verificaID(_input.LT(-1).getText());
		 				symbolTable.get(_input.LT(-1).getText()).setUsed(true);
		 			}
		 INC		{ _exprContent += _input.LT(-1).getText(); }
		 FP
		 ACH
		 (cmd)+
		 FCH
		 		{   
		 			symbolTable.remove(_varName);	
	  				listaEnquanto = stack.pop();
	  				CommandFor cmd = new CommandFor(_exprContent, listaEnquanto);
					stack.peek().add(cmd);
		 		}
		 ;

expr :  termo 
		( 
		OP { _exprContent += _input.LT(-1).getText(); } 
		termo 
		)*
		;

termo : AP 		{ _exprContent += '('; }
		expr 
		FP 		{ _exprContent += ')'; }
		| ID 
				{
					verificaID(_input.LT(-1).getText());
					verificaValue(_input.LT(-1).getText());
			 		_exprContent += _input.LT(-1).getText();
			 		rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
			 		verificaDT(leftDT, rightDT, _input.LT(-1).getText(), aux);
			 		symbolTable.get(_input.LT(-1).getText()).setUsed(true);
		   		} 
		| number
				{ 
					_exprContent += _input.LT(-1).getText(); 
					verificaDT(leftDT, _tipo, _input.LT(-1).getText(), aux);
				}
		| TEXTO
				{ 
					_exprContent += _input.LT(-1).getText(); 
					verificaDT(leftDT, 2, _input.LT(-1).getText(), aux);
				}
		;

AP : '(' ;
FP : ')' ;
SC : ';' ;
PF : '.' ;
VIR : ',' ;
ACH : '{' ;
FCH : '}' ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!=' ;

OP : '+' | '-' | '*' | '/' ;

OPLOG : 'or' | 'and' ;

ATTR : '=' ;

INC : '++' | '--' ;

ID : [a-z] ([a-z] | [A-Z] | [0-9] )* ;

number :   INT 	{ _tipo = IsiVariable.INT; } 
		 | REAL { _tipo = IsiVariable.REAL; }  
		 ;

REAL : [0-9]+ PF [0-9]+ ;

INT : [0-9]+ ;

TEXTO : '"' ([a-z]|[A-Z]|[0-9]|' '|'\t'|'!'|'-'|':'|';'|','|'.'|'['|']')* '"' ;

WS : (' ' | '\n' | '\t' | '\r') -> skip;