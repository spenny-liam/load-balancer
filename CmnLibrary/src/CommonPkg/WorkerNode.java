package CommonPkg;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WorkerNode implements java.io.Serializable {

    int nodeID;
    String nodeName;
    String nodeIP;
    int nodePort;
    int maxJobs;
    int currentJobs = 0;
    
    public WorkerNode(int newNodeID, String newNodeName, String newNodeIP,
            int newNodePort, int newMaxJobs) {
        nodeID = newNodeID;
        nodeName = newNodeName;
        nodeIP = newNodeIP;
        nodePort = newNodePort;
        maxJobs = newMaxJobs;
    }
    
    //Override toString() so that within our Network class we have more 
    //operable ArrayList to play around with.
    @Override
    public String toString() {
        return "WorkerNode{" + "nodeID=" + nodeID + ", nodeName=" + nodeName + ","
                + " nodeIP=" + nodeIP + ", nodePort=" + nodePort + ", maxJobs=" + 
                maxJobs + ", currentJobs=" + currentJobs + '}';
    }
    
    
    public void printNodeInfo() {
        System.out.println("---------");
        System.out.println("NODE INFO");
        System.out.println("---------");
        System.out.println("Node ID: " + this.nodeID);
        System.out.println("Node Name: " + this.nodeName);
        System.out.println("Node Address: " + this.nodeIP + "." + this.nodePort);
        System.out.println("Node Capacity: " + this.maxJobs);
        System.out.println("---------");
    }

    public int getNodePort() {
        System.out.println("NODE: " + this.nodeName);
        System.out.println("PORT: " + this.nodePort);
        return this.nodePort;
    }

    public String getNodeIP(){
        return this.nodeIP;
    }

    public void registerWithServer() {
        try {
            //Creating socket with the address matching our LoadBalancer.
            Socket socket = new Socket("localhost", 4000);
            //Creating an output stream capable of taking a serialized object.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //SEND the Node over the created outputstream.
            out.writeObject(this);
            System.out.println("Node sent.");
            //Close the socket after usage.
            socket.close();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    
    public void addNewJob(){
        this.currentJobs = this.currentJobs + 1;
    }
    
    public void removeJob(){
        this.currentJobs = this.currentJobs - 1;
    }

    public int getNodeID(){
        return this.nodeID;
    }

    public int getCurrentJobs(){
        return this.currentJobs;
    }

}
