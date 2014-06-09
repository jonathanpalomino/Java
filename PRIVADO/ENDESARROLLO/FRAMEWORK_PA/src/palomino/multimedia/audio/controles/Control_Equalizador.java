/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.multimedia.audio.controles;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author JONATHAN
 */
public class Control_Equalizador implements ActionListener, ChangeListener {

    private Dimension dim = null;
    boolean estado = false;
    private boolean initDone;
    private int minGain = 0;
    private int maxGain = 100;
    private int[] gainValue = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    private static int[] PRESET_NORMAL = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    private static int[] PRESET_CLASSICAL = {50, 50, 50, 50, 50, 50, 70, 70, 70, 76};
    private static int[] PRESET_CLUB = {50, 50, 42, 34, 34, 34, 42, 50, 50, 50};
    private static int[] PRESET_DANCE = {26, 34, 46, 50, 50, 66, 70, 70, 50, 50};
    private static int[] PRESET_FULLBASS = {26, 26, 26, 36, 46, 62, 76, 78, 78, 78};
    private static int[] PRESET_FULLBASSTREBLE = {34, 34, 50, 68, 62, 46, 28, 22, 18, 18};
    private static int[] PRESET_FULLTREBLE = {78, 78, 78, 62, 42, 24, 8, 8, 8, 8};
    private static int[] PRESET_LAPTOP = {38, 22, 36, 60, 58, 46, 38, 24, 16, 14};
    private static int[] PRESET_LIVE = {66, 50, 40, 36, 34, 34, 40, 42, 42, 42};
    private static int[] PRESET_PARTY = {32, 32, 50, 50, 50, 50, 50, 50, 32, 32};
    private static int[] PRESET_POP = {56, 38, 32, 30, 38, 54, 56, 56, 54, 54};
    private static int[] PRESET_REGGAE = {48, 48, 50, 66, 48, 34, 34, 48, 48, 48};
    private static int[] PRESET_ROCK = {32, 38, 64, 72, 56, 40, 28, 24, 24, 24};
    private static int[] PRESET_TECHNO = {30, 34, 48, 66, 64, 48, 30, 24, 24, 28};
    public static final int LINEARDIST = 1;
    public static final int OVERDIST = 2;
    private int eqdist = OVERDIST;
    private float[] bands = null;
    private int[] eqgains = null;
    public JSlider[] barras;
    private JPopupMenu mainpopup = null;
    public static String[] presets = {"Normal", "Classical", "Club", "Dance", "Full Bass", "Full Bass & Treble", "Full Treble", "Laptop", "Live", "Party", "Pop", "Reggae", "Rock", "Techno"};
    private javax.swing.JToggleButton btn1;
    private javax.swing.JButton btn2;
    JFrame equa;

    public Control_Equalizador(JToggleButton btn1, JButton btn2, JFrame parser) {
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.equa = parser;
        barras = new JSlider[11];
        eqgains = new int[10];
        Crear_barras();
        Crear_Submenus();
        initDone = true;
    }

    public void updateSliders(int[] gains) {
        if (gains != null) {
            for (int i = 0; i < gains.length; i++) {
                gainValue[i + 1] = gains[i];
                barras[i + 1].setValue(maxGain - gainValue[i + 1]);
            }
        }
    }

    public void SincronizarEqualizador() {
        if (btn1.isSelected()) {
            for (int j = 0; j < eqgains.length; j++) {
                eqgains[j] = -gainValue[j + 1] + maxGain;
            }
            updateBands(eqgains, minGain, maxGain);
        } else {
            for (int j = 0; j < eqgains.length; j++) {
                eqgains[j] = (maxGain - minGain) / 2;
            }
            updateBands(eqgains, minGain, maxGain);
        }
    }

    public void updateBands(int[] gains, int min, int max) {
        if ((gains != null) && (bands != null)) {
            int j = 0;
            float gvalj = (gains[j] * 2.0f / (max - min) * 1.0f) - 1.0f;
            float gvalj1 = (gains[j + 1] * 2.0f / (max - min) * 1.0f) - 1.0f;
            // Linear distribution : 10 values => 32 values.
            if (eqdist == LINEARDIST) {
                float a = (gvalj1 - gvalj) * 1.0f;
                float b = gvalj * 1.0f - (gvalj1 - gvalj) * j;
                // x=s*x'
                float s = (gains.length - 1) * 1.0f / (bands.length - 1) * 1.0f;
                for (int i = 0; i < bands.length; i++) {
                    float ind = s * i;
                    if (ind > (j + 1)) {
                        j++;
                        gvalj = (gains[j] * 2.0f / (max - min) * 1.0f) - 1.0f;
                        gvalj1 = (gains[j + 1] * 2.0f / (max - min) * 1.0f) - 1.0f;
                        a = (gvalj1 - gvalj) * 1.0f;
                        b = gvalj * 1.0f - (gvalj1 - gvalj) * j;
                    }
                    // a*x+b
                    bands[i] = a * i * 1.0f * s + b;
                    //System.out.println("bandas "+bands[i]);
                }
            } // Over distribution : 10 values => 10 first value of 32 values.
            else if (eqdist == OVERDIST) {
                for (int i = 0; i < gains.length; i++) {
                    bands[i] = (gains[i] * 2.0f / (max - min) * 1.0f) - 1.0f;
                    //System.out.println("bandas1 "+bands[i]);
                }
            }

        }
    }

