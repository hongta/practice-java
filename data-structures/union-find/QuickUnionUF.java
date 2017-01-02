/**
 * Created by hongta on 31/12/16.
 */

public class QuickUnionUF {

    private int[] id;
    private int count;     // number of components

    public QuickUnionUF(int N) {
        id = new int[N];
        count = N;

        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private void validate(int v) {
        if ( v <= 0 || v >= id.length)
            throw new IndexOutOfBoundsException("index: " + v + "out of bounds[0 - " + id.length + ").");

    }

    public int find(int n) {
        validate(n);

        while (id[n] != n) {
            n = id[n];
        }
        return n;
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        id[pRoot] = qRoot;
        count--;

    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);

        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

}
