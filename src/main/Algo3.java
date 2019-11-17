package main;

import java.util.Arrays;

public class Algo3 {

    public static void main (String [] args) {
        Algo3 t = new Algo3();
        t.a302();

    }

    private void a302() {
        // fund me pls .. I am out of money :D
        char [] ch = new char[]{'1','2','4','5','5'};
        int m = ch.length;
        a302(ch);
    }

    private void a302(char[] ch) {
        String result = "";
        for(char c : ch) {
            result += c;
        }

        System.out.println(result);
    }

    private void a301() {
        // n student's grades
        // print the avg
        double [] n = new double[]{1,2,3,4,5,6,7,8,9};
        double s = Arrays.stream(n).sum();
        System.out.println(s/n.length);
    }
}
