public  class matrices{
    public static void main(String[] args) {
        String[][] matriz2 = {
                {"sien", "encima", "mapa"},
                {"pata", "tapa", "papa"},
                {"pato", "toma", "mama"}
        };


        System.out.println("Verificación de matriz: " + verificarMatriz(matriz2,0,0));
    }


    public static boolean verificarMatriz(String[][]matriz, int fila, int columna){

        // Caso base: última palabra de la matriz
        if (fila == matriz.length - 1 && columna == matriz[fila].length - 1) {
            return true;
        }

        // Calcular siguiente posición
        int nextFila = fila;
        int nextColumna = columna + 1;
        if (nextColumna >= matriz[fila].length) {
            nextFila = fila + 1;
            nextColumna = 0;
        }

        // Verificar si la siguiente posición está fuera de la matriz
        if (nextFila >= matriz.length) {
            return true;
        }

        String actual = matriz[fila][columna];
        String siguiente = matriz[nextFila][nextColumna];

        // Verificar que las palabras tengan al menos 2 letras
        if (actual.length() < 2 || siguiente.length() < 2) {
            return false;
        }

        // Obtener las dos últimas letras de la palabra actual
        char actualPenultima = actual.charAt(actual.length() - 2);
        char actualUltima = actual.charAt(actual.length() - 1);
        // Obtener las dos primeras letras de la siguiente palabra
        char siguientePrimera = siguiente.charAt(0);
        char siguienteSegunda = siguiente.charAt(1);

        // Verificar encadenamiento
        if (actualPenultima == siguientePrimera && actualUltima == siguienteSegunda) {
            return verificarMatriz  (matriz, nextFila, nextColumna);
        } else {
            return false;
        }
    }


    public static int[][] matrizMultiplos(int [][]matriz, int fila, int columna){
        int matriz2[][] = new int [matriz.length][matriz[0].length];
        
        if (fila == matriz.length - 1 && columna == matriz[fila].length - 1) {
            return matriz2;
        }

        


        return matriz2;
    }

    public boolean esCuadradoPerfecto(int n){
        if(n<1){
            return false;
        }else{
            if(n==1){
                return true;
            }
            else{
                return esCuadradoPerfecto(n-1);
            }
        }   
    }
}