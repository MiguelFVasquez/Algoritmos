package Corte2.Multiplicación;

import java.util.Arrays;

public class MultiplicacionLoopUnrolling {
    public static void main(String[] args) {
        int [][]A={{1,2,3},{4,5,6}}; 
        int [][]B={{7,8},{9,10},{11,12}}; 
        int [][]C=naivLoopUnrollingFour(A,B);
        System.out.println("Caso Loop Unrolling: "+Arrays.deepToString(C));
    }


    /**
     * Algoritmo de multiplicación de matrices por loop unrolling
     * @param A
     * @param B
     * @return
     */
    public static int[][] naivLoopUnrollingFour(int[][] A, int[][] B) {
        // C es la matriz resultado (de tamaño filasA × columnasB)
        int[][] C = new int[A.length][B[0].length];

        // Variables auxiliares para los índices y dimensiones
        int aux, i, j, k;
        int n = A.length;     // número de filas de A
        int p = B.length;     // número de columnas de A (y filas de B)
        int m = B[0].length;  // número de columnas de B

        // Caso 1: si p (las columnas de A) es múltiplo de 4
        if (p % 4 == 0) {
            for (i = 0; i < n; i++) {          // recorre las filas de A
                for (j = 0; j < m; j++) {      // recorre las columnas de B
                    aux = 0;
                    for (k = 0; k < p; k += 4) {   // se incrementa de 4 en 4
                        // Se realizan 4 multiplicaciones y sumas por iteración
                        aux += A[i][k] * B[k][j] +
                            A[i][k+1] * B[k+1][j] +
                            A[i][k+2] * B[k+2][j] +
                            A[i][k+3] * B[k+3][j];
                    }
                    C[i][j] = aux;
                }
            }
        }

        // Caso 2: si p % 4 == 1 → sobró 1 elemento sin agrupar
        else if (p % 4 == 1) {
            int PP = p - 1; // índice del último elemento sin agrupar
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    aux = 0;
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k]*B[k][j] +
                            A[i][k+1]*B[k+1][j] +
                            A[i][k+2]*B[k+2][j] +
                            A[i][k+3]*B[k+3][j];
                    }
                    // se suma el elemento que quedó faltando
                    C[i][j] = aux + A[i][PP]*B[PP][j];
                }
            }
        }

        // Caso 3: si p % 4 == 2 → sobran 2 elementos sin agrupar
        else if (p % 4 == 2) {
            int PP = p - 2, PPP = p - 1;
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    aux = 0;
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k]*B[k][j] +
                            A[i][k+1]*B[k+1][j] +
                            A[i][k+2]*B[k+2][j] +
                            A[i][k+3]*B[k+3][j];
                    }
                    // se suman los dos últimos elementos
                    C[i][j] = aux + A[i][PP]*B[PP][j] + A[i][PPP]*B[PPP][j];
                }
            }
        }

        // Caso 4: si p % 4 == 3 → sobran 3 elementos sin agrupar
        else {
            int PP = p - 3, PPP = p - 2, PPPP = p - 1;
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    aux = 0;
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k]*B[k][j] +
                            A[i][k+1]*B[k+1][j] +
                            A[i][k+2]*B[k+2][j] +
                            A[i][k+3]*B[k+3][j];
                    }
                    // se suman los tres últimos elementos
                    C[i][j] = aux + A[i][PP]*B[PP][j] +
                                A[i][PPP]*B[PPP][j] +
                                A[i][PPPP]*B[PPPP][j];
                }
            }
        }

        return C;
    }

}
