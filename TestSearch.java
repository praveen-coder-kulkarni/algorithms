public class TestSearch {
   public static void main(String[] args) {
      Graph G = new Graph((new In(args[0])));
      int s = Integer.parseInt(args[1]);
      UFGraphSearch search = new UFGraphSearch(G, s);
      for(int v = 0 ; v < G.V(); v++){
         if(search.marked(v))
            StdOut.print(v + " ");
      }
      StdOut.println();

      StdOut.println("Number of vertices connected to source vertex: " + s + " = " + search.count());

      if(search.count() != G.V())
         StdOut.print("NOT");
      StdOut.println(" connected");
   }
}
