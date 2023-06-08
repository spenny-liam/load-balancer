/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package loadbalancer;

import CommonPkg.Job;
import CommonPkg.WorkerNode;

import java.net.Socket;
import java.io.ObjectOutputStream;
/**
 *
 * @author Matt + Liam
 */

public class LoadBalancer {    

    private static void sendJobToNode(WorkerNode worker, Job job){
        try {
            // set jobs worker
            job.setWorker(worker);
            //Creating socket with the address matching our Node.
            Socket socket = new Socket(worker.getNodeIP(), worker.getNodePort());
            //Creating an output stream capable of taking a serialized object.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //SEND the job over the created outputstream.
            out.writeObject(job);
            System.out.println("Job Object (ID:" + job.getJobID() + ") passed to Node.");
            //Close the socket after usage.
            socket.close();

                } catch (Exception error) {
                    error.printStackTrace();
                }
    }
    
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
                System.out.println("Waiting for jobs...");
                // while True:
                while(true){
                    Network network = Network.getInstance();
                    WorkerNode bestWorker = new WorkerNode();
                    int highest_capacity = 0;
                    
                    // if there is at least 1 job in the queue
                    if(!network.getJobQueue().isEmpty()){
                        System.out.println("Handling next job in the queue...");
                        // iterate through all worker nodes and find the one with highest capacity
                        for (int i = 0; i < network.getWorkers().size(); i++){
                            WorkerNode worker = network.getWorkers().get(i);
                            System.out.println(worker);
                            if(worker.getMaxJobs() - worker.getCurrentJobs() > highest_capacity) {
                                System.out.println("Better worker");
                                bestWorker = worker;
                                highest_capacity = worker.getMaxJobs() - worker.getCurrentJobs();
                            }
                        }
                        System.out.println(bestWorker);
                        System.out.println(highest_capacity);

                        // if a worker was found with capacity greater than 0
                        if(highest_capacity > 0) {
                            // get and remove job from queue
                            Job job = network.dequeueJob();
                            // send job to selected worker 
                            sendJobToNode(bestWorker, job);
                            // increment worker.curren tJObs by 1
                            bestWorker.addNewJob();
                            System.out.println("Sent a job");
                            
                        }
                    }
                }
            }
        });  
        executeJobs.start();
        
    }
    
}
