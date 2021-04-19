import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Incidence matrix implementation for the GraphInterface interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2021.
 */
public class IncidenceMatrix extends AbstractGraph
{
	private boolean incMatrix[][];
	private int numVertices;
	private int numEdges;
	private Vertex vertices[];
	private String edges[];
	/**
	 * Contructs empty graph.
	 */
	public IncidenceMatrix() {
		numVertices = 0;
		numEdges = 0;
		vertices = new Vertex[numVertices];

	} // end of IncidenceMatrix()


	public void addVertex(String vertLabel) {

		vertLabel = vertLabel.toUpperCase(); 
		if (numVertices == 0) {
			// allocate array of size 1
			vertices = new Vertex [1];
			vertices[0] = new Vertex(vertLabel);
			++numVertices;
		}
		else {
			// checks if the vertex already exists
			boolean exists = false;
			for (int i = 0; i < vertices.length; ++i) {
				if (vertices[i].getVertLabel().contains(vertLabel)) {
					exists = true;
				}
			}
			if (!exists) {
				// check if we need to allocate memory

				// increase size of array by one (not terribly efficient, but for this
				// lab we assume increase array size by one.
				Vertex newArray[] = new Vertex [vertices.length + 1];

				// copy all existing values of array to newArray
				for (int i = 0; i < vertices.length; i++) {
					newArray[i] = vertices[i];
				}

				// new entry, add to end of newArray
				newArray[vertices.length] = new Vertex(vertLabel);

				// update reference of array to point to newArray
				vertices = newArray;

				++numVertices;

			}else
				System.err.println("Vertex already exists. Try another name");
		}
	}// end of addVertex()

	public void resizeArray(Object[] array)
	{
		// increase all array sizes by 1
		Object newArray[] = new Object [array.length + 1];

		// copy all existing values of array to newArray
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		// update reference of array to point to newArray
		array = newArray;		
	}

//	public void resizeIncMatrix(boolean[] array)
//	{
//		// increase all array sizes by 1
//		boolean newArray[] = new boolean [array.length + 1];
//
//		// copy all existing values of array to newArray
//		for (int i = 0; i < array.length; i++) {
//			newArray[i] = array[i];
//		}
//		// update reference of array to point to newArray
//		array = newArray;		
//	}
	
	public void addColumn(boolean [][] inc)
	{
		for (int i=0; i<inc.length-1; i++)
		{
			boolean newRow[] = new boolean [inc[i].length+1];
			
			for (int j=0; j<inc[i].length;j++)
				newRow[j] = inc[i][j];
			inc[i] = newRow;
		}
	}

