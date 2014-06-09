/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reproductor.Librerias;

import Reproductor.Interfaz.Gui_play;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author JONATHAN
 */
public class TrayIcon {
    private Gui_play ClaseMaestra;
    public void MinimizarAlReloj() {
        getClaseMaestra().
        setVisible(false);
        getClaseMaestra().veces += 1;
        //dispose();
        //se declara el objeto tipo icono
        final java.awt.TrayIcon iconoSystemTray;
        //se verifica que el SystemTray sea soportado
        if (SystemTray.isSupported()) {
            //se obtiene una instancia estática de la clase SystemTray
            SystemTray tray = SystemTray.getSystemTray();
            //esta es la imagen de icono
            ImageIcon icono = new ImageIcon(Gui_play.class.getResource("/palomino/multimedia/audio/iconos/Reproductor.png"));
            iconoSystemTray = new java.awt.TrayIcon(icono.getImage(), "Reproductor Java", null);

            iconoSystemTray.setImageAutoSize(true);
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            ActionListener escuchadorSalir = new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Saliendo...");
                    System.exit(0);
                }
            };
            //menu que aparece al hacer click derecho
            final JPopupMenu popup = new JPopupMenu();
            JMenuItem mostrar = new JMenuItem("Mostrar Reproductor", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/icono.png")));
            JMenuItem subirVolumen = new JMenuItem("subirVolumen +");
            JMenuItem bajarVolumen = new JMenuItem("bajarVolumen -");
            JMenuItem PistaAnterior = new JMenuItem("PistaAnterior", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/anterior.JPG")));
            JMenuItem PistaSiguiente = new JMenuItem("PistaSiguiente", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/siguiente.JPG")));
            JMenuItem Pausar = new JMenuItem("Pausar", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/pausa.JPG")));
            JMenuItem Detener = new JMenuItem("Detener", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/parar.JPG")));
            JMenuItem Reproducir = new JMenuItem("Reproducir", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/reproducir.JPG")));
            JMenuItem salir = new JMenuItem("Salir", new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/salir.png")));
            salir.addActionListener(escuchadorSalir);
            //aniadir un menu con icono (swing)
            popup.add(Reproducir);
            popup.add(Pausar);
            popup.add(Detener);
            popup.addSeparator();
            popup.add(PistaAnterior);
            popup.add(PistaSiguiente);
            popup.addSeparator();
            popup.add(subirVolumen);
            popup.add(bajarVolumen);
            popup.addSeparator();
            popup.add(mostrar);
            popup.add(salir);

            Reproducir.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().getAudio().SeleccionarArchivo();
                }
            });
            Pausar.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().estado = !getClaseMaestra().estado;
                    if (getClaseMaestra().estado) {
                        getClaseMaestra().getAudio().pausar();
                    }
                    else {
                        getClaseMaestra().getAudio().resumir();
                    }
                }
            });
            Detener.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().getAudio().parar();
                }
            });
            PistaAnterior.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    try {
                        getClaseMaestra().getAudio().SiguienteAnterior(0, getClaseMaestra().control1);
                    }
                    catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            PistaSiguiente.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    try {
                        getClaseMaestra().getAudio().SiguienteAnterior(1, getClaseMaestra().control1);
                    }
                    catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            subirVolumen.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().Volumen.setValue(getClaseMaestra().Volumen.getValue() + 10);
                    getClaseMaestra().getAudio().ModificarVolumen(getClaseMaestra().Volumen);
                }
            });
            bajarVolumen.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().Volumen.setValue(getClaseMaestra().Volumen.getValue() - 10);
                    getClaseMaestra().getAudio().ModificarVolumen(getClaseMaestra().Volumen);
                }
            });
            mostrar.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    getClaseMaestra().setVisible(true);
                }
            });
            iconoSystemTray.addMouseListener(new MouseAdapter(){

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY() - 50);
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
            //se debe capturar una excepción en caso que falle la adicion de un icono
            try {
                if (getClaseMaestra().veces <= 1)//Verificar q solo se agrega una ves un tray icon
                {
                    tray.add(iconoSystemTray);
                }

            }
            catch (AWTException e) {
                System.err.println("No es posible agregar el icono al System Tray");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Tu sistema no soporta el System Tray :(", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setClaseMaestra(Gui_play ClaseMaestra) {
        this.ClaseMaestra=ClaseMaestra;
    }

    /**
     * @return the ClaseMaestra
     */
    public Gui_play getClaseMaestra() {
        return ClaseMaestra;
    }

}
