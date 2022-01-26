public class Graph{
	private int V;
	private int E;
	private Bag<Integer> adj[];

	public Graph(int V){
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[this.V];
		for(int v = 0 ; v < V ; v++)
			adj[v] = new Bag<Integer>();
	}

	public Graph(In in){
		this.V = in.readInt();
		this.E = in.readInt();
		adj = (Bag<Integer>[]) new Bag[this.V];
		for(int v = 0 ; v < V ; v++)
			adj[v] = new Bag<Integer>();
		for(int i = 0 ; i < this.E ; i++){
			int u = in.readInt();
			int v = in.readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
	}

	public Graph(Graph G){
		this(G.V());
		for(int v = 0 ; v < G.V() ; v++)
			for(int w : G.adj(v))
				addEdge(v, w);
	}

	public int V(){return this.V;}
	public int E(){return this.E;}
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		this.E++;
	}

	public boolean hasEdge(int v, int w){
		for(int u : adj(v))
			if(u == w)	return true;
		return false;
	}

	public Iterable<Integer> adj(int v){
		return adj[v];
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.V).append(" vertices, ").append(this.E).append(" edges").append("\n");
		for(int v = 0 ; v < this.V ; v++){
			sb.append(v).append(": ");
			for(int w : adj[v]){
				sb.append(w + " ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String url = args[0];
		In in = new In(url);
		Graph g = new Graph(in);
		StdOut.println(g);
	}

}