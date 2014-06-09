
package fotos_graficos;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class PanelGraficos extends JPanel
{
    String file;
   
   public String Archivo()
   {
      JFileChooser dlg = new JFileChooser();

	// abre la ventana de dialogo
	int option = dlg.showOpenDialog(this);

	// si hace click en boton abrir del dialogo
	if(option == JFileChooser.APPROVE_OPTION)
    {
            // obtiene nombre de archivo seleccionado
            file= dlg.getSelectedFile().getPath();
	}
    return file;
 
  }   
}
