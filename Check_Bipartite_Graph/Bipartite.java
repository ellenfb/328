/**
 * Class Biparitite that checks if an undirected graph is bipartite using BFS traversal and color flags
 * This class makes use of the Node.java class
 * @author Ellen Burger
 * @version 4/22/19
 */

/** Uses user input */
//import java.util.Scanner;

/** Generates random numbers */
//import java.util.Random;

/** Allows use of Java Linked List and Queue */
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {
	
	/**
	 * Bipartite checker function based on BFS function. It traverses unexplored nodes adjacent to a parent node, marking them as the
	 * opposite color to the parent's (red or blue). If an adjacent node's color is the same as its parent, the graph is not bipartite
	 * @param v Starting node
	 * @param graph Graph containing all vertexes/nodes
	 * @param edge Array containing linked lists of edges for every node
	 */
	public static void Is_bipartite(Node v, Node [] graph, LinkedList<Integer> [] edge) {
		
		/** Treats the given node as the start point and pushes it to an empty queue */
		Queue <Node> q = new LinkedList<>();
		q.add(v);
		
		/** Iterates through values added to queue until empty, added values are each node in graph*/
		while(q.size() != 0) {
			
			/** Pops the queue, takes its value in a new pointer as well as its adjacency */
			Node u = q.remove();
			
			/** Get increments until the current list exhausts its elements */
			int get = 0;
			while(edge[u.id].size() != get) {
				
				/** Calls the adjacent node's color values by iterating through u's adjacent nodes one by one, found from its id */
				Node adj = graph[edge[u.id].get(get)];
				
				/*System.out.println("Comparing " + u.id + " and " + adj.id);
				System.out.println(u.color + " vs " + adj.color); error checking print */
				
				/** If adjacent node = gray, changes to red or blue based on u, then adds it to queue to have its own adjacents checked */
				if(adj.color.equals("gray")){
					
					if(u.color.equals("blue")) {
						adj.color = "red";
					}
					else {
						adj.color = "blue";
					}
					
					q.add(adj);
					
				}
				/** If adj's color and u's color are equal, the graph is not bipartite */
				else if(adj.color.equals(u.color)) {
					
					System.out.println("NOT bipartite");
					break;
				}
				
				get++;
			}
			
		}
		
	}
	
	/**
	 * Iterates through the graph and calls Is_bipartite on any node that hasn't been checked yet (still colored gray)
	 * @param graph Graph containing all vertexes/nodes
	 * @param edge Array containing linked lists of edges for every node
	 */
	public static void Explore(Node [] graph, LinkedList<Integer> [] edge) {
		
		/** Assign each node's initial color to gray */
		for(int i = 0; i < graph.length; i++) {
			
			graph[i].color = "gray";
		}
		
		/** Sets the first node to blue and calls on it */
		graph[0].color = "blue";
		Is_bipartite(graph[0], graph, edge);
		
		/** Call Is_bipartite on each node not yet visited (still gray) */
		for(int i = 1; i < graph.length; i++) {

			if(graph[i].color.equals("gray")) {
				
				/** Sets the node being sent to the function to blue */
				graph[i].color = "blue";
				Is_bipartite(graph[i], graph, edge);
			}
		}
		
		
	}
	
	
	/**
	 * Fills the adjacency list of the graph with pre-made edges
	 * @param V Number of nodes/vertexes
	 * @param edge List of adjacent edges for each vertex
	 */
	public static void premadeEdges(int V, LinkedList<Integer>[] edge) {
		
		/** Initialized the list array's elements */
		for(int i = 0; i < V; i++) {
			edge[i] = new LinkedList();
		}
		
		/** Fills the adjacency for each node */
		edge[0].add(4);
		edge[0].add(5);
		edge[0].add(6);
		
		edge[1].add(4);
		edge[1].add(5);
		
		edge[2].add(4);
		edge[2].add(5);
		edge[2].add(6);
		
		edge[3].add(5);
		edge[3].add(6);
		
		edge[4].add(0);
		edge[4].add(1);
		edge[4].add(2);
		
		edge[5].add(0);
		edge[5].add(1);
		edge[5].add(2);
		edge[5].add(3);
		
		edge[6].add(0);
		edge[6].add(2);
		edge[6].add(3);
		
	}


	/** Main method */
	public static void main(String[] args) {
		
		/** Scanner and random objects */
		//Scanner scan = new Scanner(System.in);
		//Random ran = new Random();
		
		/** Creating graph of 8 note vertices and 10 edges */
		int V = 7;
		//int E = 10;
		
		Node [] graph = new Node [V];
		for(int i = 0; i < V; i++) {
			graph[i] = new Node();
			graph[i].id = i;
		}
		
		/** Creating an array of lists to record adjacency for each node */
		LinkedList <Integer> edge [] = new LinkedList [V];
		premadeEdges(V, edge);
		
		/** Printing the adjacency list */
		for(int i = 0; i < V; i ++) {
			
			/** Get iterates through each list in the array */
			int get = 0;
			System.out.println("Node " + i + " edges: ");
			
			/** Get increments until the current list exhausts its elements */
			while(edge[i].size() != get) {
				
				System.out.print(edge[i].get(get) + " ");
				get++;
			}
			System.out.println();
		}
		
		/** Traverses array to check if bipartite */
		Explore(graph, edge);
		
		/** Prints the colors assigned to each node */
		for(int i = 0; i < V; i++) {
			System.out.println("Node " + i + " color = " + graph[i].color);
		}
		
		//scan.close();		

	}



	

}
