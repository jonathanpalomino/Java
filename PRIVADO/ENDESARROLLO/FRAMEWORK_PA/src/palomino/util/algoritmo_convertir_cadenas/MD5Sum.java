package palomino.util.algoritmo_convertir_cadenas;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MD5Sum {

    public static final String MD5_ALGORITHM = "MD5";

    public static String computeSum(String input)
            throws NoSuchAlgorithmException {
        if (input == null) {
            throw new IllegalArgumentException("No puedes pasarme una cadena igual a null!!!");
        }
        StringBuilder sbuf = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] raw = md.digest(input.getBytes());
        for (int i = 0; i < raw.length; i++) {
            int c = raw[i];
            if (c < 0) {
                c = Math.abs(c) - 1 ^ 0xFF;
            }
            String block = toHex(c >>> 4) + toHex(c & 0xF);
            sbuf.append(block);
        }
        return sbuf.toString();
    }

    private static String toHex(int s) {
        if (s < 10) {
            return String.valueOf((char) (48 + s));
        }
        return String.valueOf((char) (65 + (s - 10)));

    }
}
