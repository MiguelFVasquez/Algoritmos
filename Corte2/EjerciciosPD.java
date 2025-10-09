package Corte2;
public class EjerciciosPD {
    public static void main(String[] args) {
        
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
    public static int ejercicio1(int n){
        if(n==0) return 3;
        if(n==1) return 2; 
        return ejercicio1(n-1)+ejercicio1(n-2);
    }
    
}
