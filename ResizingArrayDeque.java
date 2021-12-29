import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Iterable<Item>{
   private int N;
   private Item[] items;
   private int first, last;
   private final int INIT_CAPACITY = 8;


   public ResizingArrayDeque(){
      items = (Item[]) new Object[INIT_CAPACITY];
      first = INIT_CAPACITY/2;
      last = first + 1;
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
      private int currentIndex = first;
      public boolean hasNext(){ return first <= last;}
      public Item next(){
         if(!hasNext()) throw new NoSuchElementException("No elements to loop!");
         Item item = (Item) items[currentIndex++];
         return item;
      }
      public void remove(){}
   }

}
