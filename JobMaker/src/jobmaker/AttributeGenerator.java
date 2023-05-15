/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jobmaker;

public class AttributeGenerator {

    public int genJobID() {
        
        IDFileHandler fHandler = new IDFileHandler();
        int newJobID = fHandler.getCurrentIDNumber();
        return newJobID;
    }

//    public int genJobID() {
//        idCounter++;
//        return idCounter;
//    }
    public int genRandNumber(int min, int max) {
        //Generates a random number between 0 - 6
        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return randomInt;
    }

}
