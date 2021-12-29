import java.util.NoSuchElementException;

public class Steque<Item> {
   private Node first, last;
   private int N;

   private class Node{
      Item item;
      Node next;
      public Node(Item item){
         this.item = item;
         this.next = null;
      }
   }

   public boolean isEmpty(){
      return N == 0;
   }

   public Item pop(){
      if(isEmpty())
         throw new NoSuchElementException("Steque does not have any element to pop!!");
      Item item = first.item;
      first = first.next;
      N--;
      return item;
   }

   public void push(Item item){
      Node newNode = new Node(item);
      Node oldFirst = first;
      first = newNode;
      first.next = oldFirst;
      N++;
   }

   public void enqueue(Item item){
      Node newNode = new Node(item);
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         Node oldLast = last;
         last = newNode;
         if(oldLast != null)  oldLast.next = last;
      }
      N++;
   }

   public int size(){
      return N;
   }
}
