Prefix setupPrefix = new Prefix(prefixLength, text, SETUP);

for (int i = prefixLength; i < text.size(); i++ ) {
	String nextWord = text.get(i);
	
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
