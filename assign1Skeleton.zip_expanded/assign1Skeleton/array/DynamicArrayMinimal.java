package array;
import list.MyList;

/**
 * Array implementation that implements MyArray interface using a minimal approach.
 *
 * @author Jeffrey Chan
 */
public class DynamicArrayMinimal implements MyArray
{
    protected MyList array[];

    /**
     * Constructor.  Ensure you understand what the logic of it is doing,
     * will help wih understanding what arraySize means.
     */
    public DynamicArrayMinimal() {
        // no memory allocated to array, so we set the reference to null and
        // only allocate memory when we add an element.
    	array = null;
    } // end of DynamicArray()


    /**
     * Sets/replaces the @jvalue at index.  Indices start at 0.
     *
     * @param index Position in array to set/replace value.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public void set(int index, MyList newList) throws IndexOutOfBoundsException {
    	if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        array[index] = newList;
    } // end of set()


    /**
     * Gets/retrieves the value at index.  Indices start at 0.
     *
     * @param index Position in array to retrieve value from.
     * @return value of array at specified index.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public MyList get(int index) throws IndexOutOfBoundsException {
    	if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        return array[index];
    } // end of get()


    /**
     * Add value to end of array.
     *
     * @param newValue Value to add to array.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public void add(MyList newList) {
        // check if we need to allocate memory
        if (array == null) {
            // allocate array of size 1
            array = new MyList [1];
            array[0] = newList;
        }
        else {
            // increase size of array by one (not terribly efficient, but for this
            // lab we assume increase array size by one.
        	MyList newArray[] = new MyList [array.length + 1];

            // copy all existing values of array to newArray
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }

            // new entry, add to end of newArray
            newArray[array.length] = newList;

            // update reference of array to point to newArray
            array = newArray;
        }
    } // end of add()


    /**
     * Insert value at position 'index'.  Indices start at 0.
     *
     * @param index Position in array to add new value to.
     * @param newValue Value to add to array.
     *
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public void insert(int index, MyList newList) throws IndexOutOfBoundsException {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        // if not at memory size, we don't need to reallocate memory
        // check if we need to allocate memory
        if (array == null) {
            // this should hold after the exception checking, but good practice
            // to add an assert to ensure it does
            assert(index == 0);
            // allocate array of size 1
            array = new MyList [1];
            array[index] = newList;
        }
        else {
        	// increase size of array by one (not terribly efficient, but for this
        	// lab we assume increase array size by one.
            MyList newArray[] = new MyList[array.length+1];

        	// copy all values before index
        	for (int i = 0; i < index; i++) {
        		newArray[i] = array[i];
        	}

            // copy all values after index
            // we need to go in reverse direction to avoid overriding values.
        	for (int j = array.length; j > index; j--) {
        		newArray[j] = array[j-1];
        	}

        	// new entry, add to end of newArray
        	newArray[index] = newList;

            // update reference of array to point to newArray
        	array = newArray;
        }

    } // end of insert()


    /**
     * Searches for the index that contains value.  If value is not present,
     * method returns -1 (NOT_IN_ARRAY).
     * If there are multiple values that could be returned, return the one with
     * the smallest index.
     *
     * @param value Value to search for.
     * @return Index where value is located, otherwise returns -1 (NOT_IN_ARRAY).
     */
    public int search(MyList list) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
        	   if (array[i] == list) {
        		      return i;
        	   }
            }
        }

        return NOT_IN_ARRAY;
    } // end of search()


    /**
     * Print the array from front to end (index 0 to end).
    */
    public void print() {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + "\n");
            }
        }

        System.out.println("");
    } // end of print()


    /**
     * Print the array from end to front (end to index 0).
     */
    public void reversePrint() {
        if (array != null) {
            for (int i = array.length-1; i >= 0; i--) {
                System.out.print(array[i] + "\n");
            }
        }

        System.out.println("");
    } // end of reversePrint()

} // end of class DynamicArrayMinimal
