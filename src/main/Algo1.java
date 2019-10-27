package main;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Algo1 {

    public static void main(String[] args) {
        //we are who we are..
        Algo1 a1 = new Algo1();
        a1.a204();

    }

    public void a206() {


    }


    void a204() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Gimme a number");
        int x = sc.nextInt();
        int counter = 0;
        int counterSeven = 0;
        int counterThirteen = 0;
        while (x != 0 && counter++ < 10) {
            System.out.println("Number pls");
            x = sc.nextInt();
            if (x % 7 == 5) {
                counterSeven ++;
            }
            else {
                if (x % 13 == 7) {
                    counterThirteen++;
                }
            }
        }
        System.out.println("N.o. numbers which divident by 7 modulo and equals to 5 = "+counterSeven);
        System.out.println("N.o numcounterThirteen " + counterThirteen);
    }


    void a203(int[] x) {
        int [] stat = new int[3];
        for(int i = 0; i < x.length; i++) {
            if (x[i] % 3 == 0 && x[i] % 5 == 0) {
               stat[2]++;
            }
            else {
                if (x[i] % 3 == 0) {
                    stat[0]++;
                }
                if(x[i] % 5 == 0) {
                    stat[1]++;
                }
            }
        }
        Arrays.stream(stat).forEach(System.out::println);
//        for (int i = 0; i < stat.length; i++ ){
//            System.out.println(stat[i]);
//        }
    }

    void a203() {
        // read in natural numbers, calculate if there are divideable by : 3 5 15 , make stats
        a203(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 1});
    }


    void a202() {
        // I don't get this
        // the description might be bad..


    }


    void a201(int n) {
        // find the first n perfect number's
        int sum = 0;
        int counter = 1;
        while( n > 0&& counter < 100000) {
            sum = 0;
            for (int i = 1; i < counter; i++) {
                if (counter % i == 0) {
                    sum += i;
                }
            }
            if (sum == counter) {
                n--;
                System.out.println(counter);
            }
            counter++;
        }

    }
    void a201() {
        a201(5);
    }

    public void a124(int year, int month, int day) {
        LocalDate ld = LocalDate.now();
        LocalDate ld2 = LocalDate.of(year,month,day);
        long s = ld.toEpochDay() - ld2.toEpochDay();
        System.out.println("YOu lived " + s + " days.");
    }

    public void a124() {
        a124(1993, 02,05);
    }

    void a123(int date1, int date2) {

        // like date1 should be smaller
         if ( date1 > date2 ) {
             System.out.println("change the date order .. ");
             date1 += date2;
             date2 = date1 - date2;
             date1 = date1 - date2;
         }
        // cheeky date swap ... oh my

         int counter = 0;
         int diff = date2 - date1 + 1;
         counter += diff / 4;
         if (diff >= 100) {
             counter -= diff / 100;
         }
         if ( diff >= 400) {
             counter += diff / 400;
         }

         if ( date1 % 4 == 0 && date1 % 100 != 0) {
             counter++;
         }
        if ( date2 % 4 == 0 && date1 % 100 != 0) {
            counter++;
        }
        if (date1 % 400 == 0) {
            counter ++;
        }
        if (date2 % 400 == 0) {
            counter ++;
        }


        System.out.println("Leap years : " + counter);

    }

    void a123 () {
        a123(1800,2019);
    }

    void a122(int a, int b, int c) {
        double resultArea = 0;
        if ( !(a + b >= c && a + c >= b && b + c >= a)) {
            System.out.println("Not a valid triangle. Try again... ");
        }
        else {
            double p = (1f / 2f) * (a + b + c);
            resultArea = Math.sqrt(p * (p - a) * (p - b) * (p -c));
        }
        System.out.println("Area : " + resultArea);
    }

    void a122() {
        // calculate area of triangle
        a122(3,2,3);
    }


    void a121(double a, double b, double c) {
        double result [] = new double [2];
        if (a == 0) {
            System.out.println("'a' can't be zero, obviously... ");
        }
        else {
            double delta = b * b - 4 * a * c;
            if (c == 0) {
                result[0] = 0;
                result[1] = -b / a;
            } else {
                if (delta < 0) {
                    System.out.println("We don't have a real solution. I don't wanna do imaginary solving. a pity ...  ");
                }
                else {
                    if (delta == 0) {
                        result[0] = result[1] = -b / 2 * a;
                    }
                    else {
                        result[0] = 1/(2*a) * (-b + Math.sqrt(delta));
                        result[1] = 1/(2*a) * (-b - Math.sqrt(delta));
                    }
                }
            }
        }
        System.out.println("Result set : ");
        Arrays.stream(result).forEach(x -> System.out.print(x + " "));
    }

    void a121 () {
        // solve a second grade equation
        a121(-5,1,2);
    }

    void a120(int height, int age, boolean male) {

        double result = 50 + 0.75 * (height - 150) + (age - 20) / 40;
        if (!male) {
            result = result * 0.9;
        }
        System.out.println("Ideal weight: " + result);
    }

    void a120() {
        // calculate a person's ideal weight
        a120(195, 27, true);

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
