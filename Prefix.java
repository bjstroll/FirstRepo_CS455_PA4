//a Prefix class
// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013

/*
1.update our prefix, if we can update and return turn,
  if can't return false.
2.construct Prefix
**/

public class Prefix {
	//constructor, if the setup == 1, build the first sequence
	//of prefix from the text in order to build map; if setup == 0
	//build a random prefix from the text.
	public Prefix(int prefixLength, ArrayList<String> text, int setup) {

	}

	//default constructor
	public Prefix() {

	}

	//generate prefix, if it's the initial or eof, randomly. if it is
	//not in previous situations, merge the next word, drop the first
	//word
	public void updatePrefix(String nextWord) {
		currentPrefix.addLast(nextWord);
		currentPrefix.removeFirst();
	}

	private int prefixLength;
	private LinkedList<String> currentPrefix = new LinkedList<String>();
}