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
				System.out.println("i: " + i);
				incMatrix[i] = new boolean[1];
			}
		}else {
			for (int i=0; i<incMatrix.length-1; i++)
			{
				//creating a new array with one extra column
				int currEdges = incMatrix[0].length;
				boolean newColumn[] = new boolean [currEdges+1];

				//updating rows with existing columns
				for (int j=0; j<currEdges; j++)
					newColumn[j] = incMatrix[i][j];

				//giving default values to the new column
				newColumn[currEdges] = false;
				incMatrix[i] = newColumn;
			}
		}
		System.out.println("rows: " + incMatrix.length + " columsn: " + incMatrix[0].length);

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
				for (int j=0; j<incMatrix[0].length-1; j++)
					newMatrix[i][j] = incMatrix[i][j];



			//fill the new row with default values
			for (int i=0; i<incMatrix[0].length; i++)
				newMatrix[incMatrix.length][i] = false;

			incMatrix = newMatrix;
			System.out.println("rows: " + incMatrix.length + " columns: " + incMatrix[0].length);


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
					System.out.println(edges[0]);
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
						newArray[edges.length] = srcLabel+" "+tarLabel + "\n" + tarLabel + " " + srcLabel;;

						// update reference of array to point to newArray
						edges = newArray;
						addColumn();

						++numEdges;
						// increase all array sizes by 1

						//add the edges to the incidence matrix
						incMatrix[srcIndex][numEdges-1] = true;
						incMatrix[tarIndex][numEdges-1] = true;

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
