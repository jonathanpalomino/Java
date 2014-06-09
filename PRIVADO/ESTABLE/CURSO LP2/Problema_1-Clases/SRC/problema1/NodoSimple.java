

package problema1;

public class NodoSimple {
private Triangulo Triangulos;
private NodoSimple anterior;
NodoSimple(Triangulo obj){this.Triangulos = obj; }

void setTriangulos(Triangulo Tria) {this.Triangulos = Tria;}
void setAnterior(NodoSimple anterior) {this.anterior = anterior; }
Triangulo getTriangulos() { return Triangulos; }
NodoSimple getAnterior() { return anterior;}

}
