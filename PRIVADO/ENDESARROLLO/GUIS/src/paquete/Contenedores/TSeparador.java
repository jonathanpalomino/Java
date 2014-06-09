/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TSeparador extends JPanel
{
  public static final String VERTICAL = "TSeparador.VERTICAL";
  public static final String HORIZONTAL = "TSeparador.HORIZONTAL";
  public static final String DIAGONAL = "TSeparador.DIAGONAL";
  public static final String VACIO = "TSeparador.VACIO";
  protected int pixelsSeparacion = 15;
  protected Component componenteModelo;
  protected boolean dibujarSeparador = false;

  protected int alturaComponente = 15;

  protected String tipoSeparador = "TSeparador.HORIZONTAL";

  protected Color colorIzq = Color.darkGray;

  protected Color colorDer = Color.white;

  public TSeparador()
  {
    super.setOpaque(false);
    super.setRequestFocusEnabled(false);
    setTipoSeparador("TSeparador.VACIO");
    setAlturaSeparacion(0);
    setPixelsSeparacion(0);
    setLayout(new FlowLayout(1, 5, 5));
    setSize(23, 21);
  }

  public Dimension getPreferredSize()
  {
    Dimension miDim = new Dimension(getPixelsSeparacion(), getAlturaSeparacion());
    return miDim;
  }

  public Component getComponenteModelo()
  {
    return this.componenteModelo;
  }

  public void setComponenteModelo(Component componenteModelo)
  {
    this.componenteModelo = componenteModelo;
    this.pixelsSeparacion = componenteModelo.getPreferredSize().width;
  }

  public boolean getDibujarSeparador()
  {
    return this.dibujarSeparador;
  }

  public Color getColorIzq()
  {
    return this.colorIzq;
  }

  public void setColorIzq(Color colorIzq)
  {
    this.colorIzq = colorIzq;
  }

  public Color getColorDer()
  {
    return this.colorDer;
  }

  public void setColorDer(Color colorDer)
  {
    this.colorDer = colorDer;
  }

  public void setDibujarSeparador(boolean dibujarSeparador)
  {
    this.dibujarSeparador = dibujarSeparador;
  }

  public String getTipoSeparador()
  {
    return this.tipoSeparador;
  }

  public void setTipoSeparador(String tipoSeparador)
  {
    this.tipoSeparador = tipoSeparador;
  }

  /** @deprecated */
  public void setAlturaComponente(int alturaComponente)
  {
    setAlturaSeparacion(alturaComponente);
  }

  public void setAlturaSeparacion(int alturaComponente)
  {
    this.alturaComponente = alturaComponente;
  }

  /** @deprecated */
  public int getAlturaComponente()
  {
    return getAlturaSeparacion();
  }

  public int getAlturaSeparacion()
  {
    return this.alturaComponente;
  }

  public void setPixelsSeparacion(int pixelsSeparacion)
  {
    if (this.componenteModelo != null)
      this.pixelsSeparacion = this.componenteModelo.getPreferredSize().width;
    else
      this.pixelsSeparacion = pixelsSeparacion;
  }

  public int getPixelsSeparacion()
  {
    if (this.componenteModelo != null)
      this.pixelsSeparacion = this.componenteModelo.getPreferredSize().width;
    return this.pixelsSeparacion;
  }

  public void setRequestFocusEnabled(boolean pbFoco)
  {
    super.setRequestFocusEnabled(false);
  }

  public void setOpaque(boolean pbOpaco)
  {
    super.setOpaque(false);
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    if (!this.tipoSeparador.equals("TSeparador.VACIO"))
    {
      if (this.tipoSeparador.equals("TSeparador.VERTICAL"))
      {
        g.setColor(this.colorIzq);
        g.drawLine(this.pixelsSeparacion / 2, 0, this.pixelsSeparacion / 2, this.alturaComponente);
        g.setColor(this.colorDer);
        g.drawLine(this.pixelsSeparacion / 2 + 1, 0, this.pixelsSeparacion / 2 + 1, this.alturaComponente);
      }
      else if (this.tipoSeparador.equals("TSeparador.HORIZONTAL"))
      {
        g.setColor(this.colorIzq);
        g.drawLine(0, this.alturaComponente / 2 - 3, this.pixelsSeparacion, this.alturaComponente / 2 - 3);
        g.setColor(this.colorDer);
        g.drawLine(0, this.alturaComponente / 2 - 2, this.pixelsSeparacion, this.alturaComponente / 2 - 2);
      }
      else if (this.tipoSeparador.equals("TSeparador.DIAGONAL"))
      {
        g.setColor(this.colorIzq);
        g.drawLine(this.pixelsSeparacion / 2 + 2, 0, this.pixelsSeparacion / 2 - 3, this.alturaComponente);
        g.setColor(this.colorDer);
        g.drawLine(this.pixelsSeparacion / 2 + 3, 0, this.pixelsSeparacion / 2 - 2, this.alturaComponente);
      }
    }
  }

}
