/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validador.rut;
/*
 * v.java
 *
 * Creado en 09/08/2011, 05:58:13 PM
 *
 * Aplicacion creada por jonathan-palomino.blogspot.com.
 *
 */

/**
 *
 * @author Jonathan palomino <jhonelfenix@gmail.com>
 */
class v {
static String RUT="12345678";
    public static void main(String args[]) {
        int M = 0, S = 1;
        int T = Integer.parseInt(RUT);
        for (; T != 0; T /= 10) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        System.out.println(RUT+"-"+(char) (S != 0 ? S + 47 : 75));
    }
}
