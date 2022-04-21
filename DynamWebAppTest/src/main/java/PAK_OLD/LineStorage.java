package PAK_OLD;

import java.util.ArrayList;

/**
 *
 * @author Stephen Key
 */
public class LineStorage implements ILineSet {
	private ArrayList<Line> lines;

	public LineStorage() {
		lines = new ArrayList();
	}

	/**
	 * OVERVIEW: Sets the j'th character in the i'th word to the specified char
	 * value
	 * 
	 * @param i The index of the line to search for
	 * @param j The index or word to search for within that line
	 * @param k The index or character to search for within that word
	 * @param c The value to set that character to
	 */
	public void setChar(int i, int j, int k, char c) {
		if (i < lines.size() && i >= 0)
			lines.get(i).setChar(j, k, c);
	}

	/**
	 * OVERVIEW: Adds the specified character to the end of the last line in the
	 * line set
	 * 
	 * @param c The character to add
	 */
	public void addChar(char c) {
		lines.get(lines.size() - 1).addChar(c);
	}

	/**
	 * OVERVIEW: Adds a new empty word to the end of the line set
	 */
	public void addWord() {
		lines.get(lines.size() - 1).addWord();
	}

	/**
	 * OVERVIEW: Adds a new empty line to the end of the set
	 */
	public void addLine() {
		lines.add(new Line());
	}

	/**
	 * OVERVIEW: Adds a new line to the end of the set with the specified URL string
	 * 
	 * @param url The url string to insert
	 */
	public void addLine(String url) {
		lines.add(new Line(url));
	}

	/**
	 * OVERVIEW: Returns the k'th character of the j'th word of the i'th line in the
	 * list
	 * 
	 * @param i The index of the line to search for
	 * @param j The index of the line to search for within that set
	 * @param k The index of the character to search for within that word
	 * @return The char value at that index
	 */
	@Override
	public char getChar(int i, int j, int k) {
		if (i < lines.size() && i >= 0)
			return lines.get(i).getChar(j, k);
		else
			return 0;
	}

	/**
	 * OVERVIEW: returns the number of lines in this set
	 * 
	 * @return an integer value of the size of the lines list
	 */
	@Override
	public int lineCount() {
		return lines.size();
	}

	/**
	 * OVERVIEW: returns the word count of the line at the specified index
	 * 
	 * @param i The index of the line to search for
	 * @return The word count of that line
	 */
	@Override
	public int wordCountAt(int i) {
		if (i < lines.size() && i >= 0)
			return lines.get(i).wordCount();
		else
			return 0;
	}

	/**
	 * OVERVIEW: Returns the length of the j'th word in the i'th line
	 * 
	 * @param i the index of the line to search for
	 * @param j the index of the word to search for within that line
	 * @return The length of that word
	 */
	@Override
	public int charCountAt(int i, int j) {
		if (i < lines.size() && i >= 0)
			return lines.get(i).charCountAt(j);
		else
			return 0;
	}

	public String getUrl(int i) {
		return lines.get(i).getUrl();
	}
}
