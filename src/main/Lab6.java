package main;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class Lab6 extends Lab{



    /**
     * Print n into the product of prime dividents
     */
    public void a01() {
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
    public void a02() {
        int n = 10;
        a02_impl(n);
        n = 4;
        a02_impl(n);

    }

    /**
     * calculate a polinom's value in a given point
     */
    @Override
    void a03() {
        // 1 + 2x + 2 x*x
        int [] x = new int []{1, 2, 2};


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


}
