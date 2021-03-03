public class DeapthFirstPaths{

	private final int s;
	private boolean[] marked;
	private int[] edgeTo;

	public DeapthFirstPaths(Graph G, int s){
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v){
		marked[v] = true;
		for(int w : G.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v){
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int i = v ; i != s ; i = edgeTo[i])
			path.push(i);
		path.push(s);
		return path;
	}

	public static void main(String[] args) {
		String url = args[0];
		int sourceVertex = Integer.parseInt(args[1]);
		In in = new In(url);
		Graph g = new Graph(in);
		DeapthFirstPaths dfsPaths = new DeapthFirstPaths(g, sourceVertex);
		for(int v = 0 ; v < g.V() ; v++)
			if(dfsPaths.hasPathTo(v)){
				StdOut.print(v + " : ");
				for(int u : dfsPaths.pathTo(v)){
					StdOut.print(u + " ");
				}
				StdOut.println();
			}
	}
}