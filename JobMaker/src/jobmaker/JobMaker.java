/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jobmaker;

import JobPkg.Job;
import java.io.ObjectOutputStream;
import java.net.Socket;
        
/**
 *
 * @author Matt
 */


public class JobMaker {

Job newJob = new Job(1, 1);
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TESTING JOB CLASS
        Job jobTest = new Job(-1, 1);
        
        //THIS IS A TEST TO SEND DATA TO THE BALANCER///////////////////////////
        
        try {
            
            Socket socket = new Socket("localhost", 4000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            out.writeObject(jobTest);
            System.out.println("Job Object (ID:"+ jobTest.getJobID() +") sent.");
            
            socket.close();
            
        } catch(Exception error) {
            error.printStackTrace();
        }
        
        ////////////////////////////////////////////////////////////////////////
        
        
        
        
    }
    
}
