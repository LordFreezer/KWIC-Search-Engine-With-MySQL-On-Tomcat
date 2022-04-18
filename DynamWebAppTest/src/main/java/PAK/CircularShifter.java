package PAK;

import java.util.ArrayList;

/**
 *
 * @author Stephen Key
 */
public class CircularShifter implements ILineSet {

	private ILineSet reference;
	private ArrayList<Integer> lines;
	private ArrayList<Integer> offsets;

	/**
	 * OVERVIEW: Constructor, stores a value the reference attribute and creates a
	 * list of virtual circularly shifted lines
	 * 
	 * @param ls The ILineSet Object it will read characters from
	 */
	public CircularShifter(ILineSet ls) {
		reference = ls;
		lines = new ArrayList<Integer>();
		offsets = new ArrayList<Integer>();
		makeShift();
	}

	/**
	 * OVERVIEW: Creates a set of virtual circularly shifted lines based on its
	 * reference set repeatedly moving the first word in one line to the end of that
	 * line, excludes lines that start with noise words
	 */
	private void makeShift() {
		for (int g = 0; g < reference.lineCount(); g++) {
			for (int h = 0; h < reference.wordCountAt(g); h++) {
				if (!isFiller(g, h)) {
					lines.add(g);
					offsets.add(h);
				}
			}
		}
	}

	/**
	 * OVERVIEW: Used to determine if a virtual shifted line would start with a
	 * noise word determined by the ref array
	 * 
	 * @param i The index of the line to Search for
	 * @param j The index of the word to search for within that line
	 * @return If that word is found in the ref array
	 */
	private boolean isFiller(int i, int j) {
		String[] ref = { "a", "an", "the", "and", "or", "of", "to", "be", "is", "in", "out", "by", "as", "at", "off" };
		String word = "";
		for (int g = 0; g < reference.charCountAt(i, j); g++)
			word += reference.getChar(i, j, g);
		for (String s : ref) {
			if (word.equals(s))
				return true;
		}
		return false;
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
		if (i >= lines.size() || i < 0)
			return 0;
		if (j >= reference.wordCountAt(lines.get(i)))
			return 0;
		else {
			int a = j + offsets.get(i);
			if (a >= reference.wordCountAt(lines.get(i))) {
				a -= reference.wordCountAt(lines.get(i));
			}
			return reference.getChar(lines.get(i), a, k);
		}
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
		if (i < lines.size() && i >= 0) {
			return reference.wordCountAt(lines.get(i));
		} else {
			return 0;
		}
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
		if (i >= lines.size() || i < 0)
			return 0;
		if (j >= reference.wordCountAt(lines.get(i)))
			return 0;
		else {
			int a = j + offsets.get(i);
			if (a >= reference.wordCountAt(lines.get(i))) {
				a -= reference.wordCountAt(lines.get(i));
			}
			return reference.charCountAt(lines.get(i), a);
		}
	}

}
