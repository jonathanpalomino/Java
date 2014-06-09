package palomino.documentos.texto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author synccon
 */
public class Procesador_Texto implements Interface_Texto{
private BufferedWriter escritor;
private File archivo_fisico;
private boolean escritura_activa;
private FileReader Lector_archivo ;
private BufferedReader Lector_buffer;
private String texto_leido="";
private int saltos_de_linea;
    @Override
    public void Inicializar_Lectura(String ruta)  throws IOException{
        setArchivo_fisico(new File(ruta));
        setLector_archivo(new FileReader(getArchivo_fisico()));
        setLector_buffer(new BufferedReader(getLector_archivo()));
    }

    @Override
    public void Inicializar_Lectura(File path) throws IOException {
        Inicializar_Lectura(path.getAbsolutePath());
    }

    @Override
    public void Leer_Texto()  throws IOException{
      // Lectura del fichero
        final String ln = System.getProperty("line.separator");
        String linea;
        int i;
        for(i=0;(linea = getLector_buffer().readLine()) != null;i++){
             setTexto_leido(getTexto_leido()+linea+ln);
        }
        setSaltos_de_linea(i);
    }

    @Override
    public void Inicializar_Escritura(String ruta) throws IOException{
        setArchivo_fisico(new File(ruta));
        setEscritor(new BufferedWriter(new FileWriter(getArchivo_fisico())));
    }

    @Override
    public void Inicializar_Escritura(File path) throws IOException{
        Inicializar_Lectura(path.getAbsolutePath());
    }

    @Override
    public void Escribir_Texto(String contenido) throws IOException{
        if (getEscrituraActiva()){
            getEscritor().write(contenido);
        }
    }

    
    @Override
    public void Cerrar_Escritura() throws IOException{
        getEscritor().close();
    }

    /**
     * @return the escritor
     */
    public BufferedWriter getEscritor() {
        return escritor;
    }

    /**
     * @param escritor the escritor to set
     */
    public void setEscritor(BufferedWriter escritor) {
        this.escritor = escritor;
    }

    /**
     * @return the archivo_fisico
     */
    public File getArchivo_fisico() {
        return archivo_fisico;
    }

    /**
     * @param archivo_fisico the archivo_fisico to set
     */
    public void setArchivo_fisico(File archivo_fisico) {
        this.archivo_fisico = archivo_fisico;
    }
    /**
     * @param escritura_activa the escritura_activa to set
     */
    public void setEscrituraActiva(boolean escritura_activa) {
        this.escritura_activa=escritura_activa;
    }
    public boolean getEscrituraActiva() {
        return escritura_activa;
    }

    /**
     * @return the Lector_archivo
     */
    public FileReader getLector_archivo() {
        return Lector_archivo;
    }

    /**
     * @param Lector_archivo the Lector_archivo to set
     */
    public void setLector_archivo(FileReader Lector_archivo) {
        this.Lector_archivo = Lector_archivo;
    }

    /**
     * @return the Lector_buffer
     */
    public BufferedReader getLector_buffer() {
        return Lector_buffer;
    }

    /**
     * @param Lector_buffer the Lector_buffer to set
     */
    public void setLector_buffer(BufferedReader Lector_buffer) {
        this.Lector_buffer = Lector_buffer;
    }

    /**
     * @return the texto_leido
     */
    public String getTexto_leido() {
        return texto_leido;
    }

    /**
     * @param texto_leido the texto_leido to set
     */
    public void setTexto_leido(String texto_leido) {
        this.texto_leido = texto_leido;
    }

    @Override
    public void Cerrar_Lectura() throws IOException {
        Lector_archivo.close();
    }

    /**
     * @return the saltos_de_linea
     */
    public int getSaltos_de_linea() {
        return saltos_de_linea;
    }

    /**
     * @param saltos_de_linea the saltos_de_linea to set
     */
    public void setSaltos_de_linea(int saltos_de_linea) {
        this.saltos_de_linea = saltos_de_linea;
    }
    
}
