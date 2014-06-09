/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.ByteArrayOutputStream;
/**
 *
 * @author synccon
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte[] in = null;
        LZWDecode(in);
        for(byte out :LZWDecode(in)){
            System.out.println(out);
        }
    }
    public static byte[] LZWDecode(byte in[]) {
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         LZWDecoder lzw = new LZWDecoder();
         lzw.decode(in, out);
         return out.toByteArray();
    }
}
