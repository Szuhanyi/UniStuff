package main;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
