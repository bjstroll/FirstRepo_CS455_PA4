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

public class RandomTextGenerator {

	//constructor
	public RandomTextGenerator() {
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		prefixLength = 1;
		currentPrefix = new Prefix();
	}

	//constructor
	public RandomTextGenerator(int prefixLength, ArrayList<String> text) {
		searchMap = new Hashmap<Prefix, ArrayList<String>>();
		this.prefixLength = prefixLength;
		currentPrefix = new Prefix(prefixLength, text, RANDOM_MODE);
		this.textToHashmap(text);
	}
	

	//According to the prefixLength, we cut the text and generate
	//a Hashmap with prefixes as Keyset. 
	//precondiction: text.length >= prefixLength + 1;
	public void textToHashmap(ArrayList<String> text) {
		Prefix setupPrefix = new Prefix(prefixLength, text, SETUP_MODE);

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
				searchMap.put(tempPrefix, newValue);
			}
			else {
				searchMap.put(tempPrefix, searchMap.get(tempPrefix).add(nextWord));	
			}

			setupPrefix = setupPrefix.updatePrefix(nextWord);
		}


	}
	
	//look up current prefix,return the next word based on probability,
	//construct a new prefix if we reach the end of file.
	public String generate(ArrayList<String> text) {
		String nextWord;
		if (searchMap.get(currentPrefix) == null) {
			//Error how to make sure that our prefix is 100% in the text?
		}
		else {
			nextWordsArray = searchMap.get(currentPrefix);
			int indexNext = generator.nextInt(nextWordsArray.size());
			nextWord = nextWordsArray.get(indexNext);
			
			while (nextWord.equals("end of file")) {
				currentPrefix = new Prefix(prefixLength, text, RANDOM);
				nextWordsArray = searchMap.get(currentPrefix);
				int indexNext = generator.nextInt(nextWordsArray.size());
				nextWord = nextWordsArray.get(indexNext);
			}
			currentPrefix = currentPrefix.updatePrefix(nextWord);
		}
		return nextWord;
	}

	//I am consider can I use a keySet to find another? if we do so, when 
	//we generate a prefix, it won't be of the probability in the text. 

	private Random generator = new Random(); 
	private int prefixLength;//do I really need to save this?
	private Prefix currentPrefix;
	private Map<Prefix, ArrayList<String>> searchMap;

}