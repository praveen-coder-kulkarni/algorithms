public class TopM {
   public static void main(String[] args){
      int M = Integer.parseInt(args[0]);
      MinPQ<Transaction> pq = new MinPQ<Transaction>(M);
      while(!StdIn.isEmpty()){
         pq.insert(new Transaction(StdIn.readLine()));
         if(pq.size() > M)
            pq.delMin();
      }
      Stack<Transaction> s = new Stack<Transaction>();
      while(!pq.isEmpty()) s.push(pq.delMin());
      for(Transaction t : s)
         StdOut.println(t);
   }
}
