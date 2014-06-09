/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reproductor.Librerias;

import Reproductor.Interfaz.Gui_play;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSlider;

/**
 *
 * @author JONATHAN
 */
public class Propiedades {

    Properties propiedades_conf = new Properties();
    String canonicalPath;
    public String[] extencion_archivo;
    public static String[] exten;
    private Gui_play ClaseMaestra;
    private String nom_archivo="conf.txt";
    public void Acceder_Conf() {
        try {
            canonicalPath = new File(".").getCanonicalPath() + "/"+getNom_archivo();
            propiedades_conf.load(new FileInputStream(canonicalPath));
        } catch (IOException ex) {
            Crear_conf();
            System.out.println("No existe fichero se creara uno");
        }
    }
        public void Acceder_Conf(String Fichero) {
        try {
            canonicalPath = new File(".").getCanonicalPath() + "/"+Fichero;
            propiedades_conf.load(new FileInputStream(canonicalPath));
        } catch (IOException ex) {
            Crear_conf();
            System.out.println("No existe fichero se creara uno");
        }
    }
    public void CARGAR_CONFIGURACIONES() {
        getClaseMaestra().ultima_direccion = ULTIMA_DIRECCION();
        getClaseMaestra().ultima_lista = ULTIMA_LISTA();
        VOLUMEN(getClaseMaestra().Volumen);
        getClaseMaestra().estado1 = MUTE();
        MODO_PRESENTACION(getClaseMaestra().Modo_Presentacion);
        EXTENCIONES();
    }

    private String ULTIMA_DIRECCION() {
        String ultima_direccion = propiedades_conf.getProperty("ultima_direccion");
        return ultima_direccion;
    }

    private String ULTIMA_LISTA() {
        String ultima_lista = propiedades_conf.getProperty("ultima_lista");
        return ultima_lista;
    }

    private boolean MUTE() {
        boolean estado1 = Boolean.valueOf(propiedades_conf.getProperty("mute"));
        return estado1;
    }

    public void EXTENCIONES() {
        int longitud = propiedades_conf.getProperty("extenciones").split(",").length;
        exten = new String[longitud];
        exten = propiedades_conf.getProperty("extenciones").split(",");
        for (int i = 0; i < longitud; i++) {
            System.out.println(exten[i]);
        }
        extencion_archivo = exten;
    }

    public void VOLUMEN(JSlider Volumen) {
        int valor_volumen = 0;
        valor_volumen = Integer.parseInt(propiedades_conf.getProperty("volumen"));
        Volumen.setValue(valor_volumen);
    }

    private void MODO_PRESENTACION(JCheckBoxMenuItem Modo_Presentacion) {
        Modo_Presentacion.setState(Boolean.valueOf(propiedades_conf.getProperty("modo_presentacion")));
    }

    public void GUARDAR_ULTIMA_LISTA(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("ultima_lista", Valor, propiedades_conf, canonicalPath);
    }

    public void GUARDAR_VOLUMEN(int value) throws FileNotFoundException {
        GUARDAR_LLAVE("volumen", String.valueOf(value), propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMA_DIRECCION(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("ultima_direccion", Valor, propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMO_MUTE(boolean estado1) throws FileNotFoundException {
        GUARDAR_LLAVE("mute", String.valueOf(estado1), propiedades_conf, canonicalPath);
    }

    public void GUARDAR_ULTIMO_MODO(boolean selected) throws FileNotFoundException {
        GUARDAR_LLAVE("modo_presentacion", String.valueOf(selected), propiedades_conf, canonicalPath);
    }
    public void GUARDAR_ULTIMA_EXTENCIONES(String Valor) throws FileNotFoundException {
        GUARDAR_LLAVE("extenciones",Valor, propiedades_conf, canonicalPath);
    }
    public void GUARDAR_LLAVE(String llave, String Valor, Properties propiedades_conf, String canonicalPath) throws FileNotFoundException {
        propiedades_conf.setProperty(llave, Valor);
        propiedades_conf.save(new FileOutputStream(canonicalPath), null);
    }

    public void setClaseMaestra(Gui_play ClaseMaestra) {
        this.ClaseMaestra=ClaseMaestra;
    }

    /**
     * @return the ClaseMaestra
     */
    public Gui_play getClaseMaestra() {
        return ClaseMaestra;
    }

    /**
     * @return the nom_archivo
     */
    public String getNom_archivo() {
        return nom_archivo;
    }

    /**
     * @param nom_archivo the nom_archivo to set
     */
    public void setNom_archivo(String nom_archivo) {
        this.nom_archivo = nom_archivo;
    }

    private void Crear_conf() {
        try {
            canonicalPath = new File(".").getCanonicalPath() + "/"+getNom_archivo();
            File a =new File(canonicalPath);
            a.createNewFile();
            propiedades_conf.load(new FileInputStream(canonicalPath));
            GUARDAR_VOLUMEN(50);
            GUARDAR_ULTIMA_DIRECCION("C:");
            GUARDAR_ULTIMA_LISTA("C:");
            GUARDAR_ULTIMA_EXTENCIONES(".mp3,.wav,.ogg,.flac,.au");
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
