/*
 * Realizar el programa que ingrese Aleatoriamente el precio unitario y
 * cantidad de tres artículos diferentes que un cliente ha adquirido una tienda.
 * Se pide calcular el importe así como el vuelto.
 * Nota: el precio del primer artículo debe estar entre 10 a 20 soles,
 * del segundo entre 5:15 y el tercero entre 25 y 45 Soles.
 * Además las cantidades deben estar entre 5 a 20
 * 
 */

package pagosdetienda;

/**
 *
 * @author JONATHAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int p1,p2,p3,c1,c2,c3,importe,vuelto,pago;
        p1=(int)(Math.random()*11+10);
        p2=(int)(Math.random()*11+50);
        p3=(int)(Math.random()*21+25);
        c1=(int)(Math.random()*16+5);
        c2=(int)(Math.random()*16+5);
        c3=(int)(Math.random()*16+5);
        importe=p1*c1+p2*c2+p3*c3;
        pago=(int)(Math.random()*50+importe);
        vuelto=pago-importe;
        System.out.println("El importe a pagar es :"+importe);
        System.out.println("El pago realizado es :"+pago);
        System.out.println("Su vuelto es :"+vuelto);
    }

}
