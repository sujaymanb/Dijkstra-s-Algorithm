import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dijkstra {
	public static String addToPath(String add, String path){
		String newpath = "";
		newpath = add + " " + path;
		return newpath;
	}

	public static String getPath(int start, Vertex currentVertex, String path) {
		path += Integer.toString(currentVertex.name);
		while(currentVertex.parent != null || currentVertex.name != start) {
			currentVertex = currentVertex.parent;			
			path = addToPath(Integer.toString(currentVertex.name), path);
		}
		return path;
	}

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
			System.out.println("Inserting " + current);
			line.close();
		}

		int pathcost = 0;
		// starting node
		Q.decreaseKey(Q.findKeyByName(1), pathcost);
		List<Vertex> Vt = new List();

		for(int i = 0; i < n; i++) {
			currentVertex = Q.deleteMin();
			System.out.println("Deleting " + currentVertex.name);
			Vt.insert(currentVertex);
			
			for (int j = 0; j < currentVertex.adjacent.size(); j++) {
				edge = currentVertex.adjacent.get(j);
				
				if(Vt.inList(edge.to)) {
					continue;
				}

				adjVertex = Q.get(Q.findKeyByName(edge.to.name));
				
				if ((currentVertex.cost + edge.weight) < adjVertex.cost){
					edge.to.cost = currentVertex.cost + edge.weight;
					Q.vertexUpdateParent(Q.findKeyByName(edge.to.name), currentVertex);
					Q.decreaseKey(Q.findKeyByName(edge.to.name), edge.to.cost);
					System.out.println("Updating the value of " + edge.to.name + " in the queue");
				} 
				
			}

		}

		printPath(Vt);
	}
}
