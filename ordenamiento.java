import java.util.Arrays;

public class ordenamiento {
    /**
     * Ejemplos de uso de los algoritmos de ordenamiento implementados en esta clase.
     * Actualmente se muestra un arreglo ordenado con inserción; puedes cambiar
     * a otros métodos (merge sort o radix sort) según necesites.
     */
    public static void main(String[] args) {
        int[] arreglo= {4,7,1,3,0,6,5,8};
        // Ordenamiento por inserción (in-place)
        insercion(arreglo);
        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(arreglo));
    }
    
    /**
     * Ordenamiento por Inserción (Insertion Sort).
     * Recorre el arreglo y va insertando cada elemento en la posición correcta
     * de la parte ya ordenada a la izquierda.
     * 
     * Complejidad:
     *  - Peor caso: O(n^2)
     *  - Mejor caso (arreglo casi ordenado): O(n)
     *  - In-place y estable.
     */
    public static void insercion(int[] arreglo){
        int i,llave;
        for(int j=1; j<arreglo.length;j++){
            // Elemento a insertar en la sección ordenada [0..j-1]
            llave=arreglo[j];
            i= j-1;
            // Desplaza hacia la derecha los elementos mayores que la llave
            while(i>= 0 && arreglo[i] >llave){
                arreglo[i+1] = arreglo[i];
                i--;
            }
            // Coloca la llave en su posición correcta
            arreglo[i+1]=llave;
        }
    }



    /**
     * Fase de combinación de Merge Sort.
     * Fusiona dos subarreglos ordenados arr[l..m] y arr[m+1..r] en un solo
     * subarreglo ordenado dentro de arr.
     * 
     * @param arr Arreglo original
     * @param l   Índice izquierdo (inicio del primer subarreglo)
     * @param m   Índice medio (fin del primer subarreglo)
     * @param r   Índice derecho (fin del segundo subarreglo)
     */
    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copiar datos en arreglos temporales L y R
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        // Mezclar L y R en arr[l..r]
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copiar los elementos restantes de L (si los hay)
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copiar los elementos restantes de R (si los hay)
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    /**
     * Merge Sort (recursivo).
     * Divide el arreglo en mitades, ordena cada mitad y luego combina los
     * resultados usando {@link #merge(int[], int, int, int)}.
     * 
     * Complejidad: O(n log n) en todos los casos. No es in-place (usa memoria extra),
     * pero es estable.
     */
    public static void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }


    /**
     * Counting Sort por dígitos (estable) utilizado como subrutina de Radix Sort.
     * Ordena el arreglo de acuerdo con el dígito en la posición "place"
     * (unidades, decenas, centenas, ...).
     * 
     * @param array Arreglo a ordenar
     * @param place Posición del dígito (1, 10, 100, ...)
     */
    public static void countingSort(int[] array, int place) {
        int size = array.length;
        int[] output = new int[size];
        int[] count = new int[10];
        
        // 1) Contar ocurrencias de cada dígito (0..9) en la posición "place"
        for (int i = 0; i < size; i++) {
            int index = (array[i] / place) % 10;
            count[index]++;
        }
        // 2) Convertir a conteo acumulado (prefijos) para ubicar índices finales
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        // 3) Construir salida estable recorriendo de derecha a izquierda
        for (int i = size - 1; i >= 0; i--) {
            int index = (array[i] / place) % 10;
            output[count[index] - 1] = array[i];
            count[index]--;
        }
        // 4) Copiar el resultado ordenado por el dígito a "array"
        for (int i = 0; i < size; i++) {
            array[i] = output[i];
        }
    }
    /**
     * Radix Sort (base 10) para enteros no negativos.
     * Ordena por dígitos de menor a mayor significancia usando counting sort
     * como subrutina estable por cada posición de dígito.
     * 
     * Complejidad: O(d · (n + k)) donde d es el número de dígitos y k=10.
     * Estable; requiere memoria extra para counting sort.
     */
    public static void radixSort(int[] array) {
        // Obtener el elemento máximo para saber cuántos dígitos considerar
        int maxElement = getMax(array);
        // Aplicar counting sort para cada posición de dígito (1, 10, 100, ...)
        for (int place = 1; maxElement / place > 0; place *= 10) {
            countingSort(array, place);
        }
    }
    /**
     * Devuelve el valor máximo dentro del arreglo.
     */
    public static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    

}
