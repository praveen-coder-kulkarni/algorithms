public class MinOfSubArrays{
	public static void main(String[] args) {
		int sub_array_size = Integer.parseInt(args[0]);
		int N = Integer.parseInt(args[1]);
		int[] A = ArrayOperations.readInts(N);
		find_min_subarrays(A, sub_array_size);
	}

	public static void find_min_subarrays(int[] A, int K){
		int index = 0;
		MinQueue<Integer> minQueue = new MinQueue<>();
		while(index < K){
			minQueue.enqueue(A[index]);
			index++;
		}
		int startIndex = 0;
		while(index < A.length){
			System.out.println("Min between indices [" + startIndex + "," + (index-1) + "]: " + minQueue.getMin());
			minQueue.dequeue();
			minQueue.enqueue(A[index]);
			startIndex++;
			index++;
		}
		System.out.println("Min between indices [" + startIndex + "," + (index-1) + "]: " + minQueue.getMin());
	}
}