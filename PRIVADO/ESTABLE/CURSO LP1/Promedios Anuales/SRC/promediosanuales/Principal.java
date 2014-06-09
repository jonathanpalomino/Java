/*
 * Realizar el programa q asigne aleatoreamente a un arreglo lineal
 * de ventas mensuales q una empresa ha realizado durante 5 años,
 * se pide calcular e imprimir:
 * Nota: Las ventan estan en un rango de números de 5 a 6 cifras
    a) El promedio de las ventas*
    b) El promedio de cada año de las ventas*
    c)El promedio de cada semestre
    D) La venta más baja de cada semestre
 */

package promediosanuales;

public class Principal {

    public static void main(String[] args) 
    {
        int suma_anual=0,años =5;
        int año1=0,año2=0,año3=0,año4=0,año5=0;
        int semestre1=0,semestre2=0,semestre3=0,semestre4=0,semestre5=0,semestre6=0,semestre7=0,semestre8=0,semestre9=0;
        int semestre10 =0;
        int meses=años*12;
        int arreglo[] = new int [meses];
        int temporal[] = new int [7];
        for(int i=0;i<60;i++)
        {
            arreglo[i]=(int) (Math.random() * 1000000 +10000);//asigno datos
            suma_anual = suma_anual+arreglo[i];

            if(i<12)
            {
                año1=año1+arreglo[i];//suma el año1
                if(i<6)
                {
                    semestre1 = semestre1+arreglo[i];
                    temporal[i] = arreglo[i];
                    if(i==5)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 1 es :"+temporal[1]);
                    }
                }
                else if(i>=6)
                {
                    semestre2 = semestre2+arreglo[i];
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
              año2=año2+arreglo[i];
               if(i>=12 &&i<18)
               {
                semestre3 = semestre3+arreglo[i];
                temporal[i-12] = arreglo[i];
                    if(i==17)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 3 es :"+temporal[0]);
                    }
               }
               else if(i>=18)
               {
               semestre4 = semestre4+arreglo[i];
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
               año3=año3+arreglo[i];
               if(i>=24 && i<30)
               {
                   semestre5 = semestre5+arreglo[i];
                   temporal[i-24] = arreglo[i];
                    if(i==29)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 5 es :"+temporal[0]);
                    }
               }
               else if(i>=30)
               {
                   semestre6 = semestre6+arreglo[i];
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
               año4=año4+arreglo[i];
                      if(i>=36 && i<42)
               {
                   semestre7 = semestre7+arreglo[i];
                   temporal[i-36] = arreglo[i];
                    if(i==41)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 7 es :"+temporal[0]);
                    }
               }
               else if(i>=42)
               {
                   semestre8 = semestre8+arreglo[i];
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
               año5=año5+arreglo[i];
               if(i>=48 && i<54)
               {
                   semestre9 = semestre9+arreglo[i];
                   temporal[i-48] = arreglo[i];
                    if(i==53)
                    {
                       Ordenar(temporal);
                       System.out.println("La menor venta del semestre 9 es :"+temporal[0]);
                    }
               }
               else if(i>=54)
               {
                   semestre10 = semestre10+arreglo[i];
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
        System.out.println(" El promedio de las ventas del año 1 es : "+año1/12);
        System.out.println(" El promedio de las ventas del año 2 es : "+año2/12);
        System.out.println(" El promedio de las ventas del año 3 es : "+año3/12);
        System.out.println(" El promedio de las ventas del año 4 es : "+año4/12);
        System.out.println(" El promedio de las ventas del año 5 es : "+año5/12);

        System.out.println(" El promedio del semestre 1 es "+semestre1/6);
        System.out.println(" El promedio del semestre 2 es "+semestre2/6);
        System.out.println(" El promedio del semestre 3 es "+semestre3/6);
        System.out.println(" El promedio del semestre 4 es "+semestre4/6);
        System.out.println(" El promedio del semestre 5 es "+semestre5/6);
        System.out.println(" El promedio del semestre 6 es "+semestre6/6);
        System.out.println(" El promedio del semestre 7 es "+semestre7/6);
        System.out.println(" El promedio del semestre 8 es "+semestre8/6);
        System.out.println(" El promedio del semestre 9 es "+semestre9/6);
        System.out.println(" El promedio del semestre 10 es "+semestre10/6);
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
