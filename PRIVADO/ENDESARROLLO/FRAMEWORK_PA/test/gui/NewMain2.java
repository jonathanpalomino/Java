/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author synccon
 */
public class NewMain2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringBuilder ab = new StringBuilder("ooo");
        NewMain2 obj = new NewMain2();
        System.out.println(ab);

        obj.addParametros(ab, "IN", 2, 3);
        System.out.println(ab);
    }

    private void addParametros(StringBuilder ab, String iN, int i, int i0) {
        ab.replace(0, ab.length(), "iiii");
    }
}
