import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dijkstra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Create graph
		int n = Integer.parseInt(input.readLine());
		//Vertex[] graph = new Vertex[n];
		MinHeap Q = new MinHeap(n);

		int current, adj, adjWeight;
		Vertex adjVertex, currentVertex;
		Edge edge;

		for(int i = 0; i < n; i++) {
			Scanner line = new Scanner(input.readLine());
			current = line.nextInt();
			currentVertex = new Vertex(current);

			while(line.hasInt()) {
				adj = line.nextInt();
				adjVertex = new Vertex(adj);
				adjWeight = line.nextInt();
				edge = new Edge();
				edge.to = adjVertex;
				edge.from = currentVertex;
				edge.weight = adjWeight;
				currentVertex.adjacent.insert(edge);
			}

			Q.insert(currentVertex);
			line.close();
		}

		int pathcost = 0;
		// starting node
		Q.decreaseKey(0, pathcost);
		List<Vertex> Vt = new List();

		for(int i = 0; i < n-1) {
			currentVertex = Q.deleteMin();
			Vt.insert(currentVertex);
			for (int j = 0; j < currentVertex.adjacent.size(); j++) {
				edge = currentVertex.adjacent.get(j);
				if(Vt.inList(edge.to)) {
					continue;
				} else if ((currentVertex.cost + edge.weight) < edge.to.cost){
					edge.to.cost = currentVertex.cost + edge.weight;
					edge.to.parent = currentVertex;
					Q.decreaseKey(Q.findKeyByName(edge.to.name, edge.to.cost));
				}
			}

		}
	}
}