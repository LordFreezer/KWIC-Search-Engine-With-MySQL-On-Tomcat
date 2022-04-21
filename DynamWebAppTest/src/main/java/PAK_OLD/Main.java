package PAK_OLD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author eeeee
 */
public class Main {

	public static void main(String[] args) {
		LineStorage l = new LineStorage();
		addLine(l, "Hello World", "https//www.hello.world");
		addLine(l, "This is a test", "https//www.test.org");
		addLine(l, "Shared Data Design", "https//www.sharedata.design");
		addLine(l, "Another line", "https//www.one.more");
		addLine(l, "Big Beefy boi with lots and lots of words", "https//www.gobigbeef.org");
		/*
		 * addLine(l, "We need to test a few things"); addLine(l,
		 * "We need to test the alphabetizer"); addLine(l,
		 * "we need to test the alphabetizer"); addLine(l, "Something could be wrong");
		 * addLine(l, "This makes me nervous"); addLine(l, "This makes me stressed");
		 * addLine(l, "I thought it was working all along"); addLine(l,
		 * "Turns out it might not be"); addLine(l, "That is why I am writing lines");
		 * addLine(l, "Just need to start with different letters"); addLine(l,
		 * "Hopefully this works");
		 */

		// Display LineStorage
		System.out.println("--LINESTORAGE DISPLAY--");
		Display(l);
		System.out.println("--END LINESTORAGE DISPLAY--");

		// Test Circular Shifter
		System.out.println("--CIRCULAR SHIFTER TEST--");
		CircularShifter cs = new CircularShifter(l);
		Display(cs);
		System.out.println("--END CIRCULAR SHIFTER TEST--");

		// Test Alphabetizer
		System.out.println("--ALPHABETIZER TEST--");
		Alphabetizer az = new Alphabetizer(cs);
		Display(az);
		System.out.println("--END ALPHABETIZER TEST--");

		// Test Keyword retrieval
		System.out.println("--KEYWORD TEST--");
		DisplayKeyword(az);
		System.out.println("--END KEYWORD TEST--");
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
				System.out.print(" =>  " + ls.getUrl(f));
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

	public static void DisplayKeyword(Alphabetizer az) {
		int f = 0, g = 0, h = 0;
		while (true) {
			System.out.print("      Keyword: ");
			for (int i = 0; az.getChar(f, g, i) != 0; i++)
				System.out.print(az.getChar(f, g, i));
			System.out.print("\nOriginal Line: ");
			while (true) {
				if (az.getParentChar(f, g, h) == 0) {
					h = 0;
					g++;
					System.out.print(' ');
				}
				if (az.getParentChar(f, g, h) == 0) {
					System.out.println(" =>  " + az.getUrl(f) + '\n');
					g = 0;
					f++;
					break;
				}
				System.out.print(az.getParentChar(f, g, h));
				h++;
			}
			if (az.getParentChar(f, g, h) == 0) {
				break;
			}
		}
	}

	public static void addLine(LineStorage ls, String st, String url) {
		ls.addLine(url);
		ls.addWord();
		for (int g = 0; g < st.length(); g++) {
			if (st.charAt(g) == ' ')
				ls.addWord();
			else
				ls.addChar(st.charAt(g));
		}
	}
}
