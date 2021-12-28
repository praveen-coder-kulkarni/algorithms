public class Quick {

   private static final int CUTOFF = 15;

   public static void sort(Comparable[] a){
      int n = a.length;
      StdRandom.shuffle(a);
      sort(a, 0, n-1);
   }

   private static void sort(Comparable[] a, int lo, int hi){
      if(hi <= lo + CUTOFF){
         Insertion.sort(a);
         return;
      }
      int j = partition(a, lo, hi);
      sort(a, lo, j-1);
      sort(a, j+1, hi);
   }

   private static int partition(Comparable[] a, int lo, int hi){
      int i = lo;
      int j = hi + 1;
      Comparable v = a[lo];
      while(true){
         while(less(a[++i], v))
            if(i == hi) break;
         while(less(v, a[--j]))
            if(j == lo) break;
         if(j <= i) break;
         exch(a, i, j);
      }
      exch(a, lo, j);
      return j;
   }

   private static boolean less(Comparable u, Comparable v){
      return u.compareTo(v) < 0;
   }

   private static void exch(Comparable[] a, int i, int j){
      Comparable t = a[i];
      a[i] = a[j];
      a[j] = t;
   }

   public static boolean isSorted(Comparable[] a){
      for(int i = 1 ; i < a.length ; i++)
         if(less(a[i], a[i-1]))
            return false;
      return true;
   }

   public static void show(Comparable[] a){
      for(int i = 0 ; i < a.length ; i++)
         StdOut.print(a[i] + " ");
      StdOut.println();
   }

   public static void main(String[] args){
      String[] a = In.readStrings();
      sort(a);
      show(a);
   }

}
