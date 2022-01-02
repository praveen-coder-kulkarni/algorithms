import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomBag<Item> implements Iterable<Item> {

   private Node first;
   private int N;

   public RandomBag(){
      first = null;
      N = 0;
   }

   private class Node<Item>{
      Item item;
      Node next;
   }

   public boolean isEmpty(){
      return N == 0;
   }

   public int size(){
      return N;
   }

   public void add(Item item){
      Node node = new Node();
      node.item = item;
      if(isEmpty())  first = node;
      else{
         Node oldFirst = first;
         first = node;
         first.next = oldFirst;
      }
      N++;
   }

   @Override
   public Iterator<Item> iterator() {

      return new RandomBagIterator<Item>(first);
   }

   private class RandomBagIterator<Item> implements Iterator<Item>{
      private Item[] items;
      private int cursor;

      public RandomBagIterator(Node<Item> node){
         items = (Item[]) new Object[size()];
         int i = 0;
         for(Node x = node; x != null ; x = x.next) {
            items[i++] = (Item) x.item;
         }
         StdRandom.shuffle(items);
         cursor = 0;
      }

      @Override
      public boolean hasNext() {

         return cursor < items.length;
      }

      @Override
      public Item next() {
         if(!hasNext()) throw new NoSuchElementException("No elements to iterate!!");
         Item item = items[cursor++];
         return item;
      }
   }

   public static void main(String[] args) {
      RandomBag<String> b = new RandomBag<String>();
      while(!StdIn.isEmpty()){
         b.add(StdIn.readString());
      }
      System.out.println("Elements in bag are: ");
      for(String s : b)
         System.out.println(s);
      StdOut.println("Total elements in bag: " + b.size());
   }

}
