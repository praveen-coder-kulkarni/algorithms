public class FixedSizeStack<Item> {

   private Item[] a;
   private int N;

   public FixedSizeStack(int capacity){
      a = (Item[]) new Object[capacity];
   }

   public int size(){
      return N;
   }

   public boolean isEmpty(){
      return N == 0;
   }

   public void push(Item item){
      a[N++] = item;
   }

   public Item pop(){
      return a[--N];
   }

   @Override
   public String toString(){
      StringBuffer sb = new StringBuffer();
      for(int i = N - 1 ; i >= 0 ; i--)
         sb.append(a[i] + " ");
      sb.append("(" + N + " items left on the stack)");
      return sb.toString();
   }

   public static void main(String[] args){
      FixedSizeStack<Integer> fixedSizeStack = new FixedSizeStack<>(10);
      fixedSizeStack.push(10);
      fixedSizeStack.push(20);
      fixedSizeStack.pop();
      fixedSizeStack.push(30);
      fixedSizeStack.push(40);
      StdOut.println(fixedSizeStack);
   }
}
