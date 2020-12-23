/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class TourSolver implements ITourSolver {
    /*
     * Add some variables you will use.
     */
    private int[] dxs = {2, 2, 1, 1, -1, -1, -2, -2};
    private int[] dys = {-1, 1, -2, 2, -2, 2, -1, 1};

    private boolean isAvailable(Board board, int x, int y, int[] check){
    	if(0 <= x && x < board.getWidth() && 0 <=y && y < board.getHeight() && check[board.squareId(x, y)] == 0)
    		return true;

    	return false;
    }

    private int[] innerSolve(Board board, int x, int y, int[] check, int[] sol, int loc){
    	// 탐색 완료
    	if(loc == sol.length)
    		return sol;

    	int[] res = null;
    	for(int i=0;i<8;i++){
    		if(isAvailable(board, x+dxs[i], y+dys[i], check)){ 
    			int nextX = x+dxs[i];
    			int nextY = y+dys[i];
    			check[board.squareId(nextX, nextY)] = 1;
    			sol[loc] = board.squareId(nextX, nextY);
    			res = innerSolve(board, nextX, nextY, check, sol, loc+1);
    		
	    		if(res != null){
	    			return res;
	    		} else{
	    			check[board.squareId(nextX, nextY)] = 0;
	    		}
    		} else{
                return null;
            }
    	}
    	
    	return res;
    }

    @Override
    public int[] solve(Board board) {
        /*
        * Function input:
        *  + board: A board with some missing squares.
        *
        * Job:
        *  Return a seqence of knight's tour solution on the given board.
        *  If there is no solution, return an empty sequence.
        */
        // missing은 -1로 처리
        int[] check = new int[board.getWidth() * board.getHeight()];
        int len = 0;
        for(int i=0;i<board.getWidth();i++){
        	for(int j=0;j<board.getHeight();j++){
        		if(board.isMissing(i, j)){
        			check[board.squareId(i, j)] = -1;
        		} else{
        			check[board.squareId(i, j)] = 0;
        			len++;
        		}
        	}
        }

        System.out.println("len: " + len);
        int[] originSol = new int[len];
        int[] res = null;

        int i = 0, j = 0;
        outer:
            for(i=0;i<board.getWidth();i++){
            	for(j=0;j<board.getHeight();j++){
            		if(!board.isMissing(i, j)){
                        break outer;
            		}	
            	}
            }

        System.out.printf("Starting at (%d, %d)%n", i, j);
        int[] sol = originSol.clone();
        check[board.squareId(i, j)] = 1;
        sol[0] = board.squareId(i, j);
        res = innerSolve(board, i, j, check, sol, 1);
        
        if(res == null)
            res = new int[] {};

        return res;
	}
}
