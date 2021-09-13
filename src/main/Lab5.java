package main;


import com.sun.corba.se.spi.orbutil.fsm.Input;
import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.logging.ConsoleHandler;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Lab5 extends Lab {

    private int a = 1;
    private int n = 20;
    private int b = 10000;

    /**
     * Find all the lucky numbers in [a,b] interval
     */
    @Override
    public void a025() {
        List<Integer> luckyNumbers = a025_impl(a,b);
       // luckyNumbers.stream().forEach(System.out::println);
    }

    // a lucky number is number whose digits  can be separated to  two groups with equal sum
    private List<Integer> a025_impl(int a, int b) {
        List<Integer> newList = new LinkedList<>();
        // we can try every possibility... which would yield 2^n possibilities
        for(int i = a; i <=b; i++) {
            if (isLucky(i)) {
                newList.add(i);
            }
        }
        return newList;
    }

    // return if the number is lucky
    private boolean isLucky(int x) {
        boolean result = false;
        List<Integer> digits = NumberService.getDigits(x);
        List<String> possibilities = new LinkedList<>();
        int t = digits.size();
        generateVariation("",t,possibilities);

        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        for(int i = 0; i < possibilities.size(); i++) {
            String item = possibilities.get(i);
            list1.clear();
            list2.clear();
            for(int j = 0;  j < item.length(); j++) {
                if(item.charAt(j) == '0') {
                    list1.add(digits.get(j));
                }
                else {
                    list2.add(digits.get(j));
                }
            }
            if(NumberService.getSumOfList(list1) == NumberService.getSumOfList(list2)){
                result = true;
                System.out.println(x + " is lucky cause : ");
                list1.forEach(System.out::print);
                System.out.print("  ");
                list2.forEach(System.out::print);
                System.out.println();
            }
        }
        return result;
    }


    private String generateVariation(String current, int threshold, List<String> result) {
        if(threshold > 0 ) {
            generateVariation(current+0,threshold-1, result);
            generateVariation(current+1,threshold-1, result);
        }
        if(threshold == 0)
            result.add(current);
        return "";
    }


    @Override
    public void a024() {
        // there are two methods to read data in
        // ask user which one to use

        System.out.println("Gimme the format for reading in the polinoms (1/2): ? ");
        List<double[]> polis = new LinkedList<>();
        double t = -2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String mode = "";
        try {
            boolean cond = true;
            while (cond) {
                mode = br.readLine();
                try {
                    int c = Integer.parseInt(mode);
                    if (c == 1 || c == 2)
                    {cond = false; }
                    else
                    {
                        System.out.println("try again (1/2): gl");
                    }
                }
                catch(NumberFormatException e) {
                    System.out.println("try again, (1/2): gf");
                }
            }

            System.out.println("Chosen way is " + mode);

            // now for the good port,
            // read in all the polinoms which you wanna use
            System.out.println("Start introducing the polinoms.");

            cond = true;
            while(cond) {
                try {
                    System.out.println("Need power t: ");
                    String power = br.readLine();
                    t = Integer.parseInt(power);

                    System.out.println("Polinom : ");
                    power = br.readLine();

                    String [] digits = power.split(" ");
                    double[] vex = new double[digits.length +1];
                    for(int i = 0; i < digits.length; i++) {
                        vex[i] = Double.parseDouble(digits[i]);
                    }
                    polis.add(vex);
                    System.out.println(" ");
                    System.out.println("You have entered : ");
                    NumberService.printArray(vex);

                    System.out.println("enter 0 to stop introducing polinoms.");
                    power = br.readLine();
                    if(power.equals("0") ) {
                       cond = false;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Wrong input try again");
                }
            }
        } catch (IOException e) {
            System.out.println("Fck me , it is not working.");
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All arrays introduced: ");
        for(double[] d : polis) {
            NumberService.printArray(d);
        }

        double x1 = -2;
         a024_impl(polis,x1);
    }


    private void a024_impl(List<double[]> polis, double t) {
        double sum = 0 ;
        double max = 0;
        if(polis.size() == 0) {
            System.out.println("no polis given, u fat fck");
            return ;
        }
        double[] maxPoli = polis.get(0);
        max = getValueInPoint(polis.get(0),t);
        for(double [] x : polis) {
            double value = getValueInPoint(x,t);
            sum += value;
            if(value > max) {
                max = value;
                maxPoli = x;
            }
        }
        System.out.println(" sum  is : " + sum);
        System.out.println("value is highest in : " + max);
        Arrays.stream(maxPoli).forEach(System.out::print);
    }


    private double getValueInPoint(double [] polinom, double x) {
        double sum = 0;
        double d = 1;
        for(int i = 0; i < polinom.length; i++) {
            sum += polinom[i] * d;
            d *= x;
        }
        return sum;
    }


    /**
     * remove the longest word
     * but why ?
     *
     */
    @Override
    public void a023() {
        // what gives ????
        String s = "s s  ss  ss     ss     ss    s";
        System.out.println(a023_impl(s));
        String s2 = "s s  ss  ss   ssss           ss     ss    s";
        System.out.println(a023_impl(s2));
    }


    public String a023_impl(String input) {
        String output = "";
        String [] words = input.split(" ");
        int max = 0;
        for(int i = 0; i < words.length; i++) {
            if(max < words[i].length()) {
                max = words[i].length();
            }
        }
        for(int i = 0;  i < words.length; i++) {
            if(words[i].length() != max && words[i].length() > 0) {
                output += words[i] + " ";
            }
        }
        return output;
    }


    /**
     * get the saddle point
     */
    @Override
    public void a022() {

        int [][] x = NumberService.generateMatrixIntegers(n,a,b);
        int [] points = a022_impl(x);
        NumberService.printMatrix(x);
        Arrays.stream(points).forEach(System.out::print);
        // note : not tested yet. Too lazy genereate a correct input
        // perhaps introduce a fuction to read input from file, as matrix . right  ?
        //whatever ;
    }

    private int[] a022_impl(int [][] x ) {
        // max in i , and min in j
        int resultI = 0;
        int resultJ = 0;
        for(int i = 0; i < x.length; i++) {
            int min = b+1;
            int indexJ = 0;
            for(int j = 0; j < x.length; j++) {
                //whatever
                 if (min > x[i][j]) {
                     min = x[i][j];
                     indexJ = j;
                 }
            }
            // we have the min in x[i][indexJ]
            // now check weather that is the max in the column
            int max = 0;
            int indexI = 0;
            for(int k = 0; k < x.length; k++) {
                if(x[k][indexJ] > max) {
                    max = x[k][indexJ];
                    indexI = k;
                }
            }
            if(max == min && (i == indexI || x[i][indexJ]==max)) {
                // return true
                resultI = indexI;
                resultJ = indexJ;
            }
        }
        return new int[]{resultI,resultJ};
    }


    @Override
    public void a021() {
        // read in ordered number arrays
        // then merge them
        // then make statistics
        // then create a set of them

        //read in from file, then process

        File file = null;
        BufferedReader bufferedReader = null;
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            file = new File("input/input21.txt");
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            sb = new StringBuffer();
            List<Integer> allNumbers = new ArrayList<>();
            List<Integer> stats = new ArrayList<>();
            while((line= bufferedReader.readLine())!=null  && !line.equals("")) {
                sb.append(line);
                sb.append("\n");
                String [] numbers = line.split(" ");
                List<Integer> asdf = new ArrayList<>();
                Arrays.stream(numbers).forEach(p-> asdf.add(Integer.parseInt(p)));
                allNumbers = NumberService.mergeLists(allNumbers,asdf);
            }
            System.out.println(allNumbers);
            Set<Integer> sett = new HashSet<>(allNumbers);
            for(Integer i : sett) {
                List<Integer> asd = allNumbers.stream().filter(p->p.equals(i)).collect(Collectors.toList());
                stats.add(asd.size());
            }
            System.out.println(sett);
            System.out.println(stats);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void a020() {
        // read in text, then sum up the numbers
        String slsl = "asfr";
        int number = a020_impl(slsl);
        System.out.println(number);

    }

    private int a020_impl(String slsl) {
        int result = 0;
        String[] letters = slsl.split("[^0-9]");
        for(int i = 0; i < letters.length; i++) {
            if(letters[i] != null && letters[i] != "" && !letters[i].equals("")) {
                result += Integer.parseInt(letters[i]);
            }
        }
        return result;
    }

    private void a019() {
        // decide whether a number is a Vale number or a mountain number...
        int n1 = 1234321;
        int n2 = 43212345;
        int n3 = 123123;

        int r1 = a019_impl(n1);
        int r2 = a019_impl(n2);
        int r3 = a019_impl(n3);
        System.out.println(n1 + " is " + r1);
        System.out.println(n2 + " is " + r2);


        System.out.println(n3 + " is " + r3);

    }

    private int a019_impl(int n) {
        int result = 0;
        boolean isVale = isValeNumber(n);
        boolean isMountain = isMountainNumber(n);
        if(isVale) {
            result = 1;
        }
        if(isMountain) {
            result = 2;
        }
        // if 0 then it is neither
        // create one... hehehehehehehh
        if(result == 0) {
            boolean isValeOrMountain = false;
            List<Integer> digits = NumberService.getDigits(n);
            while (!isValeOrMountain && !digits.isEmpty()) {
                digits.remove(digits.get(0));
                int cutNumber = NumberService.createNumber(digits);
                if (isValeNumber(cutNumber) || isMountainNumber(cutNumber)) {
                    isValeOrMountain = true;
                    if(isValeNumber(cutNumber))
                        result = 1;
                    else
                        result = 2;
                }
            }
            System.out.println("created from : " + n + " : " + NumberService.createNumber(digits) + " is " + result);
            result = 0;
        }

        return result;
    }

    private boolean isMountainNumber(int n) {
        List<Integer> digits = NumberService.getDigits(n);
        int i = 0;
        boolean isOrdered = true;
        boolean isMountain = false;

        while (i < digits.size()-1 && isOrdered) {
            if(digits.get(i) > digits.get(i+1))
                isOrdered = false;
            i++;
        }

        if(!isOrdered) {
            while( i < digits.size() - 1) {
                if(digits.get(i) <digits.get(i+1))
                    isOrdered = true;
                i++;
            }
            if(!isOrdered) {
                isMountain = true;
            }
        }
        else {
            isMountain = false;
        }

        return isMountain;
    }

    private boolean isValeNumber(int n) {
        List<Integer> digits = NumberService.getDigits(n);
        int i = 0;
        boolean isOrdered = true;
        boolean isVale = false;

        while (i < digits.size()-1 && isOrdered) {
            if(digits.get(i) < digits.get(i+1))
                isOrdered = false;
            i++;
        }

        if(!isOrdered) {
            while( i < digits.size() - 1) {
                if(digits.get(i) > digits.get(i+1))
                    isOrdered = true;
                i++;
            }
            if(!isOrdered) {
                isVale = true;
            }
        }
        else {
            isVale = false;
        }

        return isVale;
    }


    private void a018() {
        // do a Niven numbers...
        // digits sum should divide the actual number, even in different systems
        //read from console while there is input ?
        boolean read = true;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while(read) {
            System.out.println("Gimme a pair of numbers : system , number");
            String[] input = new String[0];
            try {
                input = bufferRead.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(input.length == 2) {
                int system = Integer.parseInt(input[0]);
                int number = Integer.parseInt(input[1]);
                boolean status = a018_impl(system, number);
                System.out.println("The number : " + number + " is Niven ? " + status);
            }
            else {
                read = false;
                System.out.println("The end is near.");
            }
        }
        System.out.println("This is the end");
    }


    private boolean a018_impl(int system, int number) {
        boolean result = false;
        int digitSum = NumberService.getSumOfDigits(number);
        int numberInDecim = NumberService.convertToDecimalSystem(system,number);
        if (numberInDecim % digitSum == 0) {
            result = true;
        }
        return result;
    }


    private void a017() {
        //decide if a number is palindrome..
        // then find the first magic plaindromes/ which can be made palindrome by adding the opposite of htem
        int i1 = 10;
        int i2 = 10000;
        int nn = 12;
        int[] r2 = a017_impl(i1, i2,nn);
        NumberService.printArray(r2);
    }


    private int[] a017_impl(int i1, int i2, int nn) {
        int[] result  = new int[10000];
        int index = 0;
        for(int i = i1; i <= i2; i++) {
            int number = i;
            int counter = 0;
            while(NumberService.isPalindrom(number) && counter++ < nn) {
                number += NumberService.getOppositlyOrderedNumber(number);
            }
            if(counter < 15) {
                result[index++] = i;
            }
        }
        int [] numbers = new int[index];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = result[i];
        }
        return numbers;
    }


    private void a016() {
        // write something that would splitorder something
        // szet rendez.. hehe
        int [] x = NumberService.generateIntegers(n,a,b);
        int [] y = a016_impl(x);
        NumberService.printArray(y);

    }

    private int[] a016_impl(int[] x) {
        int [] hehe = new int[x.length];
        for(int i = 0; i < x.length-1; i++ ) {
            for(int j = i+1; j < x.length; j++) {
                if(x[i] > x[j]) {
                    int t = x[i];
                    x[i] = x[j];
                    x[j] = t;
                }
            }
        }
        for(int i = 0; i < x.length; i++) {
            if(i % 2 == 0) {
                hehe[i] = x[i];
            }
            else {
                hehe[x.length - i] = x[i];
            }
        }

        return hehe;
    }


    private void a015() {
        // translate numbers into text.. thx .. bb
        String text = "We are number 1!";
        a015_impl(text);
    }

    private void a015_impl(String text) {
        String result = "";
        String [] numbers = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for(int i = 0; i < text.length(); i++) {
            int s = 0;
            try {
                s = Integer.parseInt(text.substring(i, i+1));
                result += numbers[s];
            }
            catch (Exception e) {
                String normal = text.substring(i,i+1);
                result += normal;
            }

        }
        System.out.println(result);
    }

    private void a014() {
        // what you have always known, just hidden it away
        // say it. they are dead
        // there are  x ,y, and find all fibos, then do the biggest square matrix that there is ..
        int x = 10;
        int y = 1000;
        a014_impl(x,y);


    }

    private void a014_impl(int x, int y) {
        // what else can LoL offer for me ?  not much excitment to be sure.. but what else  ?
        int j = 2;
        boolean weCool = false;
        int[][] fibos = new int[n][n];
        while (j <= 10 && !weCool) {
             fibos = new int[j][j];
            fibos = a013_impl(j);
            int max  = fibos[j-1][j-1];
            if (max > y) {
                weCool = true;
            }
            j++;
        }
        j -= 2;
        fibos = a013_impl(j);
        if(weCool) {
            for(int i = 0; i < j; i++) {
                for(int k = 0; k < j; k++) {
                    if(fibos[i][k] < x) {
                        fibos[i][k] = 0;
                    }
                }
            }
        }
        NumberService.printMatrix(fibos);
    }

    private void a013() {
        // do fibonacci
        int [][] fibo = a013_impl(n);
        NumberService.printMatrix(fibo);
    }


    private int[][] a013_impl(int n) {
        int[][] fibo = new int[n][n];
        int f1 = 0;
        int f2 = 1;
        int f3 = f1 + f2;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++){
            f1 = f2;
            f2 = f3;
            f3 = f1 + f2;
            fibo[i][j] = f1;
            }
        }
        return fibo;
    }


    private void a012() {
        //  order columns based on their sum
        int [][] x = NumberService.generateMatrixIntegers(n,a,b);
        NumberService.printMatrix(x);

        int[][] y = a012_impl(x);
        NumberService.printMatrix(y);
    }


    private int[][] a012_impl(int[][] x) {
        //int[][] y = new int[x.length][x.length];
        int [] sums = new int[x.length];

        for(int i = 0; i < x.length; i++) {
            sums[i] = NumberService.matrixSumOfColumn(x,i);
        }
        for(int i = 0; i < x.length - 1; i++) {
            for(int j = i+1; j < x.length; j++) {
                if( sums[i] < sums[j]) {
                    int t = sums[i];
                    sums[i] = sums[j];
                    sums[j] = t;
                    x = NumberService.matrixSwapColumns(x,i,j);
                }
            }
        }

        return x;
    }


    private void a011() {
        //given matrix, get the most used number
        int [][] x = NumberService.generateMatrixIntegers(n,n*2,a,b);
        int highStat = a011_impl(x);
        System.out.println(highStat + " was the most popular element. Nice");

    }


    private int a011_impl(int[][] x) {
        int number = 0;
        int nCounter = 0;
        Map<Integer, Integer> stats = new HashMap<>();
        for(int i = 0; i < x.length; i++) {
            for(int j = 0 ; j < x[0].length; j++) {
                int counter = 1;
                if(stats.containsKey(x[i][j])) {
                    counter = stats.get(x[i][j]);
                    counter++;
                }
                stats.put(x[i][j], counter);
            }
        }
        for(Map.Entry e : stats.entrySet()) {
            if((Integer)e.getValue() > nCounter) {
                nCounter = (Integer)e.getValue();
                number = (Integer)e.getKey();
            }
        }
        return number;
    }

    private void a010() {
        // find the max element, from under the second diagonal
        int x[][] = NumberService.generateMatrixIntegers(n,a,b);
        int max = a010_impl(x);
        NumberService.printMatrix(x);
        System.out.println(max);
    }

    private int a010_impl(int[][] x) {
        int max = 0;

        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < i + 1; j++) {
                int e = x[i][n-j-1];
                if(e > max) {
                    max = e;
                }
            }
        }

        return max;
    }

    private void a009() {
        // not making much sense, but yeah, it is still good
        int [][] x = NumberService.generateMatrixIntegers(2,n,a,b);
        int [][]y = a009_impl(x);
        NumberService.printMatrix(y);
    }

    private int[][] a009_impl(int[][]x) {
        int[][] y = new int[x.length][x[0].length];
        for(int k = 0; k < x.length; k++) {
            for (int i = 0; i < x[0].length; i++) {
                //find min
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < x.length; j++) {
                    if (min > x[j][i]) {
                        min = x[j][i];
                    }
                }
                //now subtract from every column
                for (int j = 0; j < x[0].length; j++) {
                    y[k][j] = x[k][j] - min;
                }
            }
        }
        return y;
    }


    private void a008() {
        // print a matrix as a snake.. oh man
        int[][] x = NumberService.generateMatrixOderedIntegers(n,a,b);
        int [] y = a008_impl(x);
    }

    private int[] a008_impl(int[][] x) {
        int [] snake = new int[n*n+10];
        int counter = 0;
        int currentLength = n / 2;
        int [][] copy = new int[n][n];
        while(currentLength > 0) {

            // do a round..  //nice go down
            for(int i = (n/2-currentLength); i < n - n/2+currentLength-1; i++) {
                int c =  n/2-currentLength;
                snake[counter++]       = x[i][c];
                copy[i][c] = x[i][c];
            }


            // go right;
            for(int i = (n/2-currentLength); i < n - n/2+currentLength-1; i++) {
                int c = n - n/2+currentLength-1;
                snake[counter++]           = x[c][i];
                copy[c][i] = x[c][i];
            }


            // go up
            for(int i = (n/2-currentLength); i < n - n/2+currentLength-1; i++) {
                int c = n - n/2+currentLength-1;
                snake[counter++]           = x[n-i-1][c];
                copy[n-i-1][c] = x[n-i-1][c];
            }

            // go left

            for(int i = (n/2-currentLength); i < n - n/2+currentLength-1; i++) {
                int c =  n/2-currentLength;
                snake[counter++]           = x[c][n-i-1];
                copy[c][n-i-1] = x[c][n-1-i];
            }
            currentLength--;

        }
        if(n % 2 == 1) {
            int center = n/2;
            snake[counter++] = x[center][center];
            copy[center][center] = x[center][center];
        }
        NumberService.printMatrix(copy);
        NumberService.printArray(snake);
        return snake;
    }


    private void a007() {
        // whatnot
        // find items where x[i][j] mod i+j = 0
        int[][] x = NumberService.generateMatrixIntegers(n,a,b);
        a007_impls(x);
    }


    private void a007_impls(int[][] x) {
        List<Integer> numbers = new LinkedList<>();
        List<Integer> indexx = new LinkedList<>();
        List<Integer> indexy = new LinkedList<>();

        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[i].length; j++) {
                if((i+j != 0) && x[i][j] % (i + j) == 0) {
                    numbers.add(x[i][j]);
                    indexx.add(i);
                    indexy.add(j);
                }
            }
        }
        for(int i = 0; i < numbers.size(); i++ ) {
            System.out.println("Item " + numbers.get(i) + " on i:" + indexx.get(i) + " j:" + indexy.get(i) + " are special");
        }
    }


    private void a006() {
    // this is not a game //
        // same as 005, but now we will have items without prices
        int n = 5;
        int m = 100;
        int[][] data = NumberService.generateMatrixIntegers(n,m,a,b);
        int mistakes = 10;
        int [] x = NumberService.generateIntegers(mistakes, 0,n);
        int [] y = NumberService.generateIntegers(mistakes, 0,m);
        for(int i = 0; i < x.length; i++) {
            data[x[i]][y[i]] = 0;
        }
        NumberService.printMatrix(data);
        // get the store with the most 0 -s
        int st = a006_a(data);
        System.out.println("The store with the most errors: " + st);
        // get an item's avg price
        int item = NumberService.generateIntegers(10,1,m)[5];
        int avgPrice = a006_b(data,item);
        System.out.println(item + " item's avg price is : " + avgPrice);

    }

    private int a006_b(int[][] data, int item) {
        int result = 0;
        int sum = 0;
        int mistakes = 0;
        for(int i = 0; i < data.length;i++) {
            if(data[i][item] != 0) {
               sum = data[i][item];
            }
            else {
                mistakes ++;
            }
        }
        result = sum / (data.length-mistakes);
        return result;
    }

    private int a006_a(int[][] data) {
        int rubbishStore = 0;
        int counter = 0;
        int max = -1;
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length;j++) {
                if(data[i][j] == 0) {
                    counter++;
                }
            }
            if(counter > max) {
                max = counter;
                rubbishStore = i;
            }
        }
        return rubbishStore;
    }

    private void a005() {
        int n = 10;
        int m = 5;
        int[][] data = NumberService.generateMatrixIntegers(n,m,a,b);
        // have m stores, and n items
        NumberService.printMatrix(data);
        // get the store with the highest item average
        int highness = a005_a(data);
        System.out.println("Most expensive store : " + highness);

        // get the cheapest item
        int lowless = a005_b(data);
        System.out.println("The cheapest Item : " + lowless);

        // given item, get where it is sold  for the most
        int item = NumberService.generateIntegers(10,1,m)[4];

        int store =  a005_c(data,item);
        System.out.println(item + "is being sold for the most at " + store + " for at the price of : " + data[store][item]);

        // given store's avg prices
        store = NumberService.generateIntegers(10,1,n)[5];
        int avgPrice = a005_d(data,store);

        System.out.println("the avg price of items for store " + store + " is : " + avgPrice);


    }

    private int a005_d(int[][] data, int store) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < data[store].length; i++) {
            sum += data[store][i];
        }
        result = (int)Math.floor(sum/data.length);
        return result;
    }

    private int a005_c(int[][] data, int item) {
        // get the biggest value in column item
        // return row
        int result = 0;
        int max = -1;
        for(int i = 0; i < data.length;i++) {
            if(data[i][item] > max) {
                max = data[i][item];
                result = i;
            }
        }
        return result;
    }

    private int a005_b(int[][] data) {
        // gets the lowest item's index (column)
        int result = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < data.length;i++) {
            for(int j= 0; j < data[i].length;j++) {
                if(min > data[i][j]) {
                    min = data[i][j];
                    result = j;
                }
            }
        }

        return result;
    }

    private int a005_a(int[][] data) {
        //get the highest avarage of the rows
        int result = 0;
        int max = -1;

        for(int i = 0; i < data.length; i++) {
            int sum = 0;
            for(int j = 0; j < data[i].length; j++) {
                sum += data[i][j];
            }
            if(sum > max) {
                max = sum;
                result = i;
            }
        }

        return result;
    }

    private void a004() {
        // add n matrixes

        int[][] m1 = NumberService.generateMatrixIntegers(n,a,b);
        int[][] m2 = NumberService.generateMatrixIntegers(n,a,b);
        int[][] m3 = NumberService.generateMatrixIntegers(n,a,b);
         
        int[][] m = new int[n][n];

        m = NumberService.addMatrix(m,m1);
        m = NumberService.addMatrix(m,m2);
        m = NumberService.addMatrix(m,m3);

        NumberService.printMatrix(m);

    }


    public void a003() {
        // given N matrix, rotate it, thx
        int n = 10;
        int[][] x = new int[n][];
        for (int i = 0; i < n; i++) {
            x[i] = NumberService.generateIntegers(n, 1, 100);
        }
        NumberService.printMatrix(x);
        System.out.println();
        int[][] y = a003_impl(x);
        NumberService.printMatrix(y);

    }


    private int[][] a003_impl(int[][] x) {
        //rotates matrix
        int [][] result = new int[x.length][x.length];

        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[i].length; j++) {
                result[j][x.length-i -1] =  x[i][j];
            }
        }

        return result;
    }


    public void a002() {
        int n = 10;
        int [] x = NumberService.generateIntegers(n,1,100);
        a002_impl(x);
    }


    private void a002_impl(int[] x) {
        for(int i = 0; i < x.length; i++) {
            int sum = 0;
            for(int j = 0; j < x.length; j++) {
                if( j != i) {
                    sum +=x[j];
                }
            }
            x[i] = sum/(x.length - 1);
        }
        Arrays.stream(x).forEach(System.out::println);
    }


    public  void a001() {
        // given X array
        // get the average of the numbers between a,b
        // the rest should be put into another array
        int n = 10;
        double[] x = new double[n];
        for(int i = 0; i < n; i++) {
            x[i] = Math.random() * 100;
        }
        int a = 10;
        int b = 50;
        a001_impl(x,a,b);
    }

    private void a001_impl(double[] x, int a, int b) {
        double sum = 0;
        List<Double> y = new LinkedList<>();
        for(int i = 0; i < x.length; i++) {
            if(x[i] >= a && x[i] <= b) {
                sum+=x[i];
            }
            else {
                y.add(x[i]);
            }
        }
        double avg = sum / (x.length - y.size());
        System.out.println("Avg of numbers is : " + avg);
        System.out.println(" THe rest of the numbers are : " );
        System.out.println(y);
    }

}

