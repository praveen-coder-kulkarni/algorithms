import java.util.Iterator;
import java.util.NoSuchElementException;

//java Deque
//      pL Ram
//      Ram
//      Size of Deque is: 1
//      pR Laxman
//      Ram Laxman
//      Size of Deque is: 2
//      pL Seeta
//      Seeta Ram Laxman
//      Size of Deque is: 3
//      pR Swati
//      Seeta Ram Laxman Swati
//      Size of Deque is: 4
//      pL Mohan
//      Mohan Seeta Ram Laxman Swati
//      Size of Deque is: 5
//      ppR
//      Mohan Seeta Ram Laxman
//      Size of Deque is: 4
//      ppL
//      Seeta Ram Laxman
//      Size of Deque is: 3
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
      if(isEmpty()) last = null;
      return item;
   }

   public Item popRight(){
      if(isEmpty())
         throw new NoSuchElementException("Deque is empty, no items in it to pop!!");
      Item item = last.item;
      last = last.prev;
      last.next = null;
      N--;
      if(isEmpty())  first = null;
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

   public static void main(String[] args) {
      Deque<String> deque = new Deque<String>();
      while(!StdIn.isEmpty()){
         String[] s = StdIn.readLine().split(" ");
         if(s[0].equals("pL"))
            deque.pushLeft(s[1]);
         else if(s[0].equals("pR"))
            deque.pushRight(s[1]);
         else if(s[0].equals("ppL"))
            deque.popLeft();
         else if(s[0].equals("ppR"))
            deque.popRight();
         for(String ss : deque)
            StdOut.print(ss + " ");
         StdOut.println("\n\tSize of Deque is: " + deque.size());
      }
   }

}
