import java.util.Iterator;
import java.util.NoSuchElementException;

// Pop is throwing exception. Fix it.
//java ResizingArrayDeque
//      pL Pramod
//      Pramod
//      Size of Deque is: 1
//      pL Pramod
//      Pramod Pramod
//      Size of Deque is: 2
//      pL Praveen
//      Praveen Pramod Pramod
//      Size of Deque is: 3
//      pR Praveen
//      Praveen Pramod Pramod Praveen
//      Size of Deque is: 4
//      ppL
//      Pramod Pramod Praveen
//      Size of Deque is: 3
//      ppR
//      Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
//      at ResizingArrayDeque.resize(ResizingArrayDeque.java:21)
//      at ResizingArrayDeque.popRight(ResizingArrayDeque.java:59)
//      at ResizingArrayDeque.main(ResizingArrayDeque.java:90)
public class ResizingArrayDeque<Item> implements Iterable<Item>{
   private int N;
   private Item[] items;
   private int first, last;
   private final int INIT_CAPACITY = 8;


   public ResizingArrayDeque(){
      items = (Item[]) new Object[INIT_CAPACITY];
      first = INIT_CAPACITY/2;
      last = first + 1;
//      StdOut.println("first = " + first + ", last = " + last);
   }

   private void resize(int maxCapacity){
      Item[] temp = (Item[]) new Object[maxCapacity];
      for(int i = first ; i <= last ; i++)
         temp[i] = items[i];
      items = temp;
   }

   public boolean isEmpty(){
      return N == 0;
   }

   public int size(){
      return N;
   }

   public void pushLeft(Item item){
      if(N == items.length)   resize(2 * items.length);
      items[first--] = item;
      N++;
   }

   public void pushRight(Item item){
      if(N == items.length)   resize(2 * items.length);
      items[last++] = item;
      N++;
   }

   public Item popLeft(){
      if(isEmpty()) throw new NoSuchElementException("Deque is empty!!");
      Item item = items[first++];
      items[first-1] = null;
      N--;
      if(N > 0 && N == items.length / 4) resize(items.length/2);
      return item;
   }

   public Item popRight(){
      if(isEmpty()) throw new NoSuchElementException("Deque is empty!!");
      Item item = items[last--];
      items[last+1] = null;
      N--;
      if(N > 0 && N == items.length / 4) resize(items.length/2);
      return item;
   }

   public Iterator<Item> iterator(){
      return new ArrayDequeIterator();
   }

   private class ArrayDequeIterator<Item> implements  Iterator<Item>{
      private int currentIndex = first + 1;
      public boolean hasNext(){ return currentIndex <= last - 1;}
      public Item next(){
         if(!hasNext()) throw new NoSuchElementException("No elements to loop!");
//         StdOut.println("From Iterator: currentIndex = " + currentIndex);
         Item item = (Item) items[currentIndex++];
         return item;
      }
      public void remove(){}
   }

   public static void main(String[] args) {
      ResizingArrayDeque<String> resizingArrayDeque = new ResizingArrayDeque<String>();
      while(!StdIn.isEmpty()){
         String[] s = StdIn.readLine().split(" ");
         if(s[0].equals("pL"))
            resizingArrayDeque.pushLeft(s[1]);
         else if(s[0].equals("pR"))
            resizingArrayDeque.pushRight(s[1]);
         else if(s[0].equals("ppL"))
            resizingArrayDeque.popLeft();
         else if(s[0].equals("ppR"))
            resizingArrayDeque.popRight();
         for(String ss : resizingArrayDeque)
            StdOut.print(ss + " ");
         StdOut.println("\n\tSize of Deque is: " + resizingArrayDeque.size());
      }
   }

}
