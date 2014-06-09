
import javax.swing.*;
import java.awt.*;
public class Emoticones extends JPanel
{
	private String imagen;
	private boolean q=false;
	public Emoticones(int i)
	{
		imagen="imagenes/"+i+".gif";
		if(i==9)
			q=true;
	}
	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);
		if(q)
			g.drawImage(getToolkit().getImage(imagen),5,0,this);
		else
			g.drawImage(getToolkit().getImage(imagen),5,5,this);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(33,33);
	}
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}
}
