/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jobmaker;

import java.util.Scanner;

/////////////////////////
public class JobServer {

    //ATTRIBUTES
    public static boolean auto = false;

    //MAIN METHOD
    public static void main(String[] args) {

        // CREATING NECESSARY OBJECTS
        Scanner sc = new Scanner(System.in);
        
        //TESTING THREADING AUTO MODE
        while (true) {

            String input = sc.nextLine();

            if (input.equals("auto")) {
                System.out.println("ENTERING AUTO MODE");
                JobSender send = new JobSender();
                JobServer.auto = true;
                send.start();
            }
            if (input.equals("x")) {
                JobServer.auto = false;
                System.out.println("EXITING AUTO MODE");
            }
            if (input.equals("send")) {
                JobSender send = new JobSender();
                send.manualSend();
            }

        }
        
    }

}
