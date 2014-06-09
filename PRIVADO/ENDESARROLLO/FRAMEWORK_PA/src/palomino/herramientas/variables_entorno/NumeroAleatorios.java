package palomino.herramientas.variables_entorno;

import java.util.ArrayList;
public class NumeroAleatorios {
  private int valorInicial;
  private int valorFinal;
  private int anterior;
  private int posterior;
  private int actual;
  private ArrayList<Integer> listaNumero;
  private ArrayList<Integer> listaGenerada;

  public NumeroAleatorios(int valorInicial, int valorFinal){
      this.valorInicial = valorInicial;
      this.valorFinal = valorFinal;
      listaNumero = new ArrayList<Integer>();
  }
   
  private int numeroAleatorio(){
      return (int)(Math.random()*(valorFinal-valorInicial+1)+valorInicial);
  }
    
  public int generar(){
      if(getListaNumero().size() < (valorFinal - valorInicial) +1){//Aun no se han generado todos los numeros
          int numero = numeroAleatorio();//genero un numero
          if(getListaNumero().isEmpty()){//si la lista esta vacia
                getListaNumero().add(numero);
              return numero;
          }else{//si no esta vacia
              if(getListaNumero().contains(numero)){//Si el numero que gener� esta contenido en la lista
                  return generar();//recursivamente lo mando a generar otra vez
              }else{//Si no esta contenido en la lista
                    getListaNumero().add(numero);
                  return numero;
              }
          }
      }else{// ya se generaron todos los numeros
          return -1;
      }
  }
    /**
     * @return the listaNumero
     */
    public ArrayList<Integer> getListaNumero() {
        return listaNumero;
    }

    /**
     * @param listaNumero the listaNumero to set
     */
    public void setListaNumero(ArrayList<Integer> listaNumero) {
        this.listaNumero = listaNumero;
    }

    /**
     * @return the anterior
     */
    public int getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(int anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the posterior
     */
    public int getPosterior() {
        return posterior;
    }

    /**
     * @param posterior the posterior to set
     */
    public void setPosterior(int posterior) {
        this.posterior = posterior;
    }

    /**
     * @return the actual
     */
    public int getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    /**
     * @return the listaGenerada
     */
    public ArrayList<Integer> getListaGenerada() {
        listaGenerada = new ArrayList<Integer>();
        for(int i = 0; i < this.valorFinal;i++){
            listaGenerada.add(generar());
	    }
        return listaGenerada;
    }

    /**
     * @param listaGenerada the listaGenerada to set
     */
    public void setListaGenerada(ArrayList<Integer> listaGenerada) {
        this.listaGenerada = listaGenerada;
    }

    
}
