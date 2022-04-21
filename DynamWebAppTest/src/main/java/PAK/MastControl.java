package PAK;

import java.util.ArrayList;

import javax.swing.JTextArea;

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
		Display(az);
		url = DisplayKeyword(az, keyWord);
		// Display(reference);
		// Order Swap
		// Alphabetizer az = new Alphabetizer(window.local);
		// CircularShifter cs = new CircularShifter(az);
		// Display(cs, window.t2);
	}

	public static String DisplayKeyword(Alphabetizer az, String keyWord) {
		/*
		 * int f = 0, g = 0, h = 0; while (true) { System.out.print("      Keyword: ");
		 * for (int i = 0; az.getChar(f, g, i) != 0; i++) System.out.print(az.getChar(f,
		 * g, i)); System.out.print("\nOriginal Line: "); while (true) { if
		 * (az.getParentChar(f, g, h) == 0) { h = 0; g++; System.out.print(' '); } if
		 * (az.getParentChar(f, g, h) == 0) { System.out.println(" =>  " + az.getUrl(f)
		 * + '\n'); g = 0; f++; break; } System.out.print(az.getParentChar(f, g, h));
		 * h++; } if (az.getParentChar(f, g, h) == 0) { break; } }
		 */

		ArrayList<String> foundLines = new ArrayList<String>();
		int f = 0, i = 0;
		int g = 0, h = 0;
		int j = 0, k = 0;

		// String keyWord = "wordsBigBeefy";
		String shiftedLine = "";
		String originalLine = "";
		String url = "";
		MicroMiner get = new MicroMiner(az);
		boolean notfound = false;
		// System.out.println("KEYWORD" + keyWord);
		while (true) {

			while (true) {
				if (az.getChar(f, g, h) == 0) {
					h = 0;
					g++;
				}
				/*
				 * if (az.getParentChar(i, j, k) == 0) { k = 0; j++; }
				 */
				if (az.getChar(f, g, h) == 0) {

					// if (shiftedLine.contains(keyWord)) {
					// System.out.println("\nSHIFTEDLINE: " + shiftedLine);
					// get.displayParentLine(f);
					// url = az.getUrl(f);
					// foundLines.add(url);
					// }

					purged = get.Search(keyWord);

					// foundLines = get.result;
					g = 0;
					f++;
					shiftedLine = "";
					break;
				}
				/*
				 * if (az.getParentChar(i, j, k) == 0) { if (notfound) {
				 * System.out.println("\nORIGINALLINE: " + originalLine); // url = az.getUrl(i);
				 * // foundLines.add(url); // break; } j = 0; i++; originalLine = ""; break; }
				 * 
				 * 
				 * originalLine += az.getParentChar(i, j, k); k++;
				 */
				shiftedLine += az.getChar(f, g, h);
				h++;
			}
			if (az.getParentChar(f, g, h) == 0) {
				break;
			}
		}

		return url;
	}

	public static void Display(ILineSet ls, JTextArea t2) {
		int f = 0, g = 0, h = 0;
		while (true) {
			if (ls.getChar(f, g, h) == 0) {
				h = 0;
				g++;
				// System.out.print(' ');
				t2.append(" ");

			}
			if (ls.getChar(f, g, h) == 0) {
				g = 0;
				f++;
				// System.out.print('\n');
				t2.append("\n");
			}
			if (ls.getChar(f, g, h) == 0) {
				break;
			}
			// System.out.print(ls.getChar(f, g, h));
			t2.append(ls.getChar(f, g, h) + "");
			h++;
		}
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

	public static void displayLine(LineStorage ls, int i) {
		int g = 0, h = 0;
		while (true) {
			if (ls.getChar(i, g, h) == 0) {
				h = 0;
				g++;
				System.out.print(' ');
			}
			if (ls.getChar(i, g, h) == 0) {
				break;
			}
			System.out.print(ls.getChar(i, g, h));
			h++;
		}
		System.out.println(" =>  " + ls.getUrl(i));
	}
}