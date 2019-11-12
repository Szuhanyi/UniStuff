package main;

import java.util.Arrays;

public class Algo3 {

    public static void main (String [] args) {
        Algo3 t = new Algo3();
        t.a301();

    }



    private void a302() {

    }

    private void a301() {
        // n student's grades
        // print the avg
        double [] n = new double[]{1,2,3,4,5,6,7,8,9};
        double s = Arrays.stream(n).sum();
        System.out.println(s);
    }
}
