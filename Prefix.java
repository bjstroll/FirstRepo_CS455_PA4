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
		this.prefixLength = prefixLength;
		if (setup == 1) {
			for (int i = 0; i < prefixLength; i++) {
				currentPrefix.addLast(text.get(i));
			}
		}
		else {
			int indexInText = generator.nextInt(text.size() - prefixLength);
			for (int i = indexInText; i < (indexInText + prefixLength); i++) {
				currentPrefix.addLast(text.get(i));
			}
		}
	}

	//default constructor
	public Prefix() {
		prefixLength = 1;
		currentPrefix = new LinkedList<String>();
	}

	//generate prefix, if it's the initial or eof, randomly. if it is
	//not in previous situations, merge the next word, drop the first
	//word
	public Prefix updatePrefix(String nextWord) {
		Prefix prefix = new Prefix();
		prefix.prefixLength = this.prefixLength;
		prefix.currentPrefix = new LinkedList<String>(this.currentPrefix);
		prefix.currentPrefix.addLast(nextWord);
		prefix.currentPrefix.removeFirst();
		return prefix;
	}

	private Random generator = new Random();
	private int prefixLength;
	private LinkedList<String> currentPrefix;
}