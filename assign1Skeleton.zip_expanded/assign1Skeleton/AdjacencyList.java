import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
// import java.util.*;
//import array.*;
//import list.*;
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
	//private DynamicArrayMinimal graph; 
	private MyArray graph;
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
			graph.get(0).add(vertLabel);
			++vertices;

		}
		//if theres more than one element in the map and it doesnt already exist, then we add a vertex
		else
		{
			for (Map.Entry mapElement : map.entrySet()) {
				if (vertLabel.equals((String)mapElement.getKey()))
				{
					System.err.println("Vertex already exists, try another vertex name");
					exists = true;
				}
			}

			if (!exists) 
			{
				map.put(vertLabel, vertices);
				graph.add(new DoubleLinkedList());
				graph.get(vertices).add(vertLabel);
				++vertices;
			}


		}

	} // end of addVertex()


	public void addEdge(String srcLabel, String tarLabel) {
		boolean srcExist = false;
		boolean tarExist = false;
		boolean edgeExists = false;
		srcLabel = srcLabel.toUpperCase();
		tarLabel = tarLabel.toUpperCase();

		//loop through map to check if source and target exist
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if (pair.getKey().equals(srcLabel))
				srcExist = true;

			if (pair.getKey().equals(tarLabel))
				tarExist = true;
			if (srcExist & tarExist)
				break;
		}

		if (srcExist & tarExist)
		{
			//check if either the source or target either have a connection to one of them
			MyList srcList = graph.get(map.get(srcLabel));
			MyList tarList = graph.get(map.get(tarLabel));

			if (srcList.search(tarLabel)==-1 )
			{
				//if not then make a connection
				srcList.add(tarLabel);
				tarList.add(srcLabel);
			}
			else
				System.err.println("Edge already exists");
		}else
		{
			if (!srcExist)
				System.err.println("Source vertex not found");
			else
				System.err.println("Target vertex not found");
		}
	} // end of addEdge()


	public void toggleVertexState(String vertLabel) {
		boolean exists = false;
		Integer foundIndex=0;
		if (vertices!=0)
		{
			//check if vertex exists			
			for (Entry<String, Integer> entry : map.entrySet())
			{
				if (Objects.equals(vertLabel, entry.getKey()))
				{
					exists = true;
					foundIndex = entry.getValue();
				}
			}
			if (exists)
			{
				MyList currList = graph.get(foundIndex);
				currList.toggleState();
			}else
				System.err.println("Vertex doesnt exist");
			
		}
		System.err.println("Empty graph");
		
	} // end of toggleVertexState()


	public void deleteEdge(String srcLabel, String tarLabel) {
		//instead of accesing the map classes size method, we can use the local variable which holds the number of vertices
		if (vertices>1)
		{
			boolean srcExist = false;
			boolean tarExist = false;
			boolean edgeExists = false;
			srcLabel = srcLabel.toUpperCase();
			tarLabel = tarLabel.toUpperCase();

			//loop through map to check if source and target exist
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				if (pair.getKey().equals(srcLabel))
					srcExist = true;

				if (pair.getKey().equals(tarLabel))
					tarExist = true;
				if (srcExist & tarExist)
					break;
			}

			if (srcExist & tarExist)
			{
				//check if either the source or target either have a connection to one of them
				MyList srcList = graph.get(map.get(srcLabel));
				MyList tarList = graph.get(map.get(tarLabel));

				if (srcList.search(tarLabel)!=-1 )
				{
					//if edge exists, then delete edges
					srcList.remove(tarLabel);
					tarList.remove(srcLabel);

				}
				else
					System.err.println("Edge doesn't exist");
			}else
			{
				if (!srcExist)
					System.err.println("Source vertex not found");
				else
					System.err.println("Target vertex not found");
			}
		}
	} // end of deleteEdge()


	public void deleteVertex(String vertLabel) {
		boolean exists = false;
		Integer foundIndex = 0;
		vertLabel = vertLabel.toUpperCase();

		if (vertices!=0)
		{
			//check if item exists			
			for (Entry<String, Integer> entry : map.entrySet())
			{
				if (Objects.equals(vertLabel, entry.getKey()))
				{
					exists = true;
					foundIndex = entry.getValue();
				}
			}

			if (exists)
			{
				//remove each edge that is connected to this vertex
				for (int i=0; i<vertices-1; i++)
				{	
					MyList currList = graph.get(i);	
					if (currList.getLength()>0)
						for (int j=1; j<currList.getLength(); j++)
							currList.remove(vertLabel);
				}

				//remove vertex from the graph
				graph.remove(foundIndex);
				//update maps referances
				for ( int i=foundIndex; i< map.size(); i++)
					map.put(getKey(map, i), i-1);
				//remove vertex from map
				map.remove(vertLabel);
				//update vertices
				--vertices;

			}else
				System.err.println("item doesnt exist");			
		}
		else
			System.err.println("Empty graph");
	} // end of deleteVertex()


	public String[] kHopNeighbours(int k, String vertLabel) {
		// reasoning at : https://cstheory.stackexchange.com/questions/12598/count-k-hop-neighborhood-for-every-vertex
		// DFS and BFS: https://gist.github.com/rishabhverma17/bdf86c1d2934fcfe6ca932fb1a8fe3e4


		// please update!
		return null;
	} // end of kHopNeighbours()


	public void printVertices(PrintWriter os) {


		if (vertices>0)
		{
			StringBuffer str = new StringBuffer();
			for (int i=0; i<vertices; i++)
			{
				MyList currList = graph.get(i);			
				str.append("(" + currList.get(currList.getVertexIndex()) + ","+ currList.getState().toString()+") ") ;
			}		  

			os.write(str.toString());        
			os.flush();

		}else
			System.err.println("Empty Graph");
	} // end of printVertices()


	public void printEdges(PrintWriter os) {

		StringBuffer str = new StringBuffer();
		if (map.size()>0)
		{
			for (int i=0; i< map.size(); i++)

			{
				MyList currList = graph.get(i); 
				if (currList.getVertexIndex()>0)
				{
					for (int j=0; j<currList.getLength()-1; j++)
						str.append(currList.get(currList.getLength()-1) +" "+ currList.get(j) + "\n");
				}
			}
			os.write(str.toString());        
			os.flush();
		}else
			System.err.println("Empty Graph");
	} // end of printEdges()

	public static <K, V> K getKey(Map<K, V> map, V value)
	{
		for (K key: map.keySet())
		{
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}

}// end of class AdjacencyList
