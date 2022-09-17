public class WeightedQuickUnionWithPathCompressionUF {

   private int[] id;
   private int[] sz;
   private int count;

   public WeightedQuickUnionWithPathCompressionUF(int n){
      id = new int[n];
      sz = new int[n];
      for(int i = 0 ; i < n ; i++){
         id[i] = i;
         sz[i] = 1;
      }
      count = n;
   }

   public int count(){ return count; }

   public boolean connected(int p, int q){
      return find(p) == find(q);
   }

   public int find(int p){
      validate(p);
      while(p != id[p]){
         id[p] = id[id[p]];
         p = id[p];
      }
      return p;
   }

   public void union(int p, int q){
      int pRoot = find(p);
      int qRoot = find(q);
      if(pRoot == qRoot) return;
      if(sz[pRoot] < sz[qRoot]){
         id[pRoot] = qRoot;
         sz[qRoot] += sz[pRoot];
      }else{
         id[qRoot] = pRoot;
         sz[pRoot] += sz[qRoot];
      }
      count--;
   }

   private void validate(int p){
      int n = id.length;
      if(p < 0 || p >= n)
         throw new IllegalArgumentException(p + " is not between 0 and " + (n-1));
   }

   public static void main(String[] args){
      int N = StdIn.readInt();
      WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(N);
      while(!StdIn.isEmpty()){
         int p = StdIn.readInt();
         int q = StdIn.readInt();
         if(weightedQuickUnionUF.connected(p, q)) continue;
         weightedQuickUnionUF.union(p, q);
         // StdOut.println(p + " " + q);
      }
      StdOut.println(weightedQuickUnionUF.count() + " components");
   }

}
