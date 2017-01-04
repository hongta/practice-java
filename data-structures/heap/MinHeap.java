
class MinHeap {
    private int[] heap;
    private int N;

    public MinHeap(int n) {
        heap = new int[n+1];
        N = 0;
    }
    public int getMin() {
        return heap[1];
    }

    public int extractMin() {
        int t = heap[1];
        // 1.exchange first element with last element
        exchange(1, N--);
        // 2.remove last element
        heap[N+1] = 0;
        // 3.siftDown(first element)
        siftDown(1);

        return t;
    }

    public void insert(int p) {
        validate(p);

        heap[++N] = p;
        siftUp(N);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    private void validate(int p) {
        if ( p < 0 || p >= heap.length)
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (heap.length-1));
    }

    private void siftUp(int p) {
        while (p > 1 && heap[p/2] > heap[p]) {
            exchange(p, p/2);
            p = p/2;
        }
    }

    private void exchange(int p, int q) {
        int t  = heap[p];
        heap[p] = heap[q];
        heap[q] = t;
    }

    private boolean less(int p, int q) {
        return heap[p] < heap[q];
    }


    private void siftDown(int p) {
        while (2*p <= N) {
            int l = 2*p;
            if (l < N && !less(l, l+1)) l++;
            if (less(p,l)) break;

            exchange(p, l);
            p = l;
        }
    }


    public void output() {

        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i] + ", ");
        }
        System.out.print("\n");
    }


}
