package librerias;

/**
 *
 * @author JONATHAN1
 */
//Clase para una lista doble :
public class NodoDoble_Existencias {

    public Productos Elemento_Escala;// informacion
    public NodoDoble_Existencias ApuntAnt;	// enlace con el anterior
    public NodoDoble_Existencias ApuntSgte;// enlace con el siguiente

    // constructor
    public NodoDoble_Existencias(Productos Elemento_Escala) {
        this.Elemento_Escala = Elemento_Escala;
    }

    // get/set
    public void setNodo(Productos Elemento_Escala) {
        this.Elemento_Escala = Elemento_Escala;
    }

    public void setApuntSgte(NodoDoble_Existencias ApuntSgte) {
        this.ApuntSgte = ApuntSgte;
    }

    public void setApuntAnt(NodoDoble_Existencias ApuntAnt) {
        this.ApuntAnt = ApuntAnt;
    }

    public Productos getNodo() {
        return Elemento_Escala;
    }

    public NodoDoble_Existencias getApuntSgte() {
        return ApuntSgte;
    }

    public NodoDoble_Existencias getApuntAnt() {
        return ApuntAnt;
    }
}// fin de la clase de nodo doble

