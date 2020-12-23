/*
 * Name: 정재은
 * Student ID #: 2017122063
 */

/* 
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class RRScheduler implements IRRScheduler {
    /*
     * Add some variables you will use.
     */
    private CDLList jobList = new CDLList();
    private boolean isNormal = true;

    @Override
    public void insert(int id) {
        /*
        * Function input:
        *  + id: the job id to insert
        *
        * Job:
        *  Insert the job at the back of the scheduler.
        */
        jobList.insert(id);
    }

    @Override
    public void done() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  One time segment passes and the job processed is deleted
        */
        try{
        	if(isNormal){
	            jobList.rotateForward();
	            jobList.delete();
	        }else{
	        	jobList.rotateBackward();
	        	jobList.deleteForward();
	        }
        } catch(IllegalStateException e){
            System.out.println("Nothing to handle.");
        }
    }

    @Override
    public void timeflow(int n) {
        /*
        * Function input:
        *  + n: An integer.
        *
        * Job:
        *  Simulate n time segments.
        */
        for(int i=0;i<n;i++){
        	try{
        		if(isNormal)
            		jobList.rotateForward();
            	else
            		jobList.rotateBackward();
            } catch(IllegalStateException e){
            	System.out.println("Timeflow doesn't work.");
            	break;
            }
        }

    }

    @Override
    public void changeDirection() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Change the direction of the scheduler.
        */
        isNormal = !isNormal;
    }

    @Override
    public int currentJob() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the current job.
        */
        if(jobList.isEmpty())
            throw new IllegalStateException("There isn't any job processed.");
        
        return jobList.getHead().getValue();
    }
}
