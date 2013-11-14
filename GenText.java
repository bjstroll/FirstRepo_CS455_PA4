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

	private ArrayList<String> text;


	//read text into ArrayList text;
	public void readInText() {
		File sourceFile = new File(sourceFileName);
		Scanner in = new Scanner(sourceFile);//exception handler needed
		while (in.hasNext()) {
			text.add(in.next());
		}
	}
}
