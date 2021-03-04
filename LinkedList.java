class LinkedList<Item>{
	Node<Item> head;

	private static class Node<Item>{
		Item item;
		Node<Item> next;
		public Node(Item item){
			this.item = item;
			this.next = null;
		}
	}

	public void printLinkedList(){
		Node node = this.head;
		while(node != null){
			System.out.print(node.item + " ");
			node = node.next;
		}
		System.out.println();
	}

	public void insertAtFront(Item item){
		Node new_node = new Node(item);
		new_node.next = this.head;
		this.head = new_node;
	}

	public void insertAfter(Node prev_node, Item item){
		if(prev_node == null){
			System.out.println("Item can not be inserted as node after which item needs to be inserted is null!");
			return;
		}
		Node new_node = new Node(item);
		new_node.next = prev_node.next;
		prev_node.next = new_node;
	}

	public void insertAtEnd(Item item){
		Node new_node = new Node(item);
		if(this.head == null){
			this.head = new_node;
			return;
		}
		Node first = this.head;
		Node second = first.next;
		while(second != null){
			second = second.next;
			first = first.next;
		}
		first.next = new_node;
	}

	public boolean deleteNode(Item key){
		Node prev_node = null;
		Node cursor = this.head;
		if(this.head != null && this.head.item.equals(key)){
			this.head = this.head.next;
			return true;
		}
		while(cursor != null){
			if(cursor.item.equals(key)){
				prev_node.next = cursor.next;
				cursor.next = null;
				return true;
			}
			prev_node = cursor;
			cursor = cursor.next;
		}
		return false;
	}

	public boolean deleteNodeAtPosition(int position){
		Node cursor = this.head;
		if(cursor == null)
			return false;
		if(position == 0){
			this.head = cursor.next;
			return true;
		}
		for(int i = 0 ; i < position-1 && cursor != null; i++)
			cursor = cursor.next;
		if(cursor == null || cursor.next == null)
			return false;
		cursor.next = cursor.next.next;
		return true;
	}

	public int getCount(){
		Node temp = this.head;
		int count = 0;
		while(temp != null){
			count += 1;
			temp = temp.next;
		}
		return  count;
	}

	public int getCountRecursive(){
		return getCountRecursive(this.head);
	}

	private int getCountRecursive(Node t){
		if(t == null)	return 0;
		return 1 + getCountRecursive(t.next);
	}

	public boolean search(Item item){
		Node current = this.head;
		while(current != null){
			if(current.item.equals(item))
				return true;
			current = current.next;
		}
		return false;
	}

	public boolean searchRecursive(Item item){
		return searchRecursive(this.head, item);
	}

	private boolean searchRecursive(Node t, Item item){
		if(t == null)
			return false;
		if(t.item.equals(item))
			return true;
		return searchRecursive(t.next, item);
	}

	public Item getNth(int index){
		Node current = this.head;
		int count = 0;
		while(current != null && count != index){
			current = current.next;
			count += 1;
		}
		if(current == null)
			return null;
		return (Item)current.item;
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.head = new Node(1);
		Node second = new Node(20);
		Node third = new Node(3);

		list.head.next = second;
		second.next = third;

		list.printLinkedList();

		list.insertAtFront(100);

		list.printLinkedList();

		list.insertAfter(second, 200);

		list.printLinkedList();

		list.insertAtEnd(500);

		list.printLinkedList();

		if(list.deleteNode(100)){
			System.out.println("Deletion of node containing 100 is successful");
			list.printLinkedList();
		}

		list.deleteNodeAtPosition(4);

		list.printLinkedList();

		System.out.println("Number of nodes in the linked list is: " + list.getCount());
		System.out.println("Number of nodes in the linked list is via getCountRecursive(): " + list.getCount());
		System.out.println("Is 500 found in the list?: " + list.search(500));
		System.out.println("Is 200 found in the list?: " + list.search(200));
		System.out.println("Is 500 found in the list(recursive)?: " + list.searchRecursive(500));
		System.out.println("Is 200 found in the list(recursive)?: " + list.searchRecursive(200));
		System.out.println("getNth(2) = " + list.getNth(2));
		System.out.println("getNth(4) = " + list.getNth(4));
	}
}