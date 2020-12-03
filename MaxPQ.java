public class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    private int N;

    public MaxPQ(){
        pq = (Key[]) new Comparable[2];
    }

    public MaxPQ(int capacity){
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key k){
        if(N + 1 == pq.length)
            resize(2*pq.length);
        pq[++N] = k;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        if(N + 1 <= pq.length / 4)
            resize(pq.length/2);
        return max;
    }

    private void resize(int newCapacity){
        Key[] temp = (Key[]) new Comparable[newCapacity];
        for(int i = 1 ; i <= N ; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    private void swim(int k){
        while(k > 1){
            if(!less(k/2, k))
                break;
            exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1)) j++;
            if(!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");
        pq.insert("R");
        pq.insert("I");
        pq.insert("T");
        pq.insert("Y");
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }
}