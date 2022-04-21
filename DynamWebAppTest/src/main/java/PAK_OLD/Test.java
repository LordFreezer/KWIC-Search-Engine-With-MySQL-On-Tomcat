package PAK_OLD;

public class Test {
	public static void main(String[] args) {
		// Declare new LineStorage
		/*
		 * LineStorage l = new LineStorage();
		 * 
		 * // Line 1 l.addLine(); l.addWord(); l.addChar('H'); l.addChar('e');
		 * l.addChar('l'); l.addChar('l'); l.addChar('o'); l.addWord(); l.addChar('W');
		 * l.addChar('o'); l.addChar('r'); l.addChar('l'); l.addChar('d');
		 * l.addChar('!');
		 * 
		 * // Line 2 l.addLine(); l.addWord(); l.addChar('S'); l.addChar('h');
		 * l.addChar('a'); l.addChar('r'); l.addChar('e'); l.addChar('d'); l.addWord();
		 * l.addChar('D'); l.addChar('a'); l.addChar('t'); l.addChar('a'); l.addWord();
		 * l.addChar('D'); l.addChar('e'); l.addChar('s'); l.addChar('i');
		 * l.addChar('g'); l.addChar('n');
		 * 
		 * char c = l.getChar(0, 0, 0); for (int f = 0; f < l.lineCount(); f++) { c =
		 * l.getChar(f, 0, 0); for (int g = 0; g < l.wordCountAt(f); g++) { c =
		 * l.getChar(f, g, 0); for (int h = 0; h < l.charCountAt(f, g); h++) { c =
		 * l.getChar(f, g, h); System.out.print(c); } System.out.print(' '); }
		 * System.out.print('\n'); }
		 */

		LineStorage l = new LineStorage();
//		addLine(l, "Hello World");
//		addLine(l, "This is a test");
//		addLine(l, "Shared Data Design");
//		addLine(l, "Another line");
//		addLine(l, "Big Beefy boi with lots and lots of words");
		Input display = new Input(l);
//		// Display LineStorage
//		System.out.println("--LINESTORAGE DISPLAY--");
//		Display(l);
//		System.out.println("--END LINESTORAGE DISPLAY--");
//
//		// Test Circular Shifter
//		System.out.println("--CIRCULAR SHIFTER TEST--");
//		CircularShifter cs = new CircularShifter(l);
//		Display(cs);
//		System.out.println("--END CIRCULAR SHIFTER TEST--");
//
//		// Test Alphabetizer
//		System.out.println("--ALPHABETIZER TEST--");
//		Alphabetizer az = new Alphabetizer(cs);
//		Display(az);
//		System.out.println("--END ALPHABETIZER TEST--");
	}

	public static void Display(ILineSet ls) {
		int f = 0, g = 0, h = 0;
		while (true) {
			if (ls.getChar(f, g, h) == 0) {
				h = 0;
				g++;
				System.out.print(' ');
			}
			if (ls.getChar(f, g, h) == 0) {
				g = 0;
				f++;
				System.out.print('\n');
			}
			if (ls.getChar(f, g, h) == 0) {
				break;
			}
			System.out.print(ls.getChar(f, g, h));
			h++;
		}
	}

	public static void addLine(LineStorage ls, String st) {
		ls.addLine();
		ls.addWord();
		for (int g = 0; g < st.length(); g++) {
			if (st.charAt(g) == ' ')
				ls.addWord();
			else
				ls.addChar(st.charAt(g));
		}
	}
}
