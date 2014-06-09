package prueba;

import palomino.documentos.texto.Procesador_Texto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author synccon
 */

class Lectura {
static Procesador_Texto abc ;
    public static void main(String[] arg) {

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            abc = new Procesador_Texto();
            abc.Inicializar_Lectura("C:\\archivoSalida.txt");
            abc.Leer_Texto();
            // Lectura del fichero
            String linea =abc.getTexto_leido();
            System.out.println(linea);
            System.out.println(abc.getSaltos_de_linea());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                abc.Cerrar_Lectura();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}