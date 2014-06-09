
package tabla_dinamica;
/**
 *
 * @author JONATHAN1
 */
//Clase para una lista doble :
public class NodoDoble {
	private Productos Producto;// informacion
	private NodoDoble ApuntAnt;	// enlace con el anterior
	private NodoDoble ApuntSgte;// enlace con el siguiente

	// constructor
	NodoDoble(Productos Producto){this.Producto=Producto;}

	// get/set
        void setPro(Productos Producto){this.Producto=Producto;}
	void setApuntSgte(NodoDoble ApuntSgte){this.ApuntSgte=ApuntSgte;}
	void setApuntAnt(NodoDoble ApuntAnt){this.ApuntAnt = ApuntAnt;}

	Productos getPro(){return Producto;	}
	NodoDoble getApuntSgte(){return ApuntSgte;	}
	NodoDoble getApuntAnt(){return ApuntAnt;	}
}// fin de la clase de nodo doble


