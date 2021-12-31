import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SymbolGraph {

   private Map<String, Integer> st;
   private String[] keys;
   private Graph G;

   public SymbolGraph(String stream, String sp){

      st = new HashMap<String, Integer>();
      In in = new In(stream);

      while(in.hasNextLine()) {
         String[] vertices = in.readLine().split(sp);
         for (int v = 0; v < vertices.length; v++)
            if (!st.containsKey(vertices[v]))
               st.put(vertices[v], st.size());
      }

      keys = new String[st.size()];
      for(Map.Entry<String, Integer> entry : st.entrySet()){
         keys[entry.getValue()] = entry.getKey();
      }

      G = new Graph(st.size());

      in = new In(stream);
      while(in.hasNextLine()){
         String[] points = in.readLine().split(sp);
         for(int i = 1 ; i < points.length ; i++){
            int a = st.get(points[0]);
            int b = st.get(points[i]);
            G.addEdge(a, b);
         }
      }

   }

   public boolean contains(String key){
      return st.containsKey(key);
   }

   public String name(int v){
      return keys[v];
   }

   public int index(String key){
      return st.get(key);
   }

   public Graph G(){
      return G;
   }

   public static void main(String[] args){
      String filename = args[0];
      String delim = args[1];
      SymbolGraph sg = new SymbolGraph(filename, delim);
      Graph G = sg.G();
      while(StdIn.hasNextLine()){
         String key = StdIn.readLine();
         for(int w : G.adj(sg.index(key)))
            StdOut.println(" " + sg.name(w));
      }
   }

}
