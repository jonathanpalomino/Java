package promediograficoyconlista;
/**
 *
 * @author JONATHAN1
 */
public class NodoSimple  {
    private Productos Productos;	//información
    private NodoSimple ApuntSgte;	//enlace con el siguiente

    // constructor
    NodoSimple(Productos obj){this.Productos=obj;}

    // get/set
    void setProducto(Productos prod){this.Productos=prod;}
    void setApuntSgte(NodoSimple ApuntSgte){this.ApuntSgte=ApuntSgte;}
    Productos Prod(){return Productos;	}
    NodoSimple getApuntSgte(){return ApuntSgte;	}
}// fin de la clase de nodo simple
