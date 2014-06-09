package promediograficoyconlista;

/**
 *
 * @author JONATHAN1
 */
public class Cola{
	private NodoSimple inicio;
	private NodoSimple fin;

	// constructor
	Cola(){inicio=null; fin=null;}

	// get/set
	void setInicio(NodoSimple inicio){this.inicio=inicio;	}
	void setFin(NodoSimple fin){this.fin=fin;	}
	NodoSimple getInicio(){return inicio;	}
	NodoSimple getFin(){ return fin;	}

	// metodos de administracion
	void agrega(Productos obj){
		NodoSimple nuevo;

                //...preparar nuevo nodo ...
 		nuevo = new NodoSimple(obj);
		nuevo.setApuntSgte(null);

		//...adicionar nuevo nodo a la lista tipo cola
		if(inicio == null)
			inicio = nuevo;
		else
			fin.setApuntSgte(nuevo);

		fin = nuevo;
	}
	//.....busca un codigo en la cola
	NodoSimple busca(int codigo) {
            // empieza al inicio de la cola
            NodoSimple auxiliar = inicio;

            // mientras no sea null
            while(auxiliar != null) {
            if(codigo == auxiliar.Prod().getCodigo() )
		return auxiliar; // retorna nodo encontrado
            else //pasa al siguiente
            	auxiliar=auxiliar.getApuntSgte();
            }
            // termino la cola y no lo encontró
            return null;
        }
}// fin de la clase Cola
