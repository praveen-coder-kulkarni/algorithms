public class Merge {

   private static final int CUTOFF = 15;
   private static Comparable[] aux;
   public static void sort(Comparable[] a){
      int N = a.length;
      aux = new Comparable[N];
      sort(a, 0, N-1);
   }

   private static void sort(Comparable[] a, int lo, int hi){
      if(hi <= lo + CUTOFF){
         Insertion.sort(a);
         return;
      }
      if(hi <= lo)   return;
      int mid = lo + (hi - lo) / 2;
      sort(a, lo, mid);
      sort(a, mid+1, hi);
      merge(a, lo, mid, hi);
   }

   private static void merge(Comparable[] a, int lo, int mid, int hi){
      if(less(a[mid+1], a[mid]))
         return;
      int i = lo;
      int j = mid + 1;
      int k = 0;
      for(k = lo ; k <= hi; k++)
         aux[k] = a[k];
      for(k = lo ; k <= hi ; k++){
         if(i > mid)                   a[k] = aux[j++];
         else if(j > hi)               a[k] = aux[i++];
         else if(less(aux[j], aux[i])) a[k] = aux[j++];
         else                          a[k] = aux[i++];
      }
   }

   private static boolean less(Comparable u, Comparable v){
      return u.compareTo(v) < 0;
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
