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
		currentPrefix = new Prefix(prefixLength, text, RANDOM);
		this.textToHashmap(text);
	}
	

	//According to the prefixLength, we cut the text and generate
	//a Hashmap with prefixes as Keyset. 
	//precondiction: text.length >= prefixLength + 1;
	public void textToHashmap(ArrayList<String> text) {
		Prefix setupPrefix = new Prefix(prefixLength, text, SETUP);

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

			setupPrefix.update(nextWord);
		}


	}
	
	//look up current prefix,return the next word based on probability,
	//construct a new prefix if we reach the end of file.
	public String generate(ArrayList<String> text) {
		currentPrefix = 
	}

	private Random generator = new Random(); 
	private int prefixLength;//do I really need to save this?
	private Prefix currentPrefix;
	private Map<Prefix, ArrayList<String>> searchMap;

}