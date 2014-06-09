/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algo_julio_cesar;

/**
 *
 * @author JONATHAN
 */
public class Bifido {

    char[][] matriz = new char[6][6];
    char[] mensaje;
    private String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Bifido(){
        //la tabla esta formada por 25 letras, donde I=J
        matriz[1][1]='A'; matriz[1][2]='B';matriz[1][3]='C';matriz[1][4]='D';matriz[1][5]='E';
        matriz[2][1]='F'; matriz[2][2]='G';matriz[2][3]='H';matriz[2][4]='I';matriz[2][5]='K';
        matriz[3][1]='L'; matriz[3][2]='M';matriz[3][3]='N';matriz[3][4]='O';matriz[3][5]='P';
        matriz[4][1]='Q'; matriz[4][2]='R';matriz[4][3]='S';matriz[4][4]='T';matriz[4][5]='U';
        matriz[5][1]='V'; matriz[5][2]='W';matriz[5][3]='X';matriz[5][4]='Y';matriz[5][5]='Z';
    }

    public String Encriptar(String t){
        t = clean(t);
        String nt="";
        String enc="";
        int fila ;
        int col;
        //para cada caracter se extrae el par de numeros correspondientes en la matriz
        for(int i=0; i<t.length();i++){
            nt = nt + getposicion(t.charAt(i));
        }
        //Ahora se realiza el proceso de encriptacion
        for(int i=0;i<nt.length()/2;i++){
            fila = Integer.parseInt(String.valueOf(nt.charAt(i)));
            col = Integer.parseInt(String.valueOf(nt.charAt(i + nt.length()/2)));
            enc = enc + String.valueOf(matriz[fila][col]);
        }
        enc=build(enc);
        return enc;
    }

    public String Desencriptar(String t){
        t=clean(t);
        String nt="";
        String tmp1="";
        String tmp2="";
        boolean b=true;
        //para cada caracter se extrae el par de numeros correspondientes en la matriz
        for(int i=0; i<t.length();i++){
            nt = nt + getposicion(t.charAt(i));
        }
        //como la cadena de numeros se encuentra encriptada, se procede
        //a su nuevo ordenamiento
        for(int i=0;i<nt.length();i++){
            if(b){
                tmp1= tmp1 + nt.charAt(i);
                b=false;
            }
            else
            {
                tmp2= tmp2 + nt.charAt(i);
                b=true;
            }
        }
        nt= tmp1+tmp2;
        tmp1="";
        int fila;
        int col;
        //ahora que ya se tiene a la cadena de numeros con el orden original
        //se procede a reconstruir el mensaje
        for(int i=0; i<nt.length();i+=2){
            fila = Integer.parseInt(String.valueOf(nt.charAt(i)));
            col = Integer.parseInt(String.valueOf(nt.charAt(i+1)));
            tmp1 = tmp1 + String.valueOf(matriz[fila][col]);
        }
        tmp1=build(tmp1);
        return tmp1;
    }
    //obtiene la posicion de filas y columnas correspondientes al caracter
    //pasado como parametro en la matriz
    private String getposicion(char c){
        String p = null;
        if(c=='J'){//como el caracter J no se encuentra en la tabla
            //se añade automaticamente con la posicion de I
            p="24";
        }
        else{
            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    if(c==matriz[i][j]){
                      p = Integer.toString(i)+Integer.toString(j);
                      break;
                    }
                }
            }
        }
        return p;
    }
    //encargado de limpiar el mensaje extrayendo solo las letras y
    //guardando los caracteres especiales para rearmar el mensaje despues
    private String clean(String t){
      t = t.toUpperCase();
      mensaje = t.toCharArray();
      String tmp1="";
      for(int i=0;i<mensaje.length;i++){
          int x = abc.indexOf(mensaje[i]);
          if(x!=-1){//es una letra valida
              tmp1=tmp1+mensaje[i];
          }
      }
      return tmp1;
    }
    //reconstruye el mensaje con las palabras encriptadas/desencriptadas
    //añadiendo los caracteres especiales
    private String build(String t){
        String tt="";
        int count=0;
        for(int i=0;i<mensaje.length;i++){
            int x = abc.indexOf(mensaje[i]);
            if(x!=-1){
                tt= tt + t.charAt(count);
                count++;
            }
            else
            {
                tt=tt + mensaje[i];
            }
        }
        return tt;
    }
}
