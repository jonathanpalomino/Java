/*
 * Realizar el programa q asigne aleatoreamente a un arreglo lineal
 * de ventas mensuales q una empresa ha realizado durante 5 años,
 * se pide calcular e imprimir:
 * Nota: Las ventan estan en un rango de números de 5 a 6 cifras
    a) El promedio de las ventas*
    b) El promedio de cada año de las ventas*
    c)El promedio de cada semestre*
    d) La venta más baja de cada semestre*
 */

package promediosanuales;

public class Main {

    public static void main(String[] args) 
    {
        int suma_anual=0,años =5;
        int meses=años*12;
        int arreglo[] = new int [meses];
        int semestre[]= new int [11];
        int año[] = new int[años+1];
        int temporal[] = new int [7];
        for(int i=0;i<60;i++)
        {
            arreglo[i]=(int) (Math.random() * 1000000 +10000);//asigno datos
            suma_anual = suma_anual+arreglo[i];
            
            if(i<12)
            {
                año[1]=año[1]+arreglo[i];//suma el año1
                if(i<6)
                {
                    semestre[1] = semestre[1]+arreglo[i];
                    temporal[i] = arreglo[i];
                    if(i==5)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 1 es :"+temporal[1]);
                    }
                }
                else if(i>=6)
                {
                    semestre[2] = semestre[2]+arreglo[i];
                    temporal[i-6] = arreglo[i];
                    if(i==11)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 2 es :"+temporal[0]);
                    }
                }

            }
            else if(i>=12 && i<24)
            {
              año[2]=año[2]+arreglo[i];
               if(i>=12 &&i<18)
               {
                semestre[3] = semestre[3]+arreglo[i];
                temporal[i-12] = arreglo[i];
                    if(i==17)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 3 es :"+temporal[0]);
                    }
               }
               else if(i>=18)
               {
               semestre[4] = semestre[4]+arreglo[i];
               temporal[i-18] = arreglo[i];
                    if(i==23)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 4 es :"+temporal[0]);
                    }
               }
            }
            else if(i>=24 && i<36)
            {
               año[3]=año[3]+arreglo[i];
               if(i>=24 && i<30)
               {
                   semestre[5] = semestre[5]+arreglo[i];
                   temporal[i-24] = arreglo[i];
                    if(i==29)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 5 es :"+temporal[0]);
                    }
               }
               else if(i>=30)
               {
                   semestre[6] = semestre[6]+arreglo[i];
                   temporal[i-30] = arreglo[i];
                    if(i==35)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 6 es :"+temporal[0]);
                    }
               }
            }
            else if(i>=36 && i<48)
            {
               año[4]=año[4]+arreglo[i];
                      if(i>=36 && i<42)
               {
                   semestre[7] = semestre[7]+arreglo[i];
                   temporal[i-36] = arreglo[i];
                    if(i==41)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 7 es :"+temporal[0]);
                    }
               }
               else if(i>=42)
               {
                   semestre[8] = semestre[8]+arreglo[i];
                   temporal[i-42] = arreglo[i];
                    if(i==47)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 8 es :"+temporal[0]);
                    }
               }
            }
            else if(i>=48 && i<60)
            {
               año[5]=año[5]+arreglo[i];
               if(i>=48 && i<54)
               {
                   semestre[9] = semestre[9]+arreglo[i];
                   temporal[i-48] = arreglo[i];
                    if(i==53)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 9 es :"+temporal[0]);
                    }
               }
               else if(i>=54)
               {
                   semestre[10] = semestre[10]+arreglo[i];
                   temporal[i-54] = arreglo[i];
                    if(i==59)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 10 es :"+temporal[0]);
                    }
               }
            }
        }
        
        System.out.println(" El promedio de las ventas en los "+años+" años son: "+suma_anual/meses);
        
        for(int y=1;y<=5;y++)
        {
            System.out.println(" El promedio de las ventas del año "+y+" es : "+año[y]/12);
        }
        for(int y=1;y<=10;y++)
        {
            System.out.println(" El promedio del semestre "+y+" es "+semestre[y]/6);
        }
        Ordenar(arreglo);
        System.out.println(" La menor venta anual es :"+arreglo[0]);
        /*for(int u=0;u<arreglo.length;u++)
        {
          System.out.println(arreglo[u]+" indice "+u);
        }*/
        
    }

    private static void Ordenar(int[] arreglo2)
    {
        // ciclo para controlar número de pasadas
      for ( int pasada = 1; pasada < arreglo2.length; pasada++ )
      {
         // ciclo para controlar número de comparaciones
         for ( int elemento = 0;elemento < arreglo2.length - 1;elemento++ )
         {
            // comparar elementos uno a uno e intercambiarlos si
            // el primer elemento es mayor que el segundo
            if ( arreglo2[ elemento ] > arreglo2[ elemento + 1 ] )
            {
               intercambiar( arreglo2, elemento, elemento + 1 );
            }

         } // fin del ciclo para controlar las comparaciones

      } // fin del ciclo para controlar las pasadas
    }
    private static void intercambiar(int[] arreglo3, int primero, int segundo)
    {
      int temporal;  // área temporal de almacenamiento para intercambiar
      temporal = arreglo3[ primero ];
      arreglo3[ primero ] = arreglo3[ segundo ];
      arreglo3[ segundo ] = temporal;
    }

}
