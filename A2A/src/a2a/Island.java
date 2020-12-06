/*
  Masimba Banda c3059877 COMP2240 Assignment 3 Island class for information 
 * Sets the farmers obtained from input whilst utilising threads
 * Separates the farmers based on their island of origin
 */
package a2a;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Masi
 */
public class Island implements Runnable {

    private static Bridge bridge;

    private final LinkedList<Farmer> northFarmers;

    private final LinkedList<Farmer> southFarmers;

    private ArrayList<Farmer> north = new ArrayList();

    private ArrayList<Farmer> south = new ArrayList();

    private final LinkedList<Thread> southThreads = new LinkedList();

    private final LinkedList<Thread> northThreads = new LinkedList();

    private boolean hasSouth, hasNorth;

    private Thread farmerThread;

    /*default constructor.set the farmers to class array list and then
     call run method to set in threads to start
     with each new farmer added
     */
    Island(ArrayList<Farmer> north, ArrayList<Farmer> south) {

        northFarmers = new LinkedList();
        southFarmers = new LinkedList();

        this.north = north;

        this.south = south;

        hasSouth = !south.isEmpty();

        hasNorth = !north.isEmpty();

        if (!hasSouth && !hasNorth) {
            System.out.println("No farmers on either island");
            System.exit(0);
        }

    }

    //  utilise threads when adding farmers in list ready to cross
    @Override
    public void run() {

        if (hasNorth) {
            for (int i = 0; i < north.size(); i++) {
                farmerThread = new Thread(north.get(i));
                bridge = new Bridge(north.get(i));
                farmerThread.start();
                northThreads.add(farmerThread);
            }

        }

        if (hasSouth) {
            for (int i = 0; i < south.size(); i++) {
                farmerThread = new Thread(south.get(i));
                bridge = new Bridge(south.get(i));
                farmerThread.start();
                southThreads.add(farmerThread);
            }
        }

    }

}
