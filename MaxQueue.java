import java.util.NoSuchElementException;
import java.util.Iterator;

public class MaxQueue<Item extends Comparable> implements Iterable<Item>{
	
	private MaxStack<Item> s1, s2;

	public MaxQueue(){
		s1 = new MaxStack<Item>();
		s2 = new MaxStack<Item>();
	}
	
	public boolean isEmpty(){
		return s1.isEmpty() && s2.isEmpty();
	}

	public int size(){
		return s1.size() + s2.size();
	}

	public Item getMax(){
		if(isEmpty())
			throw new NoSuchElementException("Queue is empty!!");
		if(s1.isEmpty())
			return s2.getMax();
		if(s2.isEmpty())
			return s1.getMax();
		return s1.getMax().compareTo(s2.getMax()) > 0 ? s1.getMax() : s2.getMax();
	}

	public void enqueue(Item item){
		s1.push(item);
	}

	public Item dequeue(){
		if(isEmpty())
			throw new NoSuchElementException("Queue is empty!!");
		if(s2.isEmpty()){
			while(!s1.isEmpty()){
				s2.push(s1.pop());
			}
		}
		Item item = s2.pop();
		return item;
	}

	public Iterator<Item> iterator(){
		return new ListIterator(s1, s2);
	}

	private class ListIterator implements Iterator<Item>{
		private MaxStack<Item> c1, c2;

		public ListIterator(MaxStack<Item> s1, MaxStack<Item> s2){
			this.c1 = s1;
			this.c2 = s2;
		}

		public boolean hasNext(){
			return !c2.isEmpty() || !c1.isEmpty();
		}

		public Item next(){
			if(!hasNext())	throw new NoSuchElementException("Queue empty!!");
			if(c2.isEmpty()){
				while(!c1.isEmpty()){
					c2.push(c1.pop());
				}
			}	
			return c2.pop();
		}

		public void remove(){
			throw new UnsupportedOperationException("Remove operation not supported!!");
		}
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Queue<Item> queue = new Queue<Item>();
		Stack<Item> stack = new Stack<Item>();
		sb.append("MaxQueue has " + size() + " elements:\n");
		sb.append("[ ");
		for(Item item : this.s2)
			queue.enqueue(item);
		for(Item item : this.s1)
			stack.push(item);
		while(!stack.isEmpty()){
			queue.enqueue(stack.pop());
		}
		while(!queue.isEmpty()){
			sb.append(queue.dequeue() + " ");
		}
		sb.append(" ]\n");
		sb.append("Current maximum: " + getMax() + "\n");
		return sb.toString();
	}

	public static void main(String[] args) {
		MaxQueue<Integer> minQueue = new MaxQueue<Integer>();
		minQueue.enqueue(3);
		minQueue.enqueue(2);
		System.out.println(minQueue);
		minQueue.dequeue();
		System.out.println(minQueue);
		minQueue.enqueue(101);
		minQueue.enqueue(9);
		minQueue.enqueue(6);
		minQueue.enqueue(11);
		minQueue.enqueue(10);
		minQueue.enqueue(5);
		minQueue.enqueue(-5);
		System.out.println(minQueue);

	}
}

