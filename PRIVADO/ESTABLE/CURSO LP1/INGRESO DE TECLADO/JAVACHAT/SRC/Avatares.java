
import java.awt.*;
import javax.swing.*;
public class Avatares extends JPanel
{
	private String imagen;
	private int cX,cY;
	public Avatares(String nombre,int x,int y)
	{
		cX=x;
		cY=y;
		imagen=nombre;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	
		g.drawImage(getToolkit().getImage(imagen),0,0,this);
	}
	public void asignar(String i)
	{
		imagen=i;
		repaint();
	}
	public String obtenerNombre()
	{
		return imagen;
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(cX,cY);
	}
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}
}
