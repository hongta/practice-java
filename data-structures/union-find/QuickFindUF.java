/**
 * Created by hongta on 31/12/16.
 */

public class QuickFindUF {

    private int[] id;
    private int count;   // number of components

    public QuickFindUF(int N) {
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

    public void union(int p, int q) {
        validate(p);
        validate(q);

        int pVal = id[p];
        int qVal = id[q];

        // p and q are already in the same component
        if (pVal == qVal)
            return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pVal) {
                id[i] = qVal;
            }
        }
        count--;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);

        return id[p] == id[p];
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    public int count() {
        return count;
    }

}
