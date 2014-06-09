/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias;

/**
 *
 * @author JONATHAN
 */
public class Productos {

    private String Producto;
    private double Requerimiento_Anual;
    private double Precio_articulo;
    private double multiplo;

    public Productos() {
    }

    public Productos(Productos obj) {
        Producto = obj.getProducto();
        Requerimiento_Anual = obj.getReq_Anual();
        Precio_articulo = obj.getPrecio();
        multiplo = obj.getmultiplo();
    }

    /**
     * @return the periodo
     */
    public String getProducto() {
        return Producto;
    }

    /**
     * @param Producto the periodo to set
     */
    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    /**
     * @return the var_Y
     */
    public double getReq_Anual() {
        return Requerimiento_Anual;
    }

    /**
     * @param Req_Anual the var_Y to set
     */
    public void setReq_Anual(double Req_Anual) {
        this.Requerimiento_Anual = Req_Anual;
    }

    /**
     * @return the EM
     */
    public double getPrecio() {
        return Precio_articulo;
    }

    /**
     * @param Precio the EM to set
     */
    public void setPrecio(double Precio) {
        this.Precio_articulo = Precio;
    }

    /**
     * @return the VarY_y
     */
    public double getmultiplo() {
        return multiplo;
    }

    /**
     * @param multiplo the VarY_y to set
     */
    public void setmultiplo(double multiplo) {
        this.multiplo = multiplo;
    }

}
