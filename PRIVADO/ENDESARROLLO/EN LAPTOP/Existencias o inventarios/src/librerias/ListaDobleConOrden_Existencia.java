package librerias;

/**
 *
 * @author JONATHAN1
 */
public class ListaDobleConOrden_Existencia {

    private NodoDoble_Existencias inicio;
    private NodoDoble_Existencias fin;

    // constructor
    public ListaDobleConOrden_Existencia() {
        inicio = null;
        fin = null;
    }

    // get/set
    public void setInicio(NodoDoble_Existencias inicio) {
        this.inicio = inicio;
    }

    public void setFin(NodoDoble_Existencias fin) {
        this.fin = fin;
    }

    public NodoDoble_Existencias getInicio() {
        return inicio;
    }

    public NodoDoble_Existencias getFin() {
        return fin;
    }

    // metodos de administracion
    public void agrega(Productos escala) {
        NodoDoble_Existencias nuevo, auxiliar;

        //...preparar nuevo nodo ...
        nuevo = new NodoDoble_Existencias(escala);
        nuevo.setApuntSgte(null);
        nuevo.setApuntAnt(null);

        //...adicionar nuevo nodo a la lista...
        if (inicio == null) {
            //primer nodo
            inicio = nuevo;
            fin = inicio;
        } else //Por codigo orden
            if(escala.getmultiplo()<inicio.getNodo().getmultiplo()){}
        if (escala.getmultiplo()<inicio.getNodo().getmultiplo()) {
            //antes del primer nodo
            nuevo.setApuntSgte(inicio);
            inicio.setApuntAnt(nuevo);
            inicio = nuevo;
        } else if (escala.getmultiplo()>fin.getNodo().getmultiplo() || escala.getmultiplo()==fin.getNodo().getmultiplo()) {
            //despues del ultimo nodo
            nuevo.setApuntAnt(fin);
            fin.setApuntSgte(nuevo);
            fin = nuevo;
        } else {
            //entre dos nodos ya existentes
            auxiliar = inicio;

            // ubica el anterior
            while (escala.getmultiplo()>auxiliar.getNodo().getmultiplo() || escala.getmultiplo()==auxiliar.getNodo().getmultiplo()) {
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

    public NodoDoble_Existencias busca(String codigo) {
        // empieza por el primero de la lista
        NodoDoble_Existencias Auxiliar = inicio;

        // mientras no sea nulo
        while (Auxiliar != null) {
            if (codigo.equals(Auxiliar.getNodo().getProducto())) {
                return Auxiliar;// lo encontro
            } else {
                Auxiliar = Auxiliar.getApuntSgte(); // pasa al siguiente
            }
        }// fin del while
        // termina la lista y no lo encontro
        return null;
    }

    public void elimina(NodoDoble_Existencias aux) {
        if (inicio == fin && aux == inicio)//Si es el uniko
        {
            setInicio(null);
        } else if (aux == inicio)//El primer nodo
        {
            inicio = aux.getApuntSgte();
            inicio.setApuntAnt(null);
        } else if (aux.getApuntAnt() != null && aux.getApuntSgte() != null)//Eliminar el nodo del medio
        {
            aux.getApuntAnt().setApuntSgte(aux.getApuntSgte());
            aux.getApuntSgte().setApuntAnt(aux.getApuntAnt());
        } else if (aux == fin)//Si es el ultimo
        {
            aux.getApuntAnt().setApuntSgte(null);
            setFin(aux.getApuntAnt());

        }

    }
}
//fin de la clase ListaDobleConOrden

