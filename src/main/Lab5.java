package main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lab5 { 

    private int n = 10;
    private int a = 1;
    private int b = 100;

    public static void main (String [] args) {
        Lab5 l = new Lab5();
        l.a008();

    }

    private void a008() {
        // print a matrix as a snake.. oh man
        int[][] x = NumberService.generateMatrixIntegers(n,a,b);
        int [] y = a008_impl(x);


    }


    private int[] a008_impl(int[][] x) {
        int [] snake = new int[n*n];
        int counter = 0;
        int currentLength = n / 2;

        while(currentLength-- > 0) {

            // do a round..  //nice go down
            for(int i = (n/2 - currentLength); i < n - (n/2-currentLength);) {


            }


            // go right;



            // go up



            // go left

        }

        return snake;
    }


    private void a007() {
        // whatnot
        // find items where x[i][j] mod i+j = 0
        int[][] x = NumberService.generateMatrixIntegers(n,a,b);
        a007_impls(x);
    }


    private void a007_impls(int[][] x) {
        List<Integer> numbers = new LinkedList<>();
        List<Integer> indexx = new LinkedList<>();
        List<Integer> indexy = new LinkedList<>();

        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[i].length; j++) {
                if((i+j != 0) && x[i][j] % (i + j) == 0) {
                    numbers.add(x[i][j]);
                    indexx.add(i);
                    indexy.add(j);
                }
            }
        }
        for(int i = 0; i < numbers.size(); i++ ) {
            System.out.println("Item " + numbers.get(i) + " on i:" + indexx.get(i) + " j:" + indexy.get(i) + " are special");
        }
    }


    private void a006() {
    // this is not a game //
        // same as 005, but now we will have items without prices
        int n = 5;
        int m = 100;
        int[][] data = NumberService.generateMatrixIntegers(n,m,a,b);
        int mistakes = 10;
        int [] x = NumberService.generateIntegers(mistakes, 0,n);
        int [] y = NumberService.generateIntegers(mistakes, 0,m);
        for(int i = 0; i < x.length; i++) {
            data[x[i]][y[i]] = 0;
        }
        NumberService.printMatrix(data);
        // get the store with the most 0 -s
        int st = a006_a(data);
        System.out.println("The store with the most errors: " + st);
        // get an item's avg price
        int item = NumberService.generateIntegers(10,1,m)[5];
        int avgPrice = a006_b(data,item);
        System.out.println(item + " item's avg price is : " + avgPrice);

    }

    private int a006_b(int[][] data, int item) {
        int result = 0;
        int sum = 0;
        int mistakes = 0;
        for(int i = 0; i < data.length;i++) {
            if(data[i][item] != 0) {
               sum = data[i][item];
            }
            else {
                mistakes ++;
            }
        }
        result = sum / (data.length-mistakes);
        return result;
    }

    private int a006_a(int[][] data) {
        int rubbishStore = 0;
        int counter = 0;
        int max = -1;
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length;j++) {
                if(data[i][j] == 0) {
                    counter++;
                }
            }
            if(counter > max) {
                max = counter;
                rubbishStore = i;
            }
        }
        return rubbishStore;
    }

    private void a005() {
        int n = 10;
        int m = 5;
        int[][] data = NumberService.generateMatrixIntegers(n,m,a,b);
        // have m stores, and n items
        NumberService.printMatrix(data);
        // get the store with the highest item average
        int highness = a005_a(data);
        System.out.println("Most expensive store : " + highness);

        // get the cheapest item
        int lowless = a005_b(data);
        System.out.println("The cheapest Item : " + lowless);

        // given item, get where it is sold  for the most
        int item = NumberService.generateIntegers(10,1,m)[4];

        int store =  a005_c(data,item);
        System.out.println(item + "is being sold for the most at " + store + " for at the price of : " + data[store][item]);

        // given store's avg prices
        store = NumberService.generateIntegers(10,1,n)[5];
        int avgPrice = a005_d(data,store);

        System.out.println("the avg price of items for store " + store + " is : " + avgPrice);


    }

    private int a005_d(int[][] data, int store) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < data[store].length; i++) {
            sum += data[store][i];
        }
        result = (int)Math.floor(sum/data.length);
        return result;
    }

    private int a005_c(int[][] data, int item) {
        // get the biggest value in column item
        // return row
        int result = 0;
        int max = -1;
        for(int i = 0; i < data.length;i++) {
            if(data[i][item] > max) {
                max = data[i][item];
                result = i;
            }
        }
        return result;
    }

    private int a005_b(int[][] data) {
        // gets the lowest item's index (column)
        int result = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < data.length;i++) {
            for(int j= 0; j < data[i].length;j++) {
                if(min > data[i][j]) {
                    min = data[i][j];
                    result = j;
                }
            }
        }

        return result;
    }

    private int a005_a(int[][] data) {
        //get the highest avarage of the rows
        int result = 0;
        int max = -1;

        for(int i = 0; i < data.length; i++) {
            int sum = 0;
            for(int j = 0; j < data[i].length; j++) {
                sum += data[i][j];
            }
            if(sum > max) {
                max = sum;
                result = i;
            }
        }

        return result;
    }

    private void a004() {
        // add n matrixes

        int[][] m1 = NumberService.generateMatrixIntegers(n,a,b);
        int[][] m2 = NumberService.generateMatrixIntegers(n,a,b);
        int[][] m3 = NumberService.generateMatrixIntegers(n,a,b);
         
        int[][] m = new int[n][n];

        m = NumberService.addMatrix(m,m1);
        m = NumberService.addMatrix(m,m2);
        m = NumberService.addMatrix(m,m3);

        NumberService.printMatrix(m);

    }


    private void a003() {
        // given N matrix, rotate it, thx
        int n = 10;
        int[][] x = new int[n][];
        for (int i = 0; i < n; i++) {
            x[i] = NumberService.generateIntegers(n, 1, 100);
        }
        NumberService.printMatrix(x);
        System.out.println();
        int[][] y = a003_impl(x);
        NumberService.printMatrix(y);

    }


    private int[][] a003_impl(int[][] x) {
        //rotates matrix
        int [][] result = new int[x.length][x.length];

        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[i].length; j++) {
                result[j][x.length-i -1] =  x[i][j];
            }
        }

        return result;
    }


    private void a002() {
        int n = 10;
        int [] x = NumberService.generateIntegers(n,1,100);
        a002_impl(x);
    }


    private void a002_impl(int[] x) {
        for(int i = 0; i < x.length; i++) {
            int sum = 0;
            for(int j = 0; j < x.length; j++) {
                if( j != i) {
                    sum +=x[j];
                }
            }
            x[i] = sum/(x.length - 1);
        }
        Arrays.stream(x).forEach(System.out::println);
    }


    private void a001() {
        // given X array
        // get the average of the numbers between a,b
        // the rest should be put into another array
        int n = 10;
        double[] x = new double[n];
        for(int i = 0; i < n; i++) {
            x[i] = Math.random() * 100;
        }
        int a = 10;
        int b = 50;
        a001_impl(x,a,b);
    }

    private void a001_impl(double[] x, int a, int b) {
        double sum = 0;
        List<Double> y = new LinkedList<>();
        for(int i = 0; i < x.length; i++) {
            if(x[i] >= a && x[i] <= b) {
                sum+=x[i];
            }
            else {
                y.add(x[i]);
            }
        }
        double avg = sum / (x.length - y.size());
        System.out.println("Avg of numbers is : " + avg);
        System.out.println(" THe rest of the numbers are : " );
        System.out.println(y);
    }

    
}
