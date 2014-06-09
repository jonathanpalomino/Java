/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sodoku;

/**
 *
 * @author JONATHAN
 */
//LIBRERÍAS.
import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

public class SODOKU {
	
	//PROPIEDADES.
	public static int nivel = 1;
	
	
	//METODO PARA CARGAR JUEGO.
	public static int[][] cargar_juego( int nivel){
		
		int[][] matriz = new int[9][9];
		
		switch (nivel){
		
		case 2:
		
		matriz[0][0] = 7;matriz[0][4] = 5;matriz[0][6] = 4;matriz[1][0] = 4;
		matriz[1][3] = 1;matriz[1][4] = 9;matriz[1][6] = 6;matriz[1][7] = 2;
		matriz[1][8] = 7;matriz[2][2] = 6;matriz[2][8] = 9;matriz[3][0] = 9;
		matriz[3][2] = 3;matriz[3][6] = 8;matriz[4][3] = 4;matriz[4][5] = 3;
		matriz[5][2] = 8;matriz[5][6] = 5;matriz[5][8] = 2;matriz[6][0] = 5;
		matriz[6][6] = 2;matriz[7][0] = 2;matriz[7][1] = 9;matriz[7][2] = 1;
		matriz[7][4] = 4;matriz[7][5] = 7;matriz[7][8] = 8;matriz[8][2] = 7;
		matriz[8][4] = 1;matriz[8][8] = 5;
		
		break;
		
		
		case 1:
		default:
		
		matriz[0][2] = 9;matriz[0][5] = 8;matriz[0][6] = 5;	matriz[0][7] = 4;
		matriz[1][8] = 7;matriz[2][1] = 5;matriz[2][2] = 4;	matriz[2][4] = 9;
		matriz[2][6] = 1;matriz[3][5] = 6;matriz[3][6] = 3;	matriz[3][7] = 2;
		matriz[4][1] = 8;matriz[4][2] = 2;matriz[4][6] = 4;	matriz[4][7] = 1;
		matriz[5][1] = 3;matriz[5][2] = 5;matriz[5][3] = 2;	matriz[6][2] = 7;
		matriz[6][4] = 3;matriz[6][6] = 2;matriz[6][7] = 5;	matriz[7][0] = 9;
		matriz[8][1] = 4;matriz[8][2] = 3;matriz[8][3] = 8;	matriz[8][7] = 9;
		
		break;
		
		}
		
		return matriz;
	}
	
	//METODO PARA NO SOBREESCRIBIR VALORES EN EL JUEGO.
	public static boolean es_origen( int fila, int columna, int[][] matriz ){
		
		boolean resultado = false;
		if ( matriz[fila][columna] != 0)
			resultado = true;
		
		return resultado;
		
	}
	
	//METODO QUE DETECTA SI EL JUEGO FUE TERMINADO.
	public static boolean terminado( int[][] matriz ){
		
		boolean resultado = true;
		
		for ( int f = 0; f < matriz.length; f ++)
			for ( int c = 0; c < matriz[0].length; c ++)
				if ( matriz[f][c] == 0 )
					resultado = false;
		
			
		return resultado;

	}
	
	//METODO QUE IMPRIME UN MENSAJE CON BORDE.
	public static void mensaje ( String mensaje ){
		
		//CABECERA.
		System.out.print("É");
		for(int i = 0; i < (mensaje.length() + 20); i ++ )
			System.out.print ("Í");
		System.out.print ("»\n");

		//CUERPO.
		System.out.print("º          ");
		System.out.print( mensaje );
		System.out.print("          º\n");
	
		//PIE
		System.out.print("È");
		for(int i = 0; i < (mensaje.length() + 20); i ++ )
			System.out.print ("Í");
			
		System.out.print ("¼\n\n");
			
		
	}
	
	//METODO QUE IMPRIME EL VECTOR.
	public static void imprime_vector ( int[][] matriz ){
		
		
		System.out.println( " °°°°°°°°°°°°²°°°°°°°°°°°²°°°°°°°°°°°° " );
		
		for ( int f = 0; f < matriz.length; f ++ ){
		
			System.out.print(" ° ");
			
			for ( int c = 0; c < matriz.length; c++){
				
				if ( matriz[f][c] != 0 ) 
					System.out.print ( matriz[f][c] );
				else 
					System.out.print (" ");
				
				if ( es_origen( f, c, cargar_juego( nivel ) ) )
					System.out.print (" ");
				else
					System.out.print (" ");
				
				if ( c == 2 || c == 5) 
					System.out.print ("² ");
				else
					System.out.print ("° ") ;
					
			}
			System.out.println();
			if ( f != 2 && f != 5)
				System.out.print( " °°°°°°°°°°°°²°°°°°°°°°°°²°°°°°°°°°°°° " );
			else
				System.out.print( " ²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²²² " );
			System.out.println();
		}
	}

	//METODO PARA COMPROBAR FILAS.
	public static boolean existe_fila( int numero, int fila, int[][] matriz ){
		
		boolean resultado = false;
		
		for ( int i = 0; i < matriz.length; i ++ )
			if ( matriz[(fila-1)][i] == numero ){
			 	resultado = true;
			 	break;
			}
			
		//COMPROBAMOS SI ES 0.
		if ( numero == 0 ) resultado = false;
		
		return resultado;
		
	}
	
	//METODO PARA COMPROBAR COLUMNAS.
	public static boolean existe_columna( int numero, int columna, int[][] matriz ){
		
		boolean resultado = false;
		
		for ( int i = 0; i < matriz.length; i ++ )
			if ( matriz[i][(columna-1)] == numero ){
			 	resultado = true;
			 	break;
			}
			
		//COMPROBAMOS SI ES 0.
		if ( numero == 0 ) resultado = false;
		
		return resultado;
		
	}
	
