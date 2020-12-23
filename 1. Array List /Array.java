/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Array implements IArray {
    /*
     * Add some variables you will use.
     */

    private int[] arr; // stored array
    private int n; // number of elements
    private boolean sortedState; // is this array sorted?
    private final int INIT_SPACE = 50; // initial space
    private int space; // now space


    public Array() {
        /*
         * Constructor 
         * This function is an initializer for this class.
         */
        n = 0;
        sortedState = false;
        space = INIT_SPACE;
        arr = new int[space];
    }

    @Override
    public void insert(int value) {
        /*
         * Function input:
         *  + value: An integer to be inserted.
         * 
         * Job:
         *  Insert the given integer according to the state of the array.
         *  - unsorted: insert the integer as the last element of the array.
         *  - sorted: insert the integer at the position that makes the array sorted in ascending order.
         */

        if(n == space) { // no more space
        	space += INIT_SPACE ; // reallocation
        	int[] tmp = new int[space];
        	System.arraycopy(arr, 0, tmp, 0, n);
        	arr = tmp; // reference to the new obj
        }

        if(!isSorted())
        	arr[n] = value;
        else{
        	int i;
        	for(i = n-1; (i >= 0 && arr[i] > value);i--)
        		arr[i+1] = arr[i]; // moving

        	arr[i+1] = value;
        }

        n++;
    }

    @Override
    public void delete(int value) throws IllegalStateException {
        /*
         * Function input:
         *  + value: An integer to delete.
         * 
         * Job:
         *   Delete the first element that has the given integer as its value.
         *   If there is no such element, raise an exception.
         */

        int foundIdx = -1;

        try{
        	foundIdx = search(value);
        } catch(IllegalStateException e){
        	throw new IllegalStateException(e);
        }

        for(int i=foundIdx+1;i<n;i++){
        	arr[i-1] = arr[i];
        }

        // re-init
        arr[--n] = 0;
    }

    @Override
    public int search(int value) throws IllegalStateException {
        /*
         * Function input:
         *  + value: An integer to search.
         * 
         * Job:
         *  Return the first index of the element with the given value.
         *  If there is no such element, raise an exception.
         */

        // if empty, throws an exception: diff message
        if(isEmpty())
        	throw new IllegalStateException("Array is Empty.");

        int foundIdx = -1;
        
        // if sorted .. binarySearch
        if(isSorted()){
        	int left = 0;
        	int right = n-1;

        	while(left <= right){
                int mid = (left + right) / 2;
        		if(arr[mid] == value){
        			foundIdx = mid;
                    // first occurence
        			for(int i=left;i<mid;i++){
        				if(arr[i] == value){
        					foundIdx = i;
        					break;
        				}
        			}
        			break;
        		}
        		else if(arr[mid] > value){
        			right = mid - 1;
        		} else{
        			left = mid + 1;
        		}
        	}
        } else{
        	for(int i=0;i<n;i++) {
        		if(arr[i] == value){
        			foundIdx = i;
        			break;
        		}
        	}
        }

        if(foundIdx == -1)
        	throw new IllegalStateException("No such value in this Array.");
        else
        	return foundIdx;
    }

    @Override
    public void sort() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Change the state of the array to sorted.
         *  Sort the elements in the array in ascending order.
         */
        // bubble sort
        if(!isSorted()){
	        for(int i=n-1;i>0;i--){
				boolean changed = false;
				for(int j=0;j<i;j++){
					if(arr[j] > arr[j+1]){
						int tmp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = tmp;
						changed = true;
					}
				}

				if(!changed)
					break;
			}
	        sortedState = true;
    	}
    }

    @Override
    public void unsort() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Change the state of the array to unsorted.
         */
        sortedState = false;
    }

    @Override
    public int atIndex(int index) throws IndexOutOfBoundsException {
        /**
         * Function input:
         *  + index: An integer to find the element at that position
         * 
         * Job:
         *  Return the value of the element at the given index.
         */

        if(index < 0 || index >= n)
        	throw new IndexOutOfBoundsException("Unavailable index.");
        else
        	return arr[index];
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the number of elements in this array.
        */
        return n;
    }

    @Override
    public boolean isSorted() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Return true if the array is sorted and false otherwise.
         */
        return sortedState;
    }

    @Override
    public boolean isEmpty() {
        /* You do not have to edit this function. */
        return size() == 0;
    }
}
