package examenfinallp2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Problema_2 extends  JFrame implements Calculo{
JTextField txtnumero = new JTextField();
JButton btncalcular = new JButton("Calcular");
JButton btnmostrar = new JButton("Mostrar");
JButton btnGuardar = new JButton("Guardar");
JTextArea caja = new JTextArea();
JLabel vacio = new JLabel();

static class NodoDoble{
    int numero;
NodoDoble anterior;
NodoDoble siguiente;
}
NodoDoble inicio;
NodoDoble fin;
public Problema_2()
    {
setSize(500, 500);
setTitle("Programa 2");
txtnumero.setBounds(150, 100,100,20);
btncalcular.setBounds(150, 150,100,20);
btnmostrar.setBounds(150, 200,100,20);
btnGuardar.setBounds(250, 200,100,20);
caja.setBounds(100,250,350,200);
caja.setVisible(false);
btncalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                caja.setVisible(false);
                Enviar_A_Nodo();
                txtnumero.setText("");
                txtnumero.requestFocus();
            }

        });

        btnmostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Mostrar();
            }

        });

        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Guardar_En_Archivo(caja);
            }

        });
add(txtnumero);
add(btnmostrar);
add(btncalcular);
add(btnGuardar);
add(caja);
add(vacio);
    }
    public static void main(String[] args) {
        Problema_2 obj = new Problema_2();
        obj.show();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   public int Procesar(NodoDoble aux)
    {
        int cifrafin,res=0;
        int temp= aux.numero;
        int otro= aux.numero;
        cifrafin= temp%10;

        while(temp!=0)
        {
            res= temp%10;
            temp= temp/10;
        }

        if(cifrafin==res)
        {
            return  1;
        }
        else
        {
           return otro;
        }
    }
    public void Mostrar()
    {
        caja.setVisible(true);
        String acu ="Numeros encontrados"+"\n";
        NodoDoble aux=fin;
        while(aux!=null)
        {
        if(Procesar(aux)!=1)
        {
            acu = acu + String.valueOf(Procesar(aux))+"\n";
            caja.setText(acu);
        }
        aux = aux.anterior;
        }
    }
    public void Enviar_A_Nodo() {
NodoDoble nuevo;
nuevo = new NodoDoble();
nuevo.numero = Integer.parseInt(txtnumero.getText());
nuevo.siguiente= null;
nuevo.anterior = fin;

if(inicio==null)
{
    inicio= nuevo;
}
else
{
    fin.siguiente = nuevo;
}
fin= nuevo;
    }

    	public void Guardar_En_Archivo( JTextArea area )
    {
    	JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int result = fch.showSaveDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
    	try {
    		FileOutputStream fos = new FileOutputStream( fch.getSelectedFile() );
    		BufferedOutputStream bos = new BufferedOutputStream( fos );
            bos.write( area.getText().getBytes() );
            bos.flush();
        	fos.close();
        }

		catch( Exception e )
		{
			JOptionPane.showMessageDialog(this, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}
}
interface Calculo 
{
    int Procesar(Problema_2.NodoDoble uno);

}
