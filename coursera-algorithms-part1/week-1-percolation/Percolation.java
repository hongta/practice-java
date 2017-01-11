/**
 * Created by hongta on 31/12/16.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private boolean[][] sitesOpened;
    private boolean[] rowOpened;
    private int grid;
    private int numOfOpen;

    public Percolation(int n) {
        if (n<=0)
            throw new java.lang.IllegalArgumentException("Illegal Argument N");

        wqu = new WeightedQuickUnionUF(n * n);
        grid = n;
        numOfOpen = 0;
        sitesOpened = new boolean[n][n];
        rowOpened = new boolean[n];

    }

    public void open(int row, int col) {
        int current = getIdFromRowCol(row, col);

        if (isOpen(row, col))
            return;

        numOfOpen++;
        sitesOpened[row-1][col-1] = true;
        rowOpened[row-1] = true;

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

    public boolean isOpen(int row, int col) {
        if (row < 1 || row >grid || col < 1 || col > grid)
            throw new IndexOutOfBoundsException("out of range");

        return sitesOpened[row-1][col-1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > grid || col < 1 || col > grid)
            throw new IndexOutOfBoundsException("out of range");

        if (!isOpen(row, col))
            return false;

        for (int i = 0; i < row; i++) {
            if (!rowOpened[i])
                return false;
        }

        for(int i =0; i < grid; i++) {
            if (isOpen(1, i+1)  && connected(1, i+1, row, col)) {

                return true;
            }
        }

        return false;
    }

    private int getIdFromRowCol(int row, int col)
    {
        if (row < 1 || row >grid || col < 1 || col > grid) {
            throw new IndexOutOfBoundsException("out of range");
        }

        return (row-1) * grid + col-1;
    }

    private void union(int row_1, int col_1, int row_2, int col_2)
    {
        int pos_1 = (row_1 - 1) * grid + col_1 - 1;
        int pos_2 = (row_2 - 1) * grid + col_2 - 1;
        wqu.union(pos_1, pos_2);
    }

    private boolean connected(int row_1, int col_1, int row_2, int col_2)
    {
        int pos_1 = (row_1 - 1) * grid + col_1 - 1;
        int pos_2 = (row_2 - 1) * grid + col_2 - 1;
        return wqu.connected(pos_1, pos_2);
    }

    public int numberOfOpenSites() {
        return numOfOpen;
    }

    public boolean percolates() {

        for (int i = 0; i < grid; i++) {
            if (isOpen(grid, i+1)  && isFull(grid, i+1)) {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {

        int grid = 10;
        Percolation per = new Percolation(grid);
        Percolation p = new Percolation(1);

        StdOut.println(per.numberOfOpenSites());

        per.open(4, 8);
        per.open(4, 9);
        per.open(4, 10);
        per.open(3, 8);
        per.open(3, 7);
        per.open(3, 6);
        per.open(2, 6);
        per.open(2, 5);
        per.open(2, 4);
        per.open(1, 4);
        per.open(1, 4);
        per.open(5, 10);
        per.open(6, 10);
        per.open(7, 10);
        per.open(8, 10);
        per.open(9, 10);
        per.open(10, 10);
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
        StdOut.println(per.isFull(4,9));
        StdOut.println(per.numberOfOpenSites());
        StdOut.println(per.isFull(10,9));
        StdOut.println(per.percolates());

        p.open(1,1);
        StdOut.println(p.isFull(1,1));
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.percolates());

    }
}