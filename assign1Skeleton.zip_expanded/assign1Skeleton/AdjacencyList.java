import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
// import java.util.*;

import array.*;
import list.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjacencyList extends AbstractGraph
{
	//Array that can store lists
	private int numEdge;
	private DynamicArrayMinimal graph; 
	private Map<String, Integer> map;
	private int vertices;

	/**
	 * Contructs empty graph.
	 */
	public AdjacencyList() {

		map = new HashMap<String, Integer>();
		graph = new DynamicArrayMinimal();
		vertices = 0;

	} // end of AdjacencyList()


	public void addVertex(String vertLabel) {
		boolean exists = false;
		vertLabel = vertLabel.toUpperCase();
		if (map.size()==0)
		{
			map.put(vertLabel, vertices);
			graph.add(new DoubleLinkedList());
			System.out.println("added vertex :" + vertLabel + " at index: " + vertices);
			++vertices;

		}
		else
		{
			for (Map.Entry mapElement : map.entrySet()) {
				if (vertLabel.equals((String)mapElement.getKey()))
				{
					System.out.println("Vertex already exists, try another label name");
					exists = true;
				}
			}

			if (!exists) 
			{
			map.put(vertLabel, vertices);
			graph.add(new DoubleLinkedList());
			++vertices;
			}

		}

	} // end of addVertex()


	public void addEdge(String srcLabel, String tarLabel) {
		// Implement me!
	} // end of addEdge()


	public void toggleVertexState(String vertLabel) {
		// Implement me!
	} // end of toggleVertexState()


	public void deleteEdge(String srcLabel, String tarLabel) {
		// Implement me!
	} // end of deleteEdge()


	public void deleteVertex(String vertLabel) {
		// Implement me!
	} // end of deleteVertex()


	public String[] kHopNeighbours(int k, String vertLabel) {
		// Implement me!

		// please update!
		return null;
	} // end of kHopNeighbours()


	public void printVertices(PrintWriter os) {
		// Implement me!
	} // end of printVertices()


	public void printEdges(PrintWriter os) {
		// Implement me!
	} // end of printEdges()

} // end of class AdjacencyList
