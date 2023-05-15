/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class LoadBalancerSocketServer extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public LoadBalancerSocketServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        System.out.println("-----------------------------------------------");
        System.out.println("NETWORK IS ACTIVE");
        System.out.println("Listening on PORT #" + this.port);
        System.out.println("-----------------------------------------------");
        running = true;
        while (running) {
            try {
                // Call accept() to receive the next connection
                Socket receiveSocket = serverSocket.accept();

                // Pass the socket to the RequestHandler thread for processing
                LoadBalancerRequestHandler requestHandler = new LoadBalancerRequestHandler(receiveSocket);
                requestHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
