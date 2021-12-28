public class DecimalToBinary {
   public static void main(String[] args){
      int N = Integer.parseInt(args[0]);
      StdOut.printf("Binary value of decimal number %d: %s\n", N, decToBinStack(N));
   }

   private static String decToBinStack(int n) {
      String s = "";
      Stack<Integer> stack = new Stack<Integer>();
      while(n > 0){
         stack.push(n % 2);
         n /= 2;
      }
      for(int i : stack)
         s += i;
      return s;
   }
}
