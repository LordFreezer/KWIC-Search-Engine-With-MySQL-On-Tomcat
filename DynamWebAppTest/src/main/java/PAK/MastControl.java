package PAK;

import java.util.ArrayList;

/**
 * 
 */
public class MastControl {
	public ILineSet reference;
	public String url;
	public static ArrayList<Integer> purged = new ArrayList<Integer>();

	/**
	 * Default constructor
	 */
	/*
	 * public MastControl(Input window) { // Original CircularShifter cs = new
	 * CircularShifter(window.local); Alphabetizer az = new Alphabetizer(cs);
	 * Display(az, window.t2);
	 * 
	 * // Order Swap // Alphabetizer az = new Alphabetizer(window.local); //
	 * CircularShifter cs = new CircularShifter(az); // Display(cs, window.t2); }
	 */

	public MastControl(ILineSet ls, String keyWord) {
		// Original

		CircularShifter cs = new CircularShifter(ls);
		Alphabetizer az = new Alphabetizer(cs);
		reference = az;
		// Display(az);
		MicroMiner m = new MicroMiner(az);
		purged = m.Search(keyWord);

		// url = DisplayKeyword(az, keyWord);
		// Display(reference);
		// Order Swap
		// Alphabetizer az = new Alphabetizer(window.local);
		// CircularShifter cs = new CircularShifter(az);
		// Display(cs, window.t2);
	}

	public static void Display(ILineSet ls) {
		int f = 0, g = 0, h = 0;
		while (true) {
			if (ls.getChar(f, g, h) == 0) {
				h = 0;
				g++;
				System.out.print(" ");
				// t2.append(" ");

			}
			if (ls.getChar(f, g, h) == 0) {
				g = 0;
				f++;
				System.out.print("\n");
				// t2.append("\n");
			}
			if (ls.getChar(f, g, h) == 0) {
				break;
			}
			System.out.print(ls.getChar(f, g, h) + "");
			// t2.append(ls.getChar(f, g, h) + "");
			h++;
		}
	}

	public String displayLine(LineStorage ls, int i) {
		int g = 0, h = 0;
		String result = "";
		while (true) {
			if (ls.getChar(i, g, h) == 0) {
				h = 0;
				g++;
				// System.out.print(' ');
				result += ' ';
			}
			if (ls.getChar(i, g, h) == 0) {
				break;
			}
			// System.out.print(ls.getChar(i, g, h));
			result += ls.getChar(i, g, h);
			h++;
		}
		// System.out.println(" => " + ls.getUrl(i));
		result += "     " + ls.getUrl(i);
		return result;
	}
}