/**
 * Class BFS_DFS_Algorithms that implements both types and are used on a node graph.
 * This class makes use of the Node.java class
 * @author Ellen Burger
 * @version 4/22/19
 */

/** Uses user input */
import java.util.Scanner;

/** Allows use of array functions */
//import java.util.Arrays;

/** Generates random numbers */
//import java.util.Random;

/** Allows use of LinkedLists */
import java.util.LinkedList;

public class BFS_DFS_Algorithms_B {
	
	/** Used to keep track of start and end in DFS */
	public static int time = 0;
	
	/**
	 * DFS moves through every node in the graph, recording the start and end times and noticing loops
	 * @param n Graph of nodes
	 */
	public static void DFS(Node [] n) {//parameter?
		
		LinkedList <Node> list = new LinkedList<Node>();
		
		/** Iterates through every node of graph */
		for(int i = 0; i < n.length; i++) {
			
			/** Sets id of node */
			n[i].id = i + 1;
			
			/** If parent is null, calls DFSVisit on the node */
			if(n[i].parent == null) {
				
				//n[i].parent = null;
				DFSVisit(n[i]);
			}
			
			list.add(n[i]);
			
			/** Prints the start and end time of the node */
			System.out.println("Node " + (i + 1) + " start = " + n[i].start + ", end = " + n[i].end);
		}
		
		while(list.pollFirst() != null) {
			System.out.println(list.remove().toString());
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
				
				System.out.println("Cycle detected, topological sort is impossible.");
			}
		}
		
		/** Increments the time after the recursion is done for each value and sets it to the end time */
		time++;
		v.end = time;
	}

	public static void main(String[] args) {
		
		/** Scanner and random objects */
		Scanner scan = new Scanner(System.in);
		//Random ran = new Random();
		
		/** Creating graph of 8 note vertices and 10 edges */
		int V = 8;
		//int E = 10;
		
		Node [] array = new Node [8];
		for(int i = 0; i < 8; i++) {
			array[i] = new Node();
		}
		
		/**
		 * In this version, there are no cycles
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
		array[1].setAdj(new Node [] {});
		array[2].setAdj(new Node [] {array[0]});
		array[3].setAdj(new Node [] {array[2]});
		array[4].setAdj(new Node [] {array[3], array[5], array[6]});
		array[5].setAdj(new Node [] {array[6]});
		array[6].setAdj(new Node [] {});
		array[7].setAdj(new Node [] {array[3]});
		
		/** Calling the DFS function on the array for every node in the graph */
		DFS(array);
		
		scan.close();		

	}

}
