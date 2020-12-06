/*
Masimba Banda c3059877 COMP2240 Assignment 3 PMain class for Part A for
Sets the number of farmers for each island and start simulation of threads
 */
package a2a;

import java.util.ArrayList;

/**
 *
 * @author Masi
 */
public class A2A {

    /**
     * @param args the command line arguments
     */
    private Island island = null;

    private final static A2A a2 = new A2A();

    private ArrayList<Farmer> northFarmers;

    private ArrayList<Farmer> southFarmers;

    private Farmer farmer = new Farmer("", "");

    private int idNum;

    private String id;

    //default constructor
    A2A() {

        idNum = 1;
        id = "";
    }

    // set farmers from input and start threads   
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        a2.setFarmers(2, 2);

    }

    /*set farmers information and set into lists based on 
     number of farmers from each island
     */
    private void setFarmers(int north, int south) throws InterruptedException {

        idNum = 1;

        northFarmers = new ArrayList<>();
        for (int i = 0; i < north; i++) {
            id = "N_" + "Farmer" + idNum++;
            farmer = new Farmer(id, "North");
            northFarmers.add(farmer);
        }

        idNum = 1;

        southFarmers = new ArrayList<>();

        for (int i = 0; i < south; i++) {
            id = "S_" + "Farmer" + idNum++;
            farmer = new Farmer(id, "South");
            southFarmers.add(farmer);

        }

        island = new Island(northFarmers, southFarmers);
        (new Thread(new Island(northFarmers, southFarmers))).start();

    }

}
