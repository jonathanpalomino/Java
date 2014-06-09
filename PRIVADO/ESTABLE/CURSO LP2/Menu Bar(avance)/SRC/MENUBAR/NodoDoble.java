package menubar;
public class NodoDoble {
	private Productos Productos;// informacion
	private NodoDoble ApuntAnt;	// enlace con el anterior
	private NodoDoble ApuntSgte;// enlace con el siguiente

	// constructor
	NodoDoble(Productos Producto){this.Productos=Producto;}

	// get/set
        void setProducto(Productos Productos){this.Productos=Productos;}
	void setApuntSgte(NodoDoble ApuntSgte){this.ApuntSgte=ApuntSgte;}
	void setApuntAnt(NodoDoble ApuntAnt){this.ApuntAnt = ApuntAnt;}

	Productos getProducto(){return Productos;	}
	NodoDoble getApuntSgte(){return ApuntSgte;	}
	NodoDoble getApuntAnt(){return ApuntAnt;	}
}// fin de la clase de nodo doble