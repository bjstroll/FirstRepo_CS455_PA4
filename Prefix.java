//a Prefix class
// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013

public class Prefix {
	//constructor, random in text.
	public Prefix() {

	}
	//constructor
	public Prefix(LinkedList<String> wordsInPrefix) {

	}

	//generate prefix, if it's the initial or eof, randomly. if it is
	//not in previous situations, merge the next word, drop the first
	//word
	public void updatePrefix(String nextWord) {
		assert !nextWord.equal("end of file");
		currentPrefix.addLast(nextWord);
		currentPrefix.removeFirst();
	}

	private Random generator = new Random();
	private LinkedList<String> currentPrefix = new LinkedList<String>();
}