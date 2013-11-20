// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA4
// Fall 2013

/**
	RandomTextGenerator class

	author: yinzi bao

	There are 2 major objectives of this class
	1.organize souce text into hash map by prefix and next-possible words
	2.generate random text by looking up to the hash map genrated
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomTextGenerator {

	/**
		Default constructor
	*/
	public RandomTextGenerator() {
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		prefixLength = 1;
		currentPrefix = new Prefix();
		generator = new Random();
	}

	/**
		Constructor, creates RandomTextGenerator with specific parameters
		@param prefixLength the length of prefix
		@param text reference of the array list containing the souce text
		@param debugMode indicates whether in debug mode
	*/
	public RandomTextGenerator(int prefixLength, ArrayList<String> text, int debugMode) {
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		this.prefixLength = prefixLength;
		currentPrefix = new Prefix(prefixLength, text, RANDOM_MODE, debugMode);
		generator = (debugMode == 1) ? new Random(1) : new Random();// if debugMode = 1, in debug mode
	}
	

	/**
		According to the length of prefix, we organize our source text
		into pairs of <prefix, next word array> and generate a hash map
		containing these information
		@param text reference of the array list containing the souce text
		@param debugMode indicates whether in debug mode
	*/
	public void textToHashmap(ArrayList<String> text, int debugMode) {
		Prefix setupPrefix = new Prefix(prefixLength, text, SETUP_MODE, debugMode);

		for (int i = prefixLength; i <= text.size(); i++ ) {
			String nextWord;
			if (i < text.size()) {
				nextWord = text.get(i);
			}
			else {
				nextWord = "end of file";// we need to take the end of file in to 
										 // consideration in order to make the decision
										 // based on the real possibility in the text
			}

			if (searchMap.get(setupPrefix) == null) {
				ArrayList<String> newValue = new ArrayList<String>();
				newValue.add(nextWord);
				searchMap.put(setupPrefix, newValue);
			}
			else {
			    ArrayList<String> newValue = new ArrayList<String>();
			    newValue = searchMap.get(setupPrefix);
			    newValue.add(nextWord);
			}

			setupPrefix = setupPrefix.updatePrefix(nextWord);
		}
	}
	
	/**
		Look up current prefix in the hash map, return the next word based
		on probability.
		when reach the end of file, generates a new preix
		@param text reference of the source text
		@param debugMode indicate whether in debug mode
		precon: the prefix we look up is 100% in the text (hash map)
	*/
	public String generate(ArrayList<String> text, int debugMode) {
		if (debugMode == 1) {System.out.println("DEBUG: prefix: " + currentPrefix.toString());}
		ArrayList<String> nextWordsArray = searchMap.get(currentPrefix);
		if (debugMode == 1) {System.out.println("DEBUG: successors " + nextWordsArray.toString());}
		
		int indexNext = generator.nextInt(nextWordsArray.size());
		String nextWord = nextWordsArray.get(indexNext);
		
		while (nextWord.equals("end of file")) {
			currentPrefix = new Prefix(prefixLength, text, RANDOM_MODE, debugMode);
			if (debugMode == 1) {System.out.println("DEBUG: prefix: " + currentPrefix.toString());}
			nextWordsArray = searchMap.get(currentPrefix);
			if (debugMode == 1) {System.out.println("DEBUG: successors " + nextWordsArray.toString());}
			indexNext = generator.nextInt(nextWordsArray.size());
			nextWord = nextWordsArray.get(indexNext);
		}
		if (debugMode == 1) {System.out.println("DEBUG: word generated: " + nextWord);}
		currentPrefix = currentPrefix.updatePrefix(nextWord);
		return nextWord;
	}

	private Random generator; 
	private int prefixLength;
	private Prefix currentPrefix;
	private HashMap<Prefix, ArrayList<String>> searchMap;
	public static final int RANDOM_MODE = 0;
	public static final int SETUP_MODE = 1;
}