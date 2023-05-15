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
                System.out.println(((Job) obj).getJobStatus());                
                sendJobToNode("localhost", 4100, (Job)obj);
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
    
    private void sendJobToNode(String nodeIP, int nodePort, Job job){
        try {
                    //Creating socket with the address matching our Node.
                    Socket socket = new Socket(nodeIP, nodePort);
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
    
    
}
