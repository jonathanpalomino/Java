/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ventana;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JFrameUtils {
	public void createJFrame() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Frame");
		removeMinMaxButtons(frame);
		frame.setSize(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private int buttonsRemoved = 0;
	public void removeMinMaxButtons(Component component) {
		if (component instanceof JButton) {
			if (buttonsRemoved < 2) {
				component.getParent().remove(component);
				buttonsRemoved++;
			} else {
				buttonsRemoved = 0;
			}
		}
		if (component instanceof Container) {
			Component[] components = ((Container) component).getComponents();
			for (int i = 0; i < components.length; i++) {
				removeMinMaxButtons(components[i]);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JFrameUtils().createJFrame();
			}
		});
	}
}
