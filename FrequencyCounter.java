public class FrequencyCounter {
   public static void main(String[] args) {
      int minLen = Integer.parseInt(args[0]);
      BST<String, Integer> st = new BST<>();
      while(!StdIn.isEmpty()){
         String s = StdIn.readString();
         if(s.length() <= minLen)
            continue;
         if(st.get(s) != null)
            st.put(s, st.get(s) + 1);
         else
            st.put(s, 1);
      }
      String max = "";
      st.put(max, 0);
      for(String s : st.keys()){
//         StdOut.println("key: " + s + ", value: " + st.get(s));
         if(st.get(s) > st.get(max))
            max = s;
      }
      StdOut.println(max + " " + st.get(max));
   }
}
