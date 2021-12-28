import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> {
   private Item[] queue;
   private int first;
   private int last;
   private int N;

   public ResizingArrayQueue(){
      queue = (Item[]) new Object[1];
   }

   public boolean isFull(){ return last == queue.length - 1; }

   public boolean isEmpty(){ return N == 0; }

   public int size() { return N; }

   public void enqueue(Item item){
      if(isFull())
         resize(2 * queue.length);
      queue[last++] = item;
      N++;
   }

   public Item dequeue(){
      if(isEmpty())
         throw new NoSuchElementException("Queue underflow. No elements to dequeue!!");
      Item item = queue[first++];
      queue[first-1] = null;
      if(last - first + 1 == queue.length/4)
         resize(queue.length / 2);
      N--;
      return item;
   }

   private void resize(int capacity){
      Item[] temp = (Item[]) new Object[capacity];
      int index = 0;
      for(int i = first ; i <= last ; i++)
         temp[index++] = queue[i];
      queue = temp;
      first = 0;
      last = index - 1;
   }

   public static void main(String[] args) {
      ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
      while (!StdIn.isEmpty()) {
         String item = StdIn.readString();
         if (!item.equals("-")) queue.enqueue(item);
         else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
      }
      StdOut.println("(" + queue.size() + " left on queue)");
   }

}
