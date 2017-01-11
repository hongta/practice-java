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

        wqu = new WeightedQuickUnionUF(n * n + 2);
        //use n*n as virtual top site
        //  n*n+1 as virtual bottom site
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

        //top sites
        if (current < grid) {
            wqu.union(current, grid*grid);
        }

//        //bottom sites
//        if (current < grid * grid && current >= (grid-1)* grid ) {
//            wqu.union(current, grid*grid+1);
//        }

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



        int current = getIdFromRowCol(row, col);

        return wqu.connected(current, grid*grid);
    }

    private int getIdFromRowCol(int row, int col)
    {
        if (row < 1 || row >grid || col < 1 || col > grid) {
            throw new IndexOutOfBoundsException("out of range");
        }

        return (row-1) * grid + col-1;
    }


    public int numberOfOpenSites() {
        return numOfOpen;
    }

    public boolean percolates() {

        for (int i = 0; i < grid; i++) {
            int n = getIdFromRowCol(grid,i+1);
            if (wqu.connected(n, grid*grid))
                return true;
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
        StdOut.println(per.isFull(10,10));
        StdOut.println(per.percolates());

        p.open(1,1);
        StdOut.println(p.isFull(1,1));
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.percolates());

    }
}
