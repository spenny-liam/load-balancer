/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package loadbalancer;


/**
 *
 * @author Matt + Liam
 */

public class LoadBalancer {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Network network = new Network(4000);
        network.runNetwork();
    }
    
}
