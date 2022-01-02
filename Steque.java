import java.util.NoSuchElementException;

//java Steque
//      + praveen
//      praveen
//      Size of steque is: 1
//      ++ pramod
//      praveen pramod
//      Size of steque is: 2
//      -
//      pramod
//      Size of steque is: 1
//      -
//
//      Size of steque is: 0
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
      if(isEmpty()){
         first = newNode;
         last = newNode;
      }else{
         first = newNode;
         first.next = oldFirst;
      }
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
         oldLast.next = last;
      }
      N++;
   }

   public int size(){
      return N;
   }

   public String toString(){
      StringBuilder sb = new StringBuilder();
      for(Node x = first ; x != null ; x = x.next)
         sb.append(x.item + " ");
      sb.append("\n\tSize of steque is: " + size());
      return sb.toString();
   }

   public static void main(String[] args) {
      Steque<String> steque = new Steque<String>();
      while(!StdIn.isEmpty()){
         String[] s = StdIn.readLine().split(" ");
         if(s[0].equals("+")){
            steque.push(s[1]);
         }else if(s[0].equals("-")){
            steque.pop();
         }else if(s[0].equals("++")){
            steque.enqueue(s[1]);
         }
         StdOut.println(steque);
      }
   }
}
