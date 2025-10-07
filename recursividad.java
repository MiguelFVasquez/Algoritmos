public class recursividad {
    public static void main(String[] args) {
        // Test each recursive function with sample values
        int result = ejercicio1(1); 
        System.out.println("Result: " + result);

        int result2 = ejercicio2(64); 
        System.out.println("Result2: " + result2);

        int result3 = ejercicio3(4, 5); 
        System.out.println("Result3: " + result3);

        int result4 = ejercicio4(1, 6); 
        System.out.println("Result4: " + result4);

        int result5 = ejercicio5(5, 8); 
        System.out.println("Result5: " + result5);

        int result6 = ejercicio6(5, 4); 
        System.out.println("Result6: " + result6);

        int result7 = ejercicio7(5, 8); 
        System.out.println("Result7: " + result7);

        int result8 = ejercicio8(2, 5); // 2^5 = 32
        System.out.println("Result8 (2^5): " + result8);
        
        int result9 = ejercicio9(785); 
        System.out.println("Suma de dígitos de 785: " + result9);

        
        // Ejemplo de uso de la función de conteo de números pares
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int pares = ejercicio10(numeros, numeros.length - 1);
        System.out.println("Cantidad de números pares: " + pares);


        //Ejemplo de la función del número más grande que se puede formar
        int[] numeros2 = {6,7,8,1,};
        int mayorNumero = mayorNumero(numeros2,0);
        System.out.println("Mayor número: " + mayorNumero);


    }

    /**
     * Multiplica recursivamente 4 por sí mismo mientras n aumenta hasta que n == 6
     * @param n valor inicial que se incrementa en cada llamada recursiva
     * @return el resultado de la multiplicación recursiva
     */
    public static int ejercicio1(int n) {
        if (n == 6) {
            return n;
        } else {
            return 4 * ejercicio1(n + 1);
        }
    }

    /**
     * Suma recursivamente 3 mientras n se reduce a la mitad en cada llamada hasta que n == 1
     * @param n valor que se divide por 2 en cada llamada recursiva
     * @return la suma acumulada de 3s
     */
    public static int ejercicio2(int n) {   
        if (n == 1) {
            return n;
        } else {
            return 3 + ejercicio2(n/2);
        }
    }

    /**
     * Suma recursivamente 'a' a 'b' mientras 'a' se decrementa hasta ser 0
     * @param a valor que se decrementa en cada llamada
     * @param b valor al que se le suma 'a' en cada paso
     * @return la suma acumulada cuando 'a' llega a 0
     */
    public static int ejercicio3(int a, int b) {
        if (a == 0) {
            return b;
        } else {
            return ejercicio3(a-1, a+b);
        }
    }

    /**
     * Multiplica recursivamente 'a' por 'b' mientras 'a' incrementa hasta 5
     * @param a valor que se incrementa en cada llamada
     * @param b valor que se multiplica por 'a' en cada paso
     * @return el producto acumulado cuando 'a' llega a 5
     */
    public static int ejercicio4(int a, int b) {
        if (a == 5) {
            return b;
        } else {
            return ejercicio4(a+1, a*b);
        }
    }

    /**
     * Calcula recursivamente un valor basado en x y n, usando multiplicación o suma según la paridad de n
     * Si n es par, multiplica x por el resultado de la llamada recursiva con n/2
     * Si n es impar, suma x al resultado de la llamada recursiva con n-1
     * @param x valor base para los cálculos
     * @param n exponente que determina la profundidad de la recursión
     * @return el resultado del cálculo recursivo
     */
    public static int ejercicio5(int x, int n ) {
        if (n == 1) {
            return 1;
        } else {
            if(n%2 == 0){
                return ejercicio5(x,n/2) * x;
            }else{
                return ejercicio5(x,n-1) + x;
            }
        }
    }

    /**
     * Suma recursivamente x al cuadrado (y-1) veces
     * @param x valor que se eleva al cuadrado en cada paso
     * @param y contador que disminuye hasta 1
     * @return la suma de x² (y-1) veces, o 0 cuando y == 1
     */
    public static int ejercicio6(int x, int y) {
        if (y == 1) {
            return x-x;
        } else {
            return (x*x)+ ejercicio6(x, y-1);
        }
    }

    /**
     * Multiplica recursivamente n por m usando el método de multiplicación rusa
     * Si m es par: duplica n y divide m entre 2
     * Si m es impar: suma n al resultado y continúa con m-1
     * @param n primer factor de la multiplicación
     * @param m segundo factor de la multiplicación
     * @return el producto de n por m
     */
    public static int ejercicio7(int n, int m) {
        if(m==1){
            return n;
        }else{
            if(m%2==0){
                return ejercicio7(n*2,m/2);
            }else{
                return ejercicio7(n*3,m/2)+n;
            }
        }
    }

    /**
     * Calcula recursivamente la potencia de x elevado a la y (x^y)
     * @param x la base de la potencia
     * @param y el exponente (debe ser un entero no negativo)
     * @return el resultado de x elevado a la y
     */
    public static int ejercicio8(int x, int y) {
        if (y == 0) {
            return 1;  // Cualquier número elevado a 0 es 1
        } else if (y == 1) {
            return x;   // Caso base: x^1 = x
        }else {
            return x * ejercicio8(x, y - 1);  // Caso recursivo: x^y = x * x^(y-1)
        }
    }

    // Recursively sums the digits of n
    public static int ejercicio9(int n) {
        if(n<10){
            return n;
        }else{
            return n%10 + ejercicio9(n/10);
        }
    }

    // Recursively counts even numbers in an array 'datos' up to index n
    public static int ejercicio10(int[] datos, int n) {
        if(n < 0) {
            return 0;  // Caso base para índices negativos
        } else if(n == 0) {
            return (datos[0] % 2 == 0) ? 1 : 0;  // Caso base para el primer elemento
        } else {
            int count = (datos[n] % 2 == 0) ? 1 : 0;
            return count + ejercicio10(datos, n - 1);
        }
    }


    public static int mayorNumero(int[] datos, int n) {
        int mayorNumero= datos[n]; 
        if (datos.length == 1) {
            return datos[n];
        } 
        else if (n > datos.length-1) {
            return mayorNumero;
        }
        else {
            if (datos[1]>mayorNumero) {
                mayorNumero=datos[1];
                datos[0]= mayorNumero;
                return mayorNumero(datos, n+1);
            }else{
                return mayorNumero(datos, n+1);
            }
        }
    }

}