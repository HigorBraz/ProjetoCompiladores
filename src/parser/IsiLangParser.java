// Generated from IsiLang.g4 by ANTLR 4.7.1
package parser;

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
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, PF=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, OP=21, OPLOG=22, ATTR=23, INC=24, ID=25, REAL=26, INT=27, 
		TEXTO=28, WS=29;
	public static final int
		RULE_prog = 0, RULE_bloco = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_cmd = 4, 
		RULE_cmddecl = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdif = 9, RULE_cmddo = 10, RULE_cmdwhile = 11, RULE_cmdfor = 12, 
		RULE_expr = 13, RULE_termo = 14, RULE_number = 15;
	public static final String[] ruleNames = {
		"prog", "bloco", "declaravar", "tipo", "cmd", "cmddecl", "cmdleitura", 
		"cmdescrita", "cmdattrib", "cmdif", "cmddo", "cmdwhile", "cmdfor", "expr", 
		"termo", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'int'", "'real'", "'String'", "'leia'", 
		"'escreva'", "'if'", "'else'", "'do'", "'while'", "'for'", "'('", "')'", 
		"';'", "'.'", "','", "'{'", "'}'", null, null, null, "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AP", "FP", "SC", "PF", "VIR", "ACH", "FCH", "OPREL", "OP", "OPLOG", 
		"ATTR", "INC", "ID", "REAL", "INT", "TEXTO", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(33);
			bloco();
			setState(34);
			match(T__1);

						program.setVarTable(symbolTable);
						program.setComandos(stack.pop());
						notUsed();
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

						curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				cmd();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode PF() { return getToken(IsiLangParser.PF, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			tipo();
			setState(44);
			match(ID);
			 
								_varName = _input.LT(-1).getText();
								_varValue = null;
								symbol = new IsiVariable(_varName, _tipo, _varValue, false);
								verificaDeclID(_varName);
							
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(46);
				match(VIR);
				setState(47);
				match(ID);
				 
									_varName = _input.LT(-1).getText();
									_varValue = null;
									symbol = new IsiVariable(_varName, _tipo, _varValue, false);
									verificaDeclID(_varName);
								 
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(T__2);
				 _tipo = IsiVariable.INT; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__3);
				 _tipo = IsiVariable.REAL; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				match(T__4);
				 _tipo = IsiVariable.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdifContext cmdif() {
			return getRuleContext(CmdifContext.class,0);
		}
		public CmddoContext cmddo() {
			return getRuleContext(CmddoContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
		}
		public CmddeclContext cmddecl() {
			return getRuleContext(CmddeclContext.class,0);
		}
		public CmdforContext cmdfor() {
			return getRuleContext(CmdforContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdleitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdif();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmddo();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(69);
				cmdwhile();
				}
				break;
			case T__2:
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 7);
				{
				setState(70);
				cmddecl();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 8);
				{
				setState(71);
				cmdfor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmddeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public CmddeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmddecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmddecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmddecl(this);
		}
	}

	public final CmddeclContext cmddecl() throws RecognitionException {
		CmddeclContext _localctx = new CmddeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmddecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(74);
					declaravar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(77); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__5);
			setState(80);
			match(AP);
			setState(81);
			match(ID);
			 
										verificaID(_input.LT(-1).getText());
								 	 	_readID = _input.LT(-1).getText();
								   	
			setState(83);
			match(FP);
			setState(84);
			match(SC);

								   		symbolTable.get(_readID).setValue(" ");
								   		symbolTable.get(_readID).setUsed(true);
								   		IsiVariable var = (IsiVariable)symbolTable.get(_readID);
								    	CommandLeitura cmd = new CommandLeitura(_readID, var);
								    	stack.peek().add(cmd);
								   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__6);
			setState(88);
			match(AP);
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(89);
				match(ID);

									   			verificaID(_input.LT(-1).getText());
									   	 		_writeID = _input.LT(-1).getText();
									   	 		symbolTable.get(_writeID).setUsed(true);
									   		
				}
				break;
			case TEXTO:
				{
				setState(91);
				match(TEXTO);
				 _writeID = _input.LT(-1).getText(); 
				}
				break;
			case REAL:
			case INT:
				{
				setState(93);
				number();
				 _writeID = _input.LT(-1).getText(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(98);
			match(FP);
			setState(99);
			match(SC);
			 
								   			CommandEscrita cmd = new CommandEscrita(_writeID);
								   			stack.peek().add(cmd);
								   		
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(ID);

								verificaID(_input.LT(-1).getText()); 
						  		_exprID = _input.LT(-1).getText();
						  		leftDT = symbolTable.get(_exprID).getType();
						  		symbolTable.get(_exprID).setUsed(true);
							
			setState(104);
			match(ATTR);
			_exprContent = ""; 
			setState(106);
			expr();
			setState(107);
			match(SC);

								symbolTable.get(_exprID).setValue(_exprContent);
								CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
								stack.peek().add(cmd);
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdifContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(IsiLangParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(IsiLangParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(IsiLangParser.OPLOG, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdif(this);
		}
	}

	public final CmdifContext cmdif() throws RecognitionException {
		CmdifContext _localctx = new CmdifContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__7);
			setState(111);
			match(AP);
			_exprContent = ""; 
			setState(113);
			expr();
			setState(114);
			match(OPREL);
			 _exprContent += _input.LT(-1).getText(); 
			setState(116);
			expr();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(117);
				match(OPLOG);

						  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
						  			
				setState(119);
				expr();
				setState(120);
				match(OPREL);
				 _exprContent += _input.LT(-1).getText(); 
				setState(122);
				expr();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(FP);
			setState(130);
			match(ACH);

					  				curThread = new ArrayList<AbstractCommand>();
					  				stack.push(curThread);
					  			
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				cmd();
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(137);
			match(FCH);
			 listaTrue = stack.pop(); 
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(139);
				match(T__8);
				setState(140);
				match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(142);
					cmd();
					}
					}
					setState(145); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
				setState(147);
				match(FCH);
				 listaFalse = stack.pop(); 
				}
			}


									CommandDecisao cmd = new CommandDecisao(_exprContent, listaTrue, listaFalse);
									stack.peek().add(cmd);
								
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmddoContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(IsiLangParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(IsiLangParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(IsiLangParser.OPLOG, i);
		}
		public CmddoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmddo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmddo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmddo(this);
		}
	}

	public final CmddoContext cmddo() throws RecognitionException {
		CmddoContext _localctx = new CmddoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmddo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(T__9);
			setState(155);
			match(ACH);

								curThread = new ArrayList<AbstractCommand>(); 
			                    stack.push(curThread);
							
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(157);
				cmd();
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(162);
			match(FCH);
			setState(163);
			match(T__10);
			setState(164);
			match(AP);
			 _exprContent = ""; 
			setState(166);
			expr();
			setState(167);
			match(OPREL);
			 _exprContent += _input.LT(-1).getText(); 
			setState(169);
			expr();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(170);
				match(OPLOG);

						  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
						  			
				setState(172);
				expr();
				setState(173);
				match(OPREL);
				 _exprContent += _input.LT(-1).getText(); 
				setState(175);
				expr();
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(182);
			match(FP);
			setState(183);
			match(SC);
			   	
					  				listaEnquanto = stack.pop();
					  				CommandDoWhile cmd = new CommandDoWhile(_exprContent, listaEnquanto);
									stack.peek().add(cmd);
					  			
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdwhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLangParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(IsiLangParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<TerminalNode> OPLOG() { return getTokens(IsiLangParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(IsiLangParser.OPLOG, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdwhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdwhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdwhile(this);
		}
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdwhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(T__10);
			setState(187);
			match(AP);
			 
								aux = 1;
								_exprContent = "";
								
			setState(189);
			expr();
			setState(190);
			match(OPREL);
			 _exprContent += _input.LT(-1).getText(); 
			setState(192);
			expr();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(193);
				match(OPLOG);

						  				_exprContent += " " + getOprLogico(_input.LT(-1).getText()) + " ";
						  			
				setState(195);
				expr();
				setState(196);
				match(OPREL);
				 _exprContent += _input.LT(-1).getText(); 
				setState(198);
				expr();
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
			match(FP);
			setState(206);
			match(ACH);

								curThread = new ArrayList<AbstractCommand>(); 
			                    stack.push(curThread);
			                    _exprWhile = _exprContent;
							
			setState(209); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(208);
				cmd();
				}
				}
				setState(211); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(213);
			match(FCH);
			   
					  			listaEnquanto = stack.pop();
					  			CommandWhile cmd = new CommandWhile(_exprWhile, listaEnquanto);
								stack.peek().add(cmd);
								aux = 0;
					  		
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdforContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TerminalNode> SC() { return getTokens(IsiLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(IsiLangParser.SC, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode INC() { return getToken(IsiLangParser.INC, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdforContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdfor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdfor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdfor(this);
		}
	}

	public final CmdforContext cmdfor() throws RecognitionException {
		CmdforContext _localctx = new CmdforContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdfor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__11);
			setState(217);
			match(AP);
			 
					 		_exprContent = ""; 
					 		_tipo = 0;
					 		leftDT = 0;
							curThread = new ArrayList<AbstractCommand>(); 
			           		 stack.push(curThread);
					 		
			setState(219);
			match(ID);
			 
					 		_varName = _input.LT(-1).getText();
					 		_exprContent += _varName;
					 		if (symbolTable.exists(_input.LT(-1).getText())) {
					 			throw new IsiSemanticException("Simbolo " +_varName + " nao foi declarado!");
					 		}
					 	
					 		symbol = new IsiVariable(_varName, _tipo, _varName, true);
							symbolTable.add(symbol);  
					 		
			setState(221);
			match(ATTR);
			 _exprContent += "="; 
			setState(223);
			number();
			 _exprContent += _input.LT(-1).getText(); 
			setState(225);
			match(SC);
			 _exprContent += ";"; 
			setState(227);
			expr();
			setState(228);
			match(OPREL);
			 _exprContent += _input.LT(-1).getText(); 
			setState(230);
			expr();
			setState(231);
			match(SC);
			 _exprContent += ";"; 
			setState(233);
			match(ID);
			 
					 				_exprContent += _input.LT(-1).getText();
					 				verificaID(_input.LT(-1).getText());
					 				symbolTable.get(_input.LT(-1).getText()).setUsed(true);
					 			
			setState(235);
			match(INC);
			 _exprContent += _input.LT(-1).getText(); 
			setState(237);
			match(FP);
			setState(238);
			match(ACH);
			setState(240); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(239);
				cmd();
				}
				}
				setState(242); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << ID))) != 0) );
			setState(244);
			match(FCH);
			   
					 			symbolTable.remove(_varName);	
				  				listaEnquanto = stack.pop();
				  				CommandFor cmd = new CommandFor(_exprContent, listaEnquanto);
								stack.peek().add(cmd);
					 		
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			termo();
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(248);
				match(OP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(250);
				termo();
				}
				}
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode TEXTO() { return getToken(IsiLangParser.TEXTO, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			setState(269);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AP:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(AP);
				 _exprContent += '('; 
				setState(258);
				expr();
				setState(259);
				match(FP);
				 _exprContent += ')'; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(ID);

									verificaID(_input.LT(-1).getText());
									verificaValue(_input.LT(-1).getText());
							 		_exprContent += _input.LT(-1).getText();
							 		rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
							 		verificaDT(leftDT, rightDT, _input.LT(-1).getText(), aux);
							 		symbolTable.get(_input.LT(-1).getText()).setUsed(true);
						   		
				}
				break;
			case REAL:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(264);
				number();
				 
									_exprContent += _input.LT(-1).getText(); 
									verificaDT(leftDT, _tipo, _input.LT(-1).getText(), aux);
								
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(267);
				match(TEXTO);
				 
									_exprContent += _input.LT(-1).getText(); 
									verificaDT(leftDT, 2, _input.LT(-1).getText(), aux);
								
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode REAL() { return getToken(IsiLangParser.REAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_number);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(INT);
				 _tipo = IsiVariable.INT; 
				}
				break;
			case REAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(REAL);
				 _tipo = IsiVariable.REAL; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u0118\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\7\4\64"+
		"\n\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\6\7N\n\7\r\7\16\7O\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tc\n\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\177\n\13\f\13\16\13\u0082"+
		"\13\13\3\13\3\13\3\13\3\13\6\13\u0088\n\13\r\13\16\13\u0089\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\6\13\u0092\n\13\r\13\16\13\u0093\3\13\3\13\3\13\5"+
		"\13\u0099\n\13\3\13\3\13\3\f\3\f\3\f\3\f\6\f\u00a1\n\f\r\f\16\f\u00a2"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00b4"+
		"\n\f\f\f\16\f\u00b7\13\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00cb\n\r\f\r\16\r\u00ce\13\r\3\r\3\r\3"+
		"\r\3\r\6\r\u00d4\n\r\r\r\16\r\u00d5\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\6\16\u00f3\n\16\r\16\16\16\u00f4\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\7\17\u00fe\n\17\f\17\16\17\u0101\13\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0110"+
		"\n\20\3\21\3\21\3\21\3\21\5\21\u0116\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \2\2\2\u0123\2\"\3\2\2\2\4\'\3\2\2\2\6-\3\2\2\2"+
		"\b@\3\2\2\2\nJ\3\2\2\2\fM\3\2\2\2\16Q\3\2\2\2\20Y\3\2\2\2\22h\3\2\2\2"+
		"\24p\3\2\2\2\26\u009c\3\2\2\2\30\u00bc\3\2\2\2\32\u00da\3\2\2\2\34\u00f9"+
		"\3\2\2\2\36\u010f\3\2\2\2 \u0115\3\2\2\2\"#\7\3\2\2#$\5\4\3\2$%\7\4\2"+
		"\2%&\b\2\1\2&\3\3\2\2\2\')\b\3\1\2(*\5\n\6\2)(\3\2\2\2*+\3\2\2\2+)\3\2"+
		"\2\2+,\3\2\2\2,\5\3\2\2\2-.\5\b\5\2./\7\33\2\2/\65\b\4\1\2\60\61\7\23"+
		"\2\2\61\62\7\33\2\2\62\64\b\4\1\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3"+
		"\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7\22\2\29\7\3\2\2\2:"+
		";\7\5\2\2;A\b\5\1\2<=\7\6\2\2=A\b\5\1\2>?\7\7\2\2?A\b\5\1\2@:\3\2\2\2"+
		"@<\3\2\2\2@>\3\2\2\2A\t\3\2\2\2BK\5\16\b\2CK\5\20\t\2DK\5\22\n\2EK\5\24"+
		"\13\2FK\5\26\f\2GK\5\30\r\2HK\5\f\7\2IK\5\32\16\2JB\3\2\2\2JC\3\2\2\2"+
		"JD\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\13\3\2\2"+
		"\2LN\5\6\4\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\r\3\2\2\2QR\7\b"+
		"\2\2RS\7\17\2\2ST\7\33\2\2TU\b\b\1\2UV\7\20\2\2VW\7\21\2\2WX\b\b\1\2X"+
		"\17\3\2\2\2YZ\7\t\2\2Zb\7\17\2\2[\\\7\33\2\2\\c\b\t\1\2]^\7\36\2\2^c\b"+
		"\t\1\2_`\5 \21\2`a\b\t\1\2ac\3\2\2\2b[\3\2\2\2b]\3\2\2\2b_\3\2\2\2cd\3"+
		"\2\2\2de\7\20\2\2ef\7\21\2\2fg\b\t\1\2g\21\3\2\2\2hi\7\33\2\2ij\b\n\1"+
		"\2jk\7\31\2\2kl\b\n\1\2lm\5\34\17\2mn\7\21\2\2no\b\n\1\2o\23\3\2\2\2p"+
		"q\7\n\2\2qr\7\17\2\2rs\b\13\1\2st\5\34\17\2tu\7\26\2\2uv\b\13\1\2v\u0080"+
		"\5\34\17\2wx\7\30\2\2xy\b\13\1\2yz\5\34\17\2z{\7\26\2\2{|\b\13\1\2|}\5"+
		"\34\17\2}\177\3\2\2\2~w\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\20"+
		"\2\2\u0084\u0085\7\24\2\2\u0085\u0087\b\13\1\2\u0086\u0088\5\n\6\2\u0087"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7\25\2\2\u008c\u0098\b\13\1\2\u008d"+
		"\u008e\7\13\2\2\u008e\u008f\7\24\2\2\u008f\u0091\b\13\1\2\u0090\u0092"+
		"\5\n\6\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\25\2\2\u0096\u0097\b"+
		"\13\1\2\u0097\u0099\3\2\2\2\u0098\u008d\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009b\b\13\1\2\u009b\25\3\2\2\2\u009c\u009d\7\f\2"+
		"\2\u009d\u009e\7\24\2\2\u009e\u00a0\b\f\1\2\u009f\u00a1\5\n\6\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\25\2\2\u00a5\u00a6\7\r\2\2\u00a6"+
		"\u00a7\7\17\2\2\u00a7\u00a8\b\f\1\2\u00a8\u00a9\5\34\17\2\u00a9\u00aa"+
		"\7\26\2\2\u00aa\u00ab\b\f\1\2\u00ab\u00b5\5\34\17\2\u00ac\u00ad\7\30\2"+
		"\2\u00ad\u00ae\b\f\1\2\u00ae\u00af\5\34\17\2\u00af\u00b0\7\26\2\2\u00b0"+
		"\u00b1\b\f\1\2\u00b1\u00b2\5\34\17\2\u00b2\u00b4\3\2\2\2\u00b3\u00ac\3"+
		"\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7\20\2\2\u00b9\u00ba\7"+
		"\21\2\2\u00ba\u00bb\b\f\1\2\u00bb\27\3\2\2\2\u00bc\u00bd\7\r\2\2\u00bd"+
		"\u00be\7\17\2\2\u00be\u00bf\b\r\1\2\u00bf\u00c0\5\34\17\2\u00c0\u00c1"+
		"\7\26\2\2\u00c1\u00c2\b\r\1\2\u00c2\u00cc\5\34\17\2\u00c3\u00c4\7\30\2"+
		"\2\u00c4\u00c5\b\r\1\2\u00c5\u00c6\5\34\17\2\u00c6\u00c7\7\26\2\2\u00c7"+
		"\u00c8\b\r\1\2\u00c8\u00c9\5\34\17\2\u00c9\u00cb\3\2\2\2\u00ca\u00c3\3"+
		"\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7\20\2\2\u00d0\u00d1\7"+
		"\24\2\2\u00d1\u00d3\b\r\1\2\u00d2\u00d4\5\n\6\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\7\25\2\2\u00d8\u00d9\b\r\1\2\u00d9\31\3\2\2\2\u00da\u00db"+
		"\7\16\2\2\u00db\u00dc\7\17\2\2\u00dc\u00dd\b\16\1\2\u00dd\u00de\7\33\2"+
		"\2\u00de\u00df\b\16\1\2\u00df\u00e0\7\31\2\2\u00e0\u00e1\b\16\1\2\u00e1"+
		"\u00e2\5 \21\2\u00e2\u00e3\b\16\1\2\u00e3\u00e4\7\21\2\2\u00e4\u00e5\b"+
		"\16\1\2\u00e5\u00e6\5\34\17\2\u00e6\u00e7\7\26\2\2\u00e7\u00e8\b\16\1"+
		"\2\u00e8\u00e9\5\34\17\2\u00e9\u00ea\7\21\2\2\u00ea\u00eb\b\16\1\2\u00eb"+
		"\u00ec\7\33\2\2\u00ec\u00ed\b\16\1\2\u00ed\u00ee\7\32\2\2\u00ee\u00ef"+
		"\b\16\1\2\u00ef\u00f0\7\20\2\2\u00f0\u00f2\7\24\2\2\u00f1\u00f3\5\n\6"+
		"\2\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\7\25\2\2\u00f7\u00f8\b\16\1\2"+
		"\u00f8\33\3\2\2\2\u00f9\u00ff\5\36\20\2\u00fa\u00fb\7\27\2\2\u00fb\u00fc"+
		"\b\17\1\2\u00fc\u00fe\5\36\20\2\u00fd\u00fa\3\2\2\2\u00fe\u0101\3\2\2"+
		"\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\35\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0102\u0103\7\17\2\2\u0103\u0104\b\20\1\2\u0104\u0105\5\34\17"+
		"\2\u0105\u0106\7\20\2\2\u0106\u0107\b\20\1\2\u0107\u0110\3\2\2\2\u0108"+
		"\u0109\7\33\2\2\u0109\u0110\b\20\1\2\u010a\u010b\5 \21\2\u010b\u010c\b"+
		"\20\1\2\u010c\u0110\3\2\2\2\u010d\u010e\7\36\2\2\u010e\u0110\b\20\1\2"+
		"\u010f\u0102\3\2\2\2\u010f\u0108\3\2\2\2\u010f\u010a\3\2\2\2\u010f\u010d"+
		"\3\2\2\2\u0110\37\3\2\2\2\u0111\u0112\7\35\2\2\u0112\u0116\b\21\1\2\u0113"+
		"\u0114\7\34\2\2\u0114\u0116\b\21\1\2\u0115\u0111\3\2\2\2\u0115\u0113\3"+
		"\2\2\2\u0116!\3\2\2\2\24+\65@JOb\u0080\u0089\u0093\u0098\u00a2\u00b5\u00cc"+
		"\u00d5\u00f4\u00ff\u010f\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}