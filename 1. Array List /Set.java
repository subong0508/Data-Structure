/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Set implements ISet {
    /*
     * Add some variables you will use.
     */
    private int[] arr; // stored array
    private int n; // number of elements
    private final int INIT_SPACE = 50; // initial space
    private int space; // now space

    public Set() {
        /*
         * Constructor 
         * This function is an initializer for this class.
         */
        n = 0;
        space = INIT_SPACE;
        arr = new int[space];
    }

    private int search(int value){
        int foundIdx = -1;
        int left = 0;
        int right = n-1;

        while(left <= right){
                int mid = (left + right) / 2;
                if(arr[mid] == value){
                    foundIdx = mid;
                    break;
                }
                else if(arr[mid] > value){
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
        } 

        return foundIdx;
    }

    @Override
    public void insert(int value) {
        /*
         * Function input:
         *  + value: An integer to be inserted.
         * 
         * Job:
         *  Insert the integer if it does not exist.
         */
        int foundIdx = search(value);
 
        // doesn't exist ... insert!
        if(foundIdx == -1){
            if(n == space) { // no more space
	            space += INIT_SPACE; // reallocation
	            int[] tmp = new int[space];
	            System.arraycopy(arr, 0, tmp, 0, n);
	            arr = tmp; // reference to the new obj
            }

            int i;
            for(i = n-1; (i >= 0 && arr[i] > value);i--)
                arr[i+1] = arr[i]; // moving

            arr[i+1] = value;
            n++;
        }
    }

    @Override
    public void delete(int value) throws IllegalStateException {
        /**
         * Function input:
         *  + value: An integer to be deleted
         * 
         * Job:
         *  Delete the integer from the set.
         *  Raise an exception if it does not exist.
         */
        int foundIdx = search(value);

        if(foundIdx == -1)
            throw new IllegalStateException("No such value in this set.");
        else{
            for(int i=foundIdx+1;i<n;i++){
                arr[i-1] = arr[i];
            }

            // re-init
            arr[--n] = 0;
        }
    }

    @Override
    public void union(ISet set) {
        /**
         * Function input:
         *  + set: A set to be unioned
         * 
         * Job:
         *  Union the other set to this set
         */
        for(int e : set.show()){
            insert(e);
        }
    }

    @Override
    public void intersection(ISet set) {
        /**
         * Function input:
         *  + set: A set to be intersectioned
         * 
         * Job:
         *  Intersect the other set to this set
         */
        for(int e1 : this.show()){
            boolean deleteFlag = true;
            for(int e2: set.show()){
                if(e1 == e2){
                    deleteFlag = false;
                    break;
                } else if(e2 > e1){
                    break;
                }
            }

            if(deleteFlag)
                delete(e1);
        }
    }

    @Override
    public void subtraction(ISet set) {
        /**
         * Function input:
         *  + set: A set to be subtracted
         * 
         * Job:
         *  Subtract the other set from this set
         */
        for(int e : set.show()){
            if(search(e) != -1)
                delete(e);
        }
    }

    @Override
    public int[] show() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Return the elements of the set as an array.
         */
        int[] tmp = new int[n];
        System.arraycopy(arr, 0, tmp, 0, n);

        return tmp;
    }
}
