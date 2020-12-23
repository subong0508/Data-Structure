/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class LISP implements ILISP {
    public IStack<Character> stack = new Stack<Character>();
    /*
    * Use some variables for your implementation.
    */

    public LISP() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
    }

    @Override
    public boolean checkBracketBalance(String expression) {
        /*
        * Function input:
        *  + expression: A expression containing brackets.
        *
        * Job:
        *  Return if the four conditions meet:
        *   1. A left bracket and a right bracket of the same type consist a pair.
        *   2. There are no two pairs containing the same bracket.
        *   3. The left bracket must precede the corresponding right bracket.
        *   4. Pairs of different types of brackets must not intersect each other.
        */
        for(int i=0;i<expression.length();i++){
            char item = expression.charAt(i);
            switch(item){
                case '(':
                case '{':
                case '[':
                    stack.push(item);
                    break;
                case ')':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop() != '(')
                        return false;
                    break;
                case '}':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop() != '{')
                        return false;
                    break;
                case ']':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop() != '[')
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }
}
