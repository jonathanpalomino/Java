/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.coneccion;

/**
 *
 * @author JONATHAN
 */
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ModificadorClassPath {

    private static final String METODO_ADD_URL = "addURL";
    private static final Class[] PARAMETRO_METODO = new Class[]{URL.class};
    private final URLClassLoader loader;
    private final Method metodoAdd;

    public ModificadorClassPath() throws NoSuchMethodException {
        loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        metodoAdd = URLClassLoader.class.getDeclaredMethod(
                                          METODO_ADD_URL, PARAMETRO_METODO);
        metodoAdd.setAccessible(true);
    }

    public URL[] getURLs() {
        return loader.getURLs();
    }

    public void addURL(URL url) {
        if (url != null) {
            try {
                metodoAdd.invoke(loader, new Object[]{url});
            } catch (Exception ex) {
                System.err.println("Excepcion al guardar URL: " + 
                                                              ex.getLocalizedMessage());
            }
        }
    }

    public void addURLs(URL[] urls) {
        if (urls != null) {
            for (URL url : urls) {
                addURL(url);
            }
        }
    }

    public void addArchivo(File archivo) throws MalformedURLException {
        if (archivo != null) {
            addURL(archivo.toURI().toURL());
        }
    }

    public void addArchivo(String nombreArchivo) throws MalformedURLException {
        addArchivo(new File(nombreArchivo));
    }
}
