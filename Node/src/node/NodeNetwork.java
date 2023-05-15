package node;

import CommonPkg.WorkerNode;

/**
 *
 * @author Matt + Liam
 */

public class NodeNetwork {

    private static NodeNetwork network;
    private WorkerNode workerNode = new WorkerNode(1,           //NODE ID
                                                   "Node1",     //NODE NAME
                                                   "localhost", //NODE IP
                                                   4100,        //NODE PORT
                                                   30);         //NODE CAPACITY
    private NodeSocketServer server = new NodeSocketServer(workerNode.getNodePort());

    //Creating a Socket Server
    public NodeNetwork() {
        
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
