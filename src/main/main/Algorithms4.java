package main;

import java.util.Arrays;




public class Algorithms4 {

    public static void main(String[] args) {    	
        Algorithms4 alg = new Algorithms4();        
        alg.a410();       
        
    }

    private void a410() {    	
    	// find all strongly complex numbers in N interval    	 
    	int n = 1000;    	
    	System.out.println("Biggest complex number : " + a410_impl(n));    	
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
