/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problema1;

class Triangulo {
    private String Tipo;
    private String codigo;
    private double altura;
    private double base;
    private double area;

public Triangulo(){  }

Triangulo(Triangulo obj)
{
Tipo = obj.getTipo();
codigo = obj.getCodigo();
altura = obj.getAltura();
base = obj.getBase();
area = obj.getArea();
}


    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getAltura() {
        return altura;
    }

     public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
