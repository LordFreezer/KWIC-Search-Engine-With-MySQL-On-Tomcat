package PAK;

/**
 *
 * @author Stephen Key
 */

public class Alphabetizer implements ILineSet {

	private int[] indices; // The array of indices pointint to each line in it's set
	private int[] where; // The array of indices indicating the first line that starts with each letter
	private char[] charTree = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i',
			'I', 'j', 'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S',
			't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z' };
	// ^^The array of every available character in a line, designed to run adjacent
	// to the where array
	private static CircularShifter reference;

	/**
	 * OVERVIEW: Constructor, creates a list of sorted indices based on the
	 * alphabetical order of the lines in those indices. The order is: a<A<b<B< �
	 * <y<Y<z<Z
	 * 
	 * @param ls The ILineSet Object it will read characters from
	 */
	public Alphabetizer(CircularShifter ls) {
		reference = ls;
		where = new int[charTree.length];
		Sort();
	}

	/**
	 * OVWRVIEW: Sorts its indices based on the alphabetical order of the lines in
	 * those indices. The order is: (a<A<b<B< � <y<Y<z<Z), Also fills the where
	 * array with indices of the first lines that start wiht each letter
	 */
	public void Sort() {
		// Step 1: Fill the array of line indices
		indices = new int[reference.lineCount()];
		for (int g = 0; g < indices.length; g++) {
			indices[g] = g;
		}

		// Step 2: Calculate the sorted order of ref's lines
		int temp, pos;
		for (int g = 0; g < indices.length; g++) {
			temp = indices[g];
			pos = g;
			for (int h = g + 1; h < indices.length; h++) {
				if (Compare(indices[h], temp, charTree)) {
					temp = indices[h];
					pos = h;
				}
			}
			indices[pos] = indices[g];
			indices[g] = temp;
		}

		// Step 3: Fill the where array with indices
		int i = 0, j = 0;
		while (i < indices.length && j < where.length) {
			// If this letter is not found within this set, set the index to -1
			while (reference.getChar(indices[i], 0, 0) != charTree[j]) {
				where[j] = -1;
				j++;
			}
			where[j] = i;
			// If the next line starts with the same letter as the last line, skip ahead
			while (i < indices.length && reference.getChar(indices[i], 0, 0) == charTree[j])
				i++;
			j++;
		}
		// We've reached the end of this line set. Fill the rest of the where indices
		// with -1
		while (j < where.length) {
			where[j] = -1;
			j++;
		}
	}

	/**
	 * OVERVIEW: Determines if one line is alphabetically less than the other
	 *
	 * @param line1 The first line to compare
	 * @param line2 The other line to compare
	 * @return true if s1 is less than or equal to s2, false if it is not
	 */
	private static boolean Compare(int line1, int line2, char[] ref) {
		int min = Math.min(reference.wordCountAt(line1), reference.wordCountAt(line2));
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
	 * @param j The index of the word to search for within that line
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

	/**
	 * OVERVIEW: Returns the url string of the line at the specified index
	 * 
	 * @param i The index of the line to search for
	 * @return The url string of the line at that index
	 */
	@Override
	public String getUrl(int i) {
		return reference.getUrl(indices[i]);
	}

	/**
	 * OVERVIEW: Makes a request to CircularShifter's getParentChar method to return
	 * a character from this line as if it wasn't shifted
	 * 
	 * @param i The index of the line to search for
	 * @param j The index of the word to search for within that line
	 * @param k The index of the character to search for within that word
	 * @return The char value at that index
	 */
	public char getParentChar(int i, int j, int k) {
		if (i < indices.length && i >= 0)
			return reference.getParentChar(indices[i], j, k);
		else
			return 0;
	}

	/**
	 * OVERVIEW: Returns the index of the first line that starts with the specified
	 * character
	 * 
	 * @param c The character to search for
	 * @return The index of the first line in this set that starts with that
	 *         character
	 */
	public int whereIs(char c) {
		for (int g = 0; g < charTree.length; g++) {
			if (charTree[g] == c)
				return where[g];
		}
		return -1;
	}

	/**
	 * OVERVIEW: returns the original index of the line at the specified index,
	 * ignoring circular shifts.
	 * 
	 * @param i the index of the line to search for
	 * @return The index of the "parent line"
	 */
	public int getParentIndex(int i) {
		if (i >= indices.length || i < 0)
			return 0;
		else
			return reference.getParentIndex(indices[i]);
	}
}
