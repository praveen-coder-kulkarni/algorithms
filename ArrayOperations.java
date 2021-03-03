import java.util.Scanner;

public class ArrayOperations{
	private static final Scanner sc = new Scanner(System.in);

	public static int[] readInts(int N){
		int[] items = new int[N];
		for(int i = 0 ; i < N ; i++)
			items[i] = sc.nextInt();
		return items;
	}
}