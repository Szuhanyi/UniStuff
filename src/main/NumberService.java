package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NumberService {



    public static int getSumOfList(List<Integer> x) {
        int sum = 0;
        for(Integer i : x) {
            sum += i;
        }
        return sum;
    }

    public static int getSumOfDigits(int i) {
        int sum  = 0;
        while(i > 0) {
            sum += i  % 10;
            i /= 10;
        }
        return sum;
    }

    public static List<Integer> mergeLists(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        result.addAll(left);
        if(result.size() == 0) {
            result.addAll(right);
        }
        else {
            right.forEach(p -> result.add(findIndex(result, p), p));
        }
        return result;
    }

    public static int findIndex(List<Integer> x, int e) {
        int result = 0;
        for(Integer i : x) {
            if(i < e) {
                result++;
            }
        }
        return result;
    }



    public static int matrixSumOfColumn(int[][]x, int column) {
        int sum = 0;
        for(int i = 0 ; i < x.length; i++) {
            sum += x[i][column];
        }
        return sum;
    }

    public static int getOppositlyOrderedNumber(int number) {
        int result = 0;
        while (number > 0) {
            result *= 10 + number % 10;
            number /= 10;
        }
        return result;
    }

    public static boolean isPalindrom(int number) {
        int[] digits  = new int[10];
        int length = 0;

        while (number > 0) {
            int digit = number % 10;
            number /= 10;
            digits[length++] = digit;
        }
        boolean isPali = true;
        for(int i = 0; i < length /2; i++) {
            if(digits[i] != digits[length - i -1]) {
                isPali = false;
            }
        }

        return isPali;
    }


    public static int[][] matrixSwapColumns(int[][]x, int i, int j) {
        int[][] y = new int[x.length][x.length];
        try {
            for (int k = 0; k < x.length; k++) {
                for (int l = 0; l < x.length; l++) {
                    y[k][l] = x[k][l];
                }
            }
            for (int k = 0; k < x.length; k++) {
                y[k][i] = x[k][j];
                y[k][j] = x[k][i];
            }
        }
        catch (Exception e) {
            System.out.println("fck");
        }
        return y;
    }

    public static int getMin(int[] x) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < x.length; i++) {
            if(min > x[i]) {
                min = x[i];
            }
        }
        return min;
    }

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

    public static int[][] generateMatrixOderedIntegers(int count , int a, int b) {
        int [][]x = new int[count][count];
        int counter = 0;
        for(int i = 0; i < count; i++) {
            for(int j = 0; j < count; j++) {
                x[i][j] = counter++;
            }
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

    public static void printArray(int[] snake) {
        System.out.println();
        for(int i = 0; i < snake.length;i++) {
            System.out.print(snake[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(double[] snake) {
        System.out.println();
        for(int i = 0; i < snake.length;i++) {
            System.out.print(snake[i] + " ");
        }
        System.out.println();
    }

    /**
     * convert to 10 numeric system
     * @param system
     * @param number
     * @return
     */
    public static int convertToDecimalSystem(int system, int number) {
        int result = 0;
        List<Integer> digits = new ArrayList<>();
        while(number > 0) {
            digits.add(number % 10);
            number /= 10;
        }
        for(Integer n : digits) {
            result *= system;
            result += n;
        }

        return result;
    }

    public static List<Integer> getDigits(int n) {
        LinkedList<Integer> digits = new LinkedList<>();
        while(n > 0) {
            int digit = n % 10;
            n /= 10;
            //digits.add(digit);
            digits.addFirst(digit);
        }
        return digits;
    }

    public static int createNumber(List<Integer> digits) {
        int result = 0;
        for(int i = 0; i < digits.size(); i++) {
            result = result * 10 + digits.get(i);
        }
        return result;
    }
}
