public class Main {
	public static void main(String[] args) {
        cddlCheck(new CDLList());
        printLine();
        schedCheck(new RRScheduler());
        printLine();
        testSolver(6, 5);
	}

    public static void cddlCheck(ICDLList list){
        System.out.println("********** CDLList Check **********");
        // insert check
        int insertNum = (int)(Math.random()*5+3);
        for(int i=1;i<=insertNum;i++){
            System.out.printf("Inserting %d%n", i);
            list.insert(i);
        }
        System.out.println("Size: " + list.size());

        // delete check
        int delNum = (int)(Math.random()*2+1);
        for(int i=0;i<delNum;i++){
            System.out.println("Deleting ...");
            list.delete();
        }
        System.out.println("Size: " + list.size());        

        // rotateForward
        System.out.println("Head: " + list.getHead().getValue());
        list.rotateForward();
        System.out.println("After rotating forward...");
        System.out.println("Head: " + list.getHead().getValue());

        // rotateBackward
        list.rotateBackward();
        System.out.println("Went back to the original state.");
        list.rotateBackward();
        System.out.println("After rotating backward...");
        System.out.println("Head: " + list.getHead().getValue());
    }

    public static void schedCheck(IRRScheduler sched){
        System.out.println("********** RRScheduler Check **********");
        for(int i=1;i<=5;i++){
            System.out.printf("Inserting %d%n", i);
            sched.insert(i);
        }

        sched.changeDirection();
        sched.timeflow(2);

        sched.insert(6);
        for(int i=0;i<6;i++){
            System.out.printf("Doing %d%n", sched.currentJob());
            sched.done();
        }
        // for(int i=0;i<4;i++){
        //     System.out.printf("Doing %d%n", sched.currentJob());
        //     sched.done();
        // }
        // sched.done();
        // for(int i=1;i<=5;i++){
        //     System.out.printf("Inserting %d%n", i);
        //     sched.insert(i);
        // }

        // int num = (int)(Math.random()*5+1);
        // sched.changeDirection();
        // System.out.println("Changed direction.");

        // for(int i=0;i<num;i++){
        //     System.out.println("Doing Job: " + sched.currentJob());
        //     sched.done();
        // }

        // for(int i=6;i<=10;i++){
        //     System.out.printf("Inserting %d%n", i);
        //     sched.insert(i);
        // }

        // System.out.printf("Current Job: %d%n", sched.currentJob());
        // for(int i=0;i<num;i++){
        //     int run = (int)(Math.random()*3+1);
        //     sched.timeflow(run);
        //     System.out.printf("After %d timeflow... Current Job: %d%n", run, sched.currentJob());
        // }

        // sched.changeDirection();
        // System.out.println("Changed direction.");
        // System.out.printf("Current Job: %d%n", sched.currentJob());

        // num = (int)(Math.random()*5+1);
        // for(int i=0;i<num;i++){
        //     int run = (int)(Math.random()*3+1);
        //     sched.timeflow(run);
        //     System.out.printf("After %d timeflow... Current Job: %d%n", run, sched.currentJob());
        // }
    }

    public static void testSolver(int width, int height){
        System.out.println("********** TourSolver Check **********");
        ITourSolver solver = new TourSolver();
        Board board = new Board(width, height);

        System.out.println(board.getWidth() + " x " + board.getHeight());
        int missNum = (int)(Math.random()*3);
        // board.setMissing(0, 0);
        // board.setMissing(0, 1);
        for(int i=0;i<missNum;i++){
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            board.setMissing(x, y);
            System.out.printf("Missing: (%d, %d)%n", x, y);
        }
        // solve
        int[] ans = solver.solve(board);

        boolean isSolved;
        if(ans.length > 0){
            System.out.println("Printing answer...");
            isSolved = true;
        } else{
            System.out.println("No solution!");
            isSolved = false;
        }


        if(isSolved){
            int[][] grid = new int[board.getHeight()][board.getWidth()];
            int loc = 1;

            for(int e: ans){
                int x = e / board.getHeight();
                int y = e % board.getHeight();
                grid[board.getHeight()-y-1][x] = loc++;
                System.out.println("squardId: " + e);

            }

            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    if(grid[i][j] == 0)
                        System.out.printf("%3c", 'X');
                    else
                        System.out.printf("%3d", grid[i][j]);
                }
                System.out.println();
            }
        }

    }

    public static void printLine(){
        for(int i=0;i<50;i++)
            System.out.print("=");
        System.out.println();
    }

}