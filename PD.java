public class PD {
    public static void main(String[] args) {

        int n=40;
        int resultados[] = new int[n+1];
        int coeficientes[][] = new int[n+1][n+1];
        //System.out.println("Sumatoria con memorización: "+sumatoriaMemo(5, resultados));}
        //System.out.println("Sumatoria con tabulación: "+sumatoriaTabu(5, resultados));
        //System.out.println("Sumatoria de 5: "+sumatoria(5));


        long startTime= System.nanoTime();
        System.out.println("Sucesión de Lucas recursivo: "+sucesionLucas(n));
        long endTime= System.nanoTime();
        System.out.println("Tiempo de ejecución Lucas recursivo: "+(endTime-startTime) / 1_000_000.0 + " ms");

        long startTimeM= System.nanoTime();
        System.out.println("Sucesión de Lucas con memorización: "+sucesionLucasMemo(n, resultados));
        long endTimeM= System.nanoTime();
        System.out.println("Tiempo de ejecución Lucas con memorización: "+(endTimeM-startTimeM) / 1_000_000.0 + " ms");


        long startTimeTab= System.nanoTime();
        System.out.println("Sucesión de Lucas con tabulación: "+ sucesionLucasTabu(n, resultados));
        long endTimeTab= System.nanoTime();
        System.out.println("Tiempo de ejecución Lucas con tabulación: "+(endTimeTab-startTimeTab) / 1_000_000.0 + " ms");

    }


    /**
     * Sumatoria de forma recursiva
     * @param n
     * @return
     */
    public static int sumatoria(int n){
        if (n==0 || n==1) {
            return n;
        }
        if (n>1) {
            return n+sumatoria(n-1);
        }
        return 0;
    }

    /**
     * Sumatoria de forma recursiva con memorización
     * @param n
     * @param resultados
     * @return
     */
    public static int sumatoriaMemo(int n, int resultados[]){
        if(n==0 ){
            resultados[0]=0;
            System.out.println("Sumatoria de cero: " + resultados[0] );
            return 0;
        }
        if(n==1){
            resultados[1]=1;
            System.out.println("Sumatoria de uno: " + resultados[1] );
            return 1;
        }
        if(resultados[n]!=0){
            System.out.println("Sumatoria de " + n + ": " + resultados[n] );
            return resultados[n];
        }
        resultados[n] = n + sumatoriaMemo(n-1, resultados);
        System.out.println("Sumatoria de " + n + ": " + resultados[n] );
        return resultados[n];
    }
    /**
     * Sumatoria de forma iterativa con tabulación
     * @param n
     * @param resultados
     * @return
     */
    public static int sumatoriaTabu(int n, int resultados[]){
        if(n==0){
            resultados[0]=0;
            return 0;
        }
        if (resultados[1]==0) {
            resultados[1]=1;
        }
        for(int i=2; i<=n; i++){
            if(resultados[i]==0){
                System.out.println("Sumatoria de " + i + ": " + resultados[i] );
                resultados[i]= resultados [i-1] + i;
            }
        }
        System.out.println("Sumatoria de " + n + ": " + resultados[n] );
        return resultados[n];
    }

//----------------------------------------SUCESIÓN DE LUCAS------------------------
    /**
     * Sucesión de Lucas de forma recursiva
     * Para la sucesión de lucas = L(0) = 2, L(1) = 1, L(n) = L(n-1) + L(n-2)
     * @param n
     * @return
     */
    public static int sucesionLucas(int n){
        if(n==0){
            return 2;
        }
        if(n==1){
            return 1;
        }
        return sucesionLucas(n-1)+sucesionLucas(n-2);
    }

    public static int sucesionLucasMemo(int n, int resultados[]){
        if(n==0){
            resultados[0]=2;
            return 2;
        }
        if(n==1){
            resultados[1]=1;
            return 1;
        }
        if(resultados[n]!=0){
            //Si el valor que se ingresa ya se tiene en el arreglo se retorna el valor
            return resultados[n];
        }
        resultados[n] = sucesionLucasMemo(n-1, resultados) + sucesionLucasMemo(n-2, resultados); // Se calcula el valor
        return resultados[n];
    }

    public static int sucesionLucasTabu(int n, int resultados[]){
        if (n==0){
            resultados[0]=2;
            return 2;
        }
        if(n==1){
            resultados[1]=1;
            return 1;
        }
        for(int i=2; i<=n; i++){
            if(resultados[i]==0){
                resultados[i]=resultados[i-1]+resultados[i-2];
            }
        }
        return resultados[n];
    }

//----------------------------------------Coeficiente binomiales------------------------

    public static int coeficienteBinomial(int n, int k){
        int [][] coeficientes = new int[n+1][k+1];
        for(int i= 0; i<=n;i++){
            for(int j=0; j<=k;j++){
                if(j==0 || j==i){
                    coeficientes[i][j]=1;
                }
                else{
                    coeficientes[i][j]=coeficientes[i-1][j-1]+coeficientes[i-1][j];
                }
            }
        }
        return coeficientes[n][k];
    }


    public static int coeficienteBinomialTabu(int n, int k){
        int [] coeficientes= new int[k+1];
        for(int i=1; i<=n;i++){
            for(int j= Math.min(i,k); j>0;j--){
                coeficientes[j] = coeficientes[j]+coeficientes[j-1];
            }
        }
        return coeficientes[k];
    }
}   

