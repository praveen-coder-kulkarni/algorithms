import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPQ<Key>{
    private Key[] pq;
    private int N;
    private Comparator<Key> comparator;

    public MinPQ(){
        this(1);
    }

    public MinPQ(int capacity){
        pq = (Key[]) new Object[capacity + 1];
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator){
        this(initCapacity);
        this.comparator = comparator;
    }

    public MinPQ(Comparator<Key> comparator){
        this(1, comparator);
    }

    public MinPQ(Key[] keys){
        int n = keys.length;
        pq = (Key[]) new Object[n+1];
        for(int i = 0; i < n ; i++){
            pq[i+1] = keys[i];
        }
        for(int k = n/2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public void insert(Key k){
        if(N + 1 == pq.length)
            resize(2*pq.length);
        pq[++N] = k;
        swim(N);
    }

    public Key delMin(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        if(N > 0 && N == (pq.length-1)/4)
            resize(pq.length/2);
        return max;
    }

    private void resize(int newCapacity){
        Key[] temp = (Key[]) new Object[newCapacity];
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
        if(comparator == null){
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        return comparator.compare(pq[i], pq[j]) > 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean isMaxHeap(){
        for(int i = 1 ; i <= N ; i++)
            if(pq[i] == null) return false;
        for(int i = N+1; i < pq.length ; i++)
            if(pq[i] != null) return false;
        if(pq[0] != null) return false;
        return isMaxOrderedHeap(1);
    }

    private boolean isMaxOrderedHeap(int k){
        if(k > N) return true;
        int left = 2*k;
        int right = left+1;
        if(left <= N && less(left, k))      return false;
        if(right <= N && less(right, k))    return true;
        return isMaxOrderedHeap(left) && isMaxOrderedHeap(right);
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<String>();
        pq.insert("P");
        pq.insert("R");
        pq.insert("I");
        pq.insert("O");
        pq.insert("R");
        pq.insert("I");
        pq.insert("T");
        pq.insert("Y");
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
    }
}