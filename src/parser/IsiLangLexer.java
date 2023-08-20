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
	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, SC=15, PF=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, OP=21, OPLOG=22, ATTR=23, INC=24, ID=25, REAL=26, INT=27, 
		TEXTO=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "AP", "FP", "SC", "PF", "VIR", "ACH", "FCH", 
		"OPREL", "OP", "OPLOG", "ATTR", "INC", "ID", "REAL", "INT", "TEXTO", "WS"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00cf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\5\25\u0099\n\25\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\5\27\u00a2\n\27\3\30\3\30\3\31\3\31\3\31\3\31\5\31\u00aa\n\31\3"+
		"\32\3\32\7\32\u00ae\n\32\f\32\16\32\u00b1\13\32\3\33\6\33\u00b4\n\33\r"+
		"\33\16\33\u00b5\3\33\3\33\6\33\u00ba\n\33\r\33\16\33\u00bb\3\34\6\34\u00bf"+
		"\n\34\r\34\16\34\u00c0\3\35\3\35\7\35\u00c5\n\35\f\35\16\35\u00c8\13\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37\3\2\t\4\2>>@@\5\2,-//\61\61\3\2c"+
		"|\5\2\62;C\\c|\3\2\62;\t\2\13\13\"#.\60\62=C]__c|\5\2\13\f\17\17\"\"\2"+
		"\u00d9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\3=\3\2\2\2\5F\3\2\2\2\7O\3\2\2\2\tS\3\2\2\2\13X\3\2\2\2"+
		"\r_\3\2\2\2\17d\3\2\2\2\21l\3\2\2\2\23o\3\2\2\2\25t\3\2\2\2\27w\3\2\2"+
		"\2\31}\3\2\2\2\33\u0081\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2\2\2!\u0087"+
		"\3\2\2\2#\u0089\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u0098\3\2\2\2"+
		"+\u009a\3\2\2\2-\u00a1\3\2\2\2/\u00a3\3\2\2\2\61\u00a9\3\2\2\2\63\u00ab"+
		"\3\2\2\2\65\u00b3\3\2\2\2\67\u00be\3\2\2\29\u00c2\3\2\2\2;\u00cb\3\2\2"+
		"\2=>\7r\2\2>?\7t\2\2?@\7q\2\2@A\7i\2\2AB\7t\2\2BC\7c\2\2CD\7o\2\2DE\7"+
		"c\2\2E\4\3\2\2\2FG\7h\2\2GH\7k\2\2HI\7o\2\2IJ\7r\2\2JK\7t\2\2KL\7q\2\2"+
		"LM\7i\2\2MN\7\60\2\2N\6\3\2\2\2OP\7k\2\2PQ\7p\2\2QR\7v\2\2R\b\3\2\2\2"+
		"ST\7t\2\2TU\7g\2\2UV\7c\2\2VW\7n\2\2W\n\3\2\2\2XY\7U\2\2YZ\7v\2\2Z[\7"+
		"t\2\2[\\\7k\2\2\\]\7p\2\2]^\7i\2\2^\f\3\2\2\2_`\7n\2\2`a\7g\2\2ab\7k\2"+
		"\2bc\7c\2\2c\16\3\2\2\2de\7g\2\2ef\7u\2\2fg\7e\2\2gh\7t\2\2hi\7g\2\2i"+
		"j\7x\2\2jk\7c\2\2k\20\3\2\2\2lm\7k\2\2mn\7h\2\2n\22\3\2\2\2op\7g\2\2p"+
		"q\7n\2\2qr\7u\2\2rs\7g\2\2s\24\3\2\2\2tu\7f\2\2uv\7q\2\2v\26\3\2\2\2w"+
		"x\7y\2\2xy\7j\2\2yz\7k\2\2z{\7n\2\2{|\7g\2\2|\30\3\2\2\2}~\7h\2\2~\177"+
		"\7q\2\2\177\u0080\7t\2\2\u0080\32\3\2\2\2\u0081\u0082\7*\2\2\u0082\34"+
		"\3\2\2\2\u0083\u0084\7+\2\2\u0084\36\3\2\2\2\u0085\u0086\7=\2\2\u0086"+
		" \3\2\2\2\u0087\u0088\7\60\2\2\u0088\"\3\2\2\2\u0089\u008a\7.\2\2\u008a"+
		"$\3\2\2\2\u008b\u008c\7}\2\2\u008c&\3\2\2\2\u008d\u008e\7\177\2\2\u008e"+
		"(\3\2\2\2\u008f\u0099\t\2\2\2\u0090\u0091\7@\2\2\u0091\u0099\7?\2\2\u0092"+
		"\u0093\7>\2\2\u0093\u0099\7?\2\2\u0094\u0095\7?\2\2\u0095\u0099\7?\2\2"+
		"\u0096\u0097\7#\2\2\u0097\u0099\7?\2\2\u0098\u008f\3\2\2\2\u0098\u0090"+
		"\3\2\2\2\u0098\u0092\3\2\2\2\u0098\u0094\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"*\3\2\2\2\u009a\u009b\t\3\2\2\u009b,\3\2\2\2\u009c\u009d\7q\2\2\u009d"+
		"\u00a2\7t\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7p\2\2\u00a0\u00a2\7f\2\2"+
		"\u00a1\u009c\3\2\2\2\u00a1\u009e\3\2\2\2\u00a2.\3\2\2\2\u00a3\u00a4\7"+
		"?\2\2\u00a4\60\3\2\2\2\u00a5\u00a6\7-\2\2\u00a6\u00aa\7-\2\2\u00a7\u00a8"+
		"\7/\2\2\u00a8\u00aa\7/\2\2\u00a9\u00a5\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\62\3\2\2\2\u00ab\u00af\t\4\2\2\u00ac\u00ae\t\5\2\2\u00ad\u00ac\3\2\2"+
		"\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\64"+
		"\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b4\t\6\2\2\u00b3\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b9\5!\21\2\u00b8\u00ba\t\6\2\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\66\3\2\2"+
		"\2\u00bd\u00bf\t\6\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c18\3\2\2\2\u00c2\u00c6\7$\2\2\u00c3\u00c5"+
		"\t\7\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7$"+
		"\2\2\u00ca:\3\2\2\2\u00cb\u00cc\t\b\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\b\36\2\2\u00ce<\3\2\2\2\r\2\u0098\u00a1\u00a9\u00ad\u00af\u00b5\u00bb"+
		"\u00c0\u00c4\u00c6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}