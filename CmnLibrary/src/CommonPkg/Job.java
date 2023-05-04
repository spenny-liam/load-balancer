package CommonPkg;


public class Job implements java.io.Serializable {
    //Unique job identifier
    int jobID;
    //Represents a time in seconds that the job takes to complete
    int jobTime;
    //Status of the job
    String jobStatus;
    
    //Constructor method
    public Job(int newJobID, int newJobTime){
        this.jobID = newJobID;
        this.jobTime = newJobTime;
        this.jobStatus = "new";        
    }
    
    //SETTERS / GETTERS ////////////////////////////////////////////////////////
    
    //Sets the jobStatus attribute
    public void setJobStatus(String newStatus){
        this.jobStatus = newStatus;
    }
    //Gets the jobStatus attribute
    public String getJobStatus(){
        System.out.println("Returning ID" + this.jobID
                            + " Job Status: " + this.jobStatus);
        return this.jobStatus;
    }
    
    //Gets the ID status
    public int getJobID() {
        return this.jobID;
    }
    
    //Prints current attributes to the console
    public void printSelf(){
        System.out.println("Job ID: " + this.jobID);
        System.out.println("Job Time: " + this.jobTime);
        System.out.println("Job Status: " + this.jobStatus);
    }
    
}
