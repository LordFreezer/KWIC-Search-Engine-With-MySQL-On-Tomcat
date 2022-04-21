package PAK_OLD;

import javax.swing.JTextArea;

/**
 * 
 */
public class MastControl {
	public ILineSet reference;

	/**
	 * Default constructor
	 */

	public MastControl(Input window) {
		// Original CircularShifter cs = new

		// CircularShifter(window.local); Alphabetizer az = new Alphabetizer(cs);
		// Display(az, window.t2);

		// Order Swap // Alphabetizer az = new Alphabetizer(window.local); //
		// CircularShifter cs = new CircularShifter(az); // Display(cs, window.t2);
	}

	public MastControl(ILineSet ls) {
		// Original
		CircularShifter cs = new CircularShifter(ls);
		Alphabetizer az = new Alphabetizer(cs);
		reference = az;
		// Display(reference);
		// Order Swap
		// Alphabetizer az = new Alphabetizer(window.local);
		// CircularShifter cs = new CircularShifter(az);
		// Display(cs, window.t2);
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
}