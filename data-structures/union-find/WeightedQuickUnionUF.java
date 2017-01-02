/**
 * Created by hongta on 31/12/16.
 */

public class WeightedQuickUnionUF {

    private int[] id;
    private int[] st;
    private int count;

    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        st = new int[N];
        count = N;

        for (int i = 0; i < N; i++) {
            id[i] = i;
            st[i] = 1;
        }
    }

    private void validate(int v) {
        if ( v < 0 || v >= id.length)
            throw new IndexOutOfBoundsException("index: " + v + " out of bounds[0 - " + id.length + ").");

    }

    public int find(int n) {
        validate(n);

        while (id[n] != n) {

            //path compression
            id[n] = id[id[n]];
            n =  id[n];
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

        if (st[pRoot] > st[qRoot]) {
            //make pRoot become parent of qRoot
            id[qRoot] = pRoot;
            st[qRoot] += st[pRoot];
        } else {
            //make qRoot become parent of pRoot
            id[pRoot] = qRoot;
            st[pRoot] += st[qRoot];

        }
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
