
package palomino.util.algoritmo_criptografia.DES;

/**
 *
 * @author JONATHAN
 */
import java.util.*;

public class Seteo_de_bits extends BitSet {
	
	public Seteo_de_bits( int tamaño ) {
		super(tamaño);
	}
	
	public char[] Bit_en_arregloChar(int numero_De_bit_faltantes ) {
		
		char Bit_Aceptado[] = new char[numero_De_bit_faltantes];
		
		for ( int i = 0; i < numero_De_bit_faltantes; i++ ) {
			if ( super.get(i) )
			Bit_Aceptado[i] = '1';
			else
			Bit_Aceptado[i] = '0';
		}
		
		return Bit_Aceptado;
	}
	
	public void Inicializar_ArregoChar( char a[] ) {
		
		for ( int i = 0; i < a.length; i++ ) {
			if ( a[i] == '1' )
			this.set(i);
			else if ( a[i] == '0' )
			this.clear(i);
		}
	}

	public Seteo_de_bits LS_1(int numero_De_bit_faltantes) {
		
		char oldBitSet[] = this.Bit_en_arregloChar(numero_De_bit_faltantes);
		char newBitSet[] = new char[oldBitSet.length];
		
		newBitSet[ newBitSet.length - 1 ] = oldBitSet[0];
		
		for ( int i = 0; i < newBitSet.length - 1; i++ ) {
			newBitSet[i] = oldBitSet[i + 1];
		}
		
		Seteo_de_bits shiftedSet = new Seteo_de_bits(newBitSet.length);
		shiftedSet.Inicializar_ArregoChar(newBitSet);
		
		return shiftedSet;
	}
	
	public Seteo_de_bits LS_2( int numero_De_bit_faltantes ) {
		
		Seteo_de_bits primer = new Seteo_de_bits( numero_De_bit_faltantes);
		Seteo_de_bits segundo = new Seteo_de_bits(numero_De_bit_faltantes);
		
		primer = this.LS_1(numero_De_bit_faltantes);
		segundo = primer.LS_1(numero_De_bit_faltantes);
		
		return segundo;
	}
}		