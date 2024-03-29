public class Selection {
   
   public static void sort(Comparable[] a){
      int N = a.length;
      for(int i = 0 ; i < N ; i++){
         int min = i;
         for(int j = i + 1 ; j < N ; j++){
            if(less(a[j], a[min]))
               min = j;
         }
         exch(a, i, min);
      }
   }
   
   private static boolean less(Comparable u, Comparable v){ return u.compareTo(v) < 0; }

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
