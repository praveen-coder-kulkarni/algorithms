import java.util.NoSuchElementException;

public class RandomQueue<Item> {

   private Item[] queue;
   private int N;

   public RandomQueue(){
      queue = (Item[]) new Object[1];
      N = 0;
   }

   public int size(){
      return N;
   }

   public boolean isEmpty(){
      return size() == 0;
   }

   private void resize(int capacity){
      Item[] temp = (Item[]) new Object[capacity];
      for(int i = 0 ; i < N ; i++)
         temp[i] = queue[i];
      queue = temp;
   }

   public void enqueue(Item item){
      queue[N++] = item;
      if(N == queue.length)   resize(2 * queue.length);
   }

   private void swap(int i, int j){
      Item temp = queue[i];
      queue[i] = queue[j];
      queue[j] = temp;
   }

   public Item dequeue(){
      if(isEmpty()) throw new NoSuchElementException("Random queue underflow");
      Item item = null;
      int index = StdRandom.uniform(size());
      swap(index, size() - 1);
      item = queue[--N];
      queue[N+1] = null;
      if(N > 0 && N == queue.length/4) resize(queue.length / 2);
      return item;
   }

   public Item sample(){
      if(isEmpty()) throw new NoSuchElementException("Random queue underflow");
      return queue[StdRandom.uniform(size())];
   }

   public static void main(String[] args) {
      RandomQueue<String> queue = new RandomQueue<String>();
      while(!StdIn.isEmpty()){
         String s = StdIn.readString();
         if(s.equals("-"))
            StdOut.println("Sampled item from the random queue: " + queue.sample());
         else if(s.equals("--"))
            StdOut.println("Item dequeued from the random queue: " + queue.dequeue());
         else
            queue.enqueue(s);
         StdOut.println("Random queue size: " + queue.size());
      }
   }
}
