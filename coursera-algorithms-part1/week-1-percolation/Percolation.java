/**
 * Created by hongta on 31/12/16.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private WeightedQuickUnionUF wqu, auxWqu;
    private boolean[][] sitesOpened;
    private int size;
    private int numOfOpen;

    private int virtualTopSite;
    private int virtualBottomSite;

    public Percolation(int n) {
        if (n<=0)
            throw new java.lang.IllegalArgumentException("Illegal Argument N");

        // first WeightedQuickUnionUF instance with virtual top site and virtual bottom site
        wqu = new WeightedQuickUnionUF(n * n + 2);

        // second WeightedQuickUnionUF instance with only virtual top site
        auxWqu = new WeightedQuickUnionUF(n * n+1);

        //use n*n as virtual top site
        //  n*n+1 as virtual bottom site
        virtualTopSite = n*n;
        virtualBottomSite = n*n+1;

        size = n;
        numOfOpen = 0;
        sitesOpened = new boolean[n][n];

    }

    public void open(int row, int col) {
        int current = getIdFromRowCol(row, col);

        if (isOpen(row, col))
            return;

        numOfOpen++;
        sitesOpened[row-1][col-1] = true;

        //union to virtual top site for both WeightedQuickUnion instances
        if (current < size) {
            wqu.union(current, virtualTopSite);
            auxWqu.union(current, virtualTopSite);
        }

        //union to virtual bottom site for first WeightedQuickUnion instance
        if (current < size * size && current >= (size-1)* size ) {
            wqu.union(current, virtualBottomSite);
        }

        //row-1, col
        if (row-1 > 0 && isOpen(row-1, col)) {
            wqu.union(current, current - size);
            auxWqu.union(current, current - size);
        }

        //row, col+1
        if (col+1 < size+1 && isOpen(row, col+1)) {
            wqu.union(current, current + 1);
            auxWqu.union(current, current + 1);
        }

        //row+1, col
        if (row+1 < size+1 && isOpen( row+1, col)) {
            wqu.union(current, current + size);
            auxWqu.union(current, current + size);
        }

        //row, col-1
        if (col-1 > 0 && isOpen(row, col-1)) {
            wqu.union(current, current - 1);
            auxWqu.union(current, current - 1);
        }

    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row >size || col < 1 || col > size)
            throw new IndexOutOfBoundsException("out of range");

        return sitesOpened[row-1][col-1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IndexOutOfBoundsException("out of range");

        if (!isOpen(row, col))
            return false;



        int current = getIdFromRowCol(row, col);

        //use second WeighedQuickUnionUF instance to check site is full
        return auxWqu.connected(current, virtualTopSite);
    }

    private int getIdFromRowCol(int row, int col)
    {
        if (row < 1 || row >size || col < 1 || col > size) {
            throw new IndexOutOfBoundsException("out of range");
        }

        return (row-1) * size + col-1;
    }


    public int numberOfOpenSites() {
        return numOfOpen;
    }

    public boolean percolates() {

        //use first WeighedQuickUnionUF instance to check site percolates
        return wqu.connected(size*size, size*size+1);
    }

    public static void main(String[] args) {

        int size = 10;
        Percolation per = new Percolation(size);

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
        for (int row =0; row < size; row++) {
            for (int col = 0; col < size; col++) {
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

    }
}
