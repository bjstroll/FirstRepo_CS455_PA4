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
public class GenText {

	public static void main(String[] args) {
		int prefixLength;
		int numWords;
		String sourceFile;
		String outFile;
		int debugMode = 0;//1 means in debugMode


		if ((args.size() != 4) && (args.size() != 5)) {
			System.out("ERROR: please enter appropriate  arguments");
			return ;
		}

		if (args[0].equals("-d")) {
			debugMode = 1;
		}

		prefixLength = parseInt(args[0 + debugMode]);//exception handler needed
		sourceFile = args[2 + debugMode];
		outFile = args[3 + debugMode];//why can't write to output file
		readInText(sourceFile);
		numWords = parseInt(args[1 + debugMode]);//exception

		if (commandError(prefixLength, numWords, sourceFile, outFile)) {
			return ;
		}

	}

	private ArrayList<String> text;

	//process command line ERROR
	public boolean commandError(int prefixLength, int numWords, String sourceFile, String outFile) {
		if ((numWords < 0) || (prefixLength < 1) || (prefixLength > text.size())) {
			return true;
		}
		return false;
	}

	//read text into ArrayList text;
	public void readInText(String sourceFile) {
		File sourceFile = new File(sourceFileName);
		Scanner in = new Scanner(sourceFile);//exception handler needed and in.close();
		while (in.hasNext()) {
			text.add(in.next());
		}
	}

	//print random text into output file
	//" " + nextWord, so put fisrt word at first.
	public void generateAndPrint(String outFile, int numWords) {
		RandomTextGenerator textGenerator = new RandomTextGenerator(int prefixLength, ArrayList<String> text);
		String nextWord = textGenerator.generate(text);
		int nextWordLength = nextWord.length();
		PrintWriter out = new PrintWriter(outFile);

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
		out.close();//exception handler needed
	}
}
