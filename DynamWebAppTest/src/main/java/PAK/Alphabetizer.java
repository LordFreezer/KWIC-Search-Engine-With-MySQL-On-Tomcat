package PAK;

/**
 *
 * @author Stephen Key
 */

public class Alphabetizer implements ILineSet {

	private static int[] indices;
	private ILineSet reference;

	/**
	 * OVERVIEW: Constructor, creates a list of sorted indices based on the
	 * alphabetical order of the lines in those indices. The order is: a<A<b<B< …
	 * <y<Y<z<Z
	 * 
	 * @param ls The ILineSet Object it will read characters from
	 */
	public Alphabetizer(ILineSet ls) {
		reference = ls;
		Sort();

	}

	/**
	 * OVWRVIEW: Sorts its indices based on the alphabetical order of the lines in
	 * those indices. The order is: a<A<b<B< … <y<Y<z<Z
	 */
	private void Sort() {
		// Fill the array of line indices
		indices = new int[reference.lineCount()];
		for (int g = 0; g < indices.length; g++) {
			indices[g] = g;
		}
		int temp;
		int pos;
		for (int g = 0; g < indices.length; g++) {
			temp = indices[g];
			pos = g;
			for (int h = g + 1; h < indices.length; h++) {
				if (Compare(indices[h], temp)) {
					temp = indices[h];
					pos = h;
				}
			}
			indices[pos] = indices[g];
			indices[g] = temp;
		}
	}

	/**
	 * OVERVIEW: Determines if one line is alphabetically less than the other
	 *
	 * @param line1 The first line to compare
	 * @param line2 The other line to compare
	 * @return true if s1 is less than or equal to s2, false if it is not
	 */
	private boolean Compare(int line1, int line2) {
		int min = Math.min(reference.wordCountAt(line1), reference.wordCountAt(line2));
		char ref[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };
		int i1 = 0, i2 = 0, x = 0, y = 0, a = 0, b = 0;
		char c1, c2;
		while (x < min && y < min) {
			c1 = reference.getChar(line1, x, y);
			c2 = reference.getChar(line2, a, b);
			// Find the index of the character from the first line
			for (int h = 0; h < 52; h++) {
				if (ref[h] == c1) {
					i1 = h;
					break;
				}
				// throw new Exception("Character is not a letter");
			}
			// Find the index of the character from the second line
			for (int h = 0; h < 52; h++) {
				if (ref[h] == c2) {
					i2 = h;
					break;
				}
				// throw new Exception("Character is not a letter or space.");
			}
			// Compare the two indexes
			if (i1 > i2) {
				return false;
			} else if (i1 < i2) {
				return true;
			}

			// Find the next character in each line
			y++;
			b++;
			if (reference.getChar(line1, x, y) == 0) {
				y = 0;
				x++;
			}
			if (reference.getChar(line1, a, b) == 0) {
				b = 0;
				a++;
			}
		}
		return reference.wordCountAt(line1) <= reference.wordCountAt(line2);
	}

	/**
	 * OVERVIEW: Returns the k'th character of the j'th word of the i'th line in the
	 * list based on the alphabetizer's alphabetical indices
	 * 
	 * @param i The index of the line to search for
	 * @param j The index of the line to search for within that set
	 * @param k The index of the character to search for within that word
	 * @return The char value at that index
	 */
	@Override
	public char getChar(int i, int j, int k) {
		if (i < indices.length && i >= 0)
			return reference.getChar(indices[i], j, k);
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
		return reference.lineCount();
	}

	/**
	 * OVERVIEW: returns the word count of the line at the specified index
	 * 
	 * @param i The index of the line to search for
	 * @return The word count of that line
	 */
	@Override
	public int wordCountAt(int i) {
		if (i < reference.lineCount() && i >= 0)
			return reference.wordCountAt(indices[i]);
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
		if (i < reference.lineCount() && i >= 0)
			return reference.charCountAt(indices[i], j);
		else
			return 0;
	}
}
