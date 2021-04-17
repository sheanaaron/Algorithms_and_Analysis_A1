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
//		SIRState susceptable = SIRState.S;
//		SIRState infected = SIRState.I;
//		SIRState rercovered = SIRState.R;
		// TODO
	}

	public void deleteEdge(String srcLabel, String tarLabel) {
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
		adjMatrix[srcI][tarI] = false;
		adjMatrix[tarI][srcI] = false;
	}

	public void deleteVertex(String vertLabel) {
		// find the array index of value we want to remove
		int index = -1;
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].contains(vertLabel)) {
				index = i;
			}
		}

		// if value doesnt exist in array, don't continue
		if (index != -1) {
			// Create another array of size one less than previous array
			String[] anotherArray = new String[vertices.length - 1];

			// Copy the elements except the index
			// from original array to the other array
			for (int i = 0, k = 0; i < vertices.length; i++) {

				// if the index is
				// the removal element index
				if (i == index) {
					continue;
				}

				// if the index is not
				// the removal element index
				anotherArray[k++] = vertices[i];
			}

			// Reduces size of vertices array by 1, then copy data from temp array into
			// vertices array
			numVertices -= 1;
			vertices = new String[numVertices];
			vertices = anotherArray;
		}

	}

	public String[] kHopNeighbours(int k, String vertLabel) {
		// TODO

		// please update!
		return null;
	} // end of kHopNeighbours()

	public void printVertices(PrintWriter os) {
		for (int i = 0; i < numVertices; ++i) {
			System.out.println("Vertex: " + vertices[i]);
		}
	}

	public void printEdges(PrintWriter os) {
		try {
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
		} catch (Exception e) {
			// do nothing
		}

	}

} // end of class AdjacencyMatrix
