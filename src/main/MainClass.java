package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.IsiLangLexer;
import parser.IsiLangParser;
import exceptions.IsiSemanticException;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
//			lexer = new IsiLangLexer(CharStreams.fromFileName("exceptionsInput.isi"));  
			
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			parser = new IsiLangParser(tokenStream);
			
			System.out.println("Starting file parsing...");
			parser.prog();
			System.out.println("Compilation Success - Good Job!");
			
			//parser.exibeComandos();
			
			parser.generateCode();
			
		}
		catch (IsiSemanticException ex) {
			System.err.println("ERRO SEMÂNTICO - "+ex.getMessage());
			}
		catch (Exception ex) {
			System.err.println("ERRO "+ex.getMessage());
			}
	}
}