/*
 * TEstadisticas.java
 *
 * Created on 5 de octubre de 2004, 20:11
 */

package simMPLS.escenario;

import org.jfree.chart.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import simMPLS.protocolo.*;

/**
 * Esta clase es superclase abstracta. Todas las estad�sticas de los nodos de la
 * topolog�a deben heredar e implementar los m�todos abstractos.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public abstract class TEstadisticas {
    
    /**
     * Crea una nueva instancia de TEstadisticas
     * @since 1.0
     */
    public TEstadisticas() {
        estadisticasActivas = false;
    }

    /**
     * Este m�todo activa o desactiva las estad�sticas.
     * @since 1.0
     * @param a TRUE, activa las estad�sticas. FALSE las desactiva.
     */    
    public void activarEstadisticas(boolean a) {
        this.estadisticasActivas = a;
    }

    /**
     * Este m�todo obtiene los datos de la gr�fica 1.
     * @since 1.0
     * @return Datos de la gr�fica 1.
     */    
    public abstract AbstractDataset obtenerDatosGrafica1();
    /**
     * Este m�todo obtiene los datos de la gr�fica 2.
     * @since 1.0
     * @return Datos de la gr�fica 2.
     */    
    public abstract AbstractDataset obtenerDatosGrafica2();
    /**
     * Este m�todo obtiene los datos de la gr�fica 3.
     * @since 1.0
     * @return Datos de la gr�fica 3.
     */    
    public abstract AbstractDataset obtenerDatosGrafica3();
    /**
     * Este m�todo obtiene los datos de la gr�fica 4.
     * @since 1.0
     * @return Datos de la gr�fica 4.
     */    
    public abstract AbstractDataset obtenerDatosGrafica4();
    /**
     * Este m�todo obtiene los datos de la gr�fica 5.
     * @since 1.0
     * @return Datos de la gr�fica 5.
     */    
    public abstract AbstractDataset obtenerDatosGrafica5();
    /**
     * Este m�todo obtiene los datos de la gr�fica 6.
     * @since 1.0
     * @return Datos de la gr�fica 6.
     */    
    public abstract AbstractDataset obtenerDatosGrafica6();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 1.
     * @return El t�tulo de la gr�fica 1
     * @since 1.0
     */
    public abstract String obtenerTitulo1();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 2.
     * @return El t�tulo de la gr�fica 2
     * @since 1.0
     */
    public abstract String obtenerTitulo2();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 3.
     * @return El t�tulo de la gr�fica 3
     * @since 1.0
     */
    public abstract String obtenerTitulo3();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 4.
     * @return El t�tulo de la gr�fica 4
     * @since 1.0
     */
    public abstract String obtenerTitulo4();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 5.
     * @return El t�tulo de la gr�fica 5
     * @since 1.0
     */
    public abstract String obtenerTitulo5();
    /**
     * Este m�todo obtiene el t�tulo de la gr�fica 6.
     * @return El t�tulo de la gr�fica 6
     * @since 1.0
     */
    public abstract String obtenerTitulo6();
    /**
     * Este m�todo modifica las estad�sticas, a�adiendo las necesarias para el paquete
     * especificado.
     * @param paquete Paquete que se desea anotar en las estad�sticas.
     * @param entrada ENTRADA, SALIDA, DESCARTE dependiendo si el paquete ha entrado del nodo, sallido
     * o ha sido descartado.
     * @since 1.0
     */    
    public abstract void crearEstadistica(TPDU paquete, int entrada);
    /**
     * Este m�todo a�ade los datos modificados desde la �ltima vez que se llam� a este
     * m�todo, en las estad�sticas.
     * @param instante Instante al que se asignar�n los �ltimos datos.
     * @since 1.0
     */    
    public abstract void asentarDatos(long instante);
    /**
     * Devuelve el n�mero de gr�ficas que contiene la instancia.
     * @return N�mero de gr�ficas.
     * @since 1.0
     */    
    public abstract int obtenerNumeroGraficas();
    /**
     * Reinicia las estad�sticas y las deja como si acabasen de ser creadas por el
     * constructor.
     * @since 1.0
     */    
    public abstract void reset();
    
    /**
     * Este atributo almacenar� si las estad�sticas est�n activada o no.
     * @since 1.0
     */    
    protected boolean estadisticasActivas;
    
    /**
     * Esta constante es un texto que representa a paquetes de tipo IPv4
     * @since 1.0
     */    
    public static final String IPV4 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.IPv4");
    /**
     * Esta constante es un texto que representa a paquetes IPv4 con GoS 1
     * @since 1.0
     */    
    public static final String IPV4_GOS1 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.IPv4_GoS1");
    /**
     * Esta constante es un texto que representa a paquetes IPv4 con GoS 2
     * @since 1.0
     */    
    public static final String IPV4_GOS2 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.IPv4_GoS2");
    /**
     * Esta constante es un texto que representa a paquetes IPv4 con GoS 3
     * @since 1.0
     */    
    public static final String IPV4_GOS3 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.IPv4_Gos3");
    /**
     * Esta constante es un texto que representa a paquetes MPLS
     * @since 1.0
     */    
    public static final String MPLS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.MPLS");
    /**
     * Esta constante es un texto que representa a paquetes MPLS con GoS 1
     * @since 1.0
     */    
    public static final String MPLS_GOS1 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.MPLS_GoS1");
    /**
     * Esta constante es un texto que representa a paquetes MPLS con GoS 2
     * @since 1.0
     */    
    public static final String MPLS_GOS2 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.MPLS_GoS2");
    /**
     * Esta constante es un texto que representa a paquetes MPLS con GoS 3
     * @since 1.0
     */    
    public static final String MPLS_GOS3 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.MPLS_GoS3");
    /**
     * Esta constante es un texto que representa a paquetes TLDP
     * @since 1.0
     */    
    public static final String TLDP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.TLDP");
    /**
     * Esta constante es un texto que representa a paquetes GPSRP
     * @since 1.0
     */    
    public static final String GPSRP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.GPSRP");
    
    /**
     * Esta constante es un texto que representa al tiempo
     * @since 1.0
     */    
    public static final String TIEMPO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Tiempo_ns");
    /**
     * Esta constante es un texto que representa al n�mero de paquetes
     * @since 1.0
     */    
    public static final String NUMERO_DE_PAQUETES = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Numero_de_paquetes");
    /**
     * Esta constante es un texto que representa al n�mero
     * @since 1.0
     */    
    public static final String NUMERO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Numero");
    /**
     * Esta constante es un texto que representa al tama�o de la DMGP
     * @since 1.0
     */    
    public static final String TAMANIO_DMGP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Tamanio_DMGP_Kilobytes");
    
    /**
     * Esta constante es un texto que representa a los paquetes salientes
     * @since 1.0
     */    
    public static final String PAQUETES_SALIENTES = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_salientes");
    /**
     * Esta constante es un texto que representa a los paquetes entrantes.
     * @since 1.0
     */    
    public static final String PAQUETES_ENTRANTES = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_entrantes");
    /**
     * Esta constante es un texto que representa a los paquetes descartados.
     * @since 1.0
     */    
    public static final String PAQUETES_DESCARTADOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_descartados");
    /**
     * Esta constante es un texto que representa a los paquetes recuperados.
     * @since 1.0
     */    
    public static final String PAQUETES_RECUPERADOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_recuperados");
    /**
     * Esta constante es un texto que representa a los paquetes no recuperados.
     * @since 1.0
     */    
    public static final String PAQUETES_NO_RECUPERADOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_no_recuperados");
    /**
     * Esta constante es un texto que representa a las recuperaciones locales
     * @since 1.0
     */    
    public static final String RECUPERACIONES_LOCALES = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Recuperaciones_locales_de_paquetes_con_GoS");
    /**
     * Esta constante es un texto que representa a las retransmisiones atendidas
     * @since 1.0
     */    
    public static final String RETRANSMISIONES_ATENDIDAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Retransmisiones_de_paquetes_con_GoS_atendidas");
    /**
     * Esta constante es un texto que representa a las solicitudes atendidas.
     * @since 1.0
     */    
    public static final String SOLICITUDES_ATENDIDAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Solicitudes_atendidas");
    /**
     * Esta constante es un texto que representa a las retransmisiones realizadas
     * @since 1.0
     */    
    public static final String RETRANSMISIONES_REALIZADAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Retransmisiones_realizadas");
    /**
     * Esta constante es un texto que representa a las retransmisiones no realizadas.
     * @since 1.0
     */    
    public static final String RETRANSMISIONES_NO_REALIZADAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Retransmisiones_no_realizadas");
    /**
     * Esta constante es un texto que representa a las solicitudes recibidas
     * @since 1.0
     */    
    public static final String SOLICITUDES_RECIBIDAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Solicitudes_recibidas");
    /**
     * Esta constante es un texto que representa a los paquetes con GoS perdidos
     * @since 1.0
     */    
    public static final String PAQUETES_GOS_PERDIDOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_GoS_perdidos");
    /**
     * Esta constante es un texto que representa a las solicitudes emitidas.
     * @since 1.0
     */    
    public static final String SOLICITUDES_EMITIDAS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Solicitudes_emitidas");
    /**
     * Esta constante es un texto que representa a las solicitudes sin respuestas.
     * @since 1.0
     */    
    public static final String SOLICITUDES_SIN_RESPUESTA_AUN = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Solicitudes_sin_respuesta_aun");
    /**
     * Esta constante es un texto que representa a los paquetes con GoS recuperados
     * @since 1.0
     */    
    public static final String PAQUETES_GOS_RECUPERADOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_GoS_recuperados");
    /**
     * Esta constante es un texto que representa a los paquetes con GoS no recuperados.
     * @since 1.0
     */    
    public static final String PAQUETES_GOS_NO_RECUPERADOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Paquetes_GoS_no_recuperados");
    /**
     * Esta constante es un texto que representa a la descripci�n de categor�as.
     * @since 1.0
     */    
    public static final String DESCRIPCION = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TEstadisticas.Descripcion");
    
    /**
     * Esta constante indica un paquete entrante en el nodo.
     * @since 1.0
     */    
    public static final int ENTRADA = -1;
    /**
     * Esta constante indica un paquete saliente del nodo.
     * @since 1.0
     */    
    public static final int SALIDA = -2;
    /**
     * Esta constante indica un paquete descartado en el nodo.
     * @since 1.0
     */    
    public static final int DESCARTE = -3;
}
