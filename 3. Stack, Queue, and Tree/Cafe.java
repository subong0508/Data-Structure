/*
 * Name: 정재은 
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Cafe implements ICafe {
    /*
    * Use some variables for your implementation.
    */
    private Node<Customer> head = null;
    private Node<Customer> tail = null;
    private int currentTime = 0;
    private int n;
    private int waitingTime; 

    public Cafe() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
        n = 0;
        waitingTime = 0;
    }

    @Override
    public void arrive(int Id, int arrivaltime, int coffee) {
        /*
        * Function input:
        *  + Id: Students Id
        *  + arrivaltime: Students arrival time
        *  + coffee: Time needed to prepare the coffee
        *
        * Job:
        *  Save the information to use later.
        */
        Customer newCustomer = new Customer(Id, arrivaltime, coffee);
        Node<Customer> newNode = new Node<Customer>(newCustomer);

        if(head == null){ 
            head = newNode;
        } else if(tail == null){
            tail = newNode;
            head.setNext(tail);
        } else{
            tail.setNext(newNode);
            tail = newNode;
        }

        n++;
    }

    @Override
    public int serve() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Serve the coffee to a student when ready. Return the student's Id.
         */
        Customer served1 = null;
        Customer served2 = null;
        int timeElapsed = 0;

        if(tail == null) { // 줄에 한 명만 있음
            served1 = head.getValue();
            head = null;

            if(currentTime == 0)
                timeElapsed = (served1.arrivaltime + served1.coffee);
            else
                timeElapsed = served1.coffee;
        } else{
            int tmp1=0, tmp2=0;
            if(currentTime == 0){
                tmp1 = head.getValue().arrivaltime + head.getValue().coffee;
                tmp2 = head.getNext().getValue().arrivaltime + head.getNext().getValue().coffee;
            } else{
                tmp1 = head.getValue().coffee;
                tmp2 = head.getNext().getValue().coffee;
            }

            if(tmp1 > tmp2 && (currentTime == 0 || head.getNext().getValue().arrivaltime <= currentTime)){
                served1 = head.getNext().getValue();
                served2 = head.getValue();
                head.setNext(head.getNext().getNext());
                timeElapsed = tmp2;
            } else{
                served1 = head.getValue();
                served2 = head.getNext().getValue();
                head = head.getNext();
                timeElapsed = tmp1;
            }
        }

        currentTime += timeElapsed;
        if(served2 != null && served2.arrivaltime < currentTime){
            int subtract = 0;
            if(timeElapsed > served2.coffee){
                 subtract = currentTime - served2.arrivaltime;
            } else{
                subtract = timeElapsed;
            }

            served2.coffee -= subtract;
            served2.coffee = (served2.coffee < 0) ? 0 : served2.coffee;
        }

        waitingTime += (currentTime - served1.arrivaltime);

        n--;
        if(n == 1){
            tail = null;
            head.setNext(tail);
        }

        return served1.Id;
    }

    @Override
    public int stat() {
        /**
         * Function input: Nothing
         * 
         * Job:
         *  Calculate the sum of the waiting time of the served students.
         */
        return waitingTime;
    }
}

class Customer{
    public int Id;
    public int arrivaltime;
    public int coffee;

    Customer(int Id, int arrivaltime, int coffee){
        this.Id = Id;
        this.arrivaltime = arrivaltime;
        this.coffee = coffee;
    }
}
