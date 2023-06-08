package loadbalancer;

import java.net.Socket;
import java.io.ObjectInputStream;
import CommonPkg.Job;
import CommonPkg.WorkerNode;
import java.io.ObjectOutputStream;

class LoadBalancerRequestHandler extends Thread {

    private Socket socket;

    LoadBalancerRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("---------------------");
            System.out.println("Received a connection");

            // Get input
            ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());

            Object obj = (Object) stream.readObject();

            System.out.println("Object Type Received: " + obj.getClass());

            if (obj.getClass() == Job.class) {
                Job incomingJob = (Job) obj;
                // handle differently depending if complete or new job
                if(incomingJob.getJobStatus().equals("complete")){
                    // iterate through all worker nodes
                    for (int i = 0; i < Network.getInstance().getWorkers().size(); i++){
                        WorkerNode worker = Network.getInstance().getWorkers().get(i);
                        // find the workerNode of the job
                        if(worker.getNodeID() == incomingJob.getWorker().getNodeID()) {
                            // decrement worker.currentJobs by 1
                            worker.removeJob();
                        }
                    }
                } else if(incomingJob.getJobStatus().equals("new")){
                    // adding job to queue
                    Network.getInstance().addJob(incomingJob);
                }
                              
                //sendJobToNode("localhost", 4100, (Job)obj);
            }

            if (obj.getClass() == WorkerNode.class) {
                Network.getInstance().addWorker((WorkerNode) obj);
                Network.getInstance().getWorkers();
            }
            System.out.println("---------------------");

            // Close our connection
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }        
                
    }
    
    
}
