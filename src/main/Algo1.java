package main;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Algo1 {

    public static void main (String [] args) {
        //we are who we are..
        Algo1 a1 = new Algo1();

        //a1.printSwapped(1,2);
        a1.a3(1,-2,3);

    }


    void printSwapped(int a, int b) {
        System.out.println(a);
        System.out.println(b);
        System.out.println("After swap");
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a);
        System.out.println(b);
    }

    void readThreeNumbers() {

        // and then write them out in order

        Scanner sc = new Scanner(System.in);
        int [] numbers = new int [3];
        System.out.println("Type in three numbers.. ");
        for(int i = 0; i < 3; i++) {
            System.out.print("Number " + i+1 + ". .. ");
            numbers[i] = sc.nextInt();

        }
        Arrays.stream(numbers).sorted().forEach(System.out::println);
    }

    void a3(double a, double b, double c) {
        //get the min
        // then get the max ( the absolute value of the numbers)
        double min = 0;

        if (a < b) {
            if (a < c) {
                min  = a;
            }
            else {
                min = c;
            }
        }
        else {
            if (b  < c) {
                min = b;
            }
            else {
                min = c;
            }
        }

        double max = a;
        if ( Math.abs(a) > Math.abs(b)) {
            max = Math.abs(a);
        }
        if(max < Math.abs(c)) {
            max = Math.abs(c);
        }

        System.out.println("min = " + min);
        System.out.println("max abs = " + max);

    }

    void a4 (double a, double c, double d, double e) {

    }

}
