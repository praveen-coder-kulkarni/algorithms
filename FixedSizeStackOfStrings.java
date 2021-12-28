public class FixedSizeStackOfStrings {

   private String[] a;
   private int N;

   public FixedSizeStackOfStrings(int capacity){
      a = new String[capacity];
   }

   public int size(){
      return N;
   }

   public boolean isEmpty(){
      return N == 0;
   }

   public void push(String s){
      a[N++] = s;
   }

   public String pop(){
      return a[--N];
   }

   public boolean isFull(){
      return N == a.length;
   }

   @Override
   public String toString(){
      StringBuffer sb = new StringBuffer();
      for(int i = N-1 ; i >= 0 ; i--)
         sb.append(a[i] + " ");
      sb.append("(" + N + " items left in stack)");
      return sb.toString();
   }

   public static void main(String[] args){
      FixedSizeStackOfStrings fixedSizeStackOfStrings = new FixedSizeStackOfStrings(10);
      while(!StdIn.isEmpty()){
         String s = StdIn.readString();
         if(s.equals("-"))
            StdOut.println("Item popped: " + fixedSizeStackOfStrings.pop());
         else
            fixedSizeStackOfStrings.push(s);
         if(fixedSizeStackOfStrings.isFull()){
            StdOut.println("Stack if full");
            return;
         }
      }
      StdOut.println("Elements in stack are: ");
      StdOut.println(fixedSizeStackOfStrings);
   }

}
