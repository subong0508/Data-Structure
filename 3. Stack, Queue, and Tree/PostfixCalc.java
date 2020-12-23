/*
* Name: 정재은
* Student ID #: 2017122063
*/

/*
* Do NOT import any additional packages/classes.
* If you (un)intentionally use some additional packages/classes we did not
* provide, you may receive a 0 for the homework.
*/

public class PostfixCalc implements ICalc {
    public IStack<Integer> stack;
    /* Use some variables for your implementation. */

    public PostfixCalc() {
        this.stack = new Stack<>();
        /*
        * Constructor
        */
    }

    @Override
    public int evaluate(String expression) {
        /*
        * Function input:
        *  + expression: A postfix expression.
        *
        * Job:
        *  Return the value of the given expression.
        *
        * The postfix expression is a valid expression with
        * length of at least 1.
        * There are +(addition), -(subtraction) and *(multiplication)
        * operators and only non-negative integers, seperated with
        * a single space symbol.
        */
        String[] exps = expression.split(" ");
        for(String exp : exps){
            int num1, num2;
            switch(exp){
                case "+":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1+num2);
                    break;
                case "-":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1-num2);
                    break;
                case "*":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1*num2);
                    break;
                default:
                    stack.push(Integer.parseInt(exp));
                    break;
            }
        }

        return stack.pop();
    }
}