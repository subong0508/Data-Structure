/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Stack<E> implements IStack<E> {
    public Node<E> top = null;
    /*
    * Use some variables for your implementation.
    */
    private int n; // size

    public Stack() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
        n = 0;
    }

    @Override
    public void push(E item) {
        /*
        * Function input:
        *  item: an item to be inserted.
        *
        * Job:
        *  Insert the item at the top of the stack.
        */
        if(isEmpty()){
            top = new Node<E>(item);
        } else{
            Node<E> newTop = new Node<E>(item, top);
            top = newTop;
        }

        n++;
    }

    @Override
    public E pop() throws IllegalStateException {
        /*
        * Function input:
        *  item: an item to be inserted.
        *
        * Job:
        *  Remove an item from the top of the stack and return that item.
        */
        if(isEmpty())
            throw new IllegalStateException("Stack is Empty.");

        Node<E> oldTop = top;
        top = oldTop.getNext();
        n--;

        return oldTop.getValue(); 
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the number of items in the stack.
        */
        return n;
    }

    @Override
    public boolean isEmpty() {
        /* You must not edit this function. */
        return top == null;
    }
}
