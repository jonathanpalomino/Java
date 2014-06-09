package tray;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class EjemploSystemTraySwing {
    public EjemploSystemTraySwing() {
        //se declara el objeto tipo icono
        final TrayIcon iconoSystemTray;
        //se verifica que el SystemTray sea soportado
        if (SystemTray.isSupported()) {
            //se obtiene una instancia est�tica de la clase SystemTray
            SystemTray tray = SystemTray.getSystemTray();
            //esta es la imagen de icono
            Image imagenIcono = Toolkit.getDefaultToolkit().getImage("/tray/icono.png");
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            ActionListener escuchadorSalir = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Saliendo...");
                    System.exit(0);
                }
            };
            //menu que aparece al hacer click derecho
            final JPopupMenu popup = new JPopupMenu();

            //aniadir un menu con icono (swing) :D
            JMenuItem carita = new JMenuItem("casidiablo.net",  new ImageIcon("/tray/carita.png"));
            popup.add(carita);
            popup.addSeparator();

            //aniadir el item de salir
            JMenuItem item = new JMenuItem("Exit", new ImageIcon("/tray/salir.png"));
            item.addActionListener(escuchadorSalir);
            popup.add(item);
            //iniciamos el objeto TrayIcon
            iconoSystemTray = new TrayIcon(imagenIcono, "Prueba System Tray", null);
            iconoSystemTray.setImageAutoSize(true);
            //iconoSystemTray.addMouseListener(mouseListener);
            iconoSystemTray.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY()-50);
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
            //se debe capturar una excepci�n en caso que falle la adicion de un icono
            try {
                tray.add(iconoSystemTray);
            } catch (AWTException e) {
                System.err.println("No es posible agregar el icono al System Tray");
            }
        }
        else
            System.err.println("Tu sistema no soporta el System Tray :(");
    }
    public static void main(String[] args) {
        new EjemploSystemTraySwing();
    }
}