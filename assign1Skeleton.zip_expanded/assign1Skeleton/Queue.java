public class Queue extends DoubleLinkedList{

	public Queue()
	{}
	
	public void enqueue(String newVertLabel) {
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
	
	public void dequeue() {

		if (mTail!=null)
		{
			Node currNode = mHead;
			mHead = currNode.getNext();
			currNode = null;
			--mLength;
		}
		

	} // end of dequeue()
}
