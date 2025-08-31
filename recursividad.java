public class recursividad {
    public static void main(String[] args) {
        int result = ejercicio1(1); 
        System.out.println("Result: " + result);

        int result2 = ejercicio2(64); 
        System.out.println("Result2: " + result2);

        int result3 = ejercicio3(4, 5); 
        System.out.println("Result3: " + result3);

        int result4 = ejercicio4(1, 5); 
        System.out.println("Result4: " + result4);

        int result5 = ejercicio5(5, 8); 
        System.out.println("Result5: " + result5);

        int result6 = ejercicio6(5, 4); 
        System.out.println("Result6: " + result6);

        int result7 = ejercicio7(5, 8); 
        System.out.println("Result7: " + result7);

        int result8 = ejercicio8(4, 5); 
        System.out.println("Result8: " + result8);

        int result9 = ejercicio9(785); 
        System.out.println("Result9: " + result9);

    }

    public static int ejercicio1(int n) {
        if (n == 6) {
            return n;
        } else {
            return 4 * ejercicio1(n + 1);
        }
    }

    public static int ejercicio2(int n) {   
        if (n == 1) {
            return 1;
        } else {
            return 3 + ejercicio2(n/2);
        }
    }

    public static int ejercicio3(int a, int b) {
        if (a == 0) {
            return b;
        } else {
            return ejercicio3(a-1, a+b);
        }
    }


    public static int ejercicio4(int a, int b) {
        if (a == 5) {
            return b;
        } else {
            return ejercicio4(a+1, a*b);
        }
    }

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

    public static int ejercicio6(int x, int y) {
        if (y == 1) {
            return x-x;
        } else {
            return (x*x)+ ejercicio6(x, y-1);
        }
    }

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

    public static int ejercicio8(int a, int b) {
        if(a==0 || b==0){
            return 0;
        }else{
            if(b==1){
                return a;
            }else{
                return a+ejercicio8(a,b-1);
            }
        }
    }

    public static int ejercicio9(int n) {
        if(n<10){
            return n;
        }else{
            return n%10 + ejercicio9(n/10);
        }
    }

    public static int ejercicio10(int [] datos, int n) {
        if(n==1){
            if(datos[n]%2==0){
                return 1;
            }else{
                return 0;
            }
        }else{
            if(datos[n]%2==0){
                return 1 + ejercicio10(datos, n-1);
            }else{
                return ejercicio10(datos, n-1);
            }
        }
    }

}