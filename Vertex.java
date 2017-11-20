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
	@Override
	public int compareTo(Vertex other) {
		return Integer.compare(this.cost, other.cost);
	}
	@Override
	public boolean equals(Object obj) {
		Vertex other = (Vertex) obj;

		if (this.name == other.name) {
			return true;
		}

		return false;
	}
}
