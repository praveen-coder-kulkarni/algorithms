public class UFGraphSearch {
   private WeightedQuickUnionUF uf;
   private int s;
   private int V;
   public UFGraphSearch(Graph G, int s){
      this.s = s;
      this.V = G.V();
      uf = new WeightedQuickUnionUF(V);
      for(int v = 0 ; v < G.V() ; v++)
         for(int w : G.adj(v)) {
            uf.union(v, w);
         }
   }

   public boolean marked(int v){
      return uf.connected(s, v);
   }

   public int count(){
      int count = 0;
      for(int v = 0 ; v < V ; v++)
         if(v != s && uf.find(v) == uf.find(s)) count++;
      return count;
   }
}
