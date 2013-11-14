// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA1
// Fall 2013


public class TextProcessor {
	//constructor
	public TextProcessor() {
		text = new ArrayList<String>();
		searchMap = new HashMap<Prefix, ArrayList<String>>();
		prefixLength = 1;
		sourceFileName = "";
	}

	//constructor
	public TextProcessor(int prefixLength, String sourceFileName) {
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

	private int prefixLength;
	private ArrayList<String> text;
	private Map<Prefix, ArrayList<String>> searchMap;
	private String sourceFileName;
}