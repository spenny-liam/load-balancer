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
        
        //Calling the getInstance method will instantiate our Network singleton
        //class -> it's constructor will handle the creation of a socket and
        //begin listening for connections to the LoadBalancer server on port
        //4000
        Network.getInstance();
        
    }
    
}
