public class Parentheses {
   public static void main(String[] args){
      String expression = StdIn.readString();
      StdOut.println(isBalanced(expression));
   }

   private static boolean isBalanced(String expression) {
      Stack<Character> stack = new Stack<Character>();
      for(int i = 0 ; i < expression.length(); i++){
         char c = expression.charAt(i);
         if(c == '(' || c == '[' || c == '{')
            stack.push(c);
         else if(c == ')' && !stack.isEmpty() && stack.peek() == '(')
            stack.pop();
         else if(c == '}' && !stack.isEmpty() && stack.peek() == '{')
            stack.pop();
         else if(c == ']' && !stack.isEmpty() && stack.peek() == '[')
            stack.pop();
      }
      return stack.isEmpty();
   }
}
