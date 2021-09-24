package main;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class Lab6 extends Lab{



    /**
     * Print n into the product of prime dividents
     */
    public void a001() {
        // gotta use recursive function
        // n = R(i-1) * R(i-2) * R(1);
        List<Integer> x = new LinkedList<>();
        primeDivident(10,x);
        System.out.println(x);
        primeDivident(144,x);
        System.out.println(x);
    }


    private void primeDivident(int n, List<Integer> x) {
        if(n > 1) {
            int k = 2;
            while(n % k != 0)
                k++;
            primeDivident(n / k,x);
            x.add(k);
            return ;
        }
        x.clear();
        return ;
    }


    private int sumArray(List<Integer> arr) {
        if(arr.size() > 0)
        {
            int c= arr.get(0);
            arr.remove(0);
            return c + sumArray(arr);
        }
        else
            return 0;
    }


    /**
     * Check if the number is perfect ( it equals to the sum of the smaller dividents)
     */
    public void a002() {
        int n = 10;
        a02_impl(n);
        n = 4;
        a02_impl(n);

    }

    /**
     * calculate a polinom's value in a given point
     */
    @Override
    void a003() {
        // 1 + 2x + 2 x*x
        double [] x = new double []{1, 2, 2};
        double point =2;
        int i = 0;
        double value = evaluatePolinom(x,point,i);
        System.out.println(value);
        // the plunging attack. oh my


    }

    private double evaluatePolinom(double[] x, double point, int index) {
        // haha
        // escape
        if(x.length > index) {
            return x[index] * Math.pow(point,(double)index) + evaluatePolinom(x,point,index + 1);
        }

        return 0;
    }

    private void a02_impl(int n ) {
        String s = "";
        Boolean result = isPerfect(n);
        if(result) {
            s += "Perfect number : ";
        }
        else
            s += "Not a perfect number: ";
        System.out.println(s + n);
    }

    private Boolean isPerfect(int i) {
        boolean result = false;
        List<Integer> x = new LinkedList();
        primeDivident(i,x);
        int sum = 0;
        sum = sumArray(x);
        if (sum == i)
            result = true;

        return result;

    }

    /**
     * Calculate the biggest shared divisor of n numbers
     */
    @Override
    public void a004() {
//        int[] x  = new int[] {12,16,100};
        int[] x  = new int[] {20,40,90};
        int ln = a004_impl(x);
        System.out.println(ln);

    }

    private int a004_impl(int[] x) {
        int[] ln = new int[1];
        ln[0] = x[0];
        manyLnko(x,0,ln);

        return ln[0];
    }

    private void manyLnko(int[]x, int index ,int[] ln) {
        if(x.length > index ) {
            int[] rez = new int[1];
            lnko(x[index],ln[0], rez);
            ln[0] = rez[0];
            manyLnko(x,index+1,ln);
        }
    }

    private void lnko(int a, int b, int[] rez) {
        if (a != b) {
            if (a > b ) {
                lnko(a - b, b,rez);
            }
            else {
                lnko(a,b -a,rez);
            }
        }
        else {
            rez[0] = a;
        }
    }


    @Override
    public void a005() {
        int m = 2;
        int n = 2;
        int c = ack(m,n);
        System.out.println(c);
    }


    private int ack(int m, int n) {
        if(m == 0) {
            return n + 1;
        }
        else
            if(n == 0) {
                return ack(m-1,0);
            }
            else
                return ack(m - 1, ack(m,n - 1));

    }


    /**
     * calculate the fibonacci number  (the nth one)
     */
    @Override
    public void a006() {
        int n = 100;
        // what not ..
        int c = recursiveFibo(n,1,1,2);
        System.out.println(c);
    }


    private int recursiveFibo(int n, int f1, int f2, int f3 ) {
        if (n ==  0 ) {
            return f3;
        }
        else {
            return recursiveFibo(n -1,f2,f3,f1+f2);
        }
    }


    /**
     *  change number system from 16 to 10
     */
    @Override
    public void a007() {
        // what is going on
        String x1 = "AA";
        String x2 = "B";
        String x3 = "11";
        // what has happened here ?
        final String []  system = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        int x1Ten = toSystemTen(x1,0,system);
        int x2Ten = toSystemTen(x2,0,system);
        int x3Ten = toSystemTen(x3,0,system);
        System.out.println(x1Ten);
        System.out.println(x2Ten);
        System.out.println(x3Ten);
    }


    private int toSystemTen(String x1, int i, String[] system) {
       if(x1.length() > 0) {
            for(int j = 0; j < system.length; j++) {
                if(system[j].equals(x1.substring(x1.length()-1))) {
                    // a contender
                    int pp = (int) (Math.pow(16,i));
                    return  j * pp + toSystemTen(x1.substring(0,x1.length()-1),i+1,system);
                }
            }
       }
       return 0;
    }

    /**
     * generate the partitions of number n
     */
    public void a008() {
        int n = 4;
        partitions(n,1 , new LinkedList<>());
    }

    private void partitions(int n, int i, List<Integer> l) {
        if(n >= i) {
            while( n >= i) {
                l.add(i);
                partitions(n - i, i,l);
                l.remove(l.size()-1);
                i++;
            }
        }
        else {
            if(n == 0) {
                System.out.println(l);
            }

        }
    }


}
