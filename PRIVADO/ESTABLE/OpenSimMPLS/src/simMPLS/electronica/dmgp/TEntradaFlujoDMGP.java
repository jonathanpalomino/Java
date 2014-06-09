package simMPLS.electronica.dmgp;

import simMPLS.utiles.*;
import simMPLS.protocolo.*;
import java.util.*;

/**
 * Esta clase implementa una entrada de flujo para memoria DMGP.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TEntradaFlujoDMGP implements Comparable {
    
    /**
     * Este m�todo es el constructor. Crea una nueva instancia de TEntradaFlujoDMGP.
     * @param ordenLlegada Oden de llegada del flujo a la DMGP.
     * @since 1.0
     */    
    public TEntradaFlujoDMGP(int ordenLlegada) {
        orden = ordenLlegada;
        idFlujo = -1;
        porcentajeAsignado = 0;
        octetosAsignados = 0;
        octetosOcupados = 0;
        entradas = new TreeSet();
        cerrojo = new TMonitor();
        generadorId = new TIdentificadorRotativo();
    }
    
    /**
     * este m�todo establece el identificador del flujo al que representa esta entrada.
     * @param idf Identificador del flujo.
     * @since 1.0
     */    
    public void ponerIdFlujo(int idf) {
        this.idFlujo = idf;
    }
    
    /**
     * Este m�todo devuelve el identificador del flujo al que pertenece esta entrada.
     * @return El identificador del flujo.
     * @since 1.0
     */    
    public int obtenerIdFlujo() {
        return this.idFlujo;
    }
    
    /**
     * Este m�todo establece el porcentaje de DMGP asignado a este flujo.
     * @param pa Porcentaje de DMGP asignado a este flujo.
     * @since 1.0
     */    
    public void ponerPorcentajeAsignado(int pa) {
        this.porcentajeAsignado = pa;
    }
    
    /**
     * Este m�todo obtiene el porcentaje de DMGP asignado a este flujo.
     * @return El porcentaje de DMGP asignado a este flujo.
     * @since 1.0
     */    
    public int obtenerPorcentajeAsignado() {
        return this.porcentajeAsignado;
    }
    
    /**
     * Este m�todo establece el n�mero de octetos de la DMGP que se han asignado a este
     * flujo.
     * @param oa N�mero de octetos de DMGP asignados al flujo.
     * @since 1.0
     */    
    public void ponerOctetosAsignados(int oa) {
        this.octetosAsignados = oa;
    }
    
    /**
     * Este m�todo obtiene el n�mero de octetos de DMGP que se han asignado a este
     * flujo.
     * @return El n�mero de octetos de DMGO asignados a este flujo.
     * @since 1.0
     */    
    public int obtenerOctetosAsignados() {
        return this.octetosAsignados;
    }
    
    /**
     * Este m�todo establece el n�mero de octetos de DMGP ocupados actualmente por el flujo.
     * @param oo N�mero de octetos de DMGP actualmente ocupador por el flujo.
     * @since 1.0
     */    
    public void ponerOctetosOcupados(int oo) {
        this.octetosOcupados = oo;
    }
    
    /**
     * Este m�todo obtiene el n�mero de octetos de DMGP que actualmente est�n ocupados
     * por el flujo.
     * @return N�mero de octetos de DMGP actualmente ocupados por el flujo.
     * @since 1.0
     */    
    public int obtenerOctetosOcupados() {
        return this.octetosOcupados;
    }
    
    /**
     * Este m�todo obtiene el �rbol que contiene los paquetes de este flujo.
     * @return �rbol que contiene los paquetes de este flujo.
     * @since 1.0
     */    
    public TreeSet obtenerEntradas() {
        return this.entradas;
    }
    
    /**
     * Este m�todo obtiene el orden de llegada de esta entrada a la DMGP.
     * @return Orden de llegada.
     * @since 1.0
     */    
    public int obtenerOrden() {
        return this.orden;
    }
    
    /**
     * Este m�todo devuelve el cerrojo del flujo.
     * @since 1.0
     * @return El cerrojo del flujo.
     */    
    public TMonitor obtenerCerrojo() {
        return this.cerrojo;
    }
    
    private void liberarEspacio(int octetosALiberar) {
        int octetosLiberados = 0;
        Iterator it = entradas.iterator();
        TEntradaDMGP edmgp = null;
        while ((it.hasNext()) && (octetosLiberados < octetosALiberar)) {
            edmgp = (TEntradaDMGP) it.next();
            octetosLiberados += edmgp.obtenerPaquete().obtenerTamanio();
            it.remove();
        }
        this.octetosOcupados -= octetosLiberados;
    }
    
    /**
     * Este m�todo inserta un paquete perteneciente a este flujo, en la lista de
     * paquetes. Si hay sitio, se inserta el paquete, si no hay, se liberan paquetes
     * hasta que cabe; si aun as� no cabe, no se inserta.
     * @param paquete Paquete que se desea insertar en la DMGP para este flujo.
     * @since 1.0
     */    
    public void insertarPaquete(TPDUMPLS paquete) {
        this.cerrojo.bloquear();
        int octetosDisponibles = this.octetosAsignados - this.octetosOcupados;
        if (octetosDisponibles >= paquete.obtenerTamanio()) {
            TEntradaDMGP edmgp = new TEntradaDMGP(generadorId.obtenerNuevo());
            edmgp.ponerPaquete(paquete);
            this.octetosOcupados += paquete.obtenerTamanio();
            this.entradas.add(edmgp);
        } else {
            if (octetosOcupados >= paquete.obtenerTamanio()) {
                liberarEspacio(paquete.obtenerTamanio());
                TEntradaDMGP edmgp = new TEntradaDMGP(generadorId.obtenerNuevo());
                edmgp.ponerPaquete(paquete);
                this.octetosOcupados += paquete.obtenerTamanio();
                this.entradas.add(edmgp);
            } else {
                paquete = null;
            }
        }
        this.cerrojo.liberar();
    }
    
    /**
     * Compara esta entrada de flujo con otra del mismo tipo.
     * @param o La entrada con la que se va a comparar.
     * @return -1, 0 � 1, dependiendo de si la instancia actual es menor, igual o mayor que la
     * especificada por par�metros. Hablando siempre en t�rminos de ordenaci�n.
     * @since 1.0
     */    
    public int compareTo(Object o) {
        TEntradaFlujoDMGP edmgp = (TEntradaFlujoDMGP) o;
        if (this.orden < edmgp.obtenerOrden())
            return this.ESTE_MENOR;
        if (this.orden > edmgp.obtenerOrden())
            return this.ESTE_MAYOR;
        return this.ESTE_IGUAL;
    }
    
    private static final int ESTE_MENOR = -1;
    private static final int ESTE_IGUAL = 0;
    private static final int ESTE_MAYOR = 1;

    private int orden;
    private int idFlujo;
    private int porcentajeAsignado;
    private int octetosAsignados;
    private int octetosOcupados;
    private TreeSet entradas;
    private TMonitor cerrojo;
    private TIdentificadorRotativo generadorId;
}
