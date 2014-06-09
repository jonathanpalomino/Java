package encriptameintosimple;
public class Cifrar {
    

    public Cifrar() {
    }
    public static String encriptar(String s, int clave){
    String retornarValor="";
    for(int i=0; i<s.length();++i){
      char c= s.charAt(i);

      c= encriptar(c,clave);

      retornarValor +=String.valueOf(c);
      clave= (int)c;

    }
    return retornarValor;
  }
  
  //encriptar un solo caracter
 public static char encriptar(char c, int clave){

    if(c !=' ' )c= xOR(c,clave);
    
    return c;
  }
  
  //metodo encargado de la Rotacion Alfabetica
  private static char xOR(char c, int clave){

   int newKey = (c^clave) ;
    return (char) (newKey);
  }
  
  //metodo para desencriptar un String
  
   public static String desencriptar(String s, int clave){
    String retornarValor="";
    for(int i=0; i<s.length();++i){
      char c= s.charAt(i);

      c= encriptar(c,clave);

      retornarValor +=String.valueOf(c);
      clave= (int)s.charAt(i);

    }
    return retornarValor;
  }

}
