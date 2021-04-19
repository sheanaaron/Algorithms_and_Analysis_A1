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
			addRow();
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

				addRow();

				++numVertices;

			}else
				System.err.println("Vertex already exists. Try another name");
		}
	}// end of addVertex()

	public void addColumn()
	{
		if (numEdges==0)
		{
			for (int i=0; i<incMatrix.length-1; i++ )
			{
				incMatrix[i] = new boolean[1];
				incMatrix[i][0] = false;
			}
		}else {		
			boolean newMatrix[][] = new boolean [incMatrix.length][incMatrix[0].length+1];

			for (int i=0; i<incMatrix[0].length-1; i++)
			{
				for (int j=0; j<incMatrix[0].length; j++)
					newMatrix[i][j] = incMatrix[i][j];
				newMatrix[i][incMatrix[0].length] = false;
			}

			incMatrix = newMatrix;
		}	

	}

	public void addRow()
	{
		if (incMatrix == null) {
			// allocate matrix of size 1
			incMatrix = new boolean [1][0];
		}else {

			boolean newMatrix[][] = new boolean [incMatrix.length+1][incMatrix[0].length];

			//adding the old rows to the newMatrix
			for (int i=0; i<incMatrix.length; i++)
			{
				for (int j=0; j<incMatrix[0].length-1; j++)
					newMatrix[i][j] = incMatrix[i][j];
			}

			//fill the new row with default values
			for (int i=0; i<incMatrix[0].length-1; i++)
				newMatrix[incMatrix.length][i] = false;

			incMatrix = newMatrix;

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
				if (edges == null) {
					//add the edge to the edges array 
					edges = new String[1];
					edges[0] = srcLabel+" "+tarLabel + "\n" + tarLabel + " " + srcLabel; 
					addColumn();
					++numEdges;
					// add a new column to the matrix

				}else 

				{
					//check if edge already exists
					for (int i=0; i<numEdges; i++)
						if (edges[i].contains(srcLabel) && edges[i].contains(tarLabel))
							exists = true;

					if (!exists) {
						//if edge doesnt exist, add edge to the edge array
						String newArray[] = new String [edges.length + 1];

						// copy all existing values of array to newArray
						for (int i = 0; i < edges.length; i++) {
							newArray[i] = edges[i];
						}

						// new entry, add to end of newArray
						newArray[edges.length] = srcLabel+" "+tarLabel + "\n" + tarLabel + " " + srcLabel;

						// update reference of array to point to newArray
						edges = newArray;

						addColumn();

						++numEdges;
						// increase all array sizes by 1

						//add the edges to the incidence matrix
						incMatrix[srcIndex][incMatrix[0].length-1] = true;
						incMatrix[tarIndex][incMatrix[0].length-1] = true;


					}
					else
						System.err.println("Edge already exists");
				}
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

	public void removeRow()
	{

	}

	public void removeColumn(int index)
	{
		boolean newMatrix[][] = new boolean [incMatrix.length][incMatrix[0].length-1];

		for (int i=0; i<incMatrix[0].length-1; i++)
		{
			for (int j=0; j<index; j++)
				newMatrix[i][j] = incMatrix[i][j];

			for (int k=incMatrix[0].length-1; k>index; k--)
				newMatrix[i][k-1] = incMatrix[i][k];
		}

		incMatrix = newMatrix;
	}


	public void deleteEdge(String srcLabel, String tarLabel) {

		srcLabel = srcLabel.toUpperCase();
		tarLabel = tarLabel.toUpperCase();
		if (incMatrix.length>1)
		{
			boolean src= false, tar=false;
			int srcIndex=0, tarIndex=0;
			for (int i=0; i<vertices.length; ++i)
			{
				if (vertices[i].getVertLabel().equals(tarLabel))
				{
					tar= true;				
					tarIndex = i;
				}
				if (vertices[i].getVertLabel().equals(srcLabel))
				{
					src= true;
					srcIndex = i;
				}

			}

			if (src && tar)
			{
				boolean exists = false;
				int edgeIndex = 0;
				//check if edge already exists
				for (int i=0; i<numEdges; i++)
					if (edges[i].contains(srcLabel) && edges[i].contains(tarLabel))
					{
						exists = true;
						edgeIndex = i;
					}
				removeColumn(edgeIndex);
				
		        String newArray[] = new String[edges.length-1];

		        // copy all values before index
		    	for (int i = 0; i < edgeIndex; i++) {
		    		newArray[i] = edges[i];
		    	}
		        // copy all values after index
		        // we need to go in reverse direction to avoid overriding values.
		    	for (int j = edges.length-1; j > edgeIndex; j--) {
		    		newArray[j-1] = edges[j];
		    	}


		        // update reference of array to point to newArray
		    	edges = newArray; 
				


			}else	
			{
				if(!src)
					System.err.println("Source vertex doesnt exist");
				else if (!tar)
					System.err.println("Target vertex doesnt exist");
			}



		}else
			System.err.println("Insufficient nodes to perform function");
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
		for (int i=0; i<edges.length;i++)
			System.out.println(edges[i]);
	} // end of printEdges()

} // end of class IncidenceMatrix
