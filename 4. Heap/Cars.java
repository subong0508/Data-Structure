/*
* Name: 정재은
* Student ID#: 2017122063
*/

/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class Cars implements ICars {
    public Heap heap;
    /*
    * Use some variables for your implementation.
    */
    private int maxSize;

    public Cars(int k) {
        /*
        * Constructor
        * This function is an initializer for this class.
        * Input:
        *  + k: The number of the cars you have to consider.
        */
        heap = new Heap(new Integer[] {});
        maxSize = k;
    }

    @Override
    public void carDistance(int d) {
        /*
        * Function input:
        *  d: The travel distance of a car. (always positive)
        *
        * Job:
        *  Determine whether or not to keep the travel distance d.
        *  Consider the total time complexity of the program
        */

        if(heap.size() < maxSize){
            // System.out.println("insert success!");
            // O(logk)
            heap.insert(-d);
        } else if(-d < heap.max()){
            heap.getRoot().setData(-d);
            // O(logk)
            heap.downheap(heap.getRoot());
        }
    }

    @Override
    public int[] getCandidates() {
        /*
        * Function input: Nothing.
        *
        * Job:
        *  Return the k longest travel distances of the travel distances inputed until now.
        *  (You do not have to return the travel distances sorted)
        */
        Heap tmp = new Heap(new Integer[] {});
        int[] seq = new int[maxSize];
        for(int i=0;i<maxSize;i++){
            // O(logk)
            int val = heap.removeMax();
            seq[i] = -val;
            // O(logk)
            tmp.insert(val);
        }

        heap = tmp;

        return seq;
    }
}
