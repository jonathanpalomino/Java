/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reproductor.Librerias;

import Reproductor.Interfaz.Gui_play;
import Reproductor.Interfaz.Tabla;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import palomino.multimedia.audio.codec.ReproductorExcepcion;

/**
 *
 * @author JONATHAN
 */
public class Libreria_Tabla {

    private static DefaultTableModel miModelo;
    private static javax.swing.JTable Tabla;
    private  Tabla variable_tabla;
    static Direcciones Direccion;
    public static int eliminados = 0;
    private static Gui_play ClaseMaestra;

   public  Libreria_Tabla() {
    }

    
    /**
     * @return the variable_tabla
     */
    public  Tabla getVariable_tabla() {
        return variable_tabla;
    }

    /**
     * @param aVariable_tabla the variable_tabla to set
     */
    public  void setVariable_tabla(Tabla aVariable_tabla) {
        variable_tabla = aVariable_tabla;
    }
    String[] extencion_archivo;

    public static JTable getMiTabla() {
        return Tabla;
    }

    public static DefaultTableModel getMiModelo() {
        return miModelo;
    }
    public void setTabla_Datos(JTable Tabla, DefaultTableModel miModelo) {
        this.Tabla=Tabla;
        this.miModelo=miModelo;
        
    }

    public static void inicializaTabla() {
        // obtiene numero de filas de la variable_tabla
        int filas = getMiTabla().getRowCount();
        // remueve todas las filas de la variable_tabla
        for (int fila = 0; fila < filas; fila++) {
            getMiModelo().removeRow(0);
        }
    }

    public static boolean Validaciones(File files, String[] extenciones) {
        boolean estado = false;
        for (int j = 0; j < extenciones.length; j++)//Comparador interno falto implementar
        {
            //corrige el bug que evitava agregar otro archivo q no sea mp3
            estado = estado || files.getName().contains(extenciones[j]);
        }
        return estado;
    }

    public static boolean Validaciones(String files, String[] extenciones) {
        boolean estado = false;
        for (int j = 0; j < extenciones.length; j++)//Comparador interno falto implementar
        {
            //corrige el bug que evitava agregar otro archivo q no sea mp3
            estado = estado || files.contains(extenciones[j]);
        }
        return estado;
    }

    public void Traer_Lista(File file) {
        FileReader LeerArchivo = null;
        BufferedReader Temporal_memoria = null;
        try {
            LeerArchivo = new FileReader(file);
            Temporal_memoria = new BufferedReader(LeerArchivo);
            // Lectura del fichero
            String linea;
            while ((linea = Temporal_memoria.readLine()) != null) {
                if (linea.contains("#EXT") == false && Validaciones(linea, getExtension()))//Evita leer metadata de winamp
                {
                    Enviar(new File(linea).getName(), new File(linea));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Cargando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LlenarTabla(File[] Elementos) {
        Gui_play.EliminarElegido.setEnabled(true);
        Gui_play.EliminarTodo.setEnabled(true);
        Gui_play.GuardarLista.setEnabled(true);
        int tamaño = Elementos.length;
        for (int t = 0; t < tamaño; t++) {
            if (Elementos[t].isFile())//Verificar que es un archivo y no una carpeta
            {
                if (Validaciones(Elementos[t], getExtension())) {
                    Enviar(Elementos[t].getName(), Elementos[t]);
                }
            } else {
                llamar(Elementos[t]);
                //System.out.println(Elementos[t].getName());
            }

        }
    }

    public File[] llamar(File Dir) {
        File[] lista_Archivos = Dir.listFiles();
        LlenarTabla(lista_Archivos);
        return lista_Archivos;
    }

    public void Enviar(String nombre, File archivo) {
        IngresaDatos(nombre, archivo);
        if (getClaseMaestra().noreproducible) {
            try {
                Cargar_audio.loadFile(archivo.toString());
            } catch (ReproductorExcepcion ex) {
                BuscarIrreproducibles();
            }
        }
        if (getClaseMaestra().duplicado) {
            NodoDoble aux = Gui_play.ldco.busca(archivo);
            if (aux == null) {
                Gui_play.ldco.agrega(Direccion);
                Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
                getMiModelo().addRow(datos);
                getClaseMaestra().Habilitar(true);
            }
        } else {
            Gui_play.ldco.agrega(Direccion);
            Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
            getMiModelo().addRow(datos);
            getClaseMaestra().Habilitar(true);
        }
    }

    public static void IngresaDatos(String nombre, File file) {
        Direccion = new Direcciones();
        Direccion.setDireccion(file);
        Direccion.setNombre(nombre);
    }

    public void setClaseMaestra(Gui_play ClaseMaestra) {
        this.ClaseMaestra = ClaseMaestra;
    }

    /**
     * @return the ClaseMaestra
     */
    public static Gui_play getClaseMaestra() {
        return ClaseMaestra;
    }

    public static void BuscarIrreproducibles() {
        for (int i = 0; i < getMiTabla().getRowCount(); ++i) {
            String cadarchivo = getMiTabla().getValueAt(i, 1).toString();
            try {
                Cargar_audio.loadFile(cadarchivo);
            } catch (ReproductorExcepcion ex) {
                NodoDoble auxiliar = Gui_play.ldco.busca(new File(cadarchivo));
                if (auxiliar != null) {
                    Gui_play.ldco.elimina(auxiliar);
                    eliminados++;
                }
            }
        }
        ActualizaTabla();
    }

    public static void ActualizaTabla() {
        inicializaTabla();
        NodoDoble auxiliar;
        auxiliar = Gui_play.ldco.getInicio();
        while (auxiliar != null) {
            Direcciones dir = auxiliar.getNodo();
            Object[] datos = {dir.getNombre(), dir.getDireccion()};
            getMiModelo().addRow(datos);
            //retrocede al nodo anterior
            auxiliar = auxiliar.getApuntSgte();
        }

    }

    public void setExtension(String[] extencion_archivo) {
        this.extencion_archivo = extencion_archivo;
    }

    public String[] getExtension() {
        return extencion_archivo;
    }
    public void Reproduce(Object rutaTabla) {
        int filas = getMiTabla().getRowCount();
        for (int h = 0; h < filas; h++) {
            if (rutaTabla.toString().equals(getMiTabla().getValueAt(h, 1).toString())) {
                getClaseMaestra().getAudio().Reproducir(h);
                getMiTabla().changeSelection(h, 1, false, false);
            }
        }
    }

    public void inicializarVarTabla() {
        variable_tabla= new Tabla();
    }
}
