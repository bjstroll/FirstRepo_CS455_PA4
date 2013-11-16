//contains the main methods for this assignment
// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013

/*
	1.Data readin
	2.call RandomTextGenerator
	3.output files
**/

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Integer;
import java.io.File;

public class GenText {

	public static void main(String[] args) {
		int prefixLength;
		int numWords;
		String sourceFile;
		String outFile;
		int debugMode = 0;//1 means in debugMode


		if ((args.length != 4) && (args.length != 5)) {
			System.out.println("ERROR: please enter appropriate arguments");
			return ;
		}

		if (args[0].equals("-d")) {
			debugMode = 1;
		}
		try {
			prefixLength = Integer.parseInt(args[0 + debugMode]);//exception handler needed
			sourceFile = args[2 + debugMode];
			outFile = args[3 + debugMode];//why can't write to output file
			readInText(sourceFile);
			// ******************************************************
			System.out.println("DEBUG: after readInText()");
			System.out.println("Text: " + text.toString());

			numWords = Integer.parseInt(args[1 + debugMode]);//exception

			commandErrorCheck(prefixLength, numWords); //commandErrorCheck handle errors associated
													   //with prefixLength and numWorkds
													   //sourceFile error handled by exception
													   //outFile error handled by exception?
			generateAndPrint(outFile, numWords, prefixLength);
			System.out.println("Congratulations! DONE!");
		}
		catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		catch (FileNotFoundException exception) {
			System.out.println(exception.getMessage());
		}
	}


	//process command line ERROR
	public static void commandErrorCheck(int prefixLength, int numWords) throws ErrorArgsException {
		if (numWords < 0) {
			throw new ErrorArgsException("ERROR: Number of words expected in output file is less than 0. Should be biiger or equal than 0");
		}
		if (prefixLength < 1) {
			throw new ErrorArgsException("ERROR: prefix length is smaller than 1. Should be bigger or equal than 1"); 
		}
		if (prefixLength > text.size()) {
			throw new ErrorArgsException("ERROR: prefix Length is bigger than the number of words in source text. Prefix length should be smaller than " + text.size());
		}
	}

	//read text into ArrayList text;
	public static void readInText (String sourceFileName) throws FileNotFoundException {
		File sourceFile = new File(sourceFileName);
		try {
			Scanner in = new Scanner(sourceFile);//exception handler needed and in.close();
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

	//print random text into output file
	//" " + nextWord, so put fisrt word at first.
	public static void generateAndPrint(String outFile, int numWords, int prefixLength) throws FileNotFoundException {
		RandomTextGenerator textGenerator = new RandomTextGenerator(prefixLength, text);
		String nextWord = textGenerator.generate(text);
		int nextWordLength = nextWord.length();
		try {
			PrintWriter out = new PrintWriter(outFile);
			try{
				int numCharsPerLine = 0;
				for (int i = 1; i <= numWords; i++) {
					if (numCharsPerLine == 0) {
						out.print(nextWord);
						numCharsPerLine += nextWordLength;
						nextWord = textGenerator.generate(text);
						nextWordLength = nextWord.length();
					}
					else {
						if ((numCharsPerLine + nextWordLength + 1) <= 80) {
							out.print(" " + nextWord);
							numCharsPerLine += nextWordLength + 1;
							nextWord = textGenerator.generate(text);
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
}
