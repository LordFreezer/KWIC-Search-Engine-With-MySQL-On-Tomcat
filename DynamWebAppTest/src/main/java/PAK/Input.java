
/*
 * Software Architecture & Design
 * SE4433/CMSC5433
 * Assignment 5
 * 4/3/2022
 * Stephen Key and Chad Marshall
 */
package PAK;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * 
 */
public class Input {

	// Component Declarations.
	JFrame f = new JFrame();
	JButton b = new JButton();
	JButton r = new JButton();
	JTextArea t1 = new JTextArea();
	JTextArea t2 = new JTextArea();
	LineStorage local;
	int x;
	// List of lists that will hold each line, word by word.
	// static ArrayList<ArrayList<String>> box = new ArrayList<ArrayList<String>>();
	// static ArrayList<String> presentation = new ArrayList<String>();

	// Enums for ActionCommands
	public enum Actions {
		RESET, ENGAGE
	}

	/*
	 * Sets properties of display that user interacts with.
	 */
	public Input(LineStorage l) {

		ButtonEventHandler btnhandler = new ButtonEventHandler(this);

		// Add Components to Frame.
		f.add(b);
		f.add(r);
		f.add(t1);
		f.add(t2);

		// Apply settings to components.
		t1.setBounds(20, 100, 400, 400);
		t2.setBounds(520, 100, 400, 400);
		r.setBounds(700, 600, 80, 30);
		r.setLabel("Reset");
		r.setActionCommand(Actions.RESET.name());
		r.addActionListener(btnhandler);
		b.setBounds(820, 600, 80, 30);
		b.setLabel("Engage!");
		b.setActionCommand(Actions.ENGAGE.name());
		b.addActionListener(btnhandler);
		f.setSize(1000, 1000);
		f.setTitle("Webbased Search Engine");
		f.setLayout(null);
		f.setVisible(true);
		local = l;

	}

	public void Read() {

		String[] rowWords = t1.getText().split("\n");
		for (int i = 0; i < t1.getLineCount(); i++) {
			String[] colWords = rowWords[i].split(" ");
			local.addLine();
			for (int j = 0; j < colWords.length; j++) {
				local.addWord();
				for (int h = 0; h < colWords[j].length(); h++) {
					local.addChar(colWords[j].charAt(h));
					// System.out.print(colWords[j].charAt(h));
				}
				// System.out.print(' ');
			}
			// System.out.print('\n');
		}
	}

	/*
	 * EFFECTS: Acts as en event listener based on what the user does in the
	 * display.
	 * 
	 * @param ActionEvent that controls which event is executing from which Button.
	 * 
	 * @return None.
	 */
	/*
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * if (e.getActionCommand() == Actions.ENGAGE.name()) { Read(); // Output
	 * display = new Output(local); // display.Print(t2);
	 * 
	 * } else if (e.getActionCommand() == Actions.RESET.name()) { // box.clear(); //
	 * presentation.clear();
	 * 
	 * // Purge JTextAreas t1.setText(""); t2.setText("");
	 * 
	 * }
	 * 
	 * }
	 */

}