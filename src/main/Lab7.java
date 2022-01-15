package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    private enum colors {red, green, blue};

    /**
     *  There are several prizes.
     *  There are three tiers of winners. (1, 2, 3)
     *  For one tier each, winners get the same value (numbers of rewards can vary)
     *  lower tier gets less value
     */
    @Override
    public void a011() {
        int x = 2;
        int y = 2;
        int z = 1;
        int[] prizes = new int[]{5, 8, 3, 7, 12, 1, 9};
        a011_impl(x, y, z, prizes);
    }

    private void a011_impl(int tier1, int tier2, int tier3, int[] prizes) {
        // prepare data
        LinkedList<LinkedList<Integer>> variations = new LinkedList<>();
        LinkedList<Integer> prizeList = new LinkedList<>();
        int[] tierCounts = new int[3];
        tierCounts[0] = tier1;
        tierCounts[1] = tier2;
        tierCounts[2] = tier3;

        for (int x : prizes) {
            prizeList.add(x);
        }
        prizeList.sort(Comparator.reverseOrder());

        // do calculation
        variations = distributeRewards(prizeList ,tierCounts);

        // do response to user
        if(variations != null) {
            System.out.println(variations);
        }
        else
        {
            System.out.println("No feasible solutions available.");
        }
    }

    private LinkedList<LinkedList<Integer>> distributeRewards(LinkedList<Integer> prizeList,  int[] tiers) {
        // lower limit is the max value , but it is sorted
        int lowerLimit = prizeList.getFirst();
        int upperLimit = lowerLimit*2;
        int value = lowerLimit;// we start with the biggest number
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();

//        Map<Integer, List<Integer>> mappedResult = new HashMap<>();
//        mappedResult.put(tiers[0],new LinkedList<>());
//        mappedResult.put(tiers[1],new LinkedList<>());
//        mappedResult.put(tiers[2],new LinkedList<>());

        // we look for value numbers
        int count = tiers[0];
        LinkedList<Integer> prizeListBackup = new LinkedList<>(prizeList);

        // condition is that to have the number pool empty
        boolean condition = false;

        while( lowerLimit < upperLimit) {
            //try finding a value for the first tier

            LinkedList<Integer> aGoodTry = searchForNumbers(prizeList, lowerLimit, count);
            // try finding value for the second tier

            // if solution found print
            System.out.println(lowerLimit);
            System.out.println(aGoodTry);
            lowerLimit++;
        }
        return null;
    }

    // prizes
    // value to get
    // how many to find
    private LinkedList<Integer> searchForNumbers(LinkedList<Integer> prizeList, int searchedValue, int numberOfValue) {
        LinkedList<Integer> result = new LinkedList<>();
        while(numberOfValue > 0) {
           LinkedList<Integer> prizesOfValue = NumberService.findValueInList(prizeList,searchedValue);
           //if(NumberService.getSumOfList(prizesOfValue) == searchedValue) {
                numberOfValue --;
                if(prizesOfValue != null && prizesOfValue.size() > 0) {
                    result.addAll(prizesOfValue);
                }
            //}
        }
        return result;
    }



    /**
     * Chess table.
     * Horse and Runner
     * get the horse from X1 to X2
     * - without getting hit be the runner,
     * - (and) stepping on the same place more than once
     * Print first found solution. (Walk , journey);
     */
    @Override
    public void a010() {
        int tableSize = 8;
        int[] startingPoint = new int[]{0, 0};
        int[] destinationPoint = new int[]{tableSize - 1, tableSize - 1};
        int[] runnerLocation = new int[]{1, 3};
        a010_impl(tableSize, startingPoint, destinationPoint, runnerLocation);
    }

    private void a010_impl(int n, int[] startingPoint, int[] destinationPoint, int[] runnerLocation) {
        LinkedList<LinkedList<Integer>> walk = new LinkedList<>();
        boolean[][] tableGrid = new boolean[n][n];
        markRunnerInChessTable(tableGrid, runnerLocation);
        LinkedList<Integer> firstStep = new LinkedList<>();
        firstStep.add(startingPoint[0]);
        firstStep.add(startingPoint[1]);
        walk.add(firstStep);
        LinkedList<Boolean> isRunning = new LinkedList<>();
        isRunning.add(true);
        findWayTo(tableGrid, destinationPoint, walk, isRunning);
    }

    private void markRunnerInChessTable(boolean[][] x, int[] p) {
        int p1 = p[0];
        int p2 = p[1];
        if (p1 >= 0 && p1 < x.length && p2 >= 0 && p2 < x.length && !x[p1][p2]) {
            x[p1][p2] = true;
            int[] nextP = new int[]{-1, -1};
            markDirectionOnTable(x,addArrays(p,nextP), nextP);
            nextP = new int[]{1, -1};
            markDirectionOnTable(x, addArrays(p,nextP), nextP);
            nextP = new int[]{1, 1};
            markDirectionOnTable(x, addArrays(p,nextP), nextP);
            nextP = new int[]{-1,1};
            markDirectionOnTable(x, addArrays(p,nextP), nextP);
        }
    }

    private int[] addArrays(int[] x1, int[] x2) {
        int[] result = new int[x1.length];
        for(int i = 0; i < x1.length; i++) {
            result[i] = x1[i] + x2[i];
        }
        return result;
    }

    private void markDirectionOnTable(boolean[][] x, int[] p, int[] direction) {
        int p1 = p[0];
        int p2 = p[1];
        if (p1 >= 0 && p1 < x.length && p2 >= 0 && p2 < x.length && !x[p1][p2]) {
            x[p1][p2] = true;
            p[0] += direction[0];
            p[1] += direction[1];
            markDirectionOnTable(x,p,direction);
        }
    }

    private void findWayTo(boolean[][] tableGrid, int[] destinationPoint, LinkedList<LinkedList<Integer>> walk, LinkedList<Boolean> isRunning) {
        if (searchIsStillRunning(isRunning) && isLastStepInWalkCorrect(tableGrid, walk)) {
            if (arrivedAtDestination(destinationPoint, walk)) {
                //stop
                isRunning.clear();
                isRunning.add(false);
                System.out.println(walk);
            } else {
                markLastStepInWalk(tableGrid, walk);
                LinkedList<LinkedList<Integer>> nextStepPossibilites = createNextStepForWalk(walk.getLast());
                for (LinkedList<Integer> step : nextStepPossibilites) {
                    walk.addLast(step);
                    findWayTo(tableGrid, destinationPoint, walk, isRunning);
                    walk.removeLast();
                }
                markLastStepInWalk(tableGrid, walk);
            }
        }
    }


    private boolean searchIsStillRunning(LinkedList<Boolean> isRunning) {
        return isRunning.getLast();
    }


    private boolean arrivedAtDestination(int[] destinationPoint, LinkedList<LinkedList<Integer>> walk) {
        LinkedList<Integer> step = walk.getLast();
        boolean result = false;
        if (destinationPoint[0] == step.getFirst() && destinationPoint[1] == step.getLast()) {
            result = true;
        }
        return result;
    }


    /**
     * Chess table. Get all the possible ways for a horse to do a walk
     * Travel all the table
     * Cannot step to a place more than once
     */
    @Override
    public void a009() {
        int tableSize = 5;
        a009_impl(tableSize);
    }

    private void a009_impl(int n) {
        boolean[][] statusMatrix = new boolean[n][n];
        LinkedList<LinkedList<Integer>> walk = new LinkedList<>();
        LinkedList<Integer> firstStep = new LinkedList<>();
        firstStep.add(0);
        firstStep.addLast(0);
        walk.add(firstStep);
        // assume our walk start from the first square
        LinkedList<Integer> counter = new LinkedList<>();
        counter.add(0);
        doHorseWalk(statusMatrix, walk, counter);
        System.out.println(counter.getLast());

    }

    private void doHorseWalk(boolean[][] table, LinkedList<LinkedList<Integer>> currentWalk, LinkedList<Integer> counter) {
        if (remainingSpotsInWalk(table) == 1) {
            // print solution
            if (isLastStepInWalkCorrect(table, currentWalk)) {
                System.out.println(currentWalk);
                Integer c = counter.getLast();
                counter.removeLast();
                counter.add(c + 1);
            }
        } else {
            if (isLastStepInWalkCorrect(table, currentWalk)) {
                markLastStepInWalk(table, currentWalk);
                // pre next steps
                LinkedList<LinkedList<Integer>> nextStepPossibilities = createNextStepForWalk(currentWalk.getLast());
                for (LinkedList<Integer> step : nextStepPossibilities) {
                    currentWalk.addLast(step);
                    doHorseWalk(table, currentWalk, counter);
                    currentWalk.removeLast();
                }
                markLastStepInWalk(table, currentWalk);
            }
        }
    }

    private int remainingSpotsInWalk(boolean[][] table) {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j])
                    count++;
            }
        }
        return count;
    }

    private void markLastStepInWalk(boolean[][] table, LinkedList<LinkedList<Integer>> currentWalk) {
        LinkedList<Integer> nailed = currentWalk.getLast();
        table[nailed.getFirst()][nailed.getLast()] = !table[nailed.getFirst()][nailed.getLast()];
    }

    private LinkedList<LinkedList<Integer>> createNextStepForWalk(LinkedList<Integer> lastStep) {
        LinkedList<LinkedList<Integer>> steps = new LinkedList<>();
        int x = lastStep.getFirst();
        int y = lastStep.getLast();
        steps.add(newStep(x - 2, y - 1));
        steps.add(newStep(x - 1, y - 2));
        steps.add(newStep(x + 1, y - 2));
        steps.add(newStep(x + 2, y - 1));
        steps.add(newStep(x + 2, y + 1));
        steps.add(newStep(x + 1, y + 2));
        steps.add(newStep(x - 1, y + 2));
        steps.add(newStep(x - 2, y + 1));

        return steps;
    }

    private LinkedList<Integer> newStep(int x, int y) {
        LinkedList<Integer> step = new LinkedList<>();
        step.add(x);
        step.add(y);
        return step;
    }

    private boolean isLastStepInWalkCorrect(boolean[][] tableStatus, LinkedList<LinkedList<Integer>> currentWalk) {
        boolean result = false;
        LinkedList<Integer> step = currentWalk.getLast();
        int x = step.getFirst();
        int y = step.getLast();
        int n = tableStatus.length;
        if (x >= 0 && x < n && y >= 0 && y < n && !tableStatus[x][y]) {
            result = true;
        }
        return result;
    }

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
        // probably use predefined matrices.
        int[][] matrix = readMatrixFromTextFile("input/input78.txt", n);
        NumberService.printMatrix(matrix);
        a008_impl(matrix);
    }

    private int[][] readMatrixFromTextFile(String s, int n) {
        int[][] result = new int[n][n];
        BufferedReader myReader = null;
        try {
             myReader = new BufferedReader(new FileReader(s));
            String line = "";
            int lineCount = 0;
            while ((line = myReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    result[lineCount][i] = Integer.parseInt(tokens[i]);
                }
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(myReader!=null)
                    myReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void a008_impl(int[][] matrix) {
        LinkedList<LinkedList<Integer>> track = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            LinkedList<Integer> firstStep = new LinkedList<>();
            firstStep.addFirst(0);
            firstStep.addLast(i);
            track.add(firstStep);
            findTracks(matrix, track);
            track.clear();
        }
    }

    private void findTracks(int[][] matrix, LinkedList<LinkedList<Integer>> track) {
        if (isTrackAtTheEnd(track, matrix)) {
            System.out.println(track);
        } else {
            if (currentTrackIsCorrect(track, matrix)) {
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
        int[][] result = new int[3][2];
        int x = last.getFirst();
        int y = last.getLast();
        result[0][0] = x + 1;
        result[0][1] = y - 1;
        result[1][0] = x + 1;
        result[1][1] = y;
        result[2][0] = x + 1;
        result[2][1] = y + 1;
        return result;
    }

    private boolean currentTrackIsCorrect(LinkedList<LinkedList<Integer>> track, int[][] matrix) {
        boolean result = false;
        if (!track.isEmpty()) {
            LinkedList<Integer> lastStep = track.getLast();
            if (lastStep.getFirst() >= 0
                    && lastStep.getFirst() < matrix.length
                    && lastStep.getLast() >= 0
                    && lastStep.getLast() < matrix.length
            ) {
                if (track.size() > 1) {
                    LinkedList<Integer> prevoiusStep = track.get(track.size() - 2);
                    int lastStepValue = matrix[lastStep.getFirst()][lastStep.getLast()];
                    int previousStepValue = matrix[prevoiusStep.getFirst()][prevoiusStep.getLast()];
                    if (lastStepValue <= previousStepValue) {
                        result = true;
                    }
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean isTrackAtTheEnd(LinkedList<LinkedList<Integer>> track, int[][] matrix) {
        boolean result = false;
        if (track.size() > 0) {
            LinkedList<Integer> lastStep = track.getLast();
            if (lastStep.getFirst() == matrix.length - 1) {
                result = true;
            }
        }
        return result;
    }


    /**
     * There is a picture encoded with 0,1 in a matrix.
     * Create a program that can answer if on the picture:
     * there is one object
     * there are more than one objects
     * For one element there are eight neighbours.
     */
    @Override
    public void a007() {
        int n = 100;
        int[][] picture = NumberService.generateMatrixIntegers(n, 0, 1);
        a007_impl(picture);
    }

    private void a007_impl(int[][] data) {
        // find a starting point
        boolean found = false;
        int row = 0;
        int initCol = 0;
        while (!found && row < data.length) {
            int column = 0;
            while (column < data.length && !found) {
                if (data[row][column] == 1) {
                    found = true;
                    initCol = column;
                }
                column++;
            }
            row++;
        }
        int result = 0;

        if (!found) {
            result = 2;
        } else {
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
        if (result == 0) {
            System.out.println("1 object");
        } else {
            if (result == 1) {
                System.out.println("Multiple objects.");
            } else {
                System.out.println("no objects.");
            }
        }


    }

    private void exploreMap(int[][] matrix, int row, int col, boolean[][] mapOfWalk) {
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix.length) {
            if (matrix[row][col] == 1 && !mapOfWalk[row][col]) {
                mapOfWalk[row][col] = true;
                exploreMap(matrix, row + 1, col, mapOfWalk);
                exploreMap(matrix, row + 1, col + 1, mapOfWalk);
                exploreMap(matrix, row, col + 1, mapOfWalk);
                exploreMap(matrix, row - 1, col + 1, mapOfWalk);
                exploreMap(matrix, row - 1, col, mapOfWalk);
                exploreMap(matrix, row - 1, col - 1, mapOfWalk);
                exploreMap(matrix, row, col - 1, mapOfWalk);
                exploreMap(matrix, row + 1, col - 1, mapOfWalk);
            }
        }

    }


    /**
     * N cubes are available. marked with length and color.
     * Calculate the maximum length constructable
     * while
     * using the same colors,
     * and the cubes can only be placed in descending order with, length onto each other
     */
    @Override
    public void a006() {
        int cubeCount = 15;
        Map<colors, List<Integer>> cubeProperties = new HashMap<>();
        int[] cubeSizes = NumberService.generateIntegers(cubeCount, 1, 100);
        System.out.print("Cube sizes : ");
        for (int i : cubeSizes) {
            System.out.print(i + ", ");
        }
        System.out.println();
        divideColors(cubeProperties, cubeSizes);
        a006_impl(cubeProperties, cubeSizes);

    }

    private void a006_impl(Map<colors, List<Integer>> cubeProperties, int[] cubeSizes) {
        List<Integer> currentTower = new LinkedList<>();
        List<Integer> maxTower = new LinkedList<>();
        for (Map.Entry<colors, List<Integer>> fck : cubeProperties.entrySet()) {
            buildLongestCubes(fck.getKey(), fck.getValue(), cubeSizes, currentTower, maxTower);
            System.out.print(fck.getKey());
            System.out.print(" ");
            System.out.println(maxTower);
            System.out.println("Length = " + getSumOfCubes(cubeSizes, maxTower));
            maxTower.clear();
        }
    }

    /**
     * Create the longest possible tower
     */
    private void buildLongestCubes(colors color, List<Integer> numbers, int[] cubeSizes, List<Integer> currentTower, List<Integer> maxTower) {
        LinkedList<Integer> linkedCurrentList = (LinkedList) currentTower;
        if (numbers.size() == 0) {
            int maxLength = getSumOfCubes(cubeSizes, maxTower);
            int currentLength = getSumOfCubes(cubeSizes, currentTower);
            if (maxLength < currentLength) {
                maxTower.clear();
                maxTower.addAll(currentTower);
            }
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                Integer x = numbers.get(i);

                if (fitsTower(linkedCurrentList, x, cubeSizes)) {
                    numbers.remove(i);
                    linkedCurrentList.addLast(x);

                    buildLongestCubes(color, numbers, cubeSizes, linkedCurrentList, maxTower);

                    linkedCurrentList.removeLast();
                    numbers.add(i, x);
                }
            }
        }
    }

    private int getSumOfCubes(int[] cubes, List<Integer> tower) {
        int result = 0;
        for (Integer c : tower) {
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
        int[] cubeSizes = NumberService.generateIntegers(cubeCount, 1, 100);
        System.out.print("Cube sizes : ");
        for (int i : cubeSizes) {
            System.out.print(i + ", ");
        }
        System.out.println();
        divideColors(cubeProperties, cubeSizes);
        int k = 3;
        a005_impl(cubeProperties, cubeSizes, k);
    }

    private void a005_impl(Map<colors, List<Integer>> cubeProperties, int[] cubeSizes, int threshold) {
        List<Integer> currentTower = new LinkedList<>();
        for (Map.Entry<colors, List<Integer>> fck : cubeProperties.entrySet()) {
            buildCubes(fck.getKey(), fck.getValue(), cubeSizes, threshold, currentTower);
        }
    }

    private void buildCubes(colors color, List<Integer> numbers, int[] cubeSizes, int threshold, List<Integer> currentTower) {
        LinkedList<Integer> linkedCurrentList = (LinkedList) currentTower;
        if (linkedCurrentList.size() == threshold) {
            //halt
            System.out.print(color);
            System.out.println(linkedCurrentList);
//            System.out.print(" ");
//            for(Integer x : currentTower) {
//                System.out.print(cubeSizes[x] + ", ");
//            }
//            System.out.println();
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                Integer x = numbers.get(i);

                if (fitsTower(linkedCurrentList, x, cubeSizes)) {
                    numbers.remove(i);
                    linkedCurrentList.addLast(x);

                    buildCubes(color, numbers, cubeSizes, threshold, linkedCurrentList);

                    linkedCurrentList.removeLast();
                    numbers.add(i, x);
                }
            }
        }
    }

    private boolean fitsTower(List<Integer> currentTower, Integer newPiece, int[] cubeSizes) {
        boolean result = true;
        if (currentTower.size() > 0) {
            Integer topOfTower = (Integer) ((LinkedList) currentTower).getLast();
            Integer topOfTowerValue = cubeSizes[topOfTower];
            Integer newPieceValue = cubeSizes[newPiece];
            if (cubeSizes[topOfTower] < cubeSizes[newPiece]) {
                result = false;
            }
        }
        return result;
    }

    private void divideColors(Map<colors, List<Integer>> colorMap, int[] cubes) {
        for (int i = 0; i < cubes.length; i += colors.values().length) {
            int j = 0;
            for (colors c : colors.values()) {
                List<Integer> length = colorMap.get(c);
                if (length == null) {
                    length = new LinkedList<>();
                    colorMap.put(c, length);
                }
                length.add(i + j);
                j++;
            }
        }
        int remainder = cubes.length % colors.values().length;
        if (remainder != 0) {
            List<Integer> leftovers = new LinkedList<>();
            for (int i = 0; i < remainder; i++) {
                leftovers.add(cubes.length - 1 - i);
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
        a004_impl(operators, numbers, k, currentOperators);
    }

    private void a004_impl(List<String> operatorPool, List<Integer> numberArray, int limit, List<String> currentOperators) {
        if (currentOperators.size() + 1 == numberArray.size()) {
            // print solution
            if (limit == evaluateExpression(currentOperators, numberArray)) {
                System.out.println(currentOperators);
            }
        } else {
            for (String mark : operatorPool) {
                currentOperators.add(mark);
                a004_impl(operatorPool, numberArray, limit, currentOperators);
                currentOperators.remove(currentOperators.size() - 1);
            }
        }
    }

    private int evaluateExpression(List<String> currentOperators, List<Integer> numberArray) {
        List<Integer> copyArray = new LinkedList<>();
        copyArray.addAll(numberArray);

        int sum = copyArray.get(0);
        copyArray.remove(0);
        // this is cool
        for (String operator : currentOperators) {
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
        int[] numbers = NumberService.generateIntegers(10, 1, 12);
        a003_impl(s, numbers);
    }

    private void a003_impl(int s, int[] numbers) {
        List<Integer> subSet = new LinkedList<>();
        List<Integer> numberSet = new LinkedList<>();
        Arrays.stream(numbers).forEach(numberSet::add);
        numberSet = NumberService.toSet(numberSet);
        numberSet.sort(Comparator.naturalOrder());
        System.out.println(numberSet);
        trySubset(subSet, numberSet, s);
    }

    private void trySubset(List<Integer> subSet, List<Integer> numberSet, int s) {
        if (NumberService.getSumOfList(subSet) == s) {
            System.out.println(subSet);
        } else {
            int c = numberSet.size();
            for (int i = 0; i < c; i++) {
                Integer x = numberSet.get(i);
                if (subSet.isEmpty() || subSet.get(subSet.size() - 1) < x) {
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
