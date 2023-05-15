/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.Socket;
import java.io.ObjectInputStream;
import CommonPkg.Job;

class NodeRequestHandler extends Thread {

    private Socket socket;

    NodeRequestHandler(Socket socket) {
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
            }
            System.out.println("---------------------");

            // Close our connection
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
