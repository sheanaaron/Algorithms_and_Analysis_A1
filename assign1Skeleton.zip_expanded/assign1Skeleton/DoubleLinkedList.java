

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
	 * Insert VertLabel (and corresponding node) at position 'index'.  Indices start at 0.
	 *
	 * @param index Position in list to add new VertLabel to.
	 * @param newVertLabel VertLabel to add to list.
	 *
	 * @throws IndexOutOfBoundsException Index is out of bounds.
	 */
	public void insert(int index, String newVertLabel) throws IndexOutOfBoundsException {
		if (index >= mLength || index < 0) {
			throw new IndexOutOfBoundsException("Supplied index is invalid.");
		}
		Node newNode = new Node(newVertLabel);
		if (index==0)
			add(newVertLabel);
		else if (index == mLength)
		{
			newNode.setPrev(mTail);
			mTail.setNext(newNode);
			mTail = newNode;
		}
		else
		{
			Node currentNode;
			//By checking which end of the list the insertion index is closest to we increase the efficiency by
			//by which the index is found.
			if ((mLength-1-index)>=index)
			{
				currentNode = mHead;
				for(int i=0; i< index-1; i++)
					currentNode = currentNode.getNext();
				newNode.setNext(currentNode.getNext());
				newNode.setPrev(currentNode);
				currentNode.setNext(newNode);
			}
			else
			{
				currentNode = mTail;
				for (int i = 0; i < (mLength - index - 1); ++i) {
					currentNode = currentNode.getPrev();
				}
				newNode.setNext(currentNode);
				currentNode.getPrev().setNext(newNode);
				newNode.setPrev(currentNode.getPrev());
				currentNode.setPrev(newNode);
			}
			++mLength;
		}

	}

// end of insert()


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
 * Delete VertLabel (and corresponding node) at position 'index'.  Indices start at 0.
 *
 * @param index Position in list to get new VertLabel for.
 * @param dummy Dummy variable, serves no use apart from distinguishing overloaded methods.
 * @return VertLabel of node that was deleted.
 *
 * @throws IndexOutOfBoundsException Index is out of bounds.
 */
public String remove(int index, boolean dummy) throws IndexOutOfBoundsException {
	if (index >= mLength || index < 0) {
		throw new IndexOutOfBoundsException("Supplied index is invalid.");
	}

    Node currentNode;
 	if ((mLength-1 - index) >= index) {
 		currentNode = mHead;
 		for (int i = 0; i < index; ++i) {
 			currentNode = currentNode.getNext();
 		}
 		if (index == 0)
 			remove(currentNode.getVertLabel());
 		else {
 			currentNode.getPrev().setNext(currentNode.getNext());
 			currentNode.getNext().setPrev(currentNode.getPrev());
     		--mLength;
 		}
 	} else {
 		currentNode = mTail;
 		for (int i = 0; i < (mLength - index - 1); ++i) {
 			currentNode = currentNode.getPrev();
 		}
 		currentNode.getPrev().setNext(currentNode.getNext());
 		if (index != mLength - 1)
 			currentNode.getNext().setPrev(currentNode.getPrev());
 		else
 			mTail = currentNode.getPrev();
 		--mLength;
 	}

     // UPDATE (DUMMY VertLabel)
     return currentNode.getVertLabel();
} // end of remove()



/**
 * Print the list in head to tail.
 */
public void print() {
	System.out.println(toString());
} // end of print()


/**
 * Print the list from tail to head.
 */
public void reversePrint() {
	Node currentNode = mTail;

	  String str = "";

      while (currentNode != null) {
          str = str+ (currentNode.getVertLabel() + " ");
          currentNode = currentNode.getPrev();
      }
      System.out.println(str);
	

} // end of reversePrint()


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

	public void setState(SIRState newState){
		mState = newState;
	}
	
	public SIRState getState()
	{
		return mState;
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
