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

public final class Heap implements IHeap {
    public Node root;
    /*
    * Use some variables for your implementation.
    */
    private int n; // size of heap

    // total: 2n => O(n)
    public Heap(Integer[] array) {
        /*
        * Constructor
        * This function is an initializer for this class.
        * Input:
        *  + array: The initial unsorted array of values.
        *           You need to construct the heap bottom-up.
        */
        if(array == null || array.length == 0){
            n = 0;
            root = null;
        } else{
            n = array.length;
            // construction: O(n)
            for(int i=n-1;i>=0;i--){
                heapify(i, array);
            }
            root = new Node(array[0]);
            // to Node: O(n)
            arrayToHeap(array, root, 0);
        }
    }

    private void heapify(int i, Integer[] array){
        while(i < n){
            int max_idx = i;
            int l = ((2*i+1) < n) ? 2*i+1 : -1; // left idx
            int r = ((2*i+2) < n) ? 2*i+2 : -1; // right idx

            if(l != -1 && array[l] > array[max_idx])
                max_idx = l;

            if(r != -1 && array[r] > array[max_idx])
                max_idx = r;

            if(max_idx == i){
                break;
            } else{
                // swap
                int tmp = array[i];
                array[i] = array[max_idx];
                array[max_idx] = tmp;
            }

            i = max_idx;
        }
    }

    public void arrayToHeap(Integer[] array, Node node, int i){
        if(i >= n)
            return;

        if((2*i+1) < n){
            Node newNode = new Node(array[2*i+1]);
            node.setLChild(newNode);
            newNode.setParent(node);
            arrayToHeap(array, newNode, 2*i+1);
        }

        if((2*i+2) < n){
            Node newNode = new Node(array[2*i+2]);
            node.setRChild(newNode);
            newNode.setParent(node);
            arrayToHeap(array, newNode, 2*i+2);
        }
    }

    @Override
    public void insert(Integer value) {
        /*
        * Function input:
        *  value: A value to be inserted.
        *
        * Job:
        *  Insert the item value at the right position of the heap.
        */
        Node newNode = new Node(value);
        if(isEmpty()){
            root = newNode;   
        } else{
            Node parent = root;
            // O(logn)
            String direction = Integer.toBinaryString(n+1);
            direction = direction.substring(1);
            // O(logn)
            for(int i=0;i<direction.length()-1;i++){
                char c = direction.charAt(i);
                switch(c){
                    case '0':
                        parent = parent.getLChild();
                        break;
                    case '1':
                        parent  = parent.getRChild();
                        break;
                }
            }

            if(parent.getLChild() == null)
                parent.setLChild(newNode);
            else
                parent.setRChild(newNode);
            newNode.setParent(parent);

            // upheap: O(logn)
            upheap(newNode);
        }

        n++;
    }

    public void upheap(Node node){
        Node parent = node.getParent();

        while(parent!= null && node.getData() > parent.getData()){
            // swap
            int newPar = node.getData();
            int newChild = parent.getData();

            node.setData(newChild);
            parent.setData(newPar);

            node = parent;
            parent = node.getParent();
        }
    }

    @Override
    public Integer removeMax() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Delete and return the maximum value in the heap.
        *  Throw an exception if the tree is empty.
        */
        if(isEmpty())
            throw new IllegalStateException("There is no data");

        int res = max();    

        // O(logn)
        String direction = Integer.toBinaryString(n);
        direction = direction.substring(1);
        Node last = root;
        // O(logn)
        for(int i=0;i<direction.length();i++){
            char c = direction.charAt(i);
            switch(c){
                case '0':
                    last = last.getLChild();
                    break;
                case '1':
                    last = last.getRChild();
                    break;
            }
        }

        if(last == root){
            root = null;
            n = 0;
            return res;
        }

        root.setData(last.getData());
        if(last == last.getParent().getLChild()){
            last.getParent().setLChild(null);
        } else{
            last.getParent().setRChild(null);
        }

        // downheap: O(logn)
        downheap(root);
        n--;
        
        return res;
    }

    public void downheap(Node current){
        while(true){
            int max = current.getData();
            int l = (current.getLChild() != null) ? (current.getLChild().getData()) : max;
            int r = (current.getRChild() != null) ? (current.getRChild().getData()) : max;

            if(l > max)
                max = l;

            if(r > max)
                max = r;

            // swap
            if(max == current.getData()){
                break;
            } else if(max == l){
                current.getLChild().setData(current.getData());
                current.setData(max);
                current = current.getLChild();
            } else{
                current.getRChild().setData(current.getData());
                current.setData(max);
                current = current.getRChild();
            }

        }
    }

    @Override
    public Integer max() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the maximum value in the heap.
        *  Throw an exception if the tree is empty.
        */
        if(isEmpty())
            throw new IllegalStateException("There is no data");

        return root.getData();
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the size of the heap
        */
        return n;
    }

    @Override
    public Node getRoot() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the root node of the heap.
        *  Throw an exception if the tree is empty.
        */
        if(isEmpty())
            throw new IllegalStateException("There is no root");

        return root;
    }

    @Override
    public boolean isEmpty() {
        /* You must not edit this function. */
        return root == null;
    }
}
