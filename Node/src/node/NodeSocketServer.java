
package node;


import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class NodeSocketServer extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public NodeSocketServer(int port) {
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
        NodeNetwork.getInstance().registerSelf();
        System.out.println("-----------------------------------------------");
        System.out.println("NODE IS ACTIVE");
        System.out.println("Listening on PORT #" + this.port);
        System.out.println("-----------------------------------------------");
        running = true;
        while (running) {
            try {
                // Call accept() to receive the next connection
                Socket receiveSocket = serverSocket.accept();

                // Pass the socket to the RequestHandler thread for processing
                NodeRequestHandler requestHandler = new NodeRequestHandler(receiveSocket);
                requestHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}