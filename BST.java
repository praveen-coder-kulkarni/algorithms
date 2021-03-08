public class BST<Key extends Comparable<Key>, Value>{

	private Node root;

	private class Node{
		Key key;
		Value value;
		Node left, right;

		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}

	public void put(Key key, Value value){
		root = put(root, key, value);
	}

	public Value get(Key key){
		return get(root, key);
	}

	public void remove(Key key){

	}

	public Iterable<Key> iterator(){
		return null;
	}

	public Key min(){
		return min(root);
	}

	public Key max(){
		return max(root);
	}

	public Key floor(Key key){
		Node node = floor(root, key);
		if(node == null)	return null;
		return node.key;
	}

	public Key ceil(Key key){
		Node node = ceil(root, key);
		if(node == null)	return null;
		return node.key;
	}

	private Value get(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)			return get(x.left, key);
		else if(cmp > 0)	return get(x.right, key);
		else 				return x.value;
	}

	private Node put(Node x, Key key, Value value){
		if(x == null)	return new Node(key, value);
		int cmp = key.compareTo(x.key);
		if(cmp < 0)			x.left = put(x.left, key, value);
		else if(cmp > 0)	x.right = put(x.right, key, value);
		else 				x.value = value;
		return x;
	}

	private Key min(Node x){
		if(x == null)		return null;
		if(x.left != null)	return min(x.left);
		else 				return x.key;
	}

	private Key max(Node x){
		if(x == null)		return null;
		if(x.right != null)	return max(x.right);
		else 				return x.key;
	}

	private Node floor(Node x, Key key){
		if(x == null)		return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)			return floor(x.left, key);
		else if(cmp > 0){
			Node t = floor(x.right, key);
			if(t != null)	return  t;
			else 			return x;
		}else{
			return x;
		}
	}

	private Node ceil(Node x,  Key key){
		if(x == null)		return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0)			return ceil(x.right, key);
		else if(cmp < 0){
			Node t = ceil(x.left,  key);
			if(t != null)	return t;
			else 			return ceil(x.right, key);
		}else{
			return x;
		}
	}

}