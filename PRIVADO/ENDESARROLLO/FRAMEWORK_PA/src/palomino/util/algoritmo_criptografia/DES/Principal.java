/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.util.algoritmo_criptografia.DES;

/**
 *
 * @author JONATHAN
 */
public class Principal {

    private char llave[] = new char[10];
    private char subkey1[] = new char[8];
    private char subkey2[] = new char[8];

    public void Generar_llaves(String contrasena_Actual) {
        if (contrasena_Actual.length() < 10) {
        }

        llave = (new Llave(contrasena_Actual)).Llaves_Generadas();

        // P10
        char p10[] = new char[10];
        p10[0] = llave[2];
        p10[1] = llave[4];
        p10[2] = llave[1];
        p10[3] = llave[6];
        p10[4] = llave[3];
        p10[5] = llave[9];
        p10[6] = llave[0];
        p10[7] = llave[8];
        p10[8] = llave[7];
        p10[9] = llave[5];

        Seteo_de_bits LeftFiveBits = new Seteo_de_bits(5);
        Seteo_de_bits RightFiveBits = new Seteo_de_bits(5);

        for (int i = 0; i < p10.length; i++) {

            if (i < 5) {
                if (p10[i] == '1') {
                    LeftFiveBits.set(i);
                } else if (p10[i] == '0') {
                    LeftFiveBits.clear(i);
                }
            } else {
                if (p10[i] == '1') {
                    RightFiveBits.set(i);
                } else if (p10[i] == '0') {
                    RightFiveBits.clear(i);
                }
            }
        }

        // Apply LS_1 on each one

        Seteo_de_bits LS_1LeftBits = LeftFiveBits.LS_1(5);
        Seteo_de_bits LS_1RightBits = RightFiveBits.LS_1(5);

        // Apply P8 to produce the first subkey

        char La_llave_derecha_LS_1[] = new char[10];

        char left1[] = LS_1LeftBits.Bit_en_arregloChar(5);
        char right1[] = LS_1RightBits.Bit_en_arregloChar(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                La_llave_derecha_LS_1[i] = left1[i];
            } else {
                La_llave_derecha_LS_1[i] = right1[i - 5];
            }
        }

        subkey1[0] = La_llave_derecha_LS_1[5];
        subkey1[1] = La_llave_derecha_LS_1[2];
        subkey1[2] = La_llave_derecha_LS_1[6];
        subkey1[3] = La_llave_derecha_LS_1[3];
        subkey1[4] = La_llave_derecha_LS_1[7];
        subkey1[5] = La_llave_derecha_LS_1[4];
        subkey1[6] = La_llave_derecha_LS_1[9];
        subkey1[7] = La_llave_derecha_LS_1[8];

        // Apply LS_2

        Seteo_de_bits LS_2LeftBits = LS_1LeftBits.LS_2(5);
        Seteo_de_bits LS_2RightBits = LS_1RightBits.LS_2(5);

        // Apply P8

        char La_llave_Izquierda_LS_2[] = new char[10];

        char left2[] = LS_2LeftBits.Bit_en_arregloChar(5);
        char right2[] = LS_2RightBits.Bit_en_arregloChar(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                La_llave_Izquierda_LS_2[i] = left2[i];
            } else {
                La_llave_Izquierda_LS_2[i] = right2[i - 5];
            }
        }

        subkey2[0] = La_llave_Izquierda_LS_2[5];
        subkey2[1] = La_llave_Izquierda_LS_2[2];
        subkey2[2] = La_llave_Izquierda_LS_2[6];
        subkey2[3] = La_llave_Izquierda_LS_2[3];
        subkey2[4] = La_llave_Izquierda_LS_2[7];
        subkey2[5] = La_llave_Izquierda_LS_2[4];
        subkey2[6] = La_llave_Izquierda_LS_2[9];
        subkey2[7] = La_llave_Izquierda_LS_2[8];



    }

    public String Procesa_Encriptacion(String texto_entrada) {

        String textToDecrypt = texto_entrada;
        String texto_desencriptado = new String("");

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToDecrypt.length(); i++) {

            char theChar = textToDecrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }
            // chars to manipulate are now ready

            Algoritmo_DES alg = new Algoritmo_DES();

            char array1[] = new char[8];
            array1 = alg.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = alg.FK(array1, subkey2);
            char array3[] = new char[8];
            array3 = alg.SW(array2);
            char array4[] = new char[8];
            array4 = alg.FK(array3, subkey1);
            char array5[] = new char[8];
            array5 = alg.IP_inverse(array4);


            String tmp = new String("");
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            texto_desencriptado += (char) (Integer.parseInt(tmp, 2));

        }

        return texto_desencriptado;
    }

    public String Procesa_DesEncriptacion(String texto_desencriptar) {
        String textToDecrypt = texto_desencriptar;
        String texto_desencriptado = new String("");

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToDecrypt.length(); i++) {

            char theChar = textToDecrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }
            // chars to manipulate are now ready

            Algoritmo_DES s = new Algoritmo_DES();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey2);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey1);
            char array5[] = new char[8];
            array5 = s.IP_inverse(array4);


            String tmp = new String("");
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            texto_desencriptado += (char) (Integer.parseInt(tmp, 2));

        }

        return texto_desencriptado;
    }
}
