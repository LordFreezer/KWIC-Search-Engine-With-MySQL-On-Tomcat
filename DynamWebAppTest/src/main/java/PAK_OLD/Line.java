package PAK_OLD;

import java.util.ArrayList;

/**
 *
 * @author Stephen Key
 */
public class Line {
	private ArrayList<Word> words;
	private String url;

	/**
	 * OVERVIEW: Constructor, initializes the words attribute
	 */
	public Line() {
		words = new ArrayList();
	}

	/**
	 * OVERVIEW: Constructor, initializes the words and url attributes
	 * 
	 * @param s The url string to initialize
	 */
	public Line(String s) {
		words = new ArrayList();
		url = s;
	}

	/**
	 * OVERVIEW: Sets the j'th character in the i'th word to the specified char
	 * value
	 * 
	 * @param i The index of the word to search for
	 * @param j The index or character to search for within that word
	 * @param c The value to set that character to
	 */
	public void setChar(int i, int j, char c) {
		if (i < words.size() && i >= 0)
			words.get(i).setChar(j, c);
	}

	/**
	 * OVERVIEW: Adds the specified character to the end of the last word in the
	 * line
	 * 
	 * @param c The character to add
	 */
	public void addChar(char c) {
		words.get(words.size() - 1).addChar(c);
	}

	/**
	 * OVERVIEW: Adds a new empty word to the end of the line
	 */
	public void addWord() {
		words.add(new Word());
	}

	/**
	 * OVERVIEW: Returns the j'th character of the i'th word in the list
	 * 
	 * @param i The index of the word to search for
	 * @param j The index of the character to search for within that word
	 * @return The char value at that index
	 */
	public char getChar(int i, int j) {
		if (i < words.size() && i >= 0)
			return words.get(i).getChar(j);
		else
			return 0;
	}

	/**
	 * OVERVIEW: returns the number of words in this line
	 * 
	 * @return an integer value of the size of the words list
	 */
	public int wordCount() {
		return words.size();
	}

	/**
	 * OVERVIEW: returns the length of the word at the specified index
	 * 
	 * @param i The index of the word to search for
	 * @return The length of that word
	 */
	public int charCountAt(int i) {
		if (i < words.size() && i >= 0)
			return words.get(i).charCount();
		else
			return 0;
	}

	public void setUrl(String s) {
		url = s;
	}

	public String getUrl() {
		return url;
	}
}
