package node;

import CommonPkg.WorkerNode;
import java.util.Scanner;

/**
 *
 * @author Matt + Liam
 */

public class NodeNetwork {

    private static NodeNetwork network;
    private WorkerNode workerNode;
    private NodeSocketServer server;

    //Creating a Socket Server
    public NodeNetwork() {
        Scanner sc = new Scanner(System.in);
        
        // get nodeID from input
        int nodeID;
        while(true){
            System.out.println("Node ID: ");
            try {
                nodeID = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: Node ID must be an integer");
            }
        }
        // get nodeName from input
        System.out.println("Node name: ");
        String nodeName = sc.nextLine();
        
        // get nodePort from input
        int nodePort;
        while(true){
            System.out.println("Node port: ");
            try {
                nodePort = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: Node port must be an integer");
            }
        }
        
        // get maxJobs from input
        int maxJobs;
        while(true){
            System.out.println("Node max jobs: ");
            try {
                maxJobs = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Error: max jobs must be an integer");
            }
        }
        workerNode = new WorkerNode(nodeID,      //NODE ID
                                    nodeName,   //NODE NAME
                                    "localhost", //NODE IP
                                    nodePort,   //NODE PORT
                                    maxJobs);   //NODE CAPACITY
        server = new NodeSocketServer(workerNode.getNodePort());
        server.startServer();
    }

    //Returns the singleton instance of our network which should allow access
    //across entire app. If no instance is found yet it will instantiate one.
    public static synchronized NodeNetwork getInstance() {
        if (network == null) {            
            network = new NodeNetwork();
        }
        return network;
    }

    
    public void registerSelf(){
        workerNode.registerWithServer();
    }

}
