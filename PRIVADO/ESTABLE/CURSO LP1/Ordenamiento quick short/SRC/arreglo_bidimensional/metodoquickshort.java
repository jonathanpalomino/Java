
package arreglo_bidimensional;
public class metodoquickshort{
  public static void main(String a[]){
    int i;
    int array[] = {121,9,4,99,120,1,3,10,130};
    System.out.print("       Quick Sort\n\n");
    System.out.print("Valores antes del sort:\n");
    for(i = 0; i < array.length; i++)
      System.out.print( array[i]+"  ");
    quick_short(array,0,array.length-1);
    System.out.println();
    System.out.print("Valores despues del sort:\n");
    for(i = 0; i <array.length; i++)
      System.out.print(array[i]+"  ");
    System.out.println();
  }

  public static void quick_short(int array[],int low, int n){
    int lo = low;
    int hi = n;
    if (lo >= n) {
      return;
    }
    int mitad = array[(lo + hi) / 2];
    while (lo < hi) {
      while (lo<hi && array[lo] < mitad) {//jugamos con cada mitad
        lo++;
      }
      while (lo<hi && array[hi] > mitad) {//jugamos con cada mitad
        hi--;
      }
      if (lo < hi) {//aca ocurre lo que se ve en la animacion con las 2 flechas y los saltos
        int T = array[lo];
        array[lo] = array[hi];
        array[hi] = T;
      }
    }
    if (hi < lo) {//aca ocurre lo que se ve en la animacion con las 2 flechas y los saltos
      int T = hi;
      hi = lo;
      lo = T;
    }
    quick_short(array, low, lo);
    quick_short(array, lo == low ? lo+1 : lo, n);//aca esta lo explicado anteriormente en una sola linea
  }
}