package palomino.documentos.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import palomino.documentos.texto.Procesador_Texto;

/**
 *
 * @author synccon
 */
public class Leer_Excel {

    boolean antiguo = false;

    /**
     * Este metodo es usado para leer archivos Excel
     *
     * @param Nombre_Archivo - Nombre de archivo Excel.
     */
    private void Leer_Archivo_Excel(String Nombre_Archivo) {
        /**
         * Crea una nueva instancia de Lista_Datos_Celda
         */
        List Lista_Datos_Celda = new ArrayList();

        if (Nombre_Archivo.contains(".xlsx")) {
            LEER_XLSX(Nombre_Archivo, Lista_Datos_Celda);
            antiguo = false;
        } else if (Nombre_Archivo.contains(".xls")) {
            LEER_XLS(Nombre_Archivo, Lista_Datos_Celda);
            antiguo = true;
        }

        /**
         * Llama el metodo Imprimir_Consola para imprimir los datos de la celda
         * en la consola.
         */
        Imprimir_Consola(Lista_Datos_Celda, true);
    }

    private void LEER_XLSX(String Nombre_Archivo, List Lista_Datos_Celda) {
        try {
            /**
             * Crea una nueva instancia de la clase FileInputStream
             */
            FileInputStream fileInputStream = new FileInputStream(
                    Nombre_Archivo);
            /**
             * Crea una nueva instancia de la clase XSSFWorkBook
             */
            XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fileInputStream);
            XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
            /**
             * Iterar las filas y las celdas de la hoja de cálculo para obtener
             * toda la data.
             */
            Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
            while (Iterador_de_Fila.hasNext()) {
                XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
                Iterator iterador = Fila_hssf.cellIterator();
                List Lista_celda_temporal = new ArrayList();
                while (iterador.hasNext()) {
                    XSSFCell Celda_hssf = (XSSFCell) iterador.next();
                    Lista_celda_temporal.add(Celda_hssf);
                }
                Lista_Datos_Celda.add(Lista_celda_temporal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LEER_XLS(String Nombre_Archivo, List Lista_Datos_Celda) {
        try {
            /**
             * Crea una nueva instancia de la clase FileInputStream
             */
            FileInputStream fileInputStream = new FileInputStream(
                    Nombre_Archivo);
            /**
             * Crea una nueva instancia de la clase POIFSFileSystem
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /**
             * Crea una nueva instancia de la clase HSSFWorkBook
             */
            HSSFWorkbook Libro_trabajo = new HSSFWorkbook(fsFileSystem);
            HSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
            /**
             * Iterar las filas y las celdas de la hoja de cálculo para obtener
             * toda la data.
             */
            Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
            while (Iterador_de_Fila.hasNext()) {
                HSSFRow Fila_hssf = (HSSFRow) Iterador_de_Fila.next();
                Iterator iterador = Fila_hssf.cellIterator();
                List Lista_celda_temporal = new ArrayList();
                while (iterador.hasNext()) {
                    HSSFCell Celda_hssf = (HSSFCell) iterador.next();
                    Lista_celda_temporal.add(Celda_hssf);
                }
                Lista_Datos_Celda.add(Lista_celda_temporal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LEER_XLS(String Nombre_Archivo, List Lista_Datos_Celda, int parametro) throws Exception {
        System.out.println(" nombre Archivo a Leer " + Nombre_Archivo);
        try {
            /**
             * Crea una nueva instancia de la clase FileInputStream
             */
            FileInputStream fileInputStream = new FileInputStream(Nombre_Archivo);
            /**
             * Crea una nueva instancia de la clase POIFSFileSystem
             */
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            /**
             * Crea una nueva instancia de la clase HSSFWorkBook
             */
            HSSFWorkbook Libro_trabajo = new HSSFWorkbook(fsFileSystem);
            HSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);//Leo la primera hoja
            /**
             * Iterar las filas y las celdas de la hoja de cálculo para obtener
             * toda la data.
             */
            int filas = 0;
            int columna_maxima = 0;
            int columna_temp = 0;

            for (int y = parametro; y < Hoja_hssf.getLastRowNum() + 1; y++) {
                HSSFRow Fila_hssf = (HSSFRow) Hoja_hssf.getRow(y);
                if (Fila_hssf.getCell((short) 0) == null) {//primer campo diferente de vacio para ser considerado
                    break;
                } else {
                    filas++;
                }

            }
            filas = filas + parametro;
            System.out.println("FILAS -->> " + filas);

            for (int identificador = 0; identificador < filas; identificador++) {
                columna_temp = Hoja_hssf.getRow(identificador).getLastCellNum();
                if (columna_temp >= columna_maxima) {
                    columna_maxima = columna_temp;
                }
            }
            System.out.println("COLUMNAS -->> " + columna_maxima);
            for (int i = 0; i < filas; i++) {
                HSSFRow Fila_hssf = (HSSFRow) Hoja_hssf.getRow(i);
                List Lista_celda_temporal = new ArrayList();
                int contador = 1;
                HSSFCell Celda_hssf = null;
                for (int j = 0; j < columna_maxima; j++) {
                    Celda_hssf = (HSSFCell) Fila_hssf.getCell((short) j);
                    if (Celda_hssf == null) {
                        Celda_hssf = Fila_hssf.createCell((short) j);
                        Celda_hssf.setCellValue("");
                    }
                    if (Celda_hssf.toString().equals("")) {
                        contador++;
                    }
                }
                if (contador < columna_maxima) {
                    for (int j = 0; j < columna_maxima; j++) {
                        Celda_hssf = (HSSFCell) Fila_hssf.getCell((short) j);
                        Lista_celda_temporal.add(Celda_hssf);
                    }
                }
                if (!Lista_celda_temporal.isEmpty()) {
                    Lista_Datos_Celda.add(Lista_celda_temporal);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR AL ABRIR FICHERO " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR AL LEER FICHERO " + e.getMessage());
        }
        System.out.println(Nombre_Archivo + " LEIDO CORRECTAMENTE");
    }

    /**
     * Este método se utiliza para imprimir los datos de la celda a la consola.
     *
     * @param Datos_celdas - Listado de los datos que hay en la hoja de cálculo.
     */
    private void Imprimir_Consola(List Datos_celdas, boolean habil) {
        final String ln = System.getProperty("line.separator");
        String Valor_de_celda;
        try {
            Procesador_Texto abc = new Procesador_Texto();
            abc.setEscrituraActiva(true);
            abc.Inicializar_Escritura("C:\\archivoSalida.txt");
            for (int i = 0; i < Datos_celdas.size(); i++) {
                List Lista_celda_temporal = (List) Datos_celdas.get(i);
                for (int j = 0; j < Lista_celda_temporal.size(); j++) {
                    if (antiguo) {
                        HSSFCell hssfCell = (HSSFCell) Lista_celda_temporal.get(j);
                        Valor_de_celda = hssfCell.toString();
                    } else {
                        XSSFCell hssfCell = (XSSFCell) Lista_celda_temporal.get(j);
                        Valor_de_celda = hssfCell.toString();
                    }
                    System.out.print(Valor_de_celda + ln);
                    abc.Escribir_Texto(Valor_de_celda + ln);


                }
                System.out.println();
                abc.Escribir_Texto("valor int " + Lista_celda_temporal.size());

            }
            abc.Escribir_Texto(" valor ext " + Datos_celdas.size());

            abc.Cerrar_Escritura();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        String fileName = "C:" + File.separator + "Users" + File.separator
                + "synccon" + File.separator + "Desktop" + File.separator
                + "Definicion de tablas.xlsx";
        System.out.println(fileName);
        fileName = "C:/Users/synccon/Desktop/Definicion de tablas.xlsx";
        System.out.println(fileName);
        System.out.println("INICIO ARCHIVO -------------------------------------------------------");
        new Leer_Excel().Leer_Archivo_Excel(fileName);
        System.out.println("FIN ARCHIVO -------------------------------------------------------");
    }
}
