import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{

   private Item[] a;
   private int N;

   public ResizingArrayStack(){
      a = (Item[]) new Object[1];
   }

   public int size(){
      return N;
   }

   public boolean isEmpty(){
      return N == 0;
   }

   @Override
   public Iterator<Item> iterator() {

      return new ReverseArrayIterator();
   }

   private class ReverseArrayIterator implements Iterator<Item> {
      private int index = N;
      @Override
      public boolean hasNext() {

         return index > 0;
      }

      @Override
      public Item next() {
         return a[--index];
      }
   }

   private void resize(int max){
      Item[] temp = (Item[]) new Object[max];
      for(int i = 0 ; i < N ; i++)
         temp[i] = a[i];
      a = temp;
   }

   public void push(Item item){
      if(N == a.length)
         resize(2*a.length);
      a[N++] = item;
   }

   public Item pop(){
      Item item = a[--N];
      if(N == a.length/4)
         resize(a.length/2);
      a[N] = null;
      return item;
   }

   @Override
   public String toString(){
      StringBuffer sb = new StringBuffer();
      for(int i = N - 1 ; i >= 0 ; i--)
         sb.append(a[i] + " ");
      sb.append("(" + N + " items left on stack)");
      return sb.toString();
   }

   public static void main(String[] args){
      ResizingArrayStack<Integer> resizingArrayStack = new ResizingArrayStack<>();
      while(!StdIn.isEmpty()){
         String s = StdIn.readString();
         if(s.equals("-"))
            resizingArrayStack.pop();
         else
            resizingArrayStack.push(Integer.parseInt(s));
      }
      for(int i : resizingArrayStack)
         StdOut.print(i + " ");
      StdOut.println();
      StdOut.println(resizingArrayStack);
   }
}
