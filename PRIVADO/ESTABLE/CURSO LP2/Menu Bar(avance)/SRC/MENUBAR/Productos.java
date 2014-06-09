
package menubar;
public class Productos {
	// datos privados
	private String Codigo,Descripcion,Codigoventa;
        int Cantidad;
	private double PrecioU;

	// constructor vacío
	public Productos(){ }

	// constructor que recibe un objeto con datos
	public Productos(Productos pro){
		Codigo		= pro.getCodigo();
                Codigoventa = pro.getCodigoventa();
		Descripcion	= pro.getDescripcion();
		Cantidad		= pro.getCantidad();
		PrecioU 		= pro.getPrecioU();
	}
    // funciones get/set generados por NetBeans
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioU() {
        return PrecioU;
    }

    public void setPrecioU(double PrecioU) {
        this.PrecioU = PrecioU;
    }

    public String getCodigoventa() {
        return Codigoventa;
    }

    public void setCodigoventa(String Codigoventa) {
        this.Codigoventa = Codigoventa;
    }
}
