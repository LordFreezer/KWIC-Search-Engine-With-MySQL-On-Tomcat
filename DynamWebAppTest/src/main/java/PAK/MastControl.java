package PAK;

import java.util.ArrayList;

/**
 * @author Chad Marshall and Stephen Key
 *
 */

/**
 * OVERVIEW: This class manages and executes shifting, alphabetizing, and
 * microminer operations.
 */
public class MastControl {
	// ArrayList that keeps track of the indices of the original LineStorage object
	public static ArrayList<Integer> indices = new ArrayList<Integer>();

	/**
	 * OVERVIEW: Manages the operations that take place on the Tomcat server.
	 * 
	 * @param ls      - Set of descriptions that are retrieved from MySQL database
	 * @param keyWord - The set of words that the user is querying the shifted
	 *                descriptions for.
	 */
	public MastControl(ILineSet ls, String keyWord) {
		// Original

		// Circular shifts each description up to the number of words that is in the
		// description.
		CircularShifter cs = new CircularShifter(ls);
		// Alphabetizes the set of shifted descriptions.
		Alphabetizer az = new Alphabetizer(cs);
		// Traverses through the set of descriptions to find the lines that include
		// the keywords
		MicroMiner m = new MicroMiner(az);
		// Keeps track of indices of found lines.
		indices = m.Search(keyWord);

	}

	/**
	 * @param ls - Prints out LineStorage object for debugging purposes
	 */
	public static void Display(ILineSet ls) {
		int f = 0, g = 0, h = 0;
		while (true) {
			if (ls.getChar(f, g, h) == 0) {
				h = 0;
				g++;
				System.out.print(" ");
			}
			if (ls.getChar(f, g, h) == 0) {
				g = 0;
				f++;
				System.out.print("\n");
			}
			if (ls.getChar(f, g, h) == 0) {
				break;
			}
			System.out.print(ls.getChar(f, g, h) + "");
			h++;
		}
	}

	/**
	 * OVERVIEW: Displays a single description and url pair. Used to populate the
	 * output box.
	 *
	 * @param ls the line
	 * @param i  the index
	 * @return the description and url pair as a single string.
	 */
	public String displayLine(LineStorage ls, int i) {
		int g = 0, h = 0;
		String result = "";
		while (true) {
			if (ls.getChar(i, g, h) == 0) {
				h = 0;
				g++;
				result += ' ';
			}
			if (ls.getChar(i, g, h) == 0) {
				break;
			}
			result += ls.getChar(i, g, h);
			h++;
		}
		result += "     " + ls.getUrl(i);
		return result;
	}
}