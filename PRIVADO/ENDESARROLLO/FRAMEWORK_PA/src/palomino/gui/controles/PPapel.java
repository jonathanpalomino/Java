/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.gui.controles;

/**
 *
 * @author JONATHAN
 */
import java.awt.print.Paper;

public final class PPapel extends Paper
{
  public static final int A4 = 0;
  public static boolean VERTICAL = true;
  public static boolean HORIZONTAL = false;

  protected static Paper paper = new Paper();
  protected double pageWidth;
  protected double pageHeight;
  protected double leftMargin;
  protected double topMargin;
  protected double printWidth;
  protected double printHeight;

  public PPapel(int tipo, boolean vertical)
  {
    switch (tipo)
    {
    case 0:
      format(8.27D, 11.7018D, 0.0D, 0.0D, vertical);
      break;
    default:
      throw new IllegalArgumentException("Tipo incorrecto " + tipo);
    }
  }

  protected void format(double pageWidthInch, double pageHeighInch, double leftMarginCm, double topMarginCm, boolean vertical)
  {
    this.pageWidth = inchToPixel(pageWidthInch);
    this.pageHeight = inchToPixel(pageHeighInch);

    this.leftMargin = cmToPixel(leftMarginCm);
    this.topMargin = cmToPixel(topMarginCm);

    if (!vertical)
    {
      double aux = this.leftMargin;
      this.leftMargin = this.topMargin;
      this.topMargin = aux;
    }

    this.printWidth = (this.pageWidth - this.leftMargin);
    this.printHeight = (this.pageHeight - this.topMargin);
    setPageSize();
  }

  protected void setPageSize()
  {
    paper.setSize(this.pageWidth, this.pageHeight);
  }

  protected void setImageableArea()
  {
    paper.setImageableArea(this.leftMargin, this.topMargin, this.printWidth, this.printHeight);
  }

  public double pixelToCm(double value)
  {
    return pixelToInch(value) / 0.3937D;
  }

  public double pixelToInch(double value)
  {
    return value / 72.0D;
  }

  public double cmToPixel(double value)
  {
    return cmToInch(value) * 72.0D;
  }

  public double cmToInch(double value)
  {
    return value * 0.3937D;
  }

  public double inchToPixel(double value)
  {
    return 72.0D * value;
  }
}
