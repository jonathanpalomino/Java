/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ventana;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 *
 * @author JONATHAN
 */
public class AccionMenu extends AbstractAction {
	private String textoOpcion;

	/**
	 * Se le pasa el nombre que se quiere que se muestre
	 *
	 * @param textoOpcion
	 */
	public AccionMenu(String textoOpcion) {
		this.textoOpcion = textoOpcion;
		this.putValue(Action.NAME, textoOpcion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Pulsado " + textoOpcion);
	}
}