    private void Crear_barras() {
        barras[0] = new JSlider(JSlider.VERTICAL);
        barras[0].setBounds(10, 90, 25, 95);
        barras[0].setValue(maxGain - gainValue[0]);
        equa.add(barras[0]);//Es el del volumen

        for (int i = 1; i < barras.length; i++) {
            barras[i] = new JSlider(JSlider.VERTICAL);
            barras[i].setValue(maxGain - gainValue[i]);
            barras[i].setBounds((90 + (i - 1) * 32), 80, 47, 86);
            barras[i].addChangeListener(this);
            equa.add(barras[i]);
        }
    }

    public void setBands(float[] bands) {
        if (this.bands != bands) {
            this.bands = bands;
            SincronizarEqualizador();
        }

    }

    public void stateChanged(ChangeEvent e) {
        if (initDone) {
            for (int i = 0; i < barras.length; i++) {
                gainValue[i] = maxGain - barras[i].getValue();
            }
            // Aplicando valores de bandas
            SincronizarEqualizador();
        }
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        // On/Off
        if (cmd.equals("Presente")) {
            mainpopup.show(equa, btn2.getLocation().x, btn2.getLocation().y);
        } else if (cmd.equals("Normal")) {
            updateSliders(PRESET_NORMAL);
            SincronizarEqualizador();
        } else if (cmd.equals("Classical")) {
            updateSliders(PRESET_CLASSICAL);
            SincronizarEqualizador();
        } else if (cmd.equals("Club")) {
            updateSliders(PRESET_CLUB);
            SincronizarEqualizador();
        } else if (cmd.equals("Dance")) {
            updateSliders(PRESET_DANCE);
            SincronizarEqualizador();
        } else if (cmd.equals("Full Bass")) {
            updateSliders(PRESET_FULLBASS);
            SincronizarEqualizador();
        } else if (cmd.equals("Full Bass & Treble")) {
            updateSliders(PRESET_FULLBASSTREBLE);
            SincronizarEqualizador();
        } else if (cmd.equals("Full Treble")) {
            updateSliders(PRESET_FULLTREBLE);
            SincronizarEqualizador();
        } else if (cmd.equals("Laptop")) {
            updateSliders(PRESET_LAPTOP);
            SincronizarEqualizador();
        } else if (cmd.equals("Live")) {
            updateSliders(PRESET_LIVE);
            SincronizarEqualizador();
        } else if (cmd.equals("Party")) {
            updateSliders(PRESET_PARTY);
            SincronizarEqualizador();
        } else if (cmd.equals("Pop")) {
            updateSliders(PRESET_POP);
            SincronizarEqualizador();
        } else if (cmd.equals("Reggae")) {
            updateSliders(PRESET_REGGAE);
            SincronizarEqualizador();
        } else if (cmd.equals("Rock")) {
            updateSliders(PRESET_ROCK);
            SincronizarEqualizador();
        } else if (cmd.equals("Techno")) {
            updateSliders(PRESET_TECHNO);
            SincronizarEqualizador();
        }
    }

    public void checkPopup(MouseEvent eventoMouse) {
        mainpopup.show(eventoMouse.getComponent(), eventoMouse.getX(), eventoMouse.getY() - 410);
    }

    private void Crear_Submenus() {
        mainpopup = new JPopupMenu();
        JMenuItem mi;
        for (int p = 0; p < presets.length; p++) {
            mi = new JMenuItem(presets[p]);
            mi.addActionListener(this);
            mi.setActionCommand(presets[p]);
            mainpopup.add(mi);
        }
    }

    // Método que hace efectivo el cambio de dimensiones 
    public void NuevoTamaño_Ventana(Dimension dim) {
        this.dim = new Dimension(dim);
        equa.resize(dim);
    }

    // Método que nos devuelve la dimensión actual 
    public Dimension preferredSize() {
        return (new Dimension(dim));
    }
}
