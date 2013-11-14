// a RandomTextGenerator class
// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013


public class RandomTextGenerator {

	//constructor
	public RandomTextGenerator() {
		text = new ArrayList<String>();
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		prefixLength = 1;
		sourceFileName = "";
	}

	//constructor
	public RandomTextGenerator(int prefixLength, String sourceFileName) {
		text = new ArrayList<String>();
		searchMap = new Hashmap<Prefix, ArrayList<String>>();
		this.prefixLength = prefixLength;
		this.sourceFileName = sourceFileName;
	}
	
	//According to the prefixLength, we cut the text and generate
	//a Hashmap with prefixes as Keyset. 
	public void textToHashmap() {
		for (int i = 0; i <= (text.length() - prefixLength); i++) {
			LinkedList<String> currPrefix = new LinkedList<String>();
			int j = 0;
			while (j < prefixLength) {
				currPrefix.addLast(text.get(j + i));
				j++;
			}
			Prefix tempPrefix = new Prefix(currPrefix);
			String nextWord;
			if ((i + j) == text.length()) {
				nextWord = "end of file";
			}
			else {
				nextWord = text.get(i + j);
			}
			if (searchMap.get(tempPrefix) == null) {
				ArrayList<String> newValue = new ArrayList<String>();
				newValue.add(nextWord);
				searchMap.put(tempPrefix, newValue)
			}
			else {
				searchMap.put(tempPrefix,searchMap.get(tempPrefix).add(nextWord));
			}
		}
	}
	
	//read text into ArrayList text;
	public void readInText() {
		File sourceFile = new File(sourceFileName);
		Scanner in = new Scanner(sourceFile);//exception handler needed
		while (in.hasNext()) {
			text.add(in.next());
		}
	}
	
	public String lookup(Prefix currentPrefix, TextProcessor textPro) {
		ArrayList<String> currValue = textPro.searchMap.get(currentPrefix);//what if get null
		int indexWords = generator.nextInt(currValue.size());
		String nextWord = currValue.get(indexWords);
		while (nextWord.equal("end of file")) {
			
			// currentPrefix.randomPrefix(textPro);
			
			currValue = textPro.searchMap.get(currentPrefix);//what if get null
			indexWords = generator.nextInt(currValue.size());
			nextWord = currValue.get(indexWords);			
		}
		currentPrefix.updatePrefix(nextWord);
		return nextWord;
	}

	private Random generator = new Random(); 
	private int prefixLength;
	private ArrayList<String> text;
	private Map<Prefix, ArrayList<String>> searchMap;
	private String sourceFileName;

}