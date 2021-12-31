public class DegreesOfSeparation {

   public static void main(String[] args) {

      SymbolGraph sg = new SymbolGraph(args[0], args[1]);
      String source = args[2];

      if(!sg.contains(source)){
         StdOut.println(source + " is not found in database!! Try different one.");
         return;
      }

      Graph G = sg.G();
      int s = sg.index(source);

      BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(G, s);

      while(StdIn.hasNextLine()){
         String sink = StdIn.readLine();
         if(!sg.contains(sink)){
            StdOut.println(sink + " is not found in database!! Try different one");
            continue;
         }
         int u = sg.index(sink);
         if(breadthFirstPaths.hasPathTo(u)){
            for(int w : breadthFirstPaths.pathTo(u))
               StdOut.println(" " + sg.name(w));
         }
      }
   }

}
