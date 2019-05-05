/**
 * Class Node for use in Bipartite algorithm
 * @author Ellen
 * @Version 5/5/19
 */
public class Node {

	/**
	 * Node values
	 * id: number for node in graph
	 * Parent: parent value of node
	 * Distance: Distance to travel to
	 * Color: color value used in bipartite check
	 */
	public int id;
	public Node parent;
	public int distance;
	public String color;
	
	/**
	 * Default constructor initializing all values to null or 0
	 */
	public Node() {
		id = 0;
		parent = null;
		distance = 0;
		color = null;
	}
	
	/**
	 * Constructor for creating a node with all values assigned
	 * @param i Id value
	 * @param p Parent value
	 * @param d Distance value
	 * @param c Color value
	 */
	public Node(int i, Node p, int d, String c) {
		id = i;
		parent = p;
		distance = d;
		color = c;
	}
	
	
	/**
	 * toString method that returns id as a string
	 */
	public String toString() {
		return("" + id);
	}
}
