package main;


import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Algorithmic assignment, no 7 .
 */
public class Lab7  extends Lab {

    int n = 8;
    boolean[] diagonal = new boolean[2 * n - 1];
    boolean[] sideDiagonal = new boolean[2 * n - 1];
    boolean[] column = new boolean[n];
    private final List<List<Integer>> queenResults = new LinkedList<>();
    private long queenSolutionCount = 0;

    private enum colors  {red, green, blue};


    /**
     * Take a skier skiing though the given course.
     * Calculate all the possible routes that can be taken.
     * Map looks like a map.
     */
    @Override
    public void a008() {
        // defend , defend, then strike
        int n = 10;
        int a = 70;
        int b = 100;
        int[][] matrix = NumberService.generateMatrixIntegers(n,a,b);
        a008_impl(matrix);
    }

    private void a008_impl(int[][] matrix) {
        LinkedList<LinkedList<Integer>> track = new LinkedList<>();
        findTracks(matrix, track);
    }


    private void findTracks(int[][] matrix, LinkedList<LinkedList<Integer>> track) {
        if (isTrackAtTheEnd(track,matrix)) {

        } else {
            if (currentTrackIsCorrect(track,matrix)) {
                int[][] nextStepPossiblities = generateNextSteps(track.getLast());
                for (int[] step : nextStepPossiblities) {
                    LinkedList<Integer> stepOneList = new LinkedList<>();
                    stepOneList.add(step[0]);
                    stepOneList.add(step[1]);
                    track.add(stepOneList);
                    findTracks(matrix, track);
                    track.removeLast();
                }
            }
        }
    }

    private int[][] generateNextSteps(LinkedList<Integer> last) {
        int [][] result  = new int[3][2];
        int x = last.getFirst();
        int y = last.getLast();
        result[0][0] = x+1;
        result[0][1] = y-1;
        result[1][0] = x+1;
        result[1][1] = y;
        result[2][0] = x+1;
        result[2][1] = y+1;
        return result;
    }

    private boolean currentTrackIsCorrect(LinkedList<LinkedList<Integer>> track, int[][] matrix) {
        boolean result = false;
        LinkedList<Integer> step = track.getLast();
        if(step.getFirst() >= 0 && step.getFirst() < matrix.length && step.getLast() >= 0 && step.getLast() < matrix.length) {
            result = true;
        }
        return result;
    }

    private boolean isTrackAtTheEnd(LinkedList<LinkedList<Integer>> track, int[][] matrix) {
        boolean result = false;
        LinkedList<Integer> lastStep = track.getLast();
        if(lastStep.getFirst() == matrix.length) {
            result = true;
        }
        return result;
    }


    /**
     * There is a picture encoded with 0,1 in a matrix.
     * Create a program that can answer if on the picture:
     *     there is one object
     *     there are more than one objects
     * For one element there are eight neighbours.
     */
    @Override
    public void a007() {
        int n = 100;
        int[][] picture = NumberService.generateMatrixIntegers(n,0,1);
        a007_impl(picture);
    }

