/*
 Masimba Banda c3059877 COMP2240 Assignment 3 Farmer class for information 
Is an object of Farmer with information such as origin island and id of farmer
 */
package a2a;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masi
 */
public class Farmer implements Runnable {

    // variables for farmer class
    private final String originIsland;
    private final String farmerID;
    private final int steps;

    private Bridge bridge = null;

    //default constructor
    Farmer(String farmerID, String originIsland) {

        this.farmerID = farmerID;
        this.originIsland = originIsland;
        steps = 15;

    }

    //accessors to retrieve farmer information
    public String getFarmerOrigin() {
        return originIsland;
    }

    public String getFarmerID() {
        return farmerID;
    }

    public int getSteps() {
        return steps;
    }

    /*
      set each farmer thread name as the farmer id as the thread starts
      and then cross the bridge whilst making each thread sleep to allow 
      each to be processed
     */
    @Override
    public void run() {

        Thread.currentThread().setName(farmerID);
        bridge = new Bridge(this);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
        }

        bridge.initialise();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
