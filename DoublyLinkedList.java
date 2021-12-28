import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> {
   private DoubleNode first;
   private DoubleNode last;
   private int N;

   private class DoubleNode<Item>{
      Item item;
      DoubleNode<Item> prev;
      DoubleNode<Item> next;
      public DoubleNode(Item item){
         this.item = item;
      }
   }

   public int size(){ return N; }

   public boolean isEmpty(){ return N == 0 || (first == null && last == null); }

   public void insertAtBeginning(Item item){
      DoubleNode newNode = new DoubleNode(item);
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         DoubleNode oldFirst = first;
         first = newNode;
         newNode.next = oldFirst;
      }
      N++;
   }

   public void insertAtEnd(Item item){
      DoubleNode newNode = new DoubleNode(item);
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         DoubleNode oldLast = last;
         last = newNode;
         oldLast.next = last;
         last.prev = oldLast;
      }
      N++;
   }

   public DoubleNode removeAtBeginning(){
      DoubleNode node = null;
      if(isEmpty())
         throw new NoSuchElementException("Doubly linked list is empty! No items to remove!!");
      first = first.next;
      N--;
      return node;
   }

   public DoubleNode removeAtEnd(){
      DoubleNode node = null;
      if(isEmpty())
         throw new NoSuchElementException("Doubly linked list is empty! No items to remove!!");
      node = last;
      last = last.prev;
      last.next = null;
      N--;
      return node;
   }

   public void printLinkedList(){
      DoubleNode node = first;
      while(node != null){
         System.out.print(node.item + " ");
         node = node.next;
      }
      System.out.println();
   }

   public static void main(String[] args) {
      DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();
      doublyLinkedList.insertAtEnd(500);
      doublyLinkedList.insertAtBeginning(1);
      doublyLinkedList.insertAtBeginning(2);
      doublyLinkedList.insertAtBeginning(3);
      doublyLinkedList.insertAtBeginning(10);
      doublyLinkedList.printLinkedList();
      doublyLinkedList.insertAtEnd(100);
      doublyLinkedList.printLinkedList();
      doublyLinkedList.removeAtBeginning();
      doublyLinkedList.printLinkedList();
      doublyLinkedList.removeAtEnd();
      doublyLinkedList.printLinkedList();
   }
}
