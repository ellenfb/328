/**
 * Class Node for use in BFS and DFS algorithms. It contains all needed information to construct and analyze a graph
 * @author Ellen
 * @Version 4/26/19
 */
public class Node {

	/**
	 * Node values. If more time were given, these would be private and would be accessed through getter/setter methods
	 * Id: Id number of node on graph
	 * Parent: parent value of node
	 * Adj: Adjacency of node
	 * Start: start time
	 * End: end time
	 * Distance: Distance to travel to
	 */
	public int id;
	public Node parent;
	public Node [] adj;
	public int start;
	public int end;
	public int distance;
	
	/**
	 * Default constructor initializing all values to null or 0
	 */
	public Node() {
		id = 0;
		parent = null;
		adj = null;
		start = 0;
		end = 0;
		distance = 0;
	}
	
	/**
	 * Constructor allowing exclusively the adjacency list to be filled
	 * @param adjFull Full adjacency list for this node
	 */
	public Node(Node [] adjFull) {
		parent = null;
		adj = adjFull;
		start = 0;
		end = 0;
		distance = 0;
	}
	
	/**
	 * Constructor for creating a node with all values assigned
	 * @param i Id value
	 * @param p Parent value
	 * @param a Adjacency values
	 * @param s Start time
	 * @param e End time
	 * @param d Distance value
	 */
	public Node(int i, Node p, Node [] a, int s, int e, int d) {
		id = i;
		parent = p;
		adj = a;
		start = s;
		end = s;
		distance = d;
	}
	
	/**
	 * Sets the adjacency value to a parameter
	 * @param adjFull Array of adjacent values
	 */
	public void setAdj(Node [] adjFull) {
		adj = adjFull;
	}
	
	/**
	 * To string method that returns id
	 */
	public String toString() {
		return("" + id);
	}
}
