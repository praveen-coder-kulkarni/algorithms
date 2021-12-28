public class LSD{
	public static void sort(String[] a, int W){
		int N = a.length;
		String[] aux = new String[N];
		int R = 256;

		StdOut.println("Strings before sorting:");
			for(int i = 0 ; i < N ; i++)
				StdOut.println(a[i]);

		for(int d = W-1 ; d >= 0 ; d--){
			int[] count = new int[R+1];
			StdOut.println("d = " + d + ":");
			// Find frequncy of dth character in all strings
			
			for(int i = 0 ; i < N ; i++){
				// StdOut.println("i = " + i);
				count[a[i].charAt(d) + 1]++;
			}

			StdOut.println("Frequencies of each charater:");
			for(int r = 0 ; r < R ; r++){
				StdOut.print(count[r] + " ");
			}
			StdOut.println();
			// Convert frequncies to range of indices
			for(int r = 0 ; r < R ; r++){
				count[r+1] += count[r];
			}

			StdOut.println("Frequency range:");
			for(int r = 0 ; r <= R ; r++){
				StdOut.print(count[r] + " ");
			}

			// Distribute strings based on frequncy range
			for(int i = 0 ; i < N ; i++){		
				aux[count[a[i].charAt(d)]++] = a[i];
			}

			//Copy back
			for(int i = 0 ; i < N ; i++)
				a[i] = aux[i];

			StdOut.println("\nStrings after scanning " + d + "th character from right");
			for(int i = 0 ; i < N ; i++)
				StdOut.println(a[i]);
		}
	}

	public static void main(String[] args){
		String[] words = {"4PGC938","2IYE230","3CIO720","1ICK750","1OHV845","4JZY534","1ICK750","3CIO720","1OHV845","2RLA629","2RLA629","3ATW723"};
		LSD.sort(words, words[0].length());
		for(int i = 0 ; i < words.length ; i++)
			StdOut.println(words[i]);
	}
}