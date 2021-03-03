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
	}
}