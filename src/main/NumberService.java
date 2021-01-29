package main;

public class NumberService {


    public static int[] generateIntegers(int count,int a, int b) {
        int [] x = new int[count];

        for(int i = 0; i < count;i++) {
            x[i] = (int)  Math.floor((Math.random() * (b-a) + a));
        }

        return x;
    }


    public static int[][] generateMatrixIntegers(int count , int a, int b) {
     int [][]x = new int[count][count];

     for(int i =     0; i < count; i++) {
         x[i] = generateIntegers(count,a,b);
     }

     return x;
    }


    public static int[][] generateMatrixIntegers(int n, int m , int a, int b) {
        int [][]x = new int[n][m];

        for(int i =     0; i < n; i++) {
            x[i] = generateIntegers(m,a,b);
        }

        return x;
    }


    public static void printMatrix(int[][] NxM) {

         for(int i = 0; i < NxM.length;i++) {
            for(int j = 0; j<NxM[i].length;j++) {
                System.out.print(NxM[i][j] + " ");
            }
             System.out.println();
         }
    }


    public static int[][] addMatrix(int [][] x, int [][] y) {
        int n = x.length;
        int [][]z = new int[n][n];
        for (int i = 0; i < n; i++ ) {
            for(int j = 0; j < n; j++) {
                z[i][j] = x[i][j] + y[i][j];
            }
        }
        return z;
    }
}
