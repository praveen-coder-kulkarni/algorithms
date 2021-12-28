public class SortCompare {

   public static void main(String[] args){
      String alg1 = args[0];
      String alg2 = args[1];
      int N = Integer.parseInt(args[2]);
      int T = Integer.parseInt(args[3]);
      double t1 = timeRandomInput(alg1, N, T);
      double t2 = timeRandomInput(alg2, N, T);
      StdOut.printf("Time taken by %s sort is: %f\n", alg1, t1);
      StdOut.printf("Time taken by %s sort is: %f\n", alg2, t2);
      StdOut.printf("For %d random doubles, %s sort is faster than %s sort by a factor of %.2f\n", N, alg1, alg2, t2/t1);
   }

   public static double timeRandomInput(String alg, int N, int T){
      double total = 0.0;
      Double[] a = new Double[N];
      for(int t = 1 ; t <= T ; t++){
         for(int i = 0 ; i < N ; i++)
            a[i] = StdRandom.uniform();
         total += time(alg, a);
      }
      return total;
   }

   public static double time(String alg, Comparable[] a){
      StopWatch timer = new StopWatch();
      if(alg.equals("Insertion"))   Insertion.sort(a);
      else if(alg.equals("Selection")) Selection.sort(a);
      else if(alg.equals("Merge")) Merge.sort(a);
      else if(alg.equals("Quick")) Quick.sort(a);
      else{
         throw new IllegalArgumentException("No " + alg + " algorithm found to sort comparable items");
      }
      return timer.elapsedTime();
   }
}
