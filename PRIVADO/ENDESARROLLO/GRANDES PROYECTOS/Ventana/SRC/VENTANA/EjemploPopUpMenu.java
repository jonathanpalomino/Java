/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ventana;

/**
 *
 * @author JONATHAN
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;

/**
 * Ejemplo de JPopupMenu. Muestra una ventana con un JLabel sobre el que se
 * puede desplegar un menú.
 *
 */
public class EjemploPopUpMenu {

	/**
	 * El JPopupMenu. Se hace estático para poder acceder a él desde el
	 * MouseListener.
	 */
	private static JPopupMenu popup;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Construccion y visualizacion de la ventana.
		JFrame v = new JFrame("Ejemplo JPopupMenu");
		JComponent cualquierJComponent = new JLabel("Tengo un menú");
		v.getContentPane().add(cualquierJComponent);
		v.pack();
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v.setVisible(true);

		// Construccion del JPopupMenu
		popup = new JPopupMenu();
		popup.add(new AccionMenu("uno"));
		popup.add(new AccionMenu("dos"));
		popup.add(new AccionMenu("tres"));

		// Se añade el JPopupMenu al JLabel.
		// Debemos implementar todos los metodos de MouseListener porque
		// no sabemos a priori que evento usara el sistema operativo para
		// mostrar los menus.
		cualquierJComponent.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				muestraMenu(e);
			}

			/**
			 * Método que muestra el menú.
			 *
			 * @param e
			 */
			private void muestraMenu(MouseEvent e) {
				// isPopupTrigger() indica si es el evento de raton
				// por defecto en el sistema operativo para mostrar
				// el menu.
				if (e.isPopupTrigger()) {
					popup.setLocation(e.getLocationOnScreen());
					popup.setVisible(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				muestraMenu(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				muestraMenu(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				muestraMenu(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				muestraMenu(e);
			}

		});
	}

}

