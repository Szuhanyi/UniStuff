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

    private void a03_impl(int[] x) {

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


}
