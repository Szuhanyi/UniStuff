package main;

import java.time.LocalDate;
import java.util.*;

public class Algo1 {

    public static void main(String[] args) {
        //we are who we are..
        Algo1 a1 = new Algo1();
        a1.a219();

    }


    private void a219() {
        //read from input a number, then a digit,
        // print the occurencess
        Scanner sc = new Scanner(System.in);
        System.out.println("Gimme a number, max 9 digits");
        boolean reading = true;
        int number = 0;
        while(reading) {
            try {
                number = sc.nextInt();
                reading = false;
            }
            catch (Exception e) {
                System.out.println("try again pls");
            }
        }
        reading = true;
        int digit = 0;
        while(reading) {
            System.out.println("Gimme a digit");
            try {
                digit = sc.nextInt();
                if (digit / 10 == 0) {
                    reading = false;
                }
                else {
                    throw new Exception("only a digit..");
                }
            }
            catch (Exception e) {
                System.out.println("try again pls");
            }
        }
        int counter = 0;
        int originalNumber = number;
        while(number > 0) {
            int m = number % 10;
            number /= 10;
            if(m == digit){
                counter++;
            }
        }

        System.out.println("Digit: " + digit + " is in the number : " + originalNumber + " " + counter + " times.");
    }


    private void a218() {
        // find the first fibo number after n
        int n = 100;
        a218(n);
    }

    private void a218(int n) {
        int f1 = 0;
        int f2 = 1;
        int f3 = 1;
        while(f3 < n) {
            f1 = f2;
            f2 = f3;
            f3 = f1+f2;
        }
        System.out.println("Fibo after n="+n + " : " + f3);
    }

    private void a217() {
        // count how many fibo numbers are below n
        int n = 100;
        a217(n);
    }

    private void a217(int n) {
        int f1 = 0;
        int f2 = 1;
        int f3 = 1;
        int counter = 0;
        while(f3 < n) {
            f1 = f2;
            f2 = f3;
            f3 = f1+f2;
            counter++;
        }
        counter --; // we need to decrement, in order to exclude the number that's above n
        System.out.println("Fibo count below n="+n + " : " + counter);

    }

    private void a216() {
        // monkey opens the cages of animals.. then closes them.. how many are left open at the end ? let's find out
        // monkeys like bananas.. right ?
        // are you a monkey as well ? ;)

        int nOfCages = 100;
        a216(nOfCages);
    }

    private void a216(int nOfCages) {
        int [] cages = new int[nOfCages];
        for(int i = 1; i < nOfCages; i++) {
            for(int j = 0; j < nOfCages; j+=i) {
                cages[j] += 1;
                cages[j] %=2;
            }
        }

        for(int i = 0; i< nOfCages; i++) {
            if(cages[i] == 1) {
                System.out.println(i+1);
            }
        }

    }

    void a215() {
        // find x for which : x = (( (x / 3 + 1) / 3 + 1) / 3 + 1)/ 3 + 1 is a whole number
        int n = 1000;
        a215(n);
    }

    private void a215(int n) {
        boolean run = true;
        int i = 1;
        while (run) {
            int x = (((i * 3 + 1) * 3 + 1) * 3 + 1) * 3 + 1;
            if (x < n) {
                System.out.println("Possible number of coconuts: " + x);
            }
            else {
                run = false;
            }
            i++;
        }
    }

    void a214() {
        //send messages to every k-th address, till someone would get a second one
        int k = 2;  // result is 50
        // int k = 9; // result would be 0
        int n = 100;
        int b = 50;
        a214(k,n,b);
    }

    private void a214(int k, int n,int b) {
        // it doens't matter where is the starting point, in finding how many of them are going to be left out
        b = 0;
        int [] x = new int[n+1];
        boolean run = true;
        int s = 0;
        while( run ){
            for ( int i = (b + s); i < n; i += k) {

                if (x[i] != 1) {
                    x[i] = 1;
                } else {
                    run = false;
                }
            }
            s =  ((k - (n % k) + s) % k);
        }
        int i = Arrays.stream(x).sum();
        System.out.println("Memebers count who didn't receive a message : " + (n - i));
    }

    void a212() {
        // find the first n twin prime pairs
        // (3,5), (5,7), (11,13) (p1-p2) = 2
        int n = 10;
        a212(n);
    }

    private void a212(int n) {
        int i = 2;
        int o1= 2;
        int o2= 2;
        List<int[]> pairs = new LinkedList<>();
        while (n > 0 && i < 10000) {
            if (isPrime(i)) {
                o2 = o1;
                o1 = i;
                if (o1 - o2 == 2) {
                    pairs.add(new int[]{o1,o2});
                }
                n--;
            }
            i++;
        }
        pairs.forEach(this::prettyFormat);
    }

    void prettyFormat(int[] x) {
        System.out.println("("+x[1] + "," + x[2] + ")");
    }

    void a211() {
        // write in two prime number's sum
        int n = 100;
        a211(n);
    }

    private void a211(int n) {
        int d1 = 2;
        int d2 = 2;
        boolean ok = false;
        while(!ok) {
            int i = 2;
            while( i < n && !ok) {
                while(!isPrime(i)) i++;
                int j = 2;
                while(j < n && !ok) {
                    while(!isPrime(j)) j++;
                    if ((i + j) == n) {
                        ok = true;
                        d1 = i;
                        d2 = j;
                    }
                    j++;
                }
                i++;
            }
        }
        System.out.println(n + " = " + d1 + " + " + d2);
    }

     boolean isPrime(int n ) {
        boolean ok = false;
        int i = 2;
        while (i < n && !ok) {
            if (n % i == 0) {
                ok = true;
            }
            i++;
        }
        return !ok;
    }
    void a210() {
        // get the number with the most dividents, in rage [0,t]
        int t = 10000;
        a210(t);
    }

    private void a210(int treshold) {
        int max = 0;
        int maxN = 0;
        for (int i = 2; i < treshold; i++) {
            int d = getNDividents(i);
            if (max < d) {
                max =  d;
                maxN = i;
            }
        }
        System.out.println("Number with most dividents : " + maxN);
    }
    private int getNDividents(int n) {
        int divN = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                divN ++;
            }
        }
        return divN;
    }

    void a209() {
        //get the biggest prime number under one million
        long n = 1000000 - 1;
        boolean gotPrime = false;
        while(!gotPrime) {
            boolean div = false;
            for (int i = 2; i < n; i++ ){
                if (n % i == 0) {
                    div = true;
                    break;
                }
            }
            if (!div) {
                gotPrime = true;
            }
            n--;
        }
        System.out.println(n);
    }

    void a208() {
        // define if its prim number, if it is, then write to std the real dividents, and how many were
        // n > 0
        int n = 71;
        a208(n);

    }

    private void a208(int n) {
        int noD = 0;
        int o = 2;
        for ( ; o < n; o++) {
            if (n % o == 0) {
                noD ++;
            }
        }
        if (noD == 0) {
            System.out.println(n + " is a prime number");
        }
        else {
            o = 2;
            Set<Integer> divs = new HashSet<>();
            while (o < n) {
                if (n % o ==0) {
                    n = n / o;
                    divs.add(o);
                }
                else {
                    o++;
                }
            }
            System.out.println("Number of dividents : " + divs.size());
            divs.stream().forEach(System.out::println);
        }

    }

    void a205() {
        int n = 5;
        // calculte n's real biggest divident

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
