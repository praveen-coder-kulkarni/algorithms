import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxStack<Item extends Comparable> implements Iterable<Item>{

	private Node<Item> first;
	private int N;

	private class Node<Item>{
		Item item;
		Item max;
		Node next;
	}

	public boolean isEmpty(){
		return first == null || N == 0;
	}

	public int size(){
		return N;
	}

	public Item getMax(){
		if(isEmpty())
			throw new NoSuchElementException("Stack is empty!!");
		return first.max;
	}

	public void push(Item item){
		if(first == null){
			first = new Node<Item>();
			first.item = item;
			first.max = item;
			N++;
			return;
		}
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		if(item.compareTo(oldFirst.max) > 0){
			first.max = item;
		}else{
			first.max = oldFirst.max;
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
		sb.append("Current maximum: " + getMax() + "\n");
		return sb.toString();
	}

	public static void main(String[] args){
		MaxStack<Integer> maxStack = new MaxStack<Integer>();
		maxStack.push(7);
		maxStack.push(10);
		maxStack.push(25);
		maxStack.push(30);
		maxStack.push(34);
		maxStack.push(14);
		maxStack.push(12);
		
		System.out.println(maxStack);
		maxStack.pop();
		System.out.println(maxStack);
		maxStack.pop();
		System.out.println(maxStack);
		maxStack.pop();
		System.out.println(maxStack);
		maxStack.pop();

	}

}