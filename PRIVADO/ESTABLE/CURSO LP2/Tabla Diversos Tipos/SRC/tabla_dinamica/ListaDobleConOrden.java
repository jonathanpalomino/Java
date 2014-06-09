
package tabla_dinamica;


/**
 *
 * @author JONATHAN1
 */
public class ListaDobleConOrden{
	private NodoDoble inicio;
	private NodoDoble fin;

	// constructor
	ListaDobleConOrden(){inicio=null; fin=null;}

	// get/set
	void setInicio(NodoDoble inicio){this.inicio=inicio;	}
	void setFin(NodoDoble fin){this.fin=fin;	}
	NodoDoble getInicio(){return inicio;	}
	NodoDoble getFin(){ return fin;	}

	// metodos de administracion
	void agrega(Productos Producto){
		NodoDoble nuevo, auxiliar;

		//...preparar nuevo nodo ...
 		nuevo = new NodoDoble(Producto);
		nuevo.setApuntSgte(null);
		nuevo.setApuntAnt(null);

		//...adicionar nuevo nodo a la lista...
		if(inicio == null) {
			//primer nodo
			inicio = nuevo;
			fin = inicio;
		} else //Por codigo orden
                if(Producto.getCodigo().compareTo(
                    inicio.getPro().getCodigo())<0) {
                    //antes del primer nodo
                    nuevo.setApuntSgte(inicio);
                    inicio.setApuntAnt(nuevo);
                    inicio = nuevo;
                } else
                if(Producto.getCodigo().compareTo(
                    fin.getPro().getCodigo())>0 ||
			Producto.getCodigo().compareTo(
                    fin.getPro().getCodigo())==0)	{
                    //despues del ultimo nodo
                    nuevo.setApuntAnt(fin);
                    fin.setApuntSgte(nuevo);
                    fin = nuevo;
		} else {
                    //entre dos nodos ya existentes
                    auxiliar = inicio;

                    // ubica el anterior
                    while(Producto.getCodigo().compareTo(
			auxiliar.getPro().getCodigo())>0||
			Producto.getCodigo().compareTo(
			auxiliar.getPro().getCodigo())==0) {
                auxiliar = auxiliar.getApuntSgte();
            }

                    // enlaza
                    nuevo.setApuntSgte(auxiliar);
                    nuevo.setApuntAnt(auxiliar.getApuntAnt());
                    auxiliar.getApuntAnt().setApuntSgte(nuevo);
                    auxiliar.setApuntAnt(nuevo);
		}
	}
	//.....busca un codigo en la lista
	NodoDoble busca(String codigo) {
            // empieza por el primero de la lista
            NodoDoble Auxiliar = inicio;

            // mientras no sea nulo
            while(Auxiliar != null)	{
		if(codigo.equals(Auxiliar.getPro().getCodigo()))
                    return Auxiliar;// lo encontrÃ³
		else
                    Auxiliar = Auxiliar.getApuntSgte(); // pasa al siguiente
	}// fin del while
	// terminÃ³ la lista y no lo encontrÃ³
	return null;
	}


    void elimina(NodoDoble aux)
    {
        if(inicio==fin && aux==inicio)//Si es el uniko
        {
            setInicio(null);
        }
        else if(aux==inicio)//El primer nodo
        {
            inicio = aux.getApuntSgte();
            inicio.setApuntAnt(null);
        }
        else if(aux.getApuntAnt()!=null &&aux.getApuntSgte()!=null)//Eliminar el nodo del medio
        {
            aux.getApuntAnt().setApuntSgte(aux.getApuntSgte());
            aux.getApuntSgte().setApuntAnt(aux.getApuntAnt());
        }
        else if(aux==fin)//Si es el ultimo
        {
            aux.getApuntAnt().setApuntSgte(null);
            setFin(aux.getApuntAnt());

        }
    
    }

        }
//fin de la clase ListaDobleConOrden

