package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Algorithms4 {



    private void a415() {
        // get the n < 20 th member
        // apply n times the given operations, print the result
        // T1 = 1;
        // rules are simple, count and then rewrite

        int n = 15;
        LinkedList<Integer> result = a415_impl(n);


    }

    private LinkedList<Integer> a415_impl(int n) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Integer> current = new LinkedList<>();
        LinkedList<Integer> nextIter = new LinkedList();
        current.add(1);
        for(int i = 0; i < n; i++) {

            while(current.size() > 0) {
                int x = current.getFirst();
                int counter = 0;
                int p = 0;
                while (current.size() > 0 && x == (p = current.getFirst())) {
                    counter++;
                    current.removeFirst();
                }
                nextIter.add(counter);
                nextIter.add(x);
            }
            System.out.println(nextIter);
            for(Integer c : nextIter) {
                current.add(c);
            }
            nextIter.clear();
        }



        return result;
    }
    

    private void a414() {
    // find the number with the biggest characteristic
    // characteristic : if even : divide with two/ otherwise multiply it by three and add one : the number of ooerations
        int a = 2;
        int b = 1000;

        int c = a414_impl(a,b);
        int c_c = getCharacteristic(c);
        System.out.println(c + " with with character of " + c_c);
    }

    private int getCharacteristic(int c) {
        int result = 0;
        while (result < 1000 && c != 1) {
            if(c % 2 == 0) {
                c /=2 ;
            }
            else {
                c *= 3;
                c++;
            }
            result++;
        }

        return result;
    }

    private int a414_impl(int a, int b) {
        int max = -1;
        int result = 0;
        for(int i = a; i <=b; i++) {
            int c = getCharacteristic(i);
            if( c > max) {
                max = c;
                result = i;
            }
        }
        return result;
    }

    private void a413() {
        // find longest sub sequence of prime numbers

        int[] x = {2,3,4,5};
        int[] y = {2,3,5,7,11};
        int[] z = {4,6,9,15,12};
        a413_impl(x);
        a413_impl(y);
        a413_impl(z);

    }

    private void a413_impl(int[] x) {
        int maxLength = -1;
        int maxS = -1;
        int maxF = -1;
        int currentLength = 0;
        int sInd = -1;
        int fInd = -1;
        for(int i = 0; i < x.length;i++) {
            if(isPrime(x[i])) {
                fInd = i;
            }
            else {
                currentLength = fInd - sInd;
                if(currentLength > maxLength) {
                    maxLength = currentLength;
                    maxS = sInd;
                    maxF = fInd;
                }
                sInd = i;
                fInd = i;
            }
        }
        currentLength = fInd - sInd;
        if(currentLength >  maxLength) {
            maxLength = currentLength;
            maxS = sInd;
            maxF = fInd;
        }

        if(maxF != -1) {
            for(int i = maxS+1; i <= maxF; i++) {
                System.out.printf("%d ,", x[i]);
            }
        }
        else {
            System.out.println("There is no prime in array.");
        }
        System.out.println();

    }

    private boolean isPrime(int x) {
        boolean result = false;

        int d = 0;
        for(int i = 2; i < x; i++) {
            if(x % i == 0) {
                d++;
            }
        }
        if(d == 0) {
            result = true;
        }
        return result;
    }

    private void a412() {
         // find out if two numbers are friends
        // friends if in decimal system they have one matching digit
        int x1 = 123123;
        int xSystem = 10;
        int x2 = 0x123123;
        int x2System = 16;
        int x3 = 0123123;
        int x3System = 8;
        int x4 =0b111;
        int x4System = 2;
        
        boolean [] results = new boolean[4];
        results[0] = a412_impl(x1,xSystem,x2,x2System);
        results[1] = a412_impl(x1,xSystem,x3,x3System);
        results[2] = a412_impl(x1,xSystem,x4,x4System);
        results[3] = a412_impl(x2,x2System,x4,x4System);

        for(int i =0; i < results.length;i++) {
            System.out.println(results[i]);
        }
    }

    private boolean a412_impl(int x1, int xSystem, int x2, int x2System) {
        boolean result = false;
        int n1 = convertToDecimal(x1,xSystem);
        int n2 = convertToDecimal(x2,x2System);

        Set<Integer> n1Digits = new HashSet<>();
        while (n1 > 0) {
            n1Digits.add(n1 % 10);
            n1 /= 10;
        }
        int counter = 0;
        while(n2 > 0) {
            int digit = n2 % 10;
            n2 /= 10;
            if(n1Digits.contains(digit))
                counter++;
        }
        if(counter >= 1)
            result = true;

        return result;
    }

    private int convertToDecimal(int x, int system) {
        int result = 0;
        int i = 1;
        while(x > 0) {
            int digit = x % 10;
            x /= 10;
            result += digit * i;
            i *= system;
        }
        System.out.println(result);
        return result;
    }

    private void a411() {
        // decide if two numbers are relatives or not // relative when two distinct digits resemble

        int x1 = 112233;
        int x2 = 33445522;
        int x3 = 667788;

        boolean result1 = a411_impl(x1,x2);
        boolean result2 = a411_impl(x1,x3);
        System.out.printf("Are %d and %d relatives? " + result1,x1,x2);
        System.out.println();
        System.out.printf("Are %d and %d relatives? " + result2,x1,x3);
    }


    private boolean a411_impl(int a1, int a2) {
        boolean result = false;

        Set<Integer> digits1 = new HashSet<>();
        int i = 0;
        while(a1 > 0) {
            digits1.add(a1%10);
            a1 /= 10;
        }
        int counter = 0;
        while(a2 > 0) {
            int digit = a2 % 10;
            a2 /= 10;
            if(digits1.contains(digit))
                counter++;

        }
        if(counter > 2) {
            result = true;
        }
        return result;
    }

    private void a410() {    	
    	// find all strongly complex numbers in N interval    	 
    	int n = 1000;
    	int c = a410_impl(n);
    	System.out.println("Biggest complex number : " + c + " divisor count:" + getDivisorCount(c));
    }
    
    private int a410_impl(int threshold) {    	
    	int result = 0;
    	int divisorMax = 0;    	
    	for(int i = 2; i < threshold; i++) {
    		int iCount = getDivisorCount(i);
    		if (iCount > divisorMax) {
    			divisorMax = iCount;
    			result = i;
    		}
    	}    	
    	return result;   	
    }
    
    // returns zero if it's a prime
    private int getDivisorCount(int x) {
    	int count = 0;
    	for(int i = 2; i < x-1; i++) {
    		if( x % i == 0) {
    			count++;
    		}
    	}   	
    	return count;
    }


    private void a409() {
        // 1 + 2 + 3
        // how many numbers has to be added   to get at least sum N
        int n = 100;
        int c = (int)Math.floor(Math.sqrt(2*n));
        System.out.printf("%d is the length of the sum: 1 + 2 + ... + %d",c,c);
    }

    private void a408() {
        // what not. so what is not is not in not a wawht
        int n = 10;
        double x = 2;
        a408(n, x);
    }

    private void a408(int n, double x) {
        double sum = 0;
        double current = 1;

        for(int i = 1; i <= n; i++) {
            sum += current;
            current *= (-1) * (Math.pow(x,2) / (2*n* (n-1)));

        }
        System.out.println(sum);

    }

    private void a407() {
        int n  = 10;
        a407(n);
    }

    private void a407(int n) {
        double add = 1;
        double sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += 1/add;
            add *= i;
        }
        System.out.println("sum : " + sum);
    }

    private void a406() {
        // fill in now.. !!!

        // read in numbers , and then calculate the grades's average which are bigger then 5

        int n = 10;
        int [] ns = new int[n];
        for (int i = 0; i < n; i++) {
            ns[i] = (int)(Math.random() * 10);
        }
        a406(ns);
    }

    private void a406(int[] ns) {
        double sum = 0;
        int c = 0;
        //System.out.println(Arrays.stream(ns).filter(i -> i > 5).sum());

        for (int i = 0; i < ns.length; i++) {
            if(ns[i] >= 5) {
                c++;
                sum+=ns[i];
            }
        }
        System.out.println("Avg : " + sum / c);

    }


    private void a405() {
        int n = 10;
        a405(n);
    }

    private void a405(int n) {
        if(n > 10) {
            System.out.println("Too much for me to bear. gimme a smaller number man..");
        }
        else {
            long fact = fact(n);
            int sum = n * (n+1) / 2;
            System.out.println("Fact : " + fact);
            System.out.println("Sum : = " + sum);
        }
    }

    private void a404() {
        //calc some
        // and then get some :)))_))))))))))))))))))))))))))
        int n = 9;
        a404(n);
    }

    private void a404(int n) {
        double sum1 = 0;
        //int addition = 12;
        for(int i = 0; i < n; i++) {
            sum1 += Math.pow((2*n-1),2);
        }
        System.out.println( "Sum1 : " + sum1);
        double sum2 = 0;
        for(int i = 0; i < n; i++) {
            sum2 += fact(i);
        }
    }

    private long fact(int n) {
        int f = 1;
        for(int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }


    private void a403() {
        int n = 10;
        int[] ns = new int[n];
        for(int i = 0; i < n; i++) {
            ns[i] = (int)(Math.random() * 20);
        }
        a403(ns);
    }

    private void a403(int[] ns) {
        int prod = 1;
        int sum = 0;
        for(int i = 0; i < ns.length; i++) {
            if(ns[i] > 10) {
                sum += ns[i];
            }
            else {
                if(ns[i] > 7) {
                    prod *= ns[i];
                }
            }
        }
        System.out.println( "Sum : " + sum);
        System.out.println("Prod : " + prod);
    }

    private void a402() {

        int n = 10;
        int [] ns = new int[n];
        for (int i = 0; i < n; i++) {
            ns[i] = (int)(Math.random() * 100) - 50;
        }
        a402(ns);

    }

    private void a402(int[] ns) {
        double sum = 1;
        int sumCount = 0;
        double sumNeg = 0;

        for(int i = 0; i < ns.length; i++) {
            if(ns[i] <= 0) {
                sumNeg += ns[i];
            }
            else {
                sum *= ns[i];
                sumCount++;
            }
        }
        System.out.println(sum );

        System.out.println(sumNeg / (ns.length-sumCount));

    }

    private void a401() {
        int n = 10;
        int [] ns = new int[n];
        for (int i = 0; i < n; i++) {
            ns[i] = (int)(Math.random() * 100);
        }
        a401(ns);
    }

    private void a401(int[] ns) {
        double s = Arrays.stream(ns).sum();
        System.out.println(s / ns.length);
    }

}
