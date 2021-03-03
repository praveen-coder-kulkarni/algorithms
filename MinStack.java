import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinStack<Item extends Comparable> implements Iterable<Item>{

	private Node<Item> first;
	private int N;

	private class Node<Item>{
		Item item;
		Item min;
		Node next;
	}

	public boolean isEmpty(){
		return first == null || N == 0;
	}

	public int size(){
		return N;
	}

	public Item getMin(){
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty!!");
		return first.min;
	}

	public void push(Item item){
		if(first == null){
			first = new Node<Item>();
			first.item = item;
			first.min = item;
			N++;
			return;
		}
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		if(item.compareTo(oldFirst.min) < 0){
			first.min = item;
		}else{
			first.min = oldFirst.min;
		}
		first.next = oldFirst;
		N++;
	}

	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty!!");
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty!!");
		return first.item;
	}

	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item>{
		
		private Node<Item> current;
		
		public ListIterator(Node<Item> node){
			current = node;
		}

		public boolean hasNext(){return current != null;}

		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException("Stack is empty!!");
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove(){throw new UnsupportedOperationException("Remove can't be invoked in iterator!!");}

	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Stack has " + size() + " elements:\n");
		sb.append("[ ");
		for(Item item : this)
			sb.append(item + " ");
		sb.append("]\n");
		sb.append("Current minimum: " + getMin() + "\n");
		return sb.toString();
	}

	public static void main(String[] args){
		MinStack<Integer> minStack = new MinStack<Integer>();
		
		minStack.push(25);
		minStack.push(30);
		minStack.push(34);
		minStack.push(14);
		minStack.push(12);
		minStack.push(7);
		minStack.push(10);
		System.out.println(minStack);
		minStack.pop();
		System.out.println(minStack);
		minStack.pop();
		System.out.println(minStack);
		minStack.pop();
		System.out.println(minStack);
		minStack.pop();

	}

}