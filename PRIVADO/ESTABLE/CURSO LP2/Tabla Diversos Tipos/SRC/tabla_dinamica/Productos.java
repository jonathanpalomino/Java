/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tabla_dinamica;
/**
 *
 * @author JONATHAN1
 */
public class Productos {
    private // datos privados
    String Apellidos;
    private int ExamenPar;
    private int Prompp;
    private double PromFin;
    private String Codigo;
    private int ExamenFin;

	// constructor vacÃ­o
	public Productos(){ }

	// constructor que recibe un objeto con datos
	public Productos(Productos obj)
    {
        Apellidos = obj.getApellidos();
        ExamenPar = obj.getExamenPar();
        Codigo		= obj.getCodigo();
        ExamenFin = obj.getExamenFin();
	Prompp	= obj.getPrompp();
	PromFin = obj.getPromFin();

	}

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellido) {
        this.Apellidos = Apellido;
    }


    public int getExamenPar() {
        return ExamenPar;
    }


    public void setExamenPar(int ExamenPar) {
        this.ExamenPar = ExamenPar;
    }

    public int getPrompp() {
        return Prompp;
    }

    public void setPrompp(int Prompp) {
        this.Prompp = Prompp;
    }


    public double getPromFin() {
        return PromFin;
    }


    public void setPromFin(double PromFin) {
        this.PromFin = PromFin;
    }


    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Cod) {
        this.Codigo = Cod;
    }


    public int getExamenFin() {
        return ExamenFin;
    }

    public void setExamenFin(int ExamenFin) {
        this.ExamenFin = ExamenFin;
    }

}
