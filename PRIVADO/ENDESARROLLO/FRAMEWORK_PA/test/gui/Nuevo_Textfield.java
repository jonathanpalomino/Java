/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import palomino.gui.controles.PTextField;

/**
 *
 * @author synccon
 */
public class Nuevo_Textfield extends JFrame {

    public Nuevo_Textfield() {
        setSize(400, 200);
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(pnlGeneral, java.awt.BorderLayout.CENTER);

        pnlGeneral.setLayout(new java.awt.BorderLayout(0, 0));
        pnlGeneral.add(scrGeneral, java.awt.BorderLayout.CENTER);

        scrGeneral.getViewport().add(pnlDatos);

        pnlDatos.setLayout(new BorderLayout(0, 0));
        pnlDatos.add(pnlCaptura, java.awt.BorderLayout.NORTH);

        JTextField txt = new JTextField();
        JTextField lbl = new JTextField();
        PTextField pt = new PTextField();
        txt.setText("---88888------");
        lbl.setText("---99999------");
        

        pnlPlanCabecera.add(txt);
        pnlPlanRiesgo.add(pt);
        
        pnlCaptura.setLayout(new GridBagLayout());
        pnlCaptura.add(pnlPlanCabecera, new java.awt.GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
                java.awt.GridBagConstraints.WEST,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(
                0, 0, 0, 0), 0, 0));
        pnlCaptura.add(pnlPlanRiesgo, new java.awt.GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                java.awt.GridBagConstraints.WEST,
                java.awt.GridBagConstraints.NONE, new java.awt.Insets(
                0, 0, 0, 0), 0, 0));
    }

    public static void main(String args[]) {
        new Nuevo_Textfield().setVisible(true);
    }
    JScrollPane scrGeneral = new JScrollPane();
    JPanel pnlGeneral = new JPanel();
    JPanel pnlDatos = new JPanel();
    JPanel pnlCaptura = new JPanel();
    JPanel pnlPlanCabecera = new JPanel();
    JPanel pnlPlanRiesgo = new JPanel();
}
