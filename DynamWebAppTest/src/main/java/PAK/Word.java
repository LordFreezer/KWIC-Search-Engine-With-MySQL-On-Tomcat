package PAK;

import java.util.ArrayList;

/**
 *
 * @author Stephen Key
 */
public class Word {
	private ArrayList<Character> chars;

	/**
	 * OVERVIEW: Constructor, initializes chars attribute
	 */
	public Word() {
		chars = new ArrayList();
	}

	/**
	 * OVERVIEW: Sets the value at the specified index to the specified char value
	 * 
	 * @param i The index to set the value
	 * @param c The value to change
	 */
	public void setChar(int i, char c) {
		if (i <= chars.size() && i >= 0)
			chars.add(i, c);
	}

	/**
	 * OVERVIEW: Adds a character to the end of the word
	 * 
	 * @param c The character to be added
	 */
	public void addChar(char c) {
		chars.add(c);
	}

	/**
	 * OVERVIEW: returns a char value at the specified index of the word or a
	 * control value if the index is out of bounds
	 * 
	 * @param i The index to read
	 * @return The char value at that index
	 */
	public char getChar(int i) {
		if (i < chars.size() && i >= 0)
			return chars.get(i);
		else
			return 0;
	}

	/**
	 * OVERVIEW: returns the number of characters in this word
	 * 
	 * @return an integer value of the size of the chars list
	 */
	public int charCount() {
		return chars.size();
	}
}