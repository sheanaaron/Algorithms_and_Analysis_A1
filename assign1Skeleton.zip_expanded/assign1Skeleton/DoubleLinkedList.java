

/**
 * Double linked list that implements interface MyList.
 *
 * @author Jeffrey Chan, RMIT
 */
public class DoubleLinkedList implements MyList
{
	/** Reference to head of list. */
	protected Node mHead;
	/** Reference to tail of list. */
	protected Node mTail;

	/** Length of list. */
	protected int mLength;


	/**
	 * Default constructor.
	 */
	public DoubleLinkedList() {
		mHead = null;
		mTail = null;
		mLength = 0;
	} // end of DoubleLinkedList()


	/**
	 * Add a new VertLabel to the start of the list.
	 *
	 * @param newVertLabel VertLabel to add to list.
	 */
	//adding to the front of the linked list is O(1) complexity whereas adding to the back is O(n)
	public void add(String newVertLabel) {
		Node newNode = new Node(newVertLabel);

		if (mHead==null)
			mTail = newNode;
		else
		{
			newNode.setNext(mHead);
			mHead.setPrev(newNode);

		}
		mHead = newNode;
		++mLength;

	} // end of add()

	//returns the length of a string
	public int getVertexIndex()
	{
		return mLength-1;
	}

	public int getLength()
	{
		return mLength;
	}


	/**
	 * Returns the VertLabel stored in node at position 'index' of list.
	 *
	 * @param index Position in list to get new VertLabel for.
	 * @return VertLabel of element at specified position in list.
	 *
	 * @throws IndexOutOfBoundsException Index is out of bounds.
	 */
	public String get(Integer index) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}

		Node currentNode = null;
		if (index < Math.ceil(mLength / 2)) {
			currentNode = mHead;
			for (int i = 0; i < index; ++i) {
				currentNode = currentNode.getNext();
			}
		}
		else {
			currentNode = mTail;
			for (int i = mLength-1; i > index; --i) {
				currentNode = currentNode.getPrev();
			}
		}
		return currentNode.getVertLabel();
	} // end of get()

	//returns the state of the node.
	public String getState() {

		return mHead.getState().toString();
	} // end of get()

	public void toggleState(){

		//if vertex is susceptible it becomes Infected
		if (mHead.getState()==SIRState.S)
			mHead.setState(SIRState.I);
		//if vertex is Infected it becomes Revovered
		if (mHead.getState()==SIRState.I)
			mHead.setState(SIRState.R);

	}

	/**
	 * Searches for the index that contains VertLabel.  If VertLabel is not present,
	 * method returns -1 (NOT_IN_ARRAY).
	 * If there are multiple VertLabels that could be returned, return the one with
	 * the smallest index.
	 *
	 * @param VertLabel VertLabel to search for.
	 * @return Index where VertLabel is located, otherwise returns -1 (NOT_IN_ARRAY).
	 */
	public int search(String VertLabel) {
		Node currentNode = mHead;
		for (int i = 0; i < mLength; ++i) {
			if (currentNode.getVertLabel().equals(VertLabel) ) {
				return i;
			}
			currentNode = currentNode.getNext();
		}

		return NOT_IN_ARRAY;
	} // end of search()


	/**
	 * Delete given VertLabel from list (delete first instance found).
	 *
	 * @param VertLabel VertLabel to remove.
	 * @return True if deletion was successful, otherwise false.
	 */
	public boolean remove(String vertLabel) {

		boolean flag = false;
		Node currentNode = mHead;
		for (int i=0; i<mLength; i++)
		{
			if (currentNode.getVertLabel().equals(vertLabel) )
			{
				if (i!=0)
					currentNode.getPrev().setNext(currentNode.getNext());
				else
					mHead = currentNode.getNext();
				if (i != mLength-1)
					currentNode.getNext().setPrev(currentNode.getPrev());

				else
					mTail = currentNode.getPrev();
				flag = true;
				--mLength;
				break;
			}
			currentNode = currentNode.getNext();
		}
		return flag;

	} // end of remove()



	/**
	 * Print the list in head to tail.
	 */
	public void print() {
		System.out.println(toString());
	} // end of print()


	/**
	 * @return String representation of the list.
	 */
	public String toString() {
		Node currentNode = mHead;

		StringBuffer str = new StringBuffer();

		while (currentNode != null) {
			str.append(currentNode.getVertLabel() + " ");
			currentNode = currentNode.getNext();
		}

		return str.toString();
	} // end of toString();



	/**
	 * Node type, inner private class.
	 */
	class Node
	{
		/** Stored VertLabel of node. */
		private String vertLabel;
		/** Reference to next node. */
		private Node mNext;
		/** Reference to previous node. */
		private Node mPrev;
		/** State of the node */
		private SIRState mState;

		public Node(String newVertLabel) {
			vertLabel = newVertLabel;
			mNext = null;
			mPrev = null;
			mState = SIRState.S;


		}

		public SIRState getState()
		{
			return mState;
		}

		public void setState(SIRState newState){
			mState = newState;
		}

		public String getVertLabel() {
			return vertLabel;
		}


		public Node getNext() {
			return mNext;
		}


		public Node getPrev() {
			return mPrev;
		}


		public void setVertLabel(String newVertLabel) {
			vertLabel = newVertLabel;
		}


		public void setNext(Node next) {
			mNext = next;
		}

		public void setPrev(Node prev) {
			mPrev = prev;
		}
	} // end of inner class Node
} // end of class DoubleLinkedList
