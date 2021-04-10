import java.io.PrintWriter;

/**
 * Adjacency matrix implementation for the GraphInterface interface.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2021.
 */
public class AdjacencyMatrix extends AbstractGraph {
	private boolean adjMatrix[][];
	private int numVertices;
	private String vertices[];

	/**
	 * Contructs empty graph.
	 */
	public AdjacencyMatrix() {
		numVertices = 0;
		adjMatrix = new boolean[numVertices][numVertices];
		vertices = new String[numVertices];

	}

	public void addVertex(String vertLabel) {
		
		// checks if the vertex already exists
		boolean exists = false;
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].contains(vertLabel)) {
				exists = true;
			}
		}

		if (!exists) {
			// create a temp array to store old data
			boolean[][] old = new boolean[numVertices][numVertices];
			old = adjMatrix;

			// increases size of adjMatrix by 1
			numVertices += 1;
			adjMatrix = new boolean[numVertices][numVertices];

			// copies old data into resized matrix
			for (int i = 0; i < old.length; i++) {
				for (int j = 0; j < old[i].length; j++) {
					adjMatrix[i][j] = old[i][j];
				}
			}

			// create temp array for storing old vertex name data
			String[] oldNames = new String[numVertices - 1];
			oldNames = vertices;

			// increase size of vertix array by 1
			vertices = new String[numVertices];

			// copies old data into new array
			for (int i = 0; i < oldNames.length; ++i) {
				vertices[i] = oldNames[i];
			}

			// inserts new vertex name into array name table
			vertices[numVertices - 1] = vertLabel;
		}

	}

	public void addEdge(String srcLabel, String tarLabel) {
		// make two temp variables to hold src and target index points in the array
		int srcI = 0;
		int tarI = 0;

		// Scan for srcLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].contains(srcLabel)) {
				srcI = i;
			}
		}

		// Scan for tarLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].contains(tarLabel)) {
				tarI = i;
			}
		}

		// Add an edge between two vertices
		adjMatrix[srcI][tarI] = true;
		adjMatrix[tarI][srcI] = true;
	}

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
		for (int i = 0; i < numVertices; ++i) {
			System.out.println("Vertex: " + vertices[i]);
		}
	}

	public void printEdges(PrintWriter os) {
		for (int i = 0; i < adjMatrix.length; ++i) {
			for (int j = 0; j < adjMatrix.length; ++j) {
				if (i != j) {
					if (adjMatrix[i][j] == true) {
						System.out.println("Edge: " + vertices[i] + " - " + vertices[j]);
					} else if (adjMatrix[j][i] == true) {
						System.out.println("Edge: " + vertices[j] + " - " + vertices[i]);
					}
				}
			}
		}
	}

} // end of class AdjacencyMatrix
