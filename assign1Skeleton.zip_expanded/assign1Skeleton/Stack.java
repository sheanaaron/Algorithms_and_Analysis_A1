
public class Stack extends DoubleLinkedList{

	public Stack()
	{
		
	}
	
	//remove this and use "add" from DLL instead
	public void push(String newVertLabel) {
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

	} // end of push()
	
	public void pop() {

		if (mTail!=null)
		{
			Node currNode = mHead;
			mHead = currNode.getNext();
			currNode = null;
			--mLength;
		}
		

	} // end of push()
	
	


}