	public void addEdge(String srcLabel, String tarLabel) {

		srcLabel = srcLabel.toUpperCase();
		tarLabel = tarLabel.toUpperCase();

		if (numVertices>1)
		{
			//checking if source and target exist
			boolean srcExists = false, tarExists=false;
			int srcIndex=0, tarIndex=0;
			for (int i=0; i<vertices.length; ++i)
			{

				if (vertices[i].getVertLabel().equals(tarLabel))
				{
					tarExists = true;				
					tarIndex = i;
				}
				if (vertices[i].getVertLabel().equals(srcLabel))
				{
					srcExists = true;
					srcIndex = i;
				}

			}

			//if both the source and the target are found
			if (srcExists && tarExists)
			{

				boolean exists=false;
				//update the edges 
				if (numEdges == 0) {
					//add the edge to the edges array 
					edges = new String[1];
					edges[0] = srcLabel+" "+tarLabel + "\n" + tarLabel + " " + srcLabel; 
					++numEdges;
					incMatrix = new boolean[numVertices][numEdges];
					for (int i=0; i<numVertices -1; i++)
					{
						// increase all array sizes by 1
//						resizeIncMatrix(incMatrix[i]);
						//this would set all the edge values in the matrix to 0
						incMatrix[i][0] = false;
					}
				}else 
				{
					//check if edge already exists
					for (int i=0; i<numEdges; i++)
						if (edges[i].contains(srcLabel) && edges[i].contains(tarLabel))
							exists = true;	


					if (!exists) {
						//if edge doesnt exist, add edge to the edge array
						resizeArray(edges);
						edges[edges.length-1] = srcLabel+" "+tarLabel + "\n" + tarLabel + " " + srcLabel; 

						for (int i=0; i<edges.length; i++)
						{
							System.out.println(edges[i]);
						}

						System.out.println("verts: " +numVertices + "\nedges:" + numEdges);
						++numEdges;

						for (int i=0; i<numVertices-1; ++i)
						{
							// increase all array sizes by 1
//							resizeIncMatrix(incMatrix[i]);
							System.out.println("rows: " + incMatrix.length + " columsn: " + incMatrix[0].length);

							//this would set all the edge values in the matrix to 0
							incMatrix[i][numEdges] = false;
						}
						System.out.println("verts: " +numVertices + "\nedges:" + numEdges);
						System.out.println("rows: " + incMatrix.length + " columsn: " + incMatrix[0].length);

						//add the edges to the incidence matrix
						incMatrix[srcIndex][numEdges-1] = true;
						incMatrix[tarIndex][numEdges-1] = true;


						for (int i=0; i<vertices.length; i++)

						{

							for (int j=0; j<edges.length-1; j++) {
								int val = incMatrix[i][j] ? 1 : 0;
								System.out.print( val + " ");
							}
						}

					}
					else
						System.err.println("Edge already exists");
				}
				//				System.out.println("");
			}
			else
			{
				if(!srcExists)
					System.err.println("Source vertex doesnt exist");
				else if (!tarExists)
					System.err.println("Target vertex doesnt exist");
			}

		}else
			System.err.println("Insufficent nodes to make edge");
	} // end of addEdge()


	public void toggleVertexState(String vertLabel) {

		if(numVertices!=0)
		{	
			// checks if the vertex already exists
			boolean exists = false;
			Integer foundIndex =0;
			for (int i = 0; i < vertices.length; ++i) {
				if (vertices[i].getVertLabel().contains(vertLabel)) {
					exists = true;
					foundIndex = i;
				}
			}
			if(exists)
				vertices[foundIndex].setState();
			else
				System.err.println("Vertex doesnt exist");

		}else
			System.err.println("Empty Graph");
	} // end of toggleVertexState()


	public void deleteEdge(String srcLabel, String tarLabel) {
		// Implement me!
	} // end of deleteEdge()


	public void deleteVertex(String vertLabel) {

		vertLabel = vertLabel.toUpperCase();
		if (numVertices!=0) {


			boolean exists = false;
			Integer foundIndex =0;
			for (int i = 0; i < vertices.length; ++i) {
				if (vertices[i].getVertLabel().contains(vertLabel)) {
					exists = true;
					foundIndex = i;
				}
			}
			if (exists)
			{
				Vertex newArray[] = new Vertex[vertices.length-1];

				// copy all values before index
				for (int i = 0; i < foundIndex; i++) {
					newArray[i] = vertices[i];
				}
				// copy all values after index
				// we need to go in reverse direction to avoid overriding values.
				for (int j = vertices.length-1; j > foundIndex; j--) {
					newArray[j-1] = vertices[j];
				}
				// update reference of array to point to newArray
				vertices = newArray; 
				--numVertices;
			}else
				System.err.println("Vertex doesnt exist");


		}else
			System.err.println("Empty Graph");
	} // end of deleteVertex()


	public String[] kHopNeighbours(int k, String vertLabel) {
		// Implement me!

		// please update!
		return null;
	} // end of kHopNeighbours()


	public void printVertices(PrintWriter os) {
		for (int i = 0; i < numVertices; ++i) {
			System.out.print("(" + vertices[i].getVertLabel() + "," + vertices[i].getState() + ") ");
		}
		System.out.println();
	} // end of printVertices()


	public void printEdges(PrintWriter os) {
		// Implement me!
	} // end of printEdges()

} // end of class IncidenceMatrix
