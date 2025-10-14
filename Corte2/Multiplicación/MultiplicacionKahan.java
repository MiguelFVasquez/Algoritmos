package Corte2.Multiplicación;

import java.util.Arrays;

public class MultiplicacionKahan {
    public static void main(String[] args) {
        double [][]A={{1,2,3},{4,5,6}}; 
        double [][]B={{7,8},{9,10},{11,12}}; 
        double [][]C=Kahan(A,B);
        System.out.println("Caso Kahan: "+Arrays.deepToString(C));
    }
    
    
    public static double[][] Kahan(double[][] A, double[][] B){
    // Obtener las dimensiones de las matrices
    int filas = A.length;        // Número de filas de A
    int columnas = B[0].length;  // Número de columnas de B
    int n = B.length;            // Número de columnas de A (o filas de B)

    // Crear la matriz resultado (filas de A × columnas de B)
    double[][] matriz = new double[filas][columnas];

    // Recorre cada fila de A
    for (int i = 0; i < filas; i++) {
        // Recorre cada columna de B
        for (int j = 0; j < columnas; j++) {
            // Variables para el algoritmo de Kahan
            double suma = 0.0;   // Acumulador de la suma
            double error = 0.0;  // Compensación del error de redondeo
            // Recorre los elementos comunes de la fila de A y columna de B
            for (int k = 0; k < n; k++) {
                // Producto parcial
                double producto = A[i][k] * B[k][j];
                // Suma compensada de Kahan
                double y = producto - error;  // Restar el error acumulado previo
                double t = suma + y;          // Sumar el valor corregido
                error = (t - suma) - y;       // Calcular nuevo error (compensación)
                suma = t;                     // Actualizar la suma acumulada
            }

            // Guardar el resultado en la posición (i,j)
            matriz[i][j] = suma;
        }
    }

    // Retornar la matriz resultante
    return matriz;
}

}
