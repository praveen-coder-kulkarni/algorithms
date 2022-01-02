public class Josephus {
   public static void main(String[] args) {
      int M = Integer.parseInt(args[0]);
      int N = Integer.parseInt(args[1]);
      Queue<Integer> queue = new Queue<>();
      for(int i = 0 ; i < N ; i++)  queue.enqueue(i);
      while (!queue.isEmpty()){
         for(int i = 1 ; i < M ; i++)  queue.enqueue(queue.dequeue());
         StdOut.print(queue.dequeue() + " ");
      }
      StdOut.println();
   }
}
