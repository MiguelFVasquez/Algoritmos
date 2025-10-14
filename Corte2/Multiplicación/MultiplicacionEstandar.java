package Corte2.Multiplicación; // Paquete con clases del corte 2 para multiplicación de matrices
import java.util.Arrays; // Utilidad para imprimir matrices (Arrays.deepToString)

public class MultiplicacionEstandar {

    // Implementación del método estándar (naive) de multiplicación de matrices
    public static void main(String[] args) { 
        int [][] A = {{1,2,3},{4,5,6}}; 
        int [][] B = {{7,8},{9,10},{11,12}}; 
        int [][] C = naivStandard(A, B); // C = A x B (resultado 2x2)
        int [][] D = naivStandard2(A, B); // C = A x B (resultado 2x2)
        System.out.println("Caso estandar 1: "+Arrays.deepToString(C));
        System.out.println("Caso estandar 2: "+Arrays.deepToString(D));
    }
    /**
     * Algoritmo de multiplicación estándar (naive) de matrices O(n^3)
     * Caso Cuadrado O(n^3)
     * Caso general O(n*m*p)
     * @param A
     * @param B
     * @return
     */
    public static int[][] naivStandard(int [][] A, int [][] B){ 
        
        int [][] matriz = new int [A.length][B[0].length]; // Resultado: filas(A) x columnas(B)
        int auxiliar; // Acumulador para la suma de productos de (fila i) x (columna j)

        if (A[0].length == B.length) { // Verifica compatibilidad: columnas(A) == filas(B)
            for (int i = 0; i < A.length; i++){ // Recorre filas de A
                for (int j = 0; j < B[0].length; j++){ // Recorre columnas de B
                    auxiliar = 0; // Reinicia acumulador para la celda (i,j)
                    for (int k = 0; k < B.length; k++) { // Recorre columnas de A / filas de B
                        auxiliar += (A[i][k] * B[k][j]); // Suma producto elemento a elemento
                    }
                    matriz[i][j] = auxiliar; // Asigna el valor acumulado a la posición (i,j)
                }
            }
        }
        return matriz; // Devuelve la matriz resultante
    }

    /**
     * Caso Cuadrado O(n^3)
     * Caso general O(n*m*p)
     * @param A
     * @param B
     * @return
     */
    public static int[][] naivStandard2(int [][] A, int [][] B) {
        
        // Se crea la matriz resultado con dimensiones:
        // filas de A x columnas de B
        int [][] matriz = new int [A.length][B[0].length];

        // Verifica que las matrices puedan multiplicarse:
        // (número de columnas de A == número de filas de B)
        if (A[0].length == B.length) {
            // Recorre cada fila de A
            for (int i = 0; i < A.length; i++) {
                // Recorre cada columna de B
                for (int j = 0; j < B[0].length; j++) {
                    // Inicializa la celda (i,j) del resultado en 0
                    matriz[i][j] = 0;
                    // Recorre los elementos de la fila i de A y la columna j de B
                    // multiplicando y sumando (producto punto)
                    for (int k = 0; k < B.length; k++) {
                        matriz[i][j] += (A[i][k] * B[k][j]);
                    }
                }
            }
        }

        // Retorna la matriz resultado
        return matriz;
    }

}