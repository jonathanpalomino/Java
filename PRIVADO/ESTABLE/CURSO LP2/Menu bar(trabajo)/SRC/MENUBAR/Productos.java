package menubar;
/**
 *
 * @author JONATHAN1
 */
public class Productos {
    private // datos privados
    String Descripcion;
    private String Categoria;
    private double precio;
    private int cantidad;
    private String Cod;
    private int Codpro;

	// constructor vacío
	public Productos(){ }

	// constructor que recibe un objeto con datos
	public Productos(Productos obj)
    {
        Descripcion = obj.getDescripcion();
        Categoria = obj.getCategoria();
		Cod		= obj.getCodigo();
        Codpro = obj.getCodigopro();
		precio	= obj.getprecio();
		cantidad 		= obj.getcantidad();

	}

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }


    public String getCategoria() {
        return Categoria;
    }


    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public double getprecio() {
        return precio;
    }

    public void setprecio(double precio) {
        this.precio = precio;
    }


    public int getcantidad() {
        return cantidad;
    }


    public void setcantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public String getCodigo() {
        return Cod;
    }

    public void setCodigo(String Cod) {
        this.Cod = Cod;
    }


    public int getCodigopro() {
        return Codpro;
    }

    public void setCodigopro(int Codpro) {
        this.Codpro = Codpro;
    }

}