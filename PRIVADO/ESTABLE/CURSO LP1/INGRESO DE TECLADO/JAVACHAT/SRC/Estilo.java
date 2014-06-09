
import java.awt.*;
class Estilo
{
	private Color color;
	private Font fuente;
	public final int TAMANIO=12;
	private int tipo;
	public Estilo()
	{
		color=Color.BLACK;
		tipo=0;
		fuente=new Font("SansSerif",tipo,TAMANIO);
	}
	public void asignarColor(Color c)
	{
		color=c;
	}
	public void asignarNombreFuente(String s)
	{
		fuente=new Font(s,tipo,TAMANIO);
	}
	public void asignarTipo(int t)
	{
		tipo=t;
		fuente=new Font(fuente.getFontName(),tipo,TAMANIO);
	}
	public Color obtenerColor()
	{
		return color;
	}
	public Font obtenerFuente()
	{
		return fuente;
	}
}
