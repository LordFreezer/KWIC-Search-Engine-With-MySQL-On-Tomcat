package PAK;

import javax.swing.JTextArea;

/**
 * 
 */
public class Output {
	LineStorage local;

	/**
	 * Default constructor
	 */
	public Output(LineStorage l) {
		local = l;
	}

	public void Print(JTextArea t2) {
		char c = local.getChar(0, 0, 0);
		for (int f = 0; f < local.lineCount(); f++) {
			c = local.getChar(f, 0, 0);
			for (int g = 0; g < local.wordCountAt(f); g++) {
				String word = "";
				c = local.getChar(f, g, 0);
				for (int h = 0; h < local.charCountAt(f, g); h++) {
					c = local.getChar(f, g, h);
					System.out.print(c);
					word += c;
				}
				System.out.print(' ');
				t2.append(word);
				t2.append(" ");
			}
			System.out.print('\n');
			t2.append("\n");
		}
	}

}