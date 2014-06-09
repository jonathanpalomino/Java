/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package encriptamientodes;

/**
 *
 * @author JONATHAN
 */

public class Llave {
	private String Contraseña;

	private char La_Llave[] = new char[10];//Amplitud de la contraseña

	public Llave( String currentPassword )
	{
		Contraseña = currentPassword;

	}
	public char[] Llaves_Generadas()//Desarmar la llave en arreglo de caracteres
	{

		char array[] = Contraseña.toCharArray();

		int valor;

		for ( int i = 0; i < 10; i++ ) {


			valor = (int) array[i];

			String s = Integer.toBinaryString(valor);

			La_Llave[i] = s.charAt(5);


		}
		String llave_String;

		String firstChar = String.valueOf(La_Llave[0]);
		String secondChar = String.valueOf(La_Llave[1]);
		String thirdChar = String.valueOf(La_Llave[2]);
		String fourthChar = String.valueOf(La_Llave[3]);
		String fifthChar = String.valueOf(La_Llave[4]);
		String sixthChar = String.valueOf(La_Llave[5]);
		String seventhChar = String.valueOf(La_Llave[6]);
		String eightChar = String.valueOf(La_Llave[7]);
		String nineChar = String.valueOf(La_Llave[8]);
		String tenthChar = String.valueOf(La_Llave[9]);

		llave_String = firstChar + secondChar + thirdChar + fourthChar + fifthChar +
		            sixthChar + seventhChar + eightChar + nineChar + tenthChar;

	    return La_Llave;

	}
}