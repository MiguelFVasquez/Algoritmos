package Corte2;
public class EjerciciosPD {
    public static void main(String[] args) {
        //Ejercicio 1
        /* 
        System.out.println(recursivo1(5));
        System.out.println(iterativo1(5));
        System.out.println(tabulacion1(5));
        System.out.println(memo1(5));
        // Ejercicio 2
        System.out.println(recursivo2(5));
        System.out.println(iterativo2(5));
        System.out.println(tabulacion2(5));
        System.out.println(memo2(5));
        // Ejercicio 3
        System.out.println(recursivo3(5));
        System.out.println(tabulacion3(5));
        System.out.println(memo3(5));
        */
        //Ejercicio 4
        int [][] tabla= tablas(5,5);
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                System.out.print(tabla[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
        int result= tablasRecursivo(5,5);
        System.out.println(result);

    }


//----------------------------Ejercicio 1--------------------------------
    /**
     * Relación de recurrencia: 
     * 
     * Si n=0 --> 3
     * Si n=1 --> 2
     * Si n>1 --> c(n) = c(n-1) + c(n-2)
     * 
     * @param n
     * @return
     */
    public static int recursivo1(int n){
        if(n==0) return 3;
        if(n==1) return 2; 
        return recursivo1(n-1)+recursivo1(n-2);
    }
    public static int iterativo1(int n){
        if(n==0) return 3;
        if(n==1) return 2;
        int prev2 = 3; // c(0)
        int prev1 = 2; // c(1)
        int result = 0;

        for (int i = 2; i <= n; i++) {
            result = prev1 + prev2; // c(n) = c(n-1) + c(n-2)
            prev2 = prev1;          // Avanzar los valores
            prev1 = result;
        }
        return result;
    }

    public static int tabulacion1(int n){
        int resultados[] = new int[n+1];
        if(n==0) {
            resultados[0]=3;
            return 3;
        }
        if(n==1) {
            resultados[1]=2;
            return 2;
        }
        for(int i=2; i<=n; i++){
            if (resultados[i]==0) {
                resultados[i]= resultados[i-1]+resultados[i-2];
            }  
        }
        return resultados[n];
    }
    public static int memo1(int n){
        int results[]= new int[n+1];
        if(n==0){
            results[0]=3;
            return 3;
        }
        if(n==1){
            results[1]=2;
            return 2;
        }
        if(results[n]!=0){
            return results[n];
        }
        results[n]= memo1(n-1)+memo1(n-2);
        return results[n];
    }


    //---------------------------Ejercicio 2--------------------------------
    /**
     * Relación de recurrencia: 
     * 
     * Si n=0 --> 3
     * Si n=1 --> 2
     * Si n=2 --> 1
     * Si n>2 --> c(n) = n*c(n-1)
     * 
     * @param n
     * @return
     */
    public static int recursivo2(int n){
        
        if(n==0) return 3;
        if(n==1) return 2;
        if(n==2) return 1;
        return n*recursivo2(n-1);
    }

    public static int iterativo2(int n){
        if (n == 0) return 3;
        if (n == 1) return 2;
        if (n == 2) return 1;
        int result = 1; // R(2)
        for (int i = 3; i <= n; i++) {
            result = result * i; // R(i) = R(i-1) * i
        }
        return result;
    }
    public static int tabulacion2(int n){
        int result[]= new int[n+1];
        if(n==0){
            result[0]=3;
            return 3;
        }
        if(n==1){
            result[1]=2;
            return 3;
        }
        if(n==2){
            result[2]=1;
            return 1; 
        }
        for(int i=3; i<=n;i++){
            if(result[i]==0){
                result[i]= result[i-1]*i;
            }
        }
        return result[n];
    }

    public static int memo2(int n){
        int result[]= new int[n+1];
        if(n==0){
            result[0]=3;
            return 3;
        }
        if(n==1){
            result[1]=2;
            return 3;
        }
        if(n==2){
            result[2]=1;
            return 1; 
        }
        if(result[n]!=0){
            return result[n];
        }
        result[n]= n*memo2(n-1);
        return result[n];
    }

//----------------------------Ejercicio 3--------------------------------
    /**
     * Relación de recurrencia: 
     * 
     * Si n=0 --> 3
     * Si n=1 --> 2
     * Si n=2 --> 1
     * Si n>2 --> c(n) = c(n-1) + c(n-2) + c(n-3)
     * 
     * @param n
     * @return
     */
    public static int recursivo3(int n){
        if(n==0) return 3;
        if(n==1) return 2;
        if(n==2) return 1;
        
        return recursivo3(n-1)+recursivo3(n-2)+recursivo3(n-3);
    }

    public static int tabulacion3(int n){
        int result[]= new int[n+1];
        if(n==0){
            result[0]=3;
            return 3;
        } 
        if(n==1){
            result[1]=2;
            return 2;
        }
        if(n==2){
            result[2]=1;
            return 1;
        }
        for(int i=3;i<=n;i++){
            if(result[i]==0){
                result[i]=result[i-1]+result[i-2]+result[i-3];
            }
        }
        return result[n];
    }

    public static int memo3(int n){
        int result[]= new int[n+1];
        if(n==0) {
            result[0]=3;
            return 3;
        }
        if(n==1) {
            result[1]=2;
            return 2;
        }
        if(n==2) {
            result[2]=1;
            return 1;
        }
        if(result[n]!=0){
            return result[n];
        }
        result[n]= memo3(n-1)+memo3(n-2)+memo3(n-3);
        return result[n];

    }

//------------------------EJERCICIO coeficientes binomiales-------------------------
    /**
     * Si n=k o n=0 --> 3
     * De lo contrario --> c[i][j] = c[i][j-1]+c[i-1][j]+2
     * @param n
     * @param k
     * @return
     */
    public static int [][] tablas(int n, int k){
        int [][] results= new int [n+1][k+1];
        for (int i=0; i<=n;i++){
            for (int j=0; j<=Math.min(i, k);j++){
                if(j==i || j==0){
                    results[i][j]=3;
                }else{
                    results[i][j]= results[i][j-1]+results[i-1][j];
                }
            }
        }
        return results;
    }

    public static int tablasRecursivo(int n, int k){
        int [][] results= new int [n+1][k+1];
        if(n==k || n==0){
            results[n][k]=3;
            return results[n][k];
        }
        if(results[n][k]!=0){
            return results[n][k];
        }
        results[n][k]= tablasRecursivo(n-1, k)+tablasRecursivo(n, k-1);
        return results[n][k];
    }

}
