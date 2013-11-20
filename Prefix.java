// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA4
// Fall 2013

/**
	Prefix class

	author: yinzi bao

	This class is the lowest class we operate, it does 2 jobs
	1.construct Prefixes according to different conditions
	2.update prefix afiter every word generation
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.ListIterator;

public class Prefix {
	/**
		constructor, if setup =1, generate the first-appearing prefix in
		souce text, otherwise, generate a random prefix appearing in the
		text
		@param prefixLength the length of prefix
		@param text reference containing the source text file
		@param setup SETUP_MODE or RANDOM_MODE
		@param debugMode whether in debug mode
	*/
	public Prefix(int prefixLength, ArrayList<String> text, int setup, int debugMode) {
		this.prefixLength = prefixLength;
		currentPrefix = new LinkedList<String>();
		generator = (debugMode == 1) ? new Random(1) : new Random(); //debugMode == 1 means in debug mode 

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
			if (debugMode == 1) {System.out.println("DEBUG: choose a new initial prefix: " + currentPrefix.toString());}
		}
	}

	/**
		Default constructor
	*/
	public Prefix() {
		generator = new Random();
		prefixLength = 1;
		currentPrefix = new LinkedList<String>();//may redundant
	}

	/**
		Update current prefix with the next chosen word, drop the first
		word in the prefix and add the next word at the last of the prefix
		@param nextWord the next word been chosen
	*/
	public Prefix updatePrefix(String nextWord) {
		Prefix prefix = new Prefix();
		prefix.prefixLength = this.prefixLength;
		prefix.currentPrefix = new LinkedList<String>(this.currentPrefix);
		prefix.currentPrefix.addLast(nextWord);
		prefix.currentPrefix.removeFirst();
		return prefix;
	}

	/**
		hashCode method, in order to use prefix as key in a hash map
	*/
	public int hashCode() {
		ListIterator<String> iter = currentPrefix.listIterator();
		int hashCode = 0;
		int temp = 1;
		while (iter.hasNext()) {
			String item = iter.next();
			hashCode += (item.hashCode())*temp;
			temp++;
		}
		return hashCode;
	}

	/**
		equals method, in order to use prefix as key in a hash map
	*/
	public boolean equals(Object obj) {
	    Prefix b = (Prefix) obj;
		if (this.currentPrefix.size() != b.currentPrefix.size()) {
			return false;
		}
		else {
			ListIterator<String> iter1 = this.currentPrefix.listIterator();
			ListIterator<String> iter2 = b.currentPrefix.listIterator();
			while (iter1.hasNext()) {
			   String temp1 = iter1.next();
			   String temp2 = iter2.next();
				if (!temp1.equals(temp2)) {
					 	return false;
				} 
			}
			return true;
		}
	}

	/**
		toString method returns the current prefix
	*/
	public String toString() {
		return currentPrefix.toString();
	}

	private Random generator;
	private int prefixLength;
	private LinkedList<String> currentPrefix;
}