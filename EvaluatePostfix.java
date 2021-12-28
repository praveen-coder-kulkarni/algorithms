public class EvaluatePostfix {

   private static boolean isOperator(String s){
      return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
   }

   private static boolean isOperand(String s){
      for(int i = 0 ; i < s.length() ; i++)
         if(s.charAt(i) < '0' || s.charAt(i) > '9')
            return false;
      return true;
   }

   public static void main(String[] args) {
      Stack<Integer> result = new Stack<Integer>();
      while(!StdIn.isEmpty()){
         String s = StdIn.readString();
         if(isOperator(s)){
            int op2 = result.pop();
            int op1 = result.pop();
            if(s.equals("+"))
               result.push(op1 + op2);
            else if(s.equals("-"))
               result.push(op1 - op2);
            else if(s.equals("*"))
               result.push(op1 * op2);
            else if(s.equals("/"))
               result.push(op1 / op2);
         }else if(isOperand(s)){
            result.push(Integer.parseInt(s));
         }
      }
      StdOut.println(result);
   }

}
