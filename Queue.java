import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item>{

	private Node<Item> first;
	private Node<Item> last;
	private int N;

	private class Node<Item>{
		Item item;
		Node next;
	}

	public Queue(){
		first  = null;
		last = null;
		N = 0;
	}

	public void enqueue(Item item){
		if(isEmpty()){
			first = new Node();
			first.item = item;
			last = first;
		}else{
			Node oldLast = last;
			last = new Node();
			last.item = item;
			oldLast.next = last;
		}
		N++;
	}

	public Item dequeue(){
		if(isEmpty())
			throw new RuntimeException("Queue Underflow!!");
		Item item = first.item;
		first = first.next;
		N--;
		return item;
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
			if(!hasNext())	throw new NoSuchElementException("Queue empty");
			Item item = x.item;
			x = x.next;
			return item;
		}
		public void remove(){throw new UnsupportedOperationException("Remove not supported for Queue iterator");}

	}

	public static void main(String[] args) {
		Queue<String> b = new Queue<String>();
		while(!StdIn.isEmpty()){
			b.enqueue(StdIn.readString());
		}
		System.out.println("Elements in Queue are: ");
		for(String s : b)
			System.out.println(s);
		StdOut.println("Total elements in Queue: " + b.size());
	}
}