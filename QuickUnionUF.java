public class QuickUnionUF {

   private int[] id;
   private int count;

   public QuickUnionUF(int n){
      id = new int[n];
      for(int i = 0 ; i < n ; i++) id[i] = i;
      count = n;
   }

   public int count(){ return count; }

   public boolean connected(int p, int q){
      return find(p) == find(q);
   }

   public int find(int p){
      validate(p);
      while(p != id[p]) p = id[p];
      return p;
   }

   public void union(int p, int q){
      int pRoot = find(p);
      int qRoot = find(q);
      if(pRoot == qRoot) return;
      id[pRoot] = qRoot;
      count--;
   }

   private void validate(int p){
      int n = id.length;
      if(p < 0 || p >= n)
         throw new IllegalArgumentException(p + " is not between 0 and " + (n-1));
   }

   public static void main(String[] args){
      int N = StdIn.readInt();
      QuickUnionUF quickUnionUF = new QuickUnionUF(N);
      while(!StdIn.isEmpty()){
         int p = StdIn.readInt();
         int q = StdIn.readInt();
         if(quickUnionUF.connected(p, q)) continue;
         quickUnionUF.union(p, q);
         StdOut.println(p + " " + q);
      }
      StdOut.println(quickUnionUF.count() + " components");
   }

}
