package Corte2.Multiplicación;
import java.util.Arrays;

public class MultiplicacionDV {
    /**
     * Implementación de multiplicación de enteros grandes mediante
     * el paradigma Divide y Vencerás (DV), con representación decimal
     * por dígitos en arreglos de int.
     *
     * Convenciones de representación:
     * - Cada número se representa como int[] donde cada posición es un dígito decimal.
     * - El índice 0 es el dígito más significativo (MSD) y el último índice el menos significativo (LSD).
     * - Para DV se usa longitud potencia de 2 para facilitar particiones iguales.
     */
    
   public static int[] convertir(String cadena) {
        // Convierte un String decimal sin signo a arreglo de dígitos [MSD..LSD]
        int[] n = new int[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            n[i] = Character.getNumericValue(cadena.charAt(i));
        }
        return n;
    }

    public static int[] convertir2(String cadena, int tam) {
        // Convierte y ALINEA a la derecha en un arreglo de tamaño fijo 'tam'
        // Rellena con ceros a la izquierda si es necesario.
        int[] n = new int[tam];
        for (int i = cadena.length() - 1, j = tam - 1; i >= 0; i--, j--) {
            n[j] = Character.getNumericValue(cadena.charAt(i));
        }
        return n;
    }

    public static int evaluar(int a, int b) {
        // Devuelve la menor potencia de 2 >= max(a, b)
        // Se usa para normalizar longitudes y poder dividir en mitades iguales.
        int max = Math.max(a, b);
        int pot = 1;
        while (pot < max) {
            pot *= 2;
        }
        return pot;
    }

   public static int[] clasico(int[] A, int tamA, int[] B, int tamB) {
        // Multiplicación clásica O(n^2) con manejo de acarreo.
        // Recorre desde LSD hacia MSD, acumulando productos parciales.
        int[] res = new int[tamA + tamB];
        for (int i = tamA - 1; i >= 0; i--) {
            for (int j = tamB - 1; j >= 0; j--) {
                int pos = i + j + 1;
                res[pos] += A[i] * B[j];
                res[pos - 1] += res[pos] / 10;
                res[pos] %= 10;
            }
        }
        return eliminarCerosIzquierda(res);
    }

    // ---------- Algoritmo DV1 corregido ----------

    public static int[] dv1(int[] A, int[] B, int n) {
        // Multiplicación por Divide y Vencerás.
        // Caso base: para n pequeño (aquí n == 2), usar el método clásico.
        if (n == 2) return clasico(A, n, B, n);

        int mitad = n / 2;
        // División de ambos operandos en mitades: A = [w|x], B = [y|z]
        // donde w,y son partes altas (MSD..), x,z partes bajas (..LSD).
        int[] w = Arrays.copyOfRange(A, 0, mitad);
        int[] x = Arrays.copyOfRange(A, mitad, n);
        int[] y = Arrays.copyOfRange(B, 0, mitad);
        int[] z = Arrays.copyOfRange(B, mitad, n);

        // Subproblemas (4 multiplicaciones de tamaño n/2):
        // r1 = w*y, r2 = w*z, r3 = x*y, r4 = x*z
        int[] r1 = dv1(w, y, mitad);
        int[] r2 = dv1(w, z, mitad);
        int[] r3 = dv1(x, y, mitad);
        int[] r4 = dv1(x, z, mitad);

        // Combinación de resultados (recomposición en base 10):
        // A*B = r1*10^n + (r2 + r3)*10^(n/2) + r4
        // El shiftLeft agrega 'posiciones' ceros a la derecha -> multiplicar por 10^{posiciones}.
        int[] parte1 = shiftLeft(r1, 2 * mitad);         // r1 * 10^n
        int[] parte2 = shiftLeft(suma(r2, r3), mitad);   // (r2 + r3) * 10^(n/2)
        int[] parte3 = r4;                               // r4

        return suma(suma(parte1, parte2), parte3);
    }

    // Métodos auxiliares
    public static int[] suma(int[] a, int[] b) {
        // Suma de dos números representados como arreglos de dígitos [MSD..LSD]
        // Procesa de derecha a izquierda con acarreo.
        int max = Math.max(a.length, b.length);
        int[] res = new int[max + 1];
        int i = a.length - 1, j = b.length - 1, k = res.length - 1;

        while (i >= 0 || j >= 0) {
            int sum = res[k];
            if (i >= 0) sum += a[i--];
            if (j >= 0) sum += b[j--];
            res[k] = sum % 10;
            res[k - 1] += sum / 10;
            k--;
        }

        return eliminarCerosIzquierda(res);
    }

    public static int[] shiftLeft(int[] num, int posiciones) {
        // Multiplicación por 10^{posiciones}: agrega ceros al final (lado LSD)
        // dado que la representación está en orden MSD..LSD.
        int[] res = new int[num.length + posiciones];
        System.arraycopy(num, 0, res, 0, num.length);
        return res;
    }

    public static int[] eliminarCerosIzquierda(int[] num) {
        // Elimina ceros no significativos a la izquierda (lado MSD) preservando al menos un dígito.
        int i = 0;
        while (i < num.length - 1 && num[i] == 0) i++;
        return Arrays.copyOfRange(num, i, num.length);
    }

    public static String imprimir(int[] resultado) {
        // Convierte el arreglo de dígitos a String decimal.
        StringBuilder sb = new StringBuilder();
        for (int d : resultado) sb.append(d);
        return sb.toString();
    }

  
    public static void main(String[] args) {
        // Ejemplo de uso: normaliza a potencia de 2, convierte y multiplica con DV.
        String strA = "1234";
        String strB = "3623";

        int tam = evaluar(strA.length(), strB.length());

        int[] A = convertir2(strA, tam);
        int[] B = convertir2(strB, tam);
        int[] producto = dv1(A, B, tam);
        System.out.println("Resultado: " + imprimir(producto));
        
    }
}

