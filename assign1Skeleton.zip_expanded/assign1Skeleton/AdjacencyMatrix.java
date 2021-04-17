import java.io.PrintWriter;
import java.util.Arrays;

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
	private Vertex vertices[];

	/**
	 * Contructs empty graph.
	 */
	public AdjacencyMatrix() {
		numVertices = 0;
		adjMatrix = new boolean[numVertices][numVertices];
		vertices = new Vertex[numVertices];

	}

	// Vertex class to store current vertlabel and SIR state
	public class Vertex {
		private String vertLabel;
		private SIRState sirState;

		public Vertex(String vertLabel) {
			this.vertLabel = vertLabel;
			this.sirState = SIRState.S;
		}

		public String getVertLabel() {
			return vertLabel;
		}

		public SIRState getState() {
			return sirState;
		}

		public void setState() {
			if (this.sirState == SIRState.S) {
				this.sirState = SIRState.I;
			} else if (this.sirState == SIRState.I) {
				this.sirState = SIRState.R;
			}
		}
	}

	public void addVertex(String vertLabel) {

		// checks if the vertex already exists
		boolean exists = false;
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(vertLabel)) {
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
			Vertex[] oldNames = new Vertex[numVertices - 1];
			oldNames = vertices;

			// increase size of vertix array by 1
			vertices = new Vertex[numVertices];

			// copies old data into new array
			for (int i = 0; i < oldNames.length; ++i) {
				vertices[i] = oldNames[i];
			}

			// inserts new vertex name into array name table
			vertices[numVertices - 1] = new Vertex(vertLabel);
		}

	}

	public void addEdge(String srcLabel, String tarLabel) {
		// make two temp variables to hold src and target index points in the array
		int srcI = 0;
		int tarI = 0;

		// Scan for srcLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(srcLabel)) {
				srcI = i;
			}
		}

		// Scan for tarLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(tarLabel)) {
				tarI = i;
			}
		}

		// Add an edge between two vertices
		adjMatrix[srcI][tarI] = true;
		adjMatrix[tarI][srcI] = true;
	}

	// Transition SIR-State from S>I>R.
	public void toggleVertexState(String vertLabel) {
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(vertLabel)) {
				vertices[i].setState();
			}
		}
	}

	public void deleteEdge(String srcLabel, String tarLabel) {
		// make two temp variables to hold src and target index points in the array
		int srcI = 0;
		int tarI = 0;

		// Scan for srcLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(srcLabel)) {
				srcI = i;
			}
		}

		// Scan for tarLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(tarLabel)) {
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
			if (vertices[i].getVertLabel().contains(vertLabel)) {
				index = i;
			}
		}

		// if value doesnt exist in array, don't continue
		if (index != -1) {
			// Create another array of size one less than previous array
			Vertex[] anotherArray = new Vertex[vertices.length - 1];

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
			vertices = new Vertex[numVertices];
			vertices = anotherArray;
		}

	}

	// returns string array of neighbors of node vertLabel that are k hops away or
	// less
	public String[] kHopNeighbours(int k, String vertLabel) {
		// TODO - (NOT FULLY WORKING YET)
		String neighbors[] = { "" };
		int srcI = 0;

		// Scan for srcLabel in the array
		for (int i = 0; i < vertices.length; ++i) {
			if (vertices[i].getVertLabel().contains(vertLabel)) {
				srcI = i;
			}
		}

		// scans 1 hop away from node
		for (int i = 0; i < vertices.length; ++i) {
			if (adjMatrix[srcI][i]) {
				// increases array size by 1, then adds vertLabel to the end of array
				neighbors = Arrays.copyOf(neighbors, neighbors.length + 1);
				neighbors[neighbors.length - 1] = vertices[i].getVertLabel();
			}
		}

		// please update!
		return neighbors;
	} // end of kHopNeighbours()

	// Prints the vertices from our vertices array
	public void printVertices(PrintWriter os) {
		for (int i = 0; i < numVertices; ++i) {
			System.out.print("(" + vertices[i].getVertLabel() + "," + vertices[i].getState() + ") ");
		}
		System.out.println();
	}

	// prints the edges from our 2d edges array
	public void printEdges(PrintWriter os) {
		try {
			for (int i = 0; i < adjMatrix.length; ++i) {
				for (int j = 0; j < adjMatrix.length; ++j) {
					if (i != j) {
						if (adjMatrix[i][j] == true) {
							System.out.println(vertices[i].getVertLabel() + " " + vertices[j].getVertLabel());
						} else if (adjMatrix[j][i] == true) {
							System.out.println("FUCK");
						}
					}
				}
			}
		} catch (Exception e) {
			// do nothing
		}

	}

} // end of class AdjacencyMatrix
