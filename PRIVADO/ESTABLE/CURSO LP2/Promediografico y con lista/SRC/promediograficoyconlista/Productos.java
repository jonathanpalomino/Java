package promediograficoyconlista;
/**
 *
 * @author JONATHAN1
 */
public class Productos {
	// datos privados
    String Descripcion,Categoria;
    double precio;
    int cantidad,Cod,Codpro;

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
    // funciones get/set generados por NetBeans
    public int getCodigo() {
        return Cod;
    }

    public void setCodigo(int Codigo) {
        this.Cod = Codigo;
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

     public String getDescripcion()
    {
        return Descripcion;
    }
     public void setDescripcion(String Descripcion)
     {
         this.Descripcion = Descripcion;
     }

    public String getCategoria()
    {
        return Categoria;
    }
    public void setCategoria(String Categoria)
    {
        this.Categoria = Categoria;
    }

    public int getCodigopro()
    {
       return Codpro;
    }

    public void setCodigopro(int Codpro)
    {
        this.Codpro = Codpro;
    }
}