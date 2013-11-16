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
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.lang.Math;
import java.util.ListIterator;

public class Prefix {
	//constructor, if the setup == 1, build the first sequence
	//of prefix from the text in order to build map; if setup == 0
	//build a random prefix from the text.
	public Prefix(int prefixLength, ArrayList<String> text, int setup) {
		this.prefixLength = prefixLength;
		currentPrefix = new LinkedList<String>();

		if (setup == 1) {
			for (int i = 0; i < prefixLength; i++) {
				currentPrefix.addLast(text.get(i));
			}
			//*******************************************************debug 
			System.out.println("DEBUG: setup_mode, the initial prefix is " + currentPrefix.toString());
		}
		else {
			int indexInText = generator.nextInt(text.size() - prefixLength);
			for (int i = indexInText; i < (indexInText + prefixLength); i++) {
				currentPrefix.addLast(text.get(i));
			}
			// *************************************************D
			System.out.println("DEBUG: current prefix " + currentPrefix.toString());

		}
	}

	//default constructor
	public Prefix() {
		prefixLength = 1;
		currentPrefix = new LinkedList<String>();//may redundant
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
		// ***********************************************D
		System.out.println("DEBUG: current prefix " + currentPrefix.toString());
		return prefix;
	}

	//HashCode method
	public int hashCode() {
		ListIterator<String> iter = currentPrefix.listIterator();
		int hashCode = 0;
		while (iter.hasNext()) {
			String item = iter.next();
			hashCode += (item.hashCode())*Math.pow(31, prefixLength);//magic number
		}
		// *****************************D
		System.out.println("DEBUG: hashcode for current prefix");
		return hashCode;
	}

	//equals method
	public boolean equals(Prefix b) {
		if (this.currentPrefix.size() != b.currentPrefix.size()) {
			return false;
		}
		else {
			ListIterator<String> iter1 = this.currentPrefix.listIterator();
			ListIterator<String> iter2 = b.currentPrefix.listIterator();
			while (iter1.hasNext()) {
				if (!iter1.next().equals(iter2.next())) {
					 	return false;
				} 
			}
			return true;
		}
	}

	private Random generator = new Random();
	private int prefixLength;
	private LinkedList<String> currentPrefix;
}