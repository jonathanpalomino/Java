/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reproductor.Librerias;

import Reproductor.Interfaz.Gui_play;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author JONATHAN
 */
public class Archivo {

    Propiedades prop;
    private Libreria_Tabla tabla;

    public void ABRIR(String tipo, String ultima_direccion) {
        JFileChooser archivo = new JFileChooser(ultima_direccion);
        archivo.setMultiSelectionEnabled(true);
        //archivo.setDragEnabled(true);
        archivo.getDragEnabled();
        FileNameExtensionFilter filtro_total = new FileNameExtensionFilter("Audio", CONVERTIR(getPropiedades().extencion_archivo));
        GENERAR_FILTRO(archivo);
        archivo.setFileFilter(filtro_total);
        if (tipo.equals("Archivos")) {
            archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int resultado = archivo.showOpenDialog(archivo);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            } else {
                try {
                    getPropiedades().GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getParent());
                    getTabla().LlenarTabla(archivo.getSelectedFiles());
                    // lib_tabla.objeto.LlenarTabla(archivo.getSelectedFiles());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Cargando Archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (tipo.equals("Carpeta")) {
            archivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resultado = archivo.showOpenDialog(archivo);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }
            try {
                getPropiedades().GUARDAR_ULTIMA_DIRECCION(archivo.getSelectedFile().getPath());
                getTabla().llamar(archivo.getSelectedFile());
                //tabla.objeto.llamar(archivo.getSelectedFile());
            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println(e);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Cargando Carpeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setPropiedades_conf(Propiedades pro) {
        prop = pro;
    }

    public Propiedades getPropiedades() {
        return prop;
    }

    private static String[] CONVERTIR(String[] extencion_archivo) {
        String ob[] = new String[extencion_archivo.length];
        System.out.println(extencion_archivo.length);
        for (int i = 0; i < extencion_archivo.length; i++) {
            ob[i] = extencion_archivo[i].substring(1);
            //System.out.println(ob[i]);
        }
        return ob;
    }

    void GENERAR_FILTRO(JFileChooser archivo) {
        FileNameExtensionFilter[] filtro = new FileNameExtensionFilter[getPropiedades().extencion_archivo.length];
        for (int i = 0; i < getPropiedades().extencion_archivo.length; i++) {
            filtro[i] = new FileNameExtensionFilter("Archivo " + getPropiedades().extencion_archivo[i].substring(1).toUpperCase(), getPropiedades().extencion_archivo[i].substring(1));
            archivo.setFileFilter(filtro[i]);
        }
    }

    public void GuardarLista(ListaDobleConOrden ldco, String ultima_lista) {
        String Filtroingresado = "";
        JFileChooser FileGuardar = new JFileChooser(ultima_lista);
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);

        int resultado = FileGuardar.showSaveDialog(FileGuardar);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        NodoDoble auxiliar = ldco.getInicio();

        if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos M3U") == 0) {
            Filtroingresado = ".m3u";
        } else if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos REP") == 0) {
            Filtroingresado = ".rep";
        }
        try {
            FileOutputStream salida = null;

            if (FileGuardar.getSelectedFile().toString().indexOf(Filtroingresado) != -1) {
                salida = new FileOutputStream(FileGuardar.getSelectedFile());
            } else {
                salida = new FileOutputStream(FileGuardar.getSelectedFile() + Filtroingresado);
            }


            BufferedOutputStream memoria = new BufferedOutputStream(salida);
            while (auxiliar != null) {
                Direcciones dir = auxiliar.getNodo();
                Object datos = dir.getDireccion();
                memoria.write((datos.toString() + System.getProperty("line.separator")).getBytes());//Separa por lineas

                //avanza al nodo siguiente
                auxiliar = auxiliar.getApuntSgte();
            }
            memoria.flush();
            salida.close();
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void CargarLista(String ultima_lista) {
        JFileChooser FileGuardar = new JFileChooser(ultima_lista);
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos Soportados", "m3u", "rep");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);
        FileGuardar.setFileFilter(filtro2);
        int resultado = FileGuardar.showOpenDialog(FileGuardar);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        Libreria_Tabla.getClaseMaestra().getLib_tabla().Traer_Lista(FileGuardar.getSelectedFile());
        try {
            Libreria_Tabla.getClaseMaestra().getPropiedades_conf().GUARDAR_ULTIMA_LISTA(FileGuardar.getSelectedFile().getPath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gui_play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTabla(Libreria_Tabla tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the lib_tabla
     */
    public Libreria_Tabla getTabla() {
        return tabla;
    }
}
