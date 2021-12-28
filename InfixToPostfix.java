public class InfixToPostfix {

   private static final String LEFT_PARENTHESES = "(";
   private static final String RIGHT_PARENTHESES = ")";

   private static String infixToPostFix(String input){
      String[] s = input.split(" ");
      Stack<String> operand = new Stack<String>();
      Stack<String> operator = new Stack<String>();
      for(int i = 0 ; i < s.length ; i++){
         String c = s[i];
         if(c.equals(LEFT_PARENTHESES))
            continue;
         else if(isOperand(c))
            operand.push(c);
         else if(isOperator(c))
            operator.push(c);
         else if(c.equals(RIGHT_PARENTHESES)){
            String secondOperand = operand.pop();
            String firstOperand = operand.pop();
            String result = firstOperand + secondOperand + operator.pop();
            operand.push(result);
         }
      }
      return operand.pop();
   }

   private static boolean isOperand(String s){
      for(int i = 0 ; i < s.length() ; i++)
         if(s.charAt(i) < '0' || s.charAt(i) > '9')
            return false;
      return true;
   }

   private static boolean isOperator(String s){
      return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
   }

   public static void main(String[] args) {
      String input = StdIn.readLine();
      String result = infixToPostFix(input);
      for(int i = 0 ; i < result.length() ; i++)
         StdOut.print(result.charAt(i) + " ");
      StdOut.println();
   }
}
