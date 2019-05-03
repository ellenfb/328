/**
 * Class BFS_DFS_Algorithms that implements both types and are used on a node graph.
 * This class makes use of the Node.java class
 * @author Ellen Burger
 * @version 4/22/19
 */

/** Uses user input */
import java.util.Scanner;

/** Generates random numbers */
//import java.util.Random;

/** Allows use of Java Queues */
import java.util.Queue;
import java.util.LinkedList;

public class BFS_DFS_Algorithms {
	
	/** Used to keep track of start and end in DFS */
	public static int time = 0;
	
	/**
	 * Breadth First Search algorithm that traverses an entire graph by taking a starting node, 
	 * searching its neighbors, then its neighbor's neighbors and so on
	 * @param v Starting node that allows access to the rest of the graph via adjacency array
	 */
	public static void BFS(Node v) {
		
		/** Treats the given node as the start point and pushes it to an empty queue */
		v.distance = 0;
		v.parent = null;
		Queue <Node> q = new LinkedList<>();
		q.add(v);
		
		/** Iterates through values added to queue until empty, added values are each node in graph*/
		while(q.size() != 0) {
			
			/** Pops the queue, takes its value in a new pointer as well as its adjacency */
			Node w = q.remove(); //note: takes pointer, not copy
			Node [] adj = w.adj; //same as above
			
			/** Iterates through the adjacent values of the node popped from queue */
			for(int i = 0; i < adj.length; i++) {//adj[i] = u
				
				/** If the adjacent node's parent is null, set its pointer to w and add to queue */
				if(adj[i].parent == null) {
					
					adj[i].parent = w;
					adj[i].distance = w.distance + 1;
					q.add(adj[i]);
				}
				
			}
			
		}
		
	}

	/**
	 * Finds the vertexes reachable from the starting node v and assigns the start/end times
	 * @param v Starting node
	 */
	public static void DFSVisit(Node v) {
		
		/** Increment the time value for each call of each new node. This is its start time */
		time++;
		v.start = time;
		
		/** Iterates through the node's adjacent values */
		for(int i = 0; i < v.adj.length; i++) {
			
			/** If zero, the node hasn't been visited and will have its parent set to v, then itself called by the function */
			if(v.adj[i].start == 0) {
				
				v.adj[i].parent = v;				
				DFSVisit(v.adj[i]);
			}
			/** Checks if there is a cycle */
			else if(v.adj[i].start != 0 && v.adj[i].end == 0) {
				
				System.out.println("Cycle detected.");
			}
		}
		
		/** Increments the time after the recursion is done for each value and sets it to the end time */
		time++;
		v.end = time;
	}

	/** Main method */
	public static void main(String[] args) {
		
		/** Scanner and random objects */
		Scanner scan = new Scanner(System.in);
		//Random ran = new Random();
		
		/** Creating graph of 8 note vertices and 10 edges */
		//int V = 8;
		//int E = 10;
		
		Node [] array = new Node [8];
		for(int i = 0; i < 8; i++) {
			array[i] = new Node();
		}
		
		/**
		 * 0 -> 1
		 * 1 -> 2, 3
		 * 2 -> 0
		 * 3 -> 2
		 * 4 -> 3, 5, 6
		 * 5 -> 6
		 * 6 -> none
		 * 7 -> 3
		 */
		array[0].setAdj(new Node [] {array[1]});
		array[1].setAdj(new Node [] {array[2], array[3]});
		array[2].setAdj(new Node [] {array[0]});
		array[3].setAdj(new Node [] {array[2]});
		array[4].setAdj(new Node [] {array[3], array[5], array[6]});
		array[5].setAdj(new Node [] {array[6]});
		array[6].setAdj(new Node [] {});
		array[7].setAdj(new Node [] {array[3]});
		
		/** Asking for the node to start the algorithm from */
		System.out.println("Enter the starting vertex");
		int u = scan.nextInt();
		
		/** Calling BFS algorithm */
		BFS(array[u - 1]);
		
		/** Printing the distances from the starting nodes found by BFS. Note: BFS cannot detect cycles */
		System.out.println("BFS distances starting from node " + u + ":");
		for(int i = 0; i < 8; i++) {
			System.out.println("Node " + (i + 1) + " distance = " + array[i].distance);
		}
		
		/** Calling DFSVisit to find nodes reachable from starting node u */
		DFSVisit(array[u - 1]);
		
		System.out.println("DFS start and end times from node " + u + ":");
		for(int i = 0; i < 8; i++) {
			System.out.println("Node " + (i + 1) + " start = " + array[i].start + ", end = " + array[i].end);
		}
		
		scan.close();		

	}

}
