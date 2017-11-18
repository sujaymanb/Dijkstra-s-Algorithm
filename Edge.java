// edges
public class Edge implements Comparable<Edge> {
	public Vertex from;
	public Vertex to;
	public int weight;

	public int compareTo(Edge other) {
		return Integer.compare(this.weight, other.weight);
	}
}