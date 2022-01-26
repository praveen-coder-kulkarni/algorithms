public class GraphProperties{
   private int[] eccentricity;
   private int diameter;
   private int radius;
   private int center;
   private int girth;

   public GraphProperties(Graph G){
      diameter = Integer.MIN_VALUE;
      radius = Integer.MAX_VALUE;
      girth = Integer.MAX_VALUE;
      center = -1;
      eccentricity = new int[G.V()];
      for(int v = 0; v < G.V(); v++){
         BreadthFirstPaths paths = new BreadthFirstPaths(G, v);
         int maxDistance = Integer.MIN_VALUE;
         int minDistance = Integer.MAX_VALUE;
         for(int u = 0 ; u < G.V() ; u++){
            if(u == v) continue;
            if(paths.hasPathTo(u)){
               int dist = paths.distTo(u);
               maxDistance = Math.max(maxDistance, dist);
               minDistance = Math.min(minDistance, dist);
               girth = Math.min(girth, 2*dist);
            }
         }
         eccentricity[v] = maxDistance;
         diameter = Math.max(diameter, maxDistance);
         if(minDistance < radius){
            radius = minDistance;
            center = v;
         }
      }
   }
   public int eccentricity(int v){
      return eccentricity[v];
   }

   public int diameter(){
      return diameter;
   }

   public int radius(){
      return radius;
   }

   public int center(){
      return center;
   }

   public int girth(){
      return girth;
   }
}