public class BST<Key extends Comparable<Key>, Value>{

	private Node root;

	private class Node{
		Key key;
		Value value;
		Node left, right;
		int N;

		public Node(Key key, Value value, int N){
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}

	public boolean isEmpty(){ return root == null; }

	public int size(){ return size(root); }

	private int size(Node x){ return x == null ? 0 : x.N; }

	public boolean contains(Key key){ return get(key) != null; }

	public Value get(Key key){ return get(root, key); }

	private Value get(Node x, Key key){
		if(x == null) return null;
		int c = key.compareTo(x.key);
		if(c < 0)	return get(x.left, key);
		else if(c > 0)	return get(x.right, key);
		return x.value;
	}
	public void put(Key key, Value value){ root = put(root, key, value); }

	private Node put(Node x, Key key, Value value){
		if(x == null)	return new Node(key, value, 1);
		int c = key.compareTo(x.key);
		if(c < 0)	x.left = put(x.left, key, value);
		else if(c > 0)	x.right = put(x.right, key, value);
		else	x.value = value;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Key min(){
		Node x = min(root);
		if(x == null)	return null;
		return x.key;
	}

	private Node min(Node x){
		if(x == null)	return null;
		if(x.left == null)	return x;
		return min(x.left);
	}

	public Key max(){
		Node x = max(root);
		if(x == null)	return null;
		return x.key;
	}

	private Node max(Node x){
		if(x == null)	return null;
		if(x.right == null)	return x;
		return max(x.right);
	}

	public Key floor(Key key){
		Node x = floor(root, key);
		if(x == null)	return null;
		return x.key;
	}

	private Node floor(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)	return x;
		else if(cmp < 0)	return floor(x.left, key);
		Node t = floor(x.right, key);
		if(t != null)	return t;
		return x;
	}

	public Key ceiling(Key key){
		Node x = ceiling(root, key);
		if(x == null)	return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)	return x;
		else if(cmp > 0) return ceiling(x.left, key);
		Node t = ceiling(x.right, key);
		if(t != null)	return t;
		return x;
	}

	public Key select(int rank){
		Node x = select(root, rank);
		if(x == null)	return null;
		return x.key;
	}

	private Node select(Node x, int rank){
		if(x == null)	return null;
		int k = size(x.left);
		if(k < rank)		return select(x.left, rank);
		else if(k > rank)	return select(x.right, rank - (1 + k));
		else					return x;
	}

	public int rank(Key key){ return rank(root, key); }

	private int rank(Node x, Key key){
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) 		return rank(x.left, key);
		else if(cmp > 0)	return 1 + size(x.left) + rank(x.right, key);
		else 					return size(x.left);
	}

	public void delMin(){ root = delMin(root); }

	private Node delMin(Node x){
		if(x == null) return null;
		if(x.left == null) return x.right;
		x.left = delMin(x.left);
		x.N = 1 + size(x.left) + size(x.right);
		return x.right;
	}

	public void delMax(){ root = delMax(root); }

	private Node delMax(Node x){
		if(x == null) return null;
		if(x.right == null) return x.left;
		x.right = delMax(x.right);
		x.N = 1 + size(x.left) + size(x.right);
		return x.left;
	}

	public void delete(Key key){ root = delete(root, key); }

	private Node delete(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) 		x.left = delete(x.left, key);
		else if(cmp > 0)	x.right = delete(x.right, key);
		else{
			if(x.right == null)	return x.left;
			if(x.left == null)	return x.right;
			Node t = x;
			x = min(t.right);
			x.right = delMin(t.right);
			x.left = t.left;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Iterable<Key> keys(){ return keys(min(), max()); }

	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		keys(root, lo, hi, queue);
		return queue;
	}

	private void keys(Node x, Key lo, Key hi, Queue<Key> queue){
		if(x == null)	return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, lo, hi, queue);
		if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
		if(cmphi > 0) keys(x.right, lo, hi, queue);
	}
}