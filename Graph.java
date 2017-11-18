import java.util.*;


// node for linked list
class Node<T> {
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
public class List<T> {
	private Node<T> first = null;
	private int size;

	public List(Node<T> start){
		this.first = start;
		this.size = 1;
	}

	public List(){
		this.size = 0;
	}

	public void insert(Node<T> node) {
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

	public int size() {
		return size;
	}

	public T get(int i) {
		int index = 0;
		Node<T> item = first;
		while(index < i) {
			item = item.getNext();
		}

		return item.getValue();
	}

	public boolean inList(T value) {
		Node<T> item = first;
		for(int i = 0; i < this.size; i++) {
			if(item.getValue().compareTo(value) == 0) {
				return true;
			}
		}
		return false;
	}
}

// vertex of a graph
public class Vertex implements Comparable<Vertex>{
	public int name;
	public boolean visited;
	public List<Edge> adjacent;
	public int cost;
	public Vertex parent;

	public Vertex(int name) {
		this.name = name;
		this.visited = false;
		this.cost = Integer.MAX_VALUE;
		this.parent = null;
	}

	@override
	public int compareTo(Vertex other) {
		return Integer.compare(this.cost, other.cost);
	}
}

// edges
public class Edge {
	public Vertex from;
	public Vertex to;
	public int weight;
}