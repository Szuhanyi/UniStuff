package main;

import java.util.*;

public class Algo3 {

    public static void main (String [] args) {
        Algo3 t = new Algo3();
        t.a310();
    }

    private void a310() {
        // get a number's smallest divisor which is bigger then 1
        int number = 99;
        a310(number);

    }

    private void a310(int number) {

    }

    private void a309() {
        // get the given month's index..
        String [] months  = new String[] { "January","February","March","April","May","June","July","August","September","October","November","December"};
        String qMonth = "april";
        a309(months,qMonth);
    }

    private void a309(String[] months, String qMonth) {
        int index = -1;
        for(int i = 0; i < months.length; i++) {
            if(months[i].toUpperCase().equals(qMonth.toUpperCase())) {
                index = i;
            }
        }
        if(index == -1) {
            System.out.println("Try again. No such month in my calendar.. ");
        }
        else {
            System.out.println("Month index : " + index + 1);
        }
    }


    private void a308() {
        // a class's grades and names
        // query if a given student is in the first three
        int n = 5;
        double [] grades = new double[n];
        String [] names = new String[n];
        for(int i = 0; i < n; i++) {
            grades[i] = Math.random() * 9 + 1;
        }
        names[0] = "Jerry";
        names[1] = "Jerry";
        names[2] = "Jerry";
        names[3] = "Jerry";
        names[4] = "Jerry";
        String name = "Jerry";
        a308(names,grades, name);
    }

    private void a308(String[] names, double[] grades, String name) {
        for(int i = 0; i < grades.length - 1; i++ ){
            for (int j = i + 1; j < grades.length; j++ ) {
                if(grades[i] > grades[j]) {
                    double temp = grades[i];
                    grades[i] = grades[j];
                    grades[j] = temp;
                    String tName = names[i];
                    names[i] = names[j];
                    names[j] = tName;
                }
            }
        }
        // not a planet
        int index = -1;
        for(int i = 0; i < names.length; i++) {
            if(name == names[i]) {
                index = i;
            }
        }
        if (index > (names.length - 3)) {
            System.out.println("Top 3 student");
        }
        else {
            if (index == -1)
            {
                System.out.println("No such student in class.");
            }
            else {
                System.out.println("Not a top 3 student.");
            }
        }

    }

    private void a307() {
        // measurements of tempreture for a month
        // decide if the temp rose every day
        double tempMin = 20;
        double tempMax = 35;
        double [] temps = new double[31];
        for(int i = 0; i < temps.length; i++) {
            temps[i] = Math.random() * (tempMax - tempMin) + tempMin;
        }
        a307(temps);
        Arrays.stream(temps).forEach(System.out::println);
    }

    private void a307(double[] temps) {
        boolean wierdTemp = true;
        for(int i = 0; i < temps.length - 1; i++) {
            if(temps[i] > temps[i+1]) {
                wierdTemp = false;
            }
        }
        if(wierdTemp) {
            System.out.println("The temp is weird.");
        }
        else {
            System.out.println("The temps is normal, doesn't rise every day.");
        }
    }


    private void a306() {
        //given s word
        // is it a month  ?
        String s = "January";
        boolean result = isMonth(s);
        if(result) {
            System.out.println(s + " is a month");
        }
        else {
            System.out.println(s + " is not a month");
        }
    }

    private boolean isMonth(String s) {
        List<String> months = new LinkedList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        return months.contains(s);
    }

    private void a305() {
        //is prime n
        int n = 99;
        boolean prime = isPrime(n);
        if(prime) {
            System.out.println("N=" + n + " is prime.");
        }
        else {
            System.out.println("N=" + n + " is not prime.");
        }
    }

    private boolean isPrime(int n) {
        boolean prime = true;
        for(int i = 2; i < n ; i++ ) {
            if(n % i == 0) {
                prime = false;
            }
        }
        return prime;
    }

    private void a304() {
        int n = 5;
        a304(n);
    }

    private void a304(int n) {
        //calculate factorial
        long result = 1;
        for(int i = 1; i <= n ; i++) {
            result *= i;
        }
        System.out.println("n:"+n + " factorial = " + result);
    }

    private void a303() {
        // k lists , with bird race's , each containing the result of one researcher
        // build up the total bird population

        List<String[]> research = new LinkedList<>();
        String [] b1 = new String[] {"Pelican","Magpie","Crow"};
        String [] b2 = new String[] {"Albatross", "Magpie","Stork"};
        String [] b3 = new String[] {"Crow","Stork","Swallow","Sparrow"};
        research.add(b1);
        research.add(b2);
        research.add(b3);
        a303(research);
    }

    private void a303(List<String[]> research) {
        Set<String> birds = new HashSet<>();
        for(String[] birdList : research) {
            Arrays.stream(birdList).forEach(birds::add);
        }
        birds.forEach(System.out::println);
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
