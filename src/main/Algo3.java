package main;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Algo3 {

//    public static void main (String [] args) {
//        Algo3 t = new Algo3();
//        t.a330();
//    }

    private void a330() {

        List<String> class1 = new ArrayList<>();
        class1.add("Balint Jeno");
        class1.add("Pal Balazs");
        List<String> class2 = new ArrayList<>();
        class2.add("Aladar Jeno");
        class2.add("Valter Hitler");

        a330(class1,class2);


    }

    private void a330(List<String> class1, List<String> class2) {
        List<String> faculty = new ArrayList<>();
        class1.forEach(faculty::add);
        class2.forEach(faculty::add);
        faculty.stream().sorted().forEachOrdered(System.out::println);
    }

    private void a329() {
        // two teachers' calendars
        // get hours on which at least one of them could substitute for another teacher
        // in other words , get the sum of the two sets..
        // calendar of five days
        // availability from 8:00 till 18:00
        int [][]  cal1= new int[5][13];
        assignHours(cal1);

        int [][]  cal2= new int[5][13];
        assignHours(cal2);

        a329(cal1,cal2);
    }

    private void a329(int[][] cal1, int[][] cal2) {
        int [][] calSubstitute = new int[5][13];
        for(int i = 0 ; i < 5; i++ ) {
            for(int j = 0; j < 13; j++) {
                // if it is 1 , then there is capacity for substitution
                // ok ? ty
                if(cal1[i][j] + cal2[i][j] < 2) {
                    calSubstitute[i][j] = 1;
                }
            }
        }
        Arrays.stream(calSubstitute).forEach(p ->
            {
                System.out.println();
                Arrays.stream(p).forEach(System.out::print);
            });
    }

    private void assignHours(int[][] cal1) {
        //hours in twos
        // total hours : 5 x 12 = 60
        // working hours : 30
        for(int i = 0; i < 15; i++ ) {
            //create and index randomly
            boolean picked = false;
            while(!picked) {
                int hour = (int) (Math.random() * 60.0);
                int day = hour / 12;
                int actHour = hour % 12;
                if(cal1[day][actHour] == 0) {
                    picked = true;
                    cal1[day][actHour] = 1;
                    cal1[day][actHour+1] = 1;
                }
            }
        }


    }


    private void a328() {
        int [] primeDivisors1 = new int[] {2,5,7,11};
        int [] primeDivisors2 = new int[] {2,11,121};
        a328(primeDivisors1,primeDivisors2);
    }

    private void a328(int[] pd1, int[] pd2) {
        Set<Integer> leastCommonMultiple = new HashSet<>();
        for(int i = 0; i < pd1.length; i++) {
            leastCommonMultiple.add(pd1[i]);
        }
        for(int i = 0; i < pd2.length; i++) {
            leastCommonMultiple.add(pd2[i]);
        }
        System.out.println(leastCommonMultiple);
    }


    private void a327() {
        // we know four people's calendar. Find out which days are available for a cards game.
        int [][] calendarPeti = new int[5][];
        calendarPeti[0] = new int[24];
        busy(8,18,calendarPeti[0]);
        calendarPeti[1]= new int[24];
        busy(8,18,calendarPeti[1]);
        busy(19,21,calendarPeti[1]);
        System.out.println(calendarPeti[1][18]);

        calendarPeti[2] = new int[24];
        busy(8,18,calendarPeti[2]);
        calendarPeti[3] = new int[24];
        busy(8,18,calendarPeti[3]);
        calendarPeti[4] = new int[24];
        busy(8,18, calendarPeti[4]);


        int [][] calendarJancsi = new int[5][];
        calendarJancsi[0] = new int[24];
        busy(8,18,calendarJancsi[0]);
        calendarJancsi[1]= new int[24];
        busy(8,18,calendarJancsi[1]);
        calendarJancsi[2] = new int[24];
        busy(8,18,calendarJancsi[2]);
        calendarJancsi[3] = new int[24];
        busy(8,18,calendarJancsi[3]);
        calendarJancsi[4] = new int[24];
        busy(8,18, calendarJancsi[4]);

        List<int[][]> calendars = new LinkedList<>();
        calendars.add(calendarJancsi);
        calendars.add(calendarPeti);
        a327(calendars);

    }

    private void a327(List<int[][]> calendars) {
        // we are interested on which day are they free on 19.00  hours
        int partyTime = 19;
        List<Integer> freeDays = new LinkedList<>();

        for (int days = 0; days < 5; days++) {
            boolean freeday = true;
            for( int[][] calendar : calendars) {
                if(calendar[days][partyTime] > 0) {
                    freeday = false;
                }
            }
            if(freeday) {
                freeDays.add(days);
            }
        }

        System.out.println(freeDays);

        // facking piece of shit

    }


    private void busy(int i1, int i2, int[] hours) {
        for(int i = i1; i < i2 && i < 24; i++ ){
            hours[i] = 1;
        }
    }

    private void a326() {

        // k researchers    went to document species of Duna Delta, at times of winter and summer
        // get the set of birds which are not moving between seasons / nonMigratory birds

        String[][] researcher1 = new String[2][10];
        researcher1[0] = new String[] {"Snipe","Crow","Vulture","Raven"};
        researcher1[1] = new String[] {"Stork","Vulture"};

        String[][] researcher2 = new String[2][10];
        researcher2[0] = new String[] {"Snipe","Crow","Vulture","Raven","Swallow"};
        researcher2[1] = new String[] {"Stork","Vulture"};

        List<String[][]> researchers = new ArrayList<>();
        researchers.add(researcher1);
        researchers.add(researcher2);
        a326(researchers);

    }

    private void a326(List<String[][]> researchers) {
        int WINTER = 1;
        int SUMMER = 0;
        Set<String> nonMigratoryBirds = new HashSet<>();

        for(String [][] k : researchers) {

            for (String  bird : k[SUMMER]) {
                if(!nonMigratoryBirds.contains(bird)) {
                    for(int i = 0; i < researchers.size(); i++ ) {
                        for (String tempBird : k[WINTER]) {
                            if (bird.equalsIgnoreCase(tempBird)) {
                                nonMigratoryBirds.add(bird);
                                break;
                            }
                        }
                    }
                }


            }

        }

        System.out.println(nonMigratoryBirds);

    }

    private void a325() {
        // get two given number's divisors

        int a = 12;
        int b = 100;
        if(a < b) {
            a325(a, b);
        }
        else {
            a325(b, a);
        }
    }

    private void a325(int a, int b) {

        List<Integer> divisors = new LinkedList<>();
        for(int i = 2; i  < a; i++) {
            if(a % i == 0 && b % i == 0) {
                divisors.add(i);
            }
        }
        System.out.println(divisors);
    }

    private void a324() {

        // n programmer, sort them based on earnings
        int [] salaries = new int [20];
        for(int i = 0; i < salaries.length; i++) {
            salaries[i] = (int)(Math.random() * 1000 + 200);
        }
        a324(salaries);
    }

    private void a324(int[] salaries) {

        List<Integer> first = new LinkedList<>();
        List<Integer> second = new LinkedList<>();
        List<Integer> third = new LinkedList<>();
        for(int i : salaries) {
            if(i > 1000) {
                first.add(i);
            }
            else {
                if (i < 1000 && i > 300) {
                    second.add(i);
                }
                else {
                    third.add(i);
                }
            }
        }

    }

    private void a323() {
        // separate based on attribute (language grades)
        int n = 11;
        int [] german = new int[n];
        int [] english = new int[n];

        for(int i = 0; i < n; i++) {
            german[i] = (int) (Math.random() * 5 + 5);
            english[i] = (int) (Math.random() * 5 + 5);
        }
        a323(german,english);
    }

    private void a323(int[] german, int[] english) {
        int [] better = new int[german.length];
        for(int i = 0; i < better.length; i++) {
            if(german[i] > english[i]) {
                better[i] = 1;
            }
            else {
                better[i] = 2;
            }
        }
        for(int i = 0; i < better.length; i++) {
            if(better[i] == 1) {
                System.out.println("The " + i + "th student is better at german.");
            }
            else {
                System.out.println("The " + i + "th student is better at english.");
            }
        }
    }

    private void a322() {
        // sort the odd numbers from the even ones

        int [] x = new int[10];
        for(int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 100);
        }
        a322(x);
    }

    private void a322(int[] x) {
        List <Integer> oddOnes = new LinkedList<>();
        List <Integer> evenOnes = new LinkedList<>();

        for(int i = 0; i < x.length; i++) {
            if(x[i] % 2 == 1) {
                oddOnes.add(x[i]);
            }
            else {
                evenOnes.add(x[i]);
            }
        }
        System.out.println(oddOnes);
    }

    private void a321() {
        // grades ..
        // which are less then 5, replace it with 0
        // ok ?
        int [] grades = new int[30];
        for(int i = 0; i < grades.length; i++) {
            grades[i] = (int) (Math.random() * 10);
        }
        a321(grades);
    }

    private void a321(int[] grades) {
        for(int i = 0; i < grades.length; i++) {
            if(grades[i] < 5) {
                grades[i] = 0;
            }
            System.out.println(grades[i]);
        }
    }


    private void a320() {
        // given natural number, calculate the product of it's divisors
        int n = 100;
        a320(n);
    }

    private void a320(int n) {
        int product = 1;
        for(int i = 2; i < n; i++) {
            if( n % i == 0) {
                product *= i;
            }
        }
        System.out.println(product + " is the product of n= " + n + "'s divisors. have fun");
    }

    private void a319() {
        // create a table with a student group's female member's  names
        // won't do, doesn't make any sense

    }

    private void a318() {
        // n incomes, get the lowest
        double[] incomes = new double[100];
        Arrays.stream(incomes).forEach(p-> p = Math.random() * 100 + 10);
        double min = Arrays.stream(incomes).min().getAsDouble();
        System.out.println("Min : " + min);
    }

    private void a316() {
        // knowing patients' temperatures get the max
        int patientCount = 100;
        double [] temps = new double[patientCount];
        for(int i = 0; i < patientCount; i++) {
            temps[i] = Math.random() * (42 - 35) + 35;
        }
        a316(temps);
    }

    private void a316(double[] temps) {
        double max = Arrays.stream(temps).max().getAsDouble();
        System.out.println(max);
    }

    private void a317() {
        // given students, who is the first in alphabetic order
        int studentCount  = 100;
        String [] names = new String[studentCount];

        for (int i = 0; i < studentCount; i++) {
            names[i] = " Student " + i;
        }
        a317(names);
    }

    private void a317(String[] names) {
 //        how to do this ? with lambda, and java 8 streams.. huh ?
        List<String> asdf = Arrays.stream(names).sorted().collect(Collectors.toUnmodifiableList());
        System.out.println(asdf.get(3));
    }

    private void a315() {
        // count the number of vowels in a text
        String text = "asdfasdfasdfasdfasdfasdf";
        a315(text);
    }

    private void a315(String text) {
        int vowelCount = 0;
        String [] syl = text.split("");
        for(int i = 0; i < text.length(); i++) {
            if(syl[i].toUpperCase().matches("[AEUIOQ]")) {
                vowelCount++;
            }
        }
        System.out.println("Text : " + text + "\r\n No. of vowels : " + vowelCount);
    }

    private void a314() {
        // count numbers which are higher then a given value
        // how much percentage is it presenting in the total
        int n = 100;
        double [] grades = new double[n];
        double minGrade = 80;
        for(int i = 0 ; i < n; i++ ) {
            grades[i] = Math.random() * 100;
        }
        a314(grades,minGrade);
    }

    private void a314(double[] grades, double minGrade) {
        double moreThenCount = 0;
        for(int i = 0; i < grades.length; i++) {
            if(grades[i] > minGrade) {
                moreThenCount++;
            }
        }
        System.out.println("Studnents how got through to the second round in percentage : " + grades.length / moreThenCount);
    }

    private void a313() {
        // calculate how many values are below a given number
        int iCount = 100;
        double [] incomes = new double[iCount];
        for(int i = 0; i < iCount; i++) {
            incomes[i] = Math.random() * 1000;
        }
        int minIncome = 500;
        a313(incomes,minIncome);
    }

    private void a313(double[] incomes, int minIncome) {
        int lessIncomeCount = 0;
        for(int i = 0; i < incomes.length; i++) {
            if(incomes[i] < minIncome) {
                lessIncomeCount++;
            }
        }
        System.out.println("Number of ppl below minimal income : " + lessIncomeCount);
    }

    private void a312() {

        int dayCount = 30;
        double [] income = new double[dayCount];
        double [] spending = new double[dayCount];
        for(int i = 0; i < dayCount; i++) {
            income[i] = Math.random() * 100;
            spending[i] = Math.random() * 100;
        }
        a312(income,spending);
    }

    private void a312(double[] income, double[] spending) {
        List<Integer> badDays = new LinkedList<>();
        for(int i = 0; i < income.length; i++) {
            if (income[i] < spending[i]) {
                badDays.add(i);
            }
        }
        badDays.forEach(System.out::println);

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
