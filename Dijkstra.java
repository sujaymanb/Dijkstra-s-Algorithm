import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dijkstra {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Create graph
		int n = Integer.parseInt(input.nextLine());
		//Vertex[] graph = new Vertex[n];
		MinHeap Q = new MinHeap(n);

		int current, adj, adjWeight;
		Vertex adjVertex, currentVertex;
		Edge edge;

		String newline;
		//System.out.println(n);

		for(int i = 0; i < n; i++) {
			newline = input.nextLine();
			Scanner line = new Scanner(newline);
			current = line.nextInt();
			currentVertex = new Vertex(current);
			//System.out.println("Vertex: "+ current);

			while(line.hasNextInt()) {
				int tempcounter = 0;
				adj = line.nextInt();
				adjVertex = new Vertex(adj);
				adjWeight = line.nextInt();
				edge = new Edge();
				edge.to = adjVertex;
				edge.from = currentVertex;
				edge.weight = adjWeight;
				currentVertex.adjacent.insert(edge);
				//System.out.println("Edge: " + currentVertex.adjacent.get(tempcounter).to.name);
				tempcounter++;
			}

			Q.insert(currentVertex);
			line.close();
		}

		int pathcost = 0;
		// starting node
		Q.decreaseKey(0, pathcost);
		List<Vertex> Vt = new List();

		for(int i = 0; i < n; i++) {
			currentVertex = Q.deleteMin();
			System.out.println("Vertex: " + currentVertex.name + ", Cost: " + currentVertex.cost + ", No. of Adj: " + currentVertex.adjacent.size());
			Vt.insert(currentVertex);
			
			for (int j = 0; j < currentVertex.adjacent.size(); j++) {
				System.out.println("Adjacent: " + currentVertex.adjacent.get(j).to.name);
				edge = currentVertex.adjacent.get(j);

				if(Vt.inList(edge.to)) {
					continue;
				} else if ((currentVertex.cost + edge.weight) < edge.to.cost){
					edge.to.cost = currentVertex.cost + edge.weight;
					edge.to.parent = currentVertex;
					Q.decreaseKey(Q.findKeyByName(edge.to.name), edge.to.cost);
				}
			}

		}

		for(int i = 0; i < Vt.size(); i++) {
			currentVertex = Vt.get(i);
			System.out.println(currentVertex.name + ", " + currentVertex.cost + "\n");
		}
	}
}