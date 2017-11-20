import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dijkstra {
	/* Utility functions */
	// =============================
	// creates the path string, this method adds the new vertex in front of the string
	public static String addToPath(String add, String path){
		String newpath = "";
		newpath = add + " " + path;
		return newpath;
	}

	// gets the path from the vertex by going through parents iteratively
	public static String getPath(int start, Vertex currentVertex, String path) {
		path += Integer.toString(currentVertex.name);
		while(currentVertex.parent != null || currentVertex.name != start) {
			currentVertex = currentVertex.parent;			
			path = addToPath(Integer.toString(currentVertex.name), path);
		}
		return path;
	}

	// prints the final output
	private static void printPath(List<Vertex> Vt) {
		Vertex currentVertex;		
		for(int i = 0; i < Vt.size(); i++) {
			currentVertex = Vt.get(i);
			String path = "";		
			System.out.print("Shortest path to " + currentVertex.name);
			path = getPath(1, currentVertex, path);
			System.out.println(": " + path + " cost: " + currentVertex.cost);
		}	
	}
	// ===============================
	/* Main */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// Initialize the heap
		// get n
		int n = Integer.parseInt(input.nextLine());
		MinHeap Q = new MinHeap(n);

		int current, adj, adjWeight;
		Vertex adjVertex, currentVertex;
		Edge edge;

		String newline;
		
		// add the vertices to the heap
		for(int i = 0; i < n; i++) {
			newline = input.nextLine();
			Scanner line = new Scanner(newline);
			current = line.nextInt();
			currentVertex = new Vertex(current);
			
			// add edges to the vertices (adjacent vertices)
			while(line.hasNextInt()) {
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
			System.out.println("Inserting " + current);
			line.close();
		}

		// go through the heap updating their cost
		int pathcost = 0;
		// starting node cost is 0
		Q.decreaseKey(Q.findKeyByName(1), pathcost);
		List<Vertex> Vt = new List();

		for(int i = 0; i < n; i++) {
			currentVertex = Q.deleteMin();
			System.out.println("Deleting " + currentVertex.name);
			Vt.insert(currentVertex);
			// for each adjacent vertices
			for (int j = 0; j < currentVertex.adjacent.size(); j++) {
				edge = currentVertex.adjacent.get(j);
				// if the vertex has already been added to Vt, skip
				if(Vt.inList(edge.to)) {
					continue;
				}

				adjVertex = Q.get(Q.findKeyByName(edge.to.name));
				// update the cost
				if ((currentVertex.cost + edge.weight) < adjVertex.cost){
					edge.to.cost = currentVertex.cost + edge.weight;
					Q.vertexUpdateParent(Q.findKeyByName(edge.to.name), currentVertex);
					Q.decreaseKey(Q.findKeyByName(edge.to.name), edge.to.cost);
					System.out.println("Updating the value of " + edge.to.name + " in the queue");
				} 
				
			}

		}

		// output
		printPath(Vt);
	}
}
