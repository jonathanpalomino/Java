/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ventana;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author JONATHAN
 */
public class Main extends JDialog{

Main(JFrame frame, String str)
{
    super(frame,str);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
				System.exit(0);
            }
        });
}
    public static void main(String[] args) {
        		try{
			Main frame = new Main(new JFrame(), "Remove the Minimize and Maximize button from the Title Bar");
                        JPanel panel = new JPanel();
			panel.setSize(200,200);
			JLabel lbl = new JLabel("RoseIndia.Net");
			panel.add(lbl);
			frame.add(panel);
			frame.setSize(300, 300);
			frame.setVisible(true);
		}
		catch(IllegalArgumentException e){
			System.exit(0);
		}
    }

}
