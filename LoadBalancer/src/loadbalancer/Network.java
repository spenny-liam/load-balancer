
package loadbalancer;

import CommonPkg.WorkerNode;
import CommonPkg.Job;
import java.util.ArrayList;


/**
 *
 * @author Matt + Liam
 */

public class Network {
    
    private static Network network;
    private int serverPort = 4000;
    private ArrayList<WorkerNode> workers = new ArrayList<WorkerNode>();
    private LoadBalancerSocketServer server = new LoadBalancerSocketServer(serverPort);
    private ArrayList<Job> jobQueue = new ArrayList<Job>();
    
    
    //Creating a Socket Server
    public Network(){
        server.startServer();
    }
    
    //Returns the singleton instance of our network which should allow access
    //across entire app. If no instance is found yet it will instantiate one.
    public static synchronized Network getInstance(){
        if(network == null)
        {
            network = new Network();
        }
        return network;
    }
    
    //Add a worker node to the workers ArrayList
    public void addWorker(WorkerNode worker){
        workers.add(worker);
    }
    
    //Retrieve the current ArrayList of worker nodes
    public void getWorkers(){
        for(WorkerNode worker : workers){
            System.out.println(worker);
        }
        return workers;
    }

    // add job to queue
    public void addJob(Job job){
        jobQueue.add(job);
    }

    public void getJobQueue(){
        for(Job job : jobQueue){
            System.out.println(job);
        }
        return jobQueue;
    }

    // remove job from queue
    public void dequeueJob(){
        Job job = jobQueue.get(0);
        jobQueue.remove(0);
        return job;
    }
    
    
}
