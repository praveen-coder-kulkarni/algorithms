public class AddLeftParentheses {

   private static final String LEFT_PARENTHESES = "(";
   private static final String RIGHT_PARENTHESES = ")";

   private static void addLeftParentheses(String input){
      String[] s = input.split(" ");
      Stack<String> operand = new Stack<String>();
      Stack<String> operator = new Stack<String>();
      for(int i = 0 ; i < s.length ; i++){
         String c = s[i];
         if(isOperand(c))
            operand.push(c);
         else if(isOperator(c))
            operator.push(c);
         else if(c.equals(RIGHT_PARENTHESES)){
            String secondOperand = operand.pop();
            String firstOperand = operand.pop();
            String result = LEFT_PARENTHESES + firstOperand + operator.pop() + secondOperand + RIGHT_PARENTHESES;
            operand.push(result);
         }
      }
      char[] ch = operand.pop().toCharArray();

      for(int k = 0 ; k < ch.length ; k++){
         StdOut.print(ch[k]);
         StdOut.print(" ");
      }
      StdOut.println();
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
      addLeftParentheses(input);
   }
}
