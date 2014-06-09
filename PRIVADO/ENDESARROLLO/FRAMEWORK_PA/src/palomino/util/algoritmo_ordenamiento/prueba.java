package palomino.util.algoritmo_ordenamiento;

public class prueba {

    String algName;
    int[] arr;
    SortAlgorithm algorithm;

    public prueba() {
        try {
            setAlgorithm("palomino.util.algoritmo_ordenamiento.BubbleSort");
            algorithm = ((SortAlgorithm) Class.forName(algName).newInstance());
            //algorithm.setParent(algorithm);
            algorithm.init();
            algorithm.sort(arr);
            Llamar_nuevo(arr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        prueba prueba = new prueba();
    }

    void setAlgorithm(String paramString) {
        algName = (paramString + "Algorithm");
        scramble();
    }

    private void scramble() {
        int tamanio =51;
        int[] arrayOfInt = new int[51];
        int i = arrayOfInt.length;
        for (int k = 0; k < i; k++) {
            arrayOfInt[k] = (int) (i * Math.random());
            System.out.println(" valor arreglo " + arrayOfInt[k]);
        }
        arr = arrayOfInt;
    }

    private void Llamar_nuevo(int[] arr) {
        for (int k = 0; k < arr.length; k++) {
            System.out.println(" valor arreglo 2 " + arr[k]);
        }
    }
}
