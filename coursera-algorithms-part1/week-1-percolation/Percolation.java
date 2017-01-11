/**
 * Created by hongta on 31/12/16.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private int[] id;
    private int grid;
    private int num_of_open;

    public Percolation(int n) {
        if ( n <=0 )
            throw new java.lang.IllegalArgumentException("Illegal Argument N");
        wqu = new WeightedQuickUnionUF(n * n);
        id = new int[n*n];
        grid = n;
        for (int i = 0; i < n*n; i++) {
            id[i] = 0;
        }
        num_of_open = 0;
    }
    private void processQU(int row, int col) {
        int current = getIdFromRowCol(row, col);
        if (isOpen(row, col)) {
            //row-1, col
            if (row-1 > 0 && isOpen(row-1, col)) {
                wqu.union(current, current - grid);
            }

            //row, col+1
            if (col+1 < grid+1 && isOpen(row, col+1)) {
                wqu.union(current, current + 1);
            }

            //row+1, col
            if (row+1 < grid+1 && isOpen( row+1, col)) {
                wqu.union(current, current + grid);
            }

            //row, col-1
            if (col-1 > 0 && isOpen(row, col-1)) {
                wqu.union(current, current - 1);
            }

        }
    }

    public void open(int row, int col) {
        int n = getIdFromRowCol(row, col);
//        StdOut.println("N: " + n + "; Row: " + row + "; Col: " + col);
        id[n] = 1;

        processQU(row, col);
        this.num_of_open++;
    }

    public boolean isOpen(int row, int col) {
        int n = getIdFromRowCol(row, col);
        return id[n] == 1;
    }

    public boolean isFull(int row, int col) {
        int n = getIdFromRowCol(row, col);
        if (!isOpen(row, col))
            return false;

        for(int i =0; i < grid; i++) {
            if (isOpen(1, i+1) && wqu.connected(wqu.find(i), n)) {

                return true;
            }
        }

        return false;
    }

    private int getIdFromRowCol(int row, int col) {
        if (row < 1 || row >grid || col < 1 || col > grid) {
            throw new IndexOutOfBoundsException("out of range");
        }

        return (row-1) * grid + col-1;
    }

    public int numberOfOpenSites() {
        return this.num_of_open;
    }

    public boolean percolates() {

        for (int i = 1; i < grid+1; i++) {
            if (isOpen(grid, i) && isFull(grid, i)) {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {

        int grid = 10;
        Percolation per = new Percolation(grid);

        per.open(4,8);
        per.open(4,9);
        per.open(4,10);
        per.open(3,8);
        per.open(3,7);
        per.open(3,6);
        per.open(2,6);
        per.open(2,5);
        per.open(2,4);
        per.open(1,4);
        per.open(5,10);
        per.open(6,10);
        per.open(7,10);
        per.open(8,10);
        per.open(9,10);
        per.open(10,10);
        for (int row =0; row < grid; row++) {
            for (int col = 0; col < grid; col++) {
//                StdOut.print(id[row*per])
                if (per.isOpen(row+1, col+1)) {
                    StdOut.print(1);
                } else {
                    StdOut.print(0);
                }

            }
            StdOut.print("\n");
        }
        StdOut.println(per.isFull(4, 8));
        StdOut.println(per.percolates());
    }
}