    private void a007_impl(int[][] data) {
         // find a starting point
        boolean found = false;
        int row = 0;
        int initCol = 0;
        while(!found && row < data.length) {
            int column = 0;
            while(column < data.length  && !found) {
                if(data[row][column] == 1) {
                    found = true;
                    initCol = column;
                }
                column ++;
            }
            row++;
        }
        int result = 0;

        if (!found) {
            result = 2;
        }
        else {
            int initRow = row - 1;
            boolean[][] mapOfWalk = new boolean[data.length][data.length];
            exploreMap(data, initRow, initCol, mapOfWalk);
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    if (data[i][j] == 1 && !mapOfWalk[i][j]) {
                        // we have multiple objects
                        result = 1;
                    }
                }
            }
        }
        if(result == 0) {
            System.out.println("1 object");
        }
        else {
            if(result == 1) {
                System.out.println("Multiple objects.");
            }
            else {
                System.out.println("no objects.");
            }
        }




    }

    private void exploreMap(int[][] matrix, int row, int col, boolean[][] mapOfWalk) {
        if(row >=0  && row < matrix.length && col >=0 && col < matrix.length) {
            if (matrix[row][col] == 1 && !mapOfWalk[row][col]) {
                mapOfWalk[row][col] = true;
                exploreMap(matrix,row+1,col,mapOfWalk);
                exploreMap(matrix,row+1,col+1,mapOfWalk);
                exploreMap(matrix,row,col+1,mapOfWalk);
                exploreMap(matrix,row-1,col+1,mapOfWalk);
                exploreMap(matrix,row-1,col,mapOfWalk);
                exploreMap(matrix,row-1,col-1,mapOfWalk);
                exploreMap(matrix,row,col-1,mapOfWalk);
                exploreMap(matrix,row+1,col-1,mapOfWalk);
            }
        }

    }


    /**
     * N cubes are available. marked with length and color.
     * Calculate the maximum length constructable
     * while
     *      using the same colors,
     *      and the cubes can only be placed in descending order with, length onto each other
     */
    @Override
    public void a006() {
        int cubeCount = 15;
        Map<colors, List<Integer>> cubeProperties = new HashMap<>();
        int[] cubeSizes = NumberService.generateIntegers(cubeCount,1,100);
        System.out.print("Cube sizes : ");
        for(int i : cubeSizes) {
            System.out.print(i + ", ");
        }
        System.out.println();
        divideColors(cubeProperties,cubeSizes);
        a006_impl(cubeProperties,cubeSizes);

    }

    private void a006_impl(Map<colors, List<Integer>> cubeProperties, int[] cubeSizes) {
        List<Integer> currentTower = new LinkedList<>();
        List<Integer> maxTower = new LinkedList<>();
        for (Map.Entry<colors, List<Integer>> fck : cubeProperties.entrySet()) {
            buildLongestCubes(fck.getKey(), fck.getValue(), cubeSizes, currentTower, maxTower);
            System.out.print(fck.getKey());
            System.out.print(" ");
            System.out.println(maxTower);
            System.out.println("Length = " + getSumOfCubes(cubeSizes,maxTower));
            maxTower.clear();
        }
    }

    /**
     * Create the longest possible tower
     *
     */
    private void buildLongestCubes(colors color, List<Integer> numbers, int[] cubeSizes, List<Integer> currentTower,List<Integer> maxTower) {
        LinkedList<Integer> linkedCurrentList = (LinkedList)currentTower;
        if(numbers.size() == 0) {
            int maxLength = getSumOfCubes(cubeSizes,maxTower);
            int currentLength = getSumOfCubes(cubeSizes,currentTower);
            if(maxLength < currentLength) {
                maxTower.clear();
                maxTower.addAll(currentTower);
            }
        }
        else {
            for(int i = 0; i < numbers.size(); i++) {
                Integer x = numbers.get(i);

                if(fitsTower(linkedCurrentList,x,cubeSizes)) {
                    numbers.remove(i);
                    linkedCurrentList.addLast(x);

                    buildLongestCubes(color,numbers, cubeSizes, linkedCurrentList,maxTower);

                    linkedCurrentList.removeLast();
                    numbers.add(i,x);
                }
            }
        }
    }

    private int getSumOfCubes(int[] cubes, List<Integer> tower) {
        int result = 0;
        for(Integer c : tower) {
            result += cubes[c];
        }
        return result;
    }



    /**
     * N cubes are available. marked with length and color.
     * Generate all towers with k length, where cube lengths are in descending order, and without mixing colors
     */
    @Override
    public void a005() {
        int cubeCount = 15;
        Map<colors, List<Integer>> cubeProperties = new HashMap<>();
        int[] cubeSizes = NumberService.generateIntegers(cubeCount,1,100);
        System.out.print("Cube sizes : ");
        for(int i : cubeSizes) {
            System.out.print(i + ", ");
        }
        System.out.println();
        divideColors(cubeProperties,cubeSizes);
        int k = 3;
        a005_impl(cubeProperties,cubeSizes, k);
    }

    private void a005_impl(Map<colors, List<Integer>> cubeProperties, int[] cubeSizes, int threshold) {
        List<Integer> currentTower = new LinkedList<>();
        for(Map.Entry<colors,List<Integer>> fck : cubeProperties.entrySet()) {
            buildCubes(fck.getKey(),fck.getValue(),cubeSizes,threshold,currentTower);
        }
    }

    private void buildCubes(colors color, List<Integer> numbers, int[] cubeSizes, int threshold, List<Integer> currentTower) {
        LinkedList<Integer> linkedCurrentList = (LinkedList)currentTower;
        if(linkedCurrentList.size() == threshold) {
            //halt
            System.out.print(color);
            System.out.println(linkedCurrentList);
//            System.out.print(" ");
//            for(Integer x : currentTower) {
//                System.out.print(cubeSizes[x] + ", ");
//            }
//            System.out.println();
        }
        else {
            for(int i = 0; i < numbers.size(); i++) {
                Integer x = numbers.get(i);
                
                if(fitsTower(linkedCurrentList,x,cubeSizes)) {
                    numbers.remove(i);
                    linkedCurrentList.addLast(x);

                    buildCubes(color,numbers, cubeSizes, threshold, linkedCurrentList);

                    linkedCurrentList.removeLast();
                    numbers.add(i,x);
                }
            }
        }
    }

    private boolean fitsTower(List<Integer> currentTower, Integer newPiece, int[] cubeSizes) {
        boolean result = true;
        if(currentTower.size() > 0) {
            Integer topOfTower = (Integer) ((LinkedList)currentTower).getLast();
            Integer topOfTowerValue = cubeSizes[topOfTower];
            Integer newPieceValue = cubeSizes[newPiece];
            if(cubeSizes[topOfTower] < cubeSizes[newPiece]) {
                result = false;
            }
        }
        return result;
    }

    private void divideColors(Map<colors, List<Integer>> colorMap, int[] cubes) {
        for(int i = 0; i < cubes.length; i+= colors.values().length) {
            int j = 0;
            for(colors c : colors.values()) {
                List<Integer> length = colorMap.get(c);
                if(length == null) {
                    length = new LinkedList<>();
                    colorMap.put(c,length);
                }
                length.add(i + j);
                j++;
            }
        }
        int remainder = cubes.length % colors.values().length;
        if(remainder != 0) {
            List<Integer> leftovers = new LinkedList<>();
            for(int i = 0; i < remainder; i++) {
                leftovers.add(cubes.length-1-i);
            }
            colorMap.get(colors.values()[0]).addAll(leftovers);
        }
    }

    /**
     * Find a series of operations which applied to a set of numbers will give K
     */
    @Override
    public void a004() {
        List<String> operators = new LinkedList<>();
        operators.add("/");
        operators.add("+");
        operators.add("-");
        operators.add("*");
        List<Integer> numbers = new LinkedList<>();
        numbers.add(12);
        numbers.add(12);
        numbers.add(1);
        numbers.add(1);
        int k = 24;
        List<String> currentOperators = new LinkedList<>();
        System.out.println(numbers);
        a004_impl(operators,numbers,k,currentOperators);
    }

    private void a004_impl(List<String> operatorPool, List<Integer> numberArray, int  limit, List<String> currentOperators) {
        if (currentOperators.size() + 1 == numberArray.size()) {
            // print solution
            if(limit == evaluateExpression(currentOperators, numberArray)) {
                System.out.println(currentOperators);
            }
        } else {
            for (String mark : operatorPool) {
                currentOperators.add(mark);
                a004_impl(operatorPool, numberArray, limit, currentOperators);
                currentOperators.remove(currentOperators.size()-1);
            }
        }
    }

    private int evaluateExpression(List<String> currentOperators, List<Integer> numberArray) {
        List<Integer> copyArray = new LinkedList<>();
        copyArray.addAll(numberArray);

        int sum  = copyArray.get(0);
        copyArray.remove(0);
        // this is cool
        for(String operator : currentOperators) {
            Integer x1 = copyArray.get(0);
            copyArray.remove(0);
            switch (operator) {
                case "*": {
                    sum *= x1;
                    break;
                }
                case "+": {
                    sum += x1;
                    break;
                }
                case "-": {
                    sum -= x1;
                    break;
                }
                case "/": {
                    if (x1 != 0
                            && sum % x1 == 0)
                        sum /= x1;
                    else {
                        // if cannot be divided cleanly, give error...  make sure the value would not fit
                        sum = Integer.MAX_VALUE;
                    }
                    break;
                }
                default: {
                    System.out.println("Operator not regonizable: " + operator);
                }
            }
        }
        return sum;
    }


    /**
     * Have M set of numbers.
     * Print those  sets which are equal to S
     */
    @Override
    public void a003() {
        int s = 10;
        int [] numbers = NumberService.generateIntegers(10,1,12);
        a003_impl(s,numbers);
    }

    private void a003_impl(int s, int[] numbers) {
        List<Integer> subSet = new LinkedList<>();
        List<Integer> numberSet = new LinkedList<>();
        Arrays.stream(numbers).forEach(numberSet::add);
        numberSet = NumberService.toSet(numberSet);
        numberSet.sort(Comparator.naturalOrder());
        System.out.println(numberSet);
        trySubset(subSet, numberSet,s);
    }

    private void trySubset(List<Integer> subSet, List<Integer> numberSet, int s) {
        if (NumberService.getSumOfList(subSet) == s) {
            System.out.println(subSet);
        } else {
            int c = numberSet.size();
            for(int i = 0; i < c; i++) {
                Integer x = numberSet.get(i);
                if(subSet.isEmpty() || subSet.get(subSet.size() - 1) < x) {
                    numberSet.remove(i);
                    subSet.add(x);
                    trySubset(subSet, numberSet, s);
                    numberSet.add(i, x);
                    subSet.remove(x);
                }
            }
        }
    }


    /**
     * Write a program to print every permutation of 1,2,...n
     */
    @Override
    void a001() {
        int n = 4;
        a001_impl(n);
    }

    private void a001_impl(int n) {
        perm(n, 1, 1, new LinkedList<>());
    }

    /**
     * Permutations
     */
    private void perm(int n, int i, int x, List<Integer> l) {
        if (n > i) {
            for (int index = 1; index <= n; index++) {
                l.add(index);
                perm(n, i + 1, index, l);
                l.remove(l.size() - 1);
            }
        } else {
            if (i == n) {
                System.out.println(l);
            }
        }
    }

    @Override
    void a002() {
        //  int n = 4;
        a002_impl();
    }

    /**
     *
     */

    private void a002_impl() {
        tryPlacing(0);
        System.out.println("Number of solutions found : " + queenSolutionCount);
    }

    private void tryPlacing(int row) {
        if (row == n) {
            printQueens();
        } else {
            for (int i = 0; i < n; i++) {
                if (canPlaceQueen(row, i)) {
                    putDownQueen(row, i);
                    tryPlacing(row + 1);
                    removeQueen(row, i);
                }
            }
        }
    }

    private void putDownQueen(int row, int col) {
        column[col] = true;
        diagonal[row + col] = true;
        sideDiagonal[-row + n + col - 1] = true;
        LinkedList<Integer> currentResult = new LinkedList<>();
        currentResult.add(row);
        currentResult.add(col);
        queenResults.add(currentResult);
    }

    private void removeQueen(int row, int col) {
        column[col] = false;
        diagonal[row + col] = false;
        sideDiagonal[-row + n + col - 1] = false;
        if (queenResults.size() > 0) {
            queenResults.remove(queenResults.get(queenResults.size() - 1));
        }

    }

    private int chooseColumn() {
        int index = 0;

        return index;
    }

    private boolean canPlaceQueen(int row, int col) {
        boolean result = false;
        int di = col + row;
        int si = n - row + col - 1;
        int ci = col;

        if (!diagonal[di] && !sideDiagonal[si] && !column[ci]) {
            result = true;
        }
        return result;
    }

    /**
     * Print result of queen placing based on the three vectors
     */
    private void printQueens() {
        queenSolutionCount++;
//        System.out.println(queenResults);
        int[][] fck = new int[n][n];
        for (List<Integer> step : queenResults) {
            int x = step.get(0);
            int y = step.get(1);
            fck[x][y] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(fck[i][j]);
            }
            System.out.println();
        }


        System.out.println("Next solution");
    }
}
