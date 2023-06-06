/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package loadbalancer;

import CommonPkg.Job;
import CommonPkg.WorkerNode;

/**
 *
 * @author Matt + Liam
 */

public class LoadBalancer {    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Calling the getInstance method will instantiate our Network singleton
        //class -> it's constructor will handle the creation of a socket and
        //begin listening for connections to the LoadBalancer server on port
        //4000
        Network network = Network.getInstance();
        Thread executeJobs = new Thread(new Runnable() {
            @Override
            public void run() {
                // code goes here.
                // while True:
                while(true){
                    // if there is at least 1 job in the queue
                    if(network.getJobQueue().any()){

                        int highest_capacity = 0;
                        WorkerNode best_worker = new WorkerNode;

                        // iterate through all worker nodes and find the one with highest capacity
                        for (int i = 0; i < network.getWorkers().size(); i++){
                            WorkerNode worker = network.getWorkers().get(i);
                            if(worker.getCurrentJobs() > highest_capacity) {
                                best_worker = worker;
                                highest_capacity = worker.getCurrentJobs();
                            }
                        }

                        // if a worker was found with capacity greater than 0
                        if(highest_capacity > 0) {
                            // get and remove job from queue
                            Job job = network.dequeueJob();
                            // send job to selected worker 
                            sendJobToNode(best_worker, job);
                            // increment worker.curren tJObs by 1
                            best_worker.addNewJob();
                            
                        }
                    }
                }
            }
        });  
        executeJobs.start();
        
    }
    
}
