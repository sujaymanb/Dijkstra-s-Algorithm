// node for linked list
class Node<T extends Comparable> {
	private T value;
	private Node<T> next;

	// constructor
	public Node(T value) {
		this.value = value;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}
}

// linked list to store adjacent nodes of graph
public class List<T extends Comparable> {
	private Node<T> first = null;
	private int size;

	// constructor with a starting node
	public List(Node<T> start){
		this.first = start;
		this.size = 1;
	}

	// blank constructor
	public List(){
		this.size = 0;
	}

	// insert a value into the list
	public void insert(T value) {
		Node<T> node = new Node(value);
		node.setNext(first);
		first = node;
		this.size++;
	}

	public void remove() {
		if(first.getNext() != null)
			first = first.getNext();
		else first = null;
		this.size--;
	}

	// size of list
	public int size() {
		return size;
	}

	// return the item with given index
	public T get(int i) {
		int index = 0;
		Node<T> item = first;
		while(index < i) {
			item = item.getNext();
			index++;
		}
		return item.getValue();
	}

	// check if the given vertex is in list
	public boolean inList(T value) {
		Node<T> item = first;
		for(int i = 0; i < this.size; i++) {
			if(item.getValue().equals(value)) {
				return true;
			}
			item = item.getNext();
		}
		return false;
	}

	// debug
	/*
	public void print() {
		Node<T> ptr = first;

		while(ptr != null) {
			System.out.println(ptr.getValue());
			ptr = ptr.getNext();
		}
	}*/
}