/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.documentos.texto;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author synccon
 */
public interface Interface_Texto {
    public void Inicializar_Lectura(String ruta) throws IOException;
    public void Inicializar_Lectura(File path) throws IOException;
    public void Leer_Texto()  throws IOException;
    public void Cerrar_Lectura() throws IOException;
    

    public void Inicializar_Escritura(String ruta) throws IOException;
    public void Inicializar_Escritura(File path) throws IOException;
    public void Escribir_Texto(String contenido) throws IOException;
    public void Cerrar_Escritura() throws IOException;
    
}
