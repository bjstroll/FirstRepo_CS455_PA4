// a RandomTextGenerator class
// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013

/*
	1.read in text
	2.organize a map based on text
	3.look up and return the next word

**/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomTextGenerator {

	//constructor
	public RandomTextGenerator() {
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		prefixLength = 1;
		currentPrefix = new Prefix();
		generator = new Random();
	}

	//constructor
	public RandomTextGenerator(int prefixLength, ArrayList<String> text, int debugMode) {
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		this.prefixLength = prefixLength;
		//this.textToHashmap(text, debugMode);
		// ***********************************D
		// System.out.println("DEBUG: after generate HashMap");
		currentPrefix = new Prefix(prefixLength, text, RANDOM_MODE, debugMode);
		generator = (debugMode == 1) ? new Random(1) : new Random();
		// ***********************************
	}
	

	//According to the prefixLength, we cut the text and generate
	//a Hashmap with prefixes as Keyset. 
	//precondiction: text.length >= prefixLength + 1;
	public void textToHashmap(ArrayList<String> text, int debugMode) {
		Prefix setupPrefix = new Prefix(prefixLength, text, SETUP_MODE, debugMode);

		for (int i = prefixLength; i <= text.size(); i++ ) {
			String nextWord;
			if (i < text.size()) {
				nextWord = text.get(i);
			}
			else {
				nextWord = "end of file";
			}

			if (searchMap.get(setupPrefix) == null) {
				ArrayList<String> newValue = new ArrayList<String>();
				newValue.add(nextWord);
				// ******************************D
			//	System.out.println("DEBUG: First time next Word Array " + newValue.toString());
				searchMap.put(setupPrefix, newValue);
			}
			else {
			    ArrayList<String> newValue = new ArrayList<String>();
			    newValue = searchMap.get(setupPrefix);
			    newValue.add(nextWord);
			    // ******************************D
			//    System.out.println("DEBUG: another time next word Array " + newValue.toString());
			//	searchMap.put(setupPrefix, newValue);	
			}

			setupPrefix = setupPrefix.updatePrefix(nextWord);
		}
	}
	
	//look up current prefix,return the next word based on probability,
	//construct a new prefix if we reach the end of file.
	//precon: our prefix now is 100% in the text
	public String generate(ArrayList<String> text, int debugMode) {
		if (debugMode == 1) {System.out.println("DEBUG: prefix: " + currentPrefix.toString());}
		ArrayList<String> nextWordsArray = searchMap.get(currentPrefix);
		//*****************************************************D
		// System.out.println("DEBUG: after search map once and get next words array");
		// if (nextWordsArray == null) {
			// System.out.println("DEBUG: next words array doesn't set up correctly");
		// }
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

	//I am consider can I use a keySet to find another? if we do so, when 
	//we generate a prefix, it won't be of the probability in the text. 

	private Random generator; 
	private int prefixLength;//do I really need to save this?
	private Prefix currentPrefix;
	private HashMap<Prefix, ArrayList<String>> searchMap;
	public static final int RANDOM_MODE = 0;
	public static final int SETUP_MODE = 1;
}