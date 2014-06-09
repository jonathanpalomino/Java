/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pru;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import ultimo.JPFrame;
import ultimo.JPTextField;

/**
 *
 * @author JONATHAN
 */
public class Test2 extends JPFrame{
JPanel pnlPrueba = new JPanel();
    public Test2() {
        setSize(200, 200);
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(pnlPrueba);
        
        JPTextField jp = new JPTextField();
        JPTextField jp2 = new JPTextField();
        
        jp.setLongitud("10,10");
        jp.setMascaraFecha("MM-yyyy");
        jp.setTipoDato("FECHA");
        jp.setFuncionValidacion("PAQUETE.P_DATOS");
        jp.setMetodoPreValidacion(this, "preValjp");
        jp.setObligatorio(true);
        
        //jp.setMetodoPostCampo(this, "metodo");
        jp.setPanelGrupo(pnlPrueba);
        
        jp2.setLongitud("10,10");
        jp2.setObligatorio(true);
        jp2.setMetodoPreCampo(this,"metodo");
        //jp2.setTipoDato("TEXTO");
        jp2.setPanelGrupo(pnlPrueba);
        
        pnlPrueba.add(jp);
        pnlPrueba.add(jp2);
    }

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        new Test2().setVisible(true);
    }
    public Boolean metodo(){
        System.out.println("*********************");
        return Boolean.TRUE;
    }
    
    public Boolean preValjp(){
        System.out.println("---------------------");
        return Boolean.TRUE;
    }
}
