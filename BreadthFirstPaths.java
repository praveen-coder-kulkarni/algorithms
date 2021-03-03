public class BreadthFirstPaths{

	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstPaths(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		bfs(G, s);
	}

	private void bfs(Graph G, int v){
		for(int i = 0 ; i < G.V() ; i++){
			distTo[i] = INFINITY;
		}
		Queue<Integer> q = new Queue<Integer>();
		marked[v] = true;
		distTo[v] = 0;
		q.enqueue(v);
		while(!q.isEmpty()){
			int p = q.dequeue();
			for(int w : G.adj(p)){
				if(!marked[w]){
					q.enqueue(w);
					edgeTo[w] = p;
					distTo[w] = distTo[p] + 1;
					marked[w] = true;
				}
			}
		}
	}

	public boolean hasPathTo(int v){
		return marked[v];
	}

	public int distTo(int v){
		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<Integer> path = new Stack<Integer>();
		int i;
		for(i = v ; distTo[i] != 0 ; i = edgeTo[i])
			path.push(i);
		path.push(i);
		return path;
	}

	public static void main(String[] args) {
		String url = args[0];
		int sourceVertex = Integer.parseInt(args[1]);
		In in = new In(url);
		Graph g = new Graph(in);
		BreadthFirstPaths bfsPaths = new BreadthFirstPaths(g, sourceVertex);
		for(int v = 0 ; v < g.V() ; v++)
			if(bfsPaths.hasPathTo(v)){
				StdOut.print(v + " : ");
				for(int u : bfsPaths.pathTo(v)){
					StdOut.print(u + " ");
				}
				StdOut.println(", Total hops = " + bfsPaths.distTo[v]);
			}
	}
}