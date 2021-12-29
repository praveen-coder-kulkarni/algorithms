import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{

   private int N;
   private Node first, last;


   private class Node{
      Item item;
      Node prev;
      Node next;
   }

   public boolean isEmpty(){ return N == 0; }

   public int size(){ return N; }

   public void pushLeft(Item item){
      Node newNode = new Node();
      newNode.item = item;
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         Node oldFirst = first;
         first = newNode;
         first.next = oldFirst;
         oldFirst.prev = first;
      }
      N++;
   }

   public void pushRight(Item item){
      Node newNode = new Node();
      newNode.item = item;
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         Node oldLast = last;
         last = newNode;
         last.prev = oldLast;
         oldLast.next = last;
      }
      N++;
   }

   public Item popLeft(){
      if(isEmpty())
         throw new NoSuchElementException("Deque is empty, no items in it to pop!!");
      Item item = first.item;
      first = first.next;
      N--;
      return item;
   }

   public Item popRight(){
      if(isEmpty())
         throw new NoSuchElementException("Deque is empty, no items in it to pop!!");
      Item item = last.item;
      last = last.prev;
      last.next = null;
      N--;
      return item;
   }

   @Override
   public Iterator<Item> iterator() {

      return new DequeIterator(first);
   }

   private class DequeIterator<Item> implements Iterator<Item>{
      Node current;
      public DequeIterator(Node node){current = node;}
      public boolean hasNext(){return current != null;}
      public Item next(){
         if(!hasNext())
            throw new NoSuchElementException("No elements to loop!!");
         Item item = (Item) current.item;
         current = current.next;
         return item;
      }
      public void remove(){}
   }

}
