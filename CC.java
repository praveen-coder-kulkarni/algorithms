public class CC{
	private int count;
	private int[] id;
	private boolean[] marked;

	public CC(Graph G){
		id = new int[G.V()];
		marked = new boolean[G.V()];
		count = 0;
		for(int i = 0 ; i < G.V() ; i++){
			if(!marked[i]){
				dfs(G, i);
				count++;
			}
		}
	}

	private void dfs(Graph G, int v){
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)){
			if(!marked[w]){
				dfs(G, w);
			}
		}
	}

	public int count(){
		return count;
	}

	public int id(int v){
		return id[v];
	}

	public static void main(String[] args) {
		String url = args[0];
		In in = new In(url);
		Graph G = new Graph(in);
		CC cc = new CC(G);
		for(int v = 0 ; v < G.V() ; v++){
			StdOut.print(v + " : ");
			for(int w : G.adj(v)){
				StdOut.print(w + " ");
			}
			StdOut.println();
		}
		StdOut.println("Number of connected components are: " + cc.count() + " and they are as follows.");
		for(int i = 0 ; i < cc.count() ; i++){
			StdOut.print("Vertices part of component number " + (i + 1) + " are : ");
			for(int v = 0 ; v < G.V() ; v++){
				if(cc.id(v) == i)
					StdOut.print(v + " ");
			}
			StdOut.println();
		}

	}
}