	// METODO PARA COMPROBAR LOS INDICES.
	public static boolean comprobar_indice ( int indice ){
		
		if ( indice > 0 && indice < 10)
			return true;
		else
			return false;
		
	}
	
	// METODO PARA COMPROBAR LOS VALORES.
	public static boolean comprobar_valor ( int valor ){
		
		if ( valor >= 0 && valor < 10)
			return true;
		else
			return false;
		
	}
	
	//METODO PARA COMPROBAR LAS CAJAS.
	public static boolean existe_caja ( int valor, int fila, int columna, int[][] matriz ){
		
		//VARIABLES.
		int minimo_fila;
		int maximo_fila;
		int minimo_columna;
		int maximo_columna;
		boolean resultado = false;
	
		//DETERMINAMOS LAS FILAS DE LA CAJA.
		if ( fila > 0 && fila < 4){
			minimo_fila = 0;
			maximo_fila = 2;
		}else if ( fila > 3 && fila < 7 ){
			minimo_fila = 3;
			maximo_fila = 5;
		}else{
			minimo_fila = 6;
			maximo_fila = 8;
		}
				
		//DETERMINAMOS LAS COLUMNAS DE LA CAJA.
		if ( columna > 0 && columna < 4){
			minimo_columna = 0;
			maximo_columna = 2;
		}else if ( columna > 3 && columna < 7 ){
			minimo_columna = 3;
			maximo_columna = 5;
		}else{
			minimo_columna = 6;
			maximo_columna = 8;
		}
		
		//RECORREMOS EL RANGO DE LA CAJA, Y BUSCAMOS EL VALOR.
		for ( int f = minimo_fila; f <= maximo_fila; f++ )
			for ( int c = minimo_columna; c <= maximo_columna; c++)
				if ( matriz[f][c] == valor ){
					resultado = true;
					break;	
					
				}
			
			
		//COMPROBAMOS SI ES 0.
		if ( valor == 0 ) resultado = false;
		
		//REGRESAMOS EL VALOR BOOLEANO.
		return resultado;
			
	
		
	}
	
	//METODO PRINCIPAL
	public static void main ( String[] args ) throws Exception
	{
		
		//VARIABLES.
		BufferedReader teclado = new BufferedReader( new InputStreamReader ( System.in ) );
		int[][] sudoku = new int[9][9];
		int fila = 0;
		int columna = 0;
		int valor = 0;
		
		//CARGAMOS EL JUEGO.
		sudoku = cargar_juego( nivel );
			
		while ( true ){
		
		//IMPRIMIMOS EL VECTOR.
		imprime_vector ( sudoku );
		
		//PEDIMOS LOS DATOS.
		System.out.println( "Inserte las coordenadas (fila/columna): " );
		
		//FILA.
		System.out.print( "[fila]: " );
		fila = Integer.parseInt( teclado.readLine() );
		
		//COLUMNA.
		System.out.print( "[columna]: " );
		columna = Integer.parseInt( teclado.readLine() );
		
		//VALOR.
		System.out.print( "[valor]: " );
		valor = Integer.parseInt( teclado.readLine() );
		
		//COMPROBAMOS LA FILA ESTA EN RANGO.
		if ( !comprobar_indice(fila) )
			mensaje ("El valor de la fila no es correcto..");
		
		//COMPROBAMOS LA COLUMNA ESTA EN RANGO.
		else if ( !comprobar_indice(columna) )
			mensaje ( "El valor de la columna no es correcto.");
			
		//COMPROBAMOS QUE EL VALOR ESTA EN RANGO.
		else if ( !comprobar_valor(valor) )
			mensaje ( "El valor introducido no es valido..");
			
		//COMPROBAMOS QUE USE CASILLAS DISPONIBLES.
		else if ( es_origen( (fila - 1), (columna - 1), cargar_juego( nivel ) ) )
			mensaje ( "Ese valor es predeterminado del juego...");
			
		//COMPRUEBA QUE NO SE REPITA EL VALOR EN LA FILA.
		else if ( existe_fila( valor, fila, sudoku ) )
			mensaje ("[X] El valor " + valor + " ya ha sido usado en la fila..");
			
		//COMPRUEBA QUE NO SE REPITA EL VALOR EN LA COLUMNA..
		else if ( existe_columna( valor, columna, sudoku ) )
			mensaje ( "[X] El valor " + valor + " ya ha sido usado en la columna..");
			
		//COMPRUEBA QUE EL VALOR NO ESTÉ EN LA CAJA.
		else if ( existe_caja( valor, fila, columna, sudoku ) )
			mensaje ( "[X] El valor ya existe en la caja..");
			
		//INTRODUCIMOS EL VALOR A LA MATRIZ.
		else {
			sudoku[(fila - 1)][(columna - 1)] = valor;
			mensaje( "[" + fila + "," + columna + "]=" + valor + " Correcto.");
		}
		
		
		//COMPRUEBA SI SE TERMINÓ EL JUEGO.
		if ( terminado( sudoku ) ){
			mensaje( "FELICIDADES!!!! HAS TERMINADO EL JUEGO!!");
			imprime_vector( sudoku );
			System.out.println ( "Presiona una tecla para continuar en el siguiente nivel..");
			teclado.readLine();
			
			//AUMENTAMOS EL NIVEL DEL JUEGO.
			nivel ++;
			sudoku = cargar_juego( nivel );
			mensaje( "SUDOKU NIVEL " + nivel );
		}
	
	
		}
	
	}	

	
}

