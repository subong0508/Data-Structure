/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class CDLList implements ICDLList {
    private Node head;
    /*
     * Add some variables you will use.
     */
    private int n; // size


    public CDLList() {
        /*
         * Constructor 
         * This function is an initializer for this class.
         */
        n = 0;
        head = null; 
    }

    @Override
    public void insert(int value) {
        /*
         * Function input:
         *  + value: An integer to be inserted.
         * 
         * Job:
         *  Insert the given integer to the list.
         */
        // if empty
        if(isEmpty()){
        	head = new Node(value);
        	head.prev = head;
        	head.next = head;
        } else{
        	// newNode의 prev: head.prev, next: head
        	Node newNode = new Node(value, head.prev, head);
        	// tail의 next: newNode 
        	head.prev.next = newNode; 
        	// head의 prev: newNode
        	head.prev = newNode;
        }

        n++;
    }

    @Override
    public void delete() throws IllegalStateException {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  Delete the previous node of the head.
         */
        // if empty
        if(isEmpty())
        	throw new IllegalStateException("No item to delete.");
        else if(head == head.prev){ // 원소가 하나
        	head = null;
        } else{
        	// tail 바로 전의 next가 head를 가리키게 함.
        	head.prev.prev.next = head; 
        	// head의 prev가 tail의 prev
        	head.prev = head.prev.prev; 
        }

        n--;
    }

    // head의 next 삭제
    public void deleteForward() throws IllegalStateException {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  Delete the previous node of the head.
         */
        // if empty
        if(isEmpty())
            throw new IllegalStateException("No item to delete.");
        else if(head == head.prev){ // 원소가 하나
            head = null;
        } else{
            head.next.next.prev = head; 
            head.next = head.next.next; 
        }

        n--;
    }

    @Override
    public Node getHead() throws IllegalStateException {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  return the reference of the head. If none, raise an exception.
         */
        if(isEmpty())
        	throw new IllegalStateException("No head!");
        else
        	return head;
    }

    @Override
    public void rotateForward() throws IllegalStateException {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  Rotate the list forward. If none, raise an exception.
         */
        if(isEmpty())
        	throw new IllegalStateException("No head!");
        else
        	head = head.next;

    }

    @Override
    public void rotateBackward() throws IllegalStateException {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  Rotate the list backward. If none, raise an exception.
         */
        if(isEmpty())
        	throw new IllegalStateException("No head!");
        else
        	head = head.prev;
    }

    @Override
    public int size() {
        /*
         * Function input: Nothing
         * 
         * Job:
         *  Return the size of the list
         */
        return n;
    }

    @Override
    public boolean isEmpty() {
        /* You do not have to edit this function. */
        return size() == 0;
    }
}
