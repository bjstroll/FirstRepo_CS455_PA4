// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA4
// Fall 2013

/**
	GenText class

	author: yinzi bao

	This class contains the main method, and do 4 major jobs,
	1.process command line arguments
	2.Read in text from source file
	3.use RandomTextGenerator to generate random text
	4.output data in to destination file
*/

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Integer;
import java.io.File;
import java.lang.NumberFormatException;

public class GenText {

	/**
		User interface for random text generating
		if it is in debug mode, we need 5 arguments, otherwise 4 arguments
		@param args containing the command line arguments
	*/
	public static void main(String[] args) {
		int debugMode = NON_DEBUG_MODE;//1 means in debugMode
		try {
			if (args[0].equals("-d")) {
				debugMode = DEBUG_MODE;
				if (args.length < 5) {
					throw new ErrorArgsException("ERROR: missing command line arguments");
				}	
				else if (args.length > 5) {
					throw new ErrorArgsException("ERROR: extra command line arguments");
				}
			}
			else {
				if (args.length < 4) {
					throw new ErrorArgsException("ERROR: missing command line arguments");
				}
				else if (args.length > 4) {
					throw new ErrorArgsException("ERROR: extra command line arguments");
				}
			}

			int prefixLength = Integer.parseInt(args[0 + debugMode]);//NumberFormatException
			int numWords = Integer.parseInt(args[1 + debugMode]);//NumberFormatException
			String sourceFile = args[2 + debugMode];
			String outFile = args[3 + debugMode];
			readInText(sourceFile);

			commandError(prefixLength, numWords, args);
													   //commandErrorCheck handles errors 
													   //with prefixLength and numWorkds
													   //sourceFile error handled by exception
													   //outFile error handled by exception
			generateAndPrint(outFile, numWords, prefixLength, debugMode);
		}
		catch (NumberFormatException exception) {
			System.out.println("ERROR: prefixLength or numWords arguments are not integers");
		}
		catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		catch (FileNotFoundException exception) {
			System.out.println(exception.getMessage());
		}
	}


	/**
		Check parts of command line errors, throws ErrorArgsException
		@param prefixLengh the length of prefix 
		@param numWords number of words in the output file
		@param args command line arguments
	*/
	public static void commandError(int prefixLength, int numWords, String[] args) throws ErrorArgsException {
		if (numWords < 0) {
			throw new ErrorArgsException("ERROR: Number of words expected in output file is less than 0. Should be biiger or equal than 0");
		}
		if (prefixLength < 1) {
			throw new ErrorArgsException("ERROR: prefix length is smaller than 1. Should be bigger or equal than 1"); 
		}
		if (text.size() == 0 || text.size() == 1) {
			throw new ErrorArgsException("ERROR: there are only " + text.size() + " words in the source file, too few to process");
		}
		if (prefixLength >= text.size()) {
			throw new ErrorArgsException("ERROR: prefix Length is bigger than the number of words in source text. Prefix length should be smaller than " + text.size());
		}
	}

	/**
		Read text from source file into array list, throws FileNotFoundException
		@param sourceFileName the name of souce file
	*/
	public static void readInText (String sourceFileName) throws FileNotFoundException {
		File sourceFile = new File(sourceFileName);
		try {
			Scanner in = new Scanner(sourceFile);
			try{
				while (in.hasNext()) {
					text.add(in.next());
				}
			}
			finally {
				in.close();
			}
		}
		catch(FileNotFoundException exception) {
			throw new FileNotFoundException("ERROR: Input file not found");
		}
	}

	/**
		Generate random text and print them into output file, throws FileNotFoundException
		@param outFile the name of output file
		@param numWords number of words in the output file
		@param prefixLength the length of prefixes
		@param debugMode determines whether the program is in debug mode
	*/
	public static void generateAndPrint(String outFile, int numWords, int prefixLength, int debugMode) throws FileNotFoundException {
		RandomTextGenerator textGenerator = new RandomTextGenerator(prefixLength, text, debugMode);
		textGenerator.textToHashmap(text, debugMode);
		String nextWord = textGenerator.generate(text, debugMode);
		int nextWordLength = nextWord.length();
		try {
			PrintWriter out = new PrintWriter(outFile);
			try{
				int numCharsPerLine = 0;
				for (int i = 1; i <= numWords; i++) {
					if (numCharsPerLine == 0) {
						out.print(nextWord);
						numCharsPerLine += nextWordLength;
						nextWord = textGenerator.generate(text, debugMode);
						nextWordLength = nextWord.length();
					}
					else {
						if ((numCharsPerLine + nextWordLength + 1) <= CHARACTER_PER_LINE) {
							out.print(" " + nextWord);
							numCharsPerLine += nextWordLength + 1;
							nextWord = textGenerator.generate(text, debugMode);
							nextWordLength = nextWord.length();
						}
						else {
							out.println();
							numCharsPerLine = 0;
						}
					}
				}
			}
			finally {
				out.close();
			}
		}
		catch(FileNotFoundException exception) {
			throw new FileNotFoundException("ERROR: Output file not found");
		}
	}

	private static ArrayList<String> text = new ArrayList<String>();
	public static final int DEBUG_MODE = 1;
	public static final int NON_DEBUG_MODE = 0;
	public static final int CHARACTER_PER_LINE = 80;
	
}
