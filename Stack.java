import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{

	private Node<Item> first;
	private int N;

	private class Node<Item>{
		Item item;
		Node next;
	}

	public Stack(){
		first  = null;
		N = 0;
	}

	public void push(Item item){
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}

	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("Stack Underflow!!");
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

			public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Stack Underflow!!");
		return first.item;
	}

	public boolean isEmpty(){
		return first == null || N == 0;
	}

	public int size(){
		return N;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Item item : this){
			sb.append(item);
			sb.append(' ');
		}
		return sb.toString();
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
			if(!hasNext())	throw new NoSuchElementException("Stack empty");
			Item item = x.item;
			x = x.next;
			return item;
		}
		public void remove(){throw new UnsupportedOperationException("Remove not supported for Stack iterator");}

	}

	public static void main(String[] args) {
		Stack<String> b = new Stack<String>();
		while(!StdIn.isEmpty()){
			String string = StdIn.readString();
			if(string.equals("-"))
				b.pop();
			else
				b.push(string);
		}
		System.out.println("Elements in Stack are: ");
		for(String s : b)
			System.out.println(s);
		StdOut.println("Total elements in Stack: " + b.size());
	}
}