/**
 * Created by hongta on 31/12/16.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {


    private int grid;
    private int trials;
    private double[] result;
    private boolean flagProcessed;
    public PercolationStats(int n, int trials)
    {
        if (n<=0 || trials <=0)
            throw new java.lang.IllegalArgumentException("Illegal Arguments");

        this.grid = n;
        this.trials = trials;
        this.result = new double[trials];
        this.flagProcessed = false;
    }

    public double mean()
    {
        if (!flagProcessed) {
            this.performPercolation();
        }

        return StdStats.mean(this.result);
    }
    public double stddev()
    {
        if (!flagProcessed) {
            this.performPercolation();
        }

        return StdStats.stddev(this.result);
    }

    public double confidenceLo()
    {
        return mean() - 1.96 * stddev()/Math.sqrt(this.trials);
    }
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev()/Math.sqrt(this.trials);
    }

    private void performPercolation()
    {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(this.grid);
            int count = 0;
            while (!p.percolates() && count < grid * grid) {
                int row = StdRandom.uniform(1, grid + 1);
                int col = StdRandom.uniform(1, grid + 1);
                if (!p.isOpen(row, col)) {
                    count++;
                    p.open(row, col);
                }
            }
            this.result[i] = (double)count / (double)(this.grid * this.grid);

        }
        this.flagProcessed = true;
    }


    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);         // n-by-n percolation system
        int trials = Integer.parseInt(args[1]);

        PercolationStats pStats = new PercolationStats(n, trials);

        StdOut.println("mean\t\t\t\t\t= " + pStats.mean());
        StdOut.println("stddev\t\t\t\t\t= " + pStats.stddev());
        StdOut.println("95% confidence interval\t= " + pStats.confidenceLo()+", " + pStats.confidenceHi());
    }



}
