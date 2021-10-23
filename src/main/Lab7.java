package main;

import java.util.LinkedList;
import java.util.List;

/**
 * Algorithmic assignment, no 7 .
 */
public class Lab7  extends Lab{

    int n = 8;
    boolean [] diagonal = new boolean [2*n-1];
    boolean [] sideDiagonal = new boolean[2*n-1];
    boolean [] column = new boolean[n];
    private final List<List<Integer>> queenResults = new LinkedList<>();
    private long queenSolutionCount = 0;

    @Override
    /**
     *
     */
    public void a003() {
        //
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
        perm(n,1, 1, new LinkedList<>());
    }

    /**
     * Permutations
     *
     */
    private void perm(int n,int i,int x, List<Integer> l) {
        if(n > i) {
           for(int index = 1; index <= n; index++) {
                l.add(index);
                perm(n, i+1,index, l);
                l.remove(l.size()-1);
            }
        }
        else {
            if( i==n) {
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
        diagonal[row+col] = true;
        sideDiagonal[-row+n+col-1] = true;
        LinkedList<Integer> currentResult = new LinkedList<>();
        currentResult.add(row);
        currentResult.add(col);
        queenResults.add(currentResult);
    }

    private void removeQueen(int row, int col) {
        column[col] = false;
        diagonal[row+col] = false;
        sideDiagonal[-row+n+col-1] = false;
        if(queenResults.size() > 0) {
            queenResults.remove(queenResults.get(queenResults.size()-1));
        }

    }

    private int chooseColumn() {
        int index = 0;

        return index;
    }

    private boolean canPlaceQueen(int row, int col) {
        boolean result = false;
        int di= col + row;
        int si = n-row + col-1;
        int ci = col;

        if(!diagonal[di] && !sideDiagonal[si] && !column[ci]) {
            result = true;
        }
        return result;
    }

    /**
     * Print result of queen placing based on the three vectors
     */
    private void printQueens() {
//        System.out.println("Solution found.. yayyy");
//        System.out.println(queenResults);
        queenSolutionCount++;

    }

//        for(int i = 0; i < n; i++ ) {
//            for(int j = 0; j < n; j++) {
//                if(diagonal[i+j] && sideDiagonal[n-i+j-1] ) {
//                    System.out.print("X");
//                }
//                else {
//                    System.out.print("0");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("Next solution");
//


}
