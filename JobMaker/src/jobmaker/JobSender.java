/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobmaker;

import CommonPkg.Job;
import java.io.ObjectOutputStream;
import java.net.Socket;

//This class has responsibility for sending a job made in the JobServer over the
//network to the LoadBalancer.
public class JobSender extends Thread {

    //ATTRIBUTES
    AttributeGenerator gen = new AttributeGenerator();

    //METHODS
    //Runs a loop when the JobServer is in 'auto' mode sending Jobs at random
    //intervals.
    public void run() {
        while (JobServer.auto == true) {
            int waitTime = gen.genRandNumber(5, 10);
            Job newJob = new Job(gen.genJobID(), gen.genRandNumber(1, 6));
            sendJob(newJob);

            try {
                System.out.println("Auto Job complete. Time until next job: " + waitTime);
                System.out.println("---");
                Thread.sleep(waitTime * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Sends a single Job object when the 'send' command is executed
    public void manualSend() {
        
        Job newJob = new Job(gen.genJobID(), gen.genRandNumber(1, 6));
        sendJob(newJob);
        System.out.println("Manual Job complete.");
        System.out.println("---");

    }

    //Opens a socket on port 4000, creates an output stream and send the Job
    //object along the output stream.
    public void sendJob(Job job) {

        try {
            //Creating socket with the address matching our LoadBalancer.
            Socket socket = new Socket("localhost", 4000);
            //Creating an output stream capable of taking a serialized object.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //SEND the job over the created outputstream.
            out.writeObject(job);
            System.out.println("Job Object (ID:" + job.getJobID() + ") sent.");
            //Close the socket after usage.
            socket.close();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
