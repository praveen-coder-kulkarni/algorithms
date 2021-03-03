import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item>{

	private Node<Item> first;
	private int N;

	private class Node<Item>{
		Item item;
		Node next;
	}

	public Bag(){
		first  = null;
		N = 0;
	}

	public void add(Item item){
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}

	public boolean isEmpty(){
		return first == null || N == 0;
	}

	public int size(){
		return N;
	}

	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item>{
		Node<Item> x;
		public ListIterator(Node<Item> x){
			this.x = x;
		}

		public boolean hasNext(){return x != null;}
		public Item next(){
			if(!hasNext())	throw new NoSuchElementException("Bag empty");
			Item item = x.item;
			x = x.next;
			return item;
		}
		public void remove(){throw new UnsupportedOperationException("Remove not supported for Bag iterator");}

	}

	public static void main(String[] args) {
		Bag<String> b = new Bag<String>();
		while(!StdIn.isEmpty()){
			b.add(StdIn.readString());
		}
		System.out.println("Elements in bag are: ");
		for(String s : b)
			System.out.println(s);
		StdOut.println("Total elements in bag: " + b.size());
	}
}