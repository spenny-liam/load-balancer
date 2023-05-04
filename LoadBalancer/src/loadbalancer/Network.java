/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import CommonPkg.Job;

/**
 *
 * @author Matt + Liam
 */

public class Network {
    private int serverPort = -1;
    private ServerSocket socket = null;
    
    //CONSTRUCTOR
    public Network(int port) {
        serverPort = port;
    }
    
    //Starting the network, listening for activity on assigned port#
    public void runNetwork(){
        System.out.println("-----------------------------------------------");
        System.out.println("NETWORK IS ACTIVE");
        System.out.println("Listening on PORT #" + this.serverPort);
        System.out.println("-----------------------------------------------");
        
        while(true)
        {
        try{
            
            socket = new ServerSocket(serverPort);
            Socket reciever = socket.accept();
            socket.setSoTimeout(0); // 0 = Listens forever
            
            ObjectInputStream stream = new ObjectInputStream(reciever.getInputStream());
            
            Object testObject = (Object)stream.readObject();
            
            System.out.println("Object Type Received: " + testObject.getClass());
            System.out.println( ((Job)testObject).getJobStatus() );
            
            reciever.close();
            socket.close();

        } catch(Exception error) {
            error.printStackTrace();
        } finally {
            //In the event of a failure, we close our socket down trying to
            //avoid system crash
            try {
                socket.close();
            } catch(Exception error){                
            }
        }
        }
    }
}
