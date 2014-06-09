/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;
import java.io.Serializable;

public abstract class DatosBH
  implements Serializable
{
  public static String[] botoneras()
  {
    return new String[] { "DatosBHVacia", 
      "DatosBHGeneral", 
      "DatosBHMantenimiento", 
      "DatosBHMantenimientoEdicion", 
      "DatosBHImputacion", 
      "DatosBHConsulta", 
      "DatosBHSeleccion" };
  }

  public abstract String[] getIconosBoton();

  public abstract String[] getTooltipsBoton();

  public abstract String[] getPulsadosBoton();

  public abstract String[] getPropietariosBoton();

  public abstract String[] getFlagsBoton();

  public abstract String[] getMetodosBoton();

  public void datosBH(TBarraHerramientas pBarra)
  {
    pBarra.iniciaIconosBotones(getIconosBoton());
    pBarra.iniciaTooltipsBotones(getTooltipsBoton());
    pBarra.iniciaPulsadosBotones(getPulsadosBoton());
    pBarra.iniciaPropietariosBotones(getPropietariosBoton());
    pBarra.iniciaFlagsBotones(getFlagsBoton());
    pBarra.iniciaMetodosBotones(getMetodosBoton());
  }
}