package PAK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import PAK.Input.Actions;

public class ButtonEventHandler implements ActionListener {
	private Input window;

	public ButtonEventHandler(Input window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		final long startTime = System.currentTimeMillis();
		if (event.getSource() instanceof JButton) {
			if (event.getActionCommand() == Actions.ENGAGE.name()) {
				window.Read();

				MastControl controller = new MastControl(window);

			} else if (event.getActionCommand() == Actions.RESET.name()) {

				// Purge JTextAreas
				window.t1.setText("");
				window.t2.setText("");
			}
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) + "ms");

	}
}
