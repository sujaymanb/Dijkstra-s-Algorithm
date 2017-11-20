import java.util.*;


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
		this.adjacent = new List();
	}

	// override the compareTo method of Comparable
	// returns value > 0 if greater, < 0 if less than other
	@Override
	public int compareTo(Vertex other) {
		return Integer.compare(this.cost, other.cost);
	}

	// override the equals method of Object class
	@Override
	public boolean equals(Object obj) {
		Vertex other = (Vertex) obj;

		if (this.name == other.name) {
			return true;
		}

		return false;
	}
}
