package main;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Algo1 {

    public static void main(String[] args) {
        //we are who we are..
        Algo1 a1 = new Algo1();
        a1.a19();

    }


    void a19() {
        a19(1.2,1.3,1.4,1.5,1.6);
    }

    private void a19(double x, double a, double b, double c, double d) {
        double result = 0;
        if (x < 5) {
            result = 3 * x - 5;
        }
        if (x >= 5 && x <= 10) {
            result = 10;
        }
        if (x > 10) {
            result = 9*x + 1;
        }
        System.out.println("F(x) = " + result);

        result = 0;
        if (a + c > 2 * d && b > 0) {
            result = d - 3 * b;
        }
        else if (a + c > 0 && b < 0) {
            result = d + 3 * b;
        }
        else {
            result = 4;
        }
        System.out.println("E(a,b,c,d) = " + result);
    }


    void a18( int lenght, int width, int cableLength) {
        // calcualte how much cable you need for the area to fence.
        int parcelPeriphery = 2 * lenght + 2 * width;
        if (parcelPeriphery > cableLength) {
            System.out.println("We need additionally " + (parcelPeriphery - cableLength) + " more wire");
        }
        else {
            System.out.println(" Remaining wire volume : " + (cableLength - parcelPeriphery));
        }
    }
    void a18() {
        a18(5,2,14);
    }

    void a17(double h, double r) {
        // get the straight cylinder's  cylindrical surface, surface area , and volume
        // we have the cylinder's height, and radian ?

        double cs = 2 * Math.PI * r * h;
        System.out.println("open cylinder surface: " + cs);

        double allCS = cs + 2 * Math.PI * r * r;
        System.out.println("total surface of culinder : " + allCS);

        double volume = Math.PI * r * r * h;

        System.out.println("Volume :  " + volume);

    }

    void a17 () {
        a17(2,1);
    }

    void a15(double a, double b, double c, double d) {
        printOut(a, b, c, d);
        if (d > 0) {
            printOut(a, c, b, d);
        } else {
            printOut(a, b, d, c);
        }
    }

    void printOut(double a, double b, double c, double d) {
        double[] x = new double[]{a, b, c, d};
        Arrays.stream(x).forEach(it -> System.out.println(it));
        System.out.println("Done and Done.. ");
    }

    void a15() {
        a15(2, 2, 4, 4);
    }

    void printSwapped(int a, int b) {
        // 1.1
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
        // 1.2
        // and then write them out in sorted
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[3];
        System.out.println("Type in three numbers.. ");
        for (int i = 0; i < 3; i++) {
            System.out.print("Number " + i + 1 + " ... \r\n");
            numbers[i] = sc.nextInt();
            System.out.println("Thx bro");
        }
        Arrays.stream(numbers).sorted().forEach(System.out::println);
    }

    void a13(double x) {
        // evaluate x
        double result = Double.MAX_VALUE;
        if (x >= -2 && x < 0) {
            result = 2 * x;
        }
        if (x >= 0 && x < 2) {
            result = x * x;
        }
        if (x > 2) {
            result = 10;
        }
        if (result != Double.MAX_VALUE) {
            System.out.println("F(x) = : " + result);
        } else {
            System.out.println("Invalid x");
        }


    }

    void a13() {
        a13(0);
    }

    void a3(double a, double b, double c) {
        //get the min `
        // then get the max ( the absolute value of the numbers)
        double min = 0;

        if (a < b) {
            if (a < c) {
                min = a;
            } else {
                min = c;
            }
        } else {
            if (b < c) {
                min = b;
            } else {
                min = c;
            }
        }

        double max = a;
        if (Math.abs(a) > Math.abs(b)) {
            max = Math.abs(a);
        }
        if (max < Math.abs(c)) {
            max = Math.abs(c);
        }

        System.out.println("min = " + min);
        System.out.println("max abs = " + max);

    }


    void a4(double a, double b, double c) {
        // Adott három szigorúan pozitív valós szám: a, b, c. Képezhetik-e ezek a szá­mok egy háromszög oldalait? Ha igen, határozzuk meg és írjuk ki a há­rom­szög­be írt, illetve a háromszög köré írt kör sugarát! Ha nem, írjunk ki meg­fe­lelő ­üzenetet.
        if (a > 0 && b > 0 && c > 0) {
            boolean triangle = true;
            if (Math.sqrt(c) > Math.pow(a, 2) + Math.pow(b, 2))
                triangle = false;

            if (Math.sqrt(a) > Math.pow(b, 2) + Math.pow(c, 2))
                triangle = false;

            if (Math.sqrt(b) > Math.pow(a, 2) + Math.pow(c, 2))
                triangle = false;

            if (!triangle) {
                System.out.println("Ths can't be a triangle.. Do something else pls. lll hashtag.. stupid idiot.. ahaha  ");
                return;
            } else {
                // r  of inscribed circle .. area/halfPerimeter * 2
                double halfPerimeter = (a + b + c) / 2;
                double area = Math.sqrt((halfPerimeter) * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
                double r = area / halfPerimeter * 2;
                System.out.println("Raduis of the inscribed circle: " + r);
            }
        }
    }


}
