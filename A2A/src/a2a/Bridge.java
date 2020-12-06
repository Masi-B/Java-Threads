/*
Masimba Banda c3059877 COMP2240 Assignment 3 Bridge class for information 
about the current farmers on bridge and waiting to cross simulated by threads
Bridge 'has a' signal system hence also implements SemaphoreInterface 
 */
package a2a;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Masi
 */
public class Bridge implements SemaphoreInterface {

    private final Farmer farmer = new Farmer("", "");

    private LinkedList<Thread> readyCrossing;

    private static Semaphore sem;

    private static int numCrossed;

    private static int bridgeSteps;

    private int semValue;

    //default constructor
    Bridge(Farmer farmer) {

        readyCrossing = new LinkedList();

        numCrossed = 0;

        bridgeSteps = 0;

        sem = null;

        semValue = 0;

    }

    /* to initialise the number of semaphores from input and ready the constant stream
       waiting farmers that will cross the bridge to and fro their home and foreign island
     */
     
    
    @Override
    public synchronized void initialise() {

        sem = new Semaphore(1, true);
        semValue = sem.availablePermits();

        while (true) {
            if (Thread.currentThread().getName().contains("N")
                    || Thread.currentThread().getName().contains("S")) {
                readyCrossing.add(Thread.currentThread());
            }

            if (Thread.currentThread().getName().contains("N")) {
                System.out.format("%s\n", Thread.currentThread().getName() + ": Waiting for bridge. Going towards South"
                );
            }
            if (Thread.currentThread().getName().contains("S")) {
                System.out.format("%s\n", Thread.currentThread().getName() + ": Waiting for bridge. Going towards North"
                );
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bridge.class.getName()).log(Level.SEVERE, null, ex);
            }

            semWait();

            //return home 
            if (Thread.currentThread().getName().contains("N")) {
                System.out.format("%s\n", Thread.currentThread().getName() + ": Waiting for bridge. Going towards North");
            }
            if (Thread.currentThread().getName().contains("S")) {
                System.out.format("%s\n", Thread.currentThread().getName() + ": Waiting for bridge. Going towards South");
            }

            semWait();
        }
    }

    /* acquire semaphores else block the incoming crossing requests 
      open bridge to let only one farmer cross  
      check whether farmer is either going to foreign island or returning home
      and place in appropriate list
     */
    @Override
    public synchronized void semWait() {

        try {

            if (semValue == 1) {

                sem.acquire();

                semValue--;

                semSignal();

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Bridge.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*let farmers cross checking every 5 steps
     each step equivalent of 100 milliseconds of thread sleep time
     one farmer crosses release semaphore    
     */
    @Override
    public synchronized void semSignal() {

        int stepsTaken = 0;

        bridgeSteps = farmer.getSteps() * 100;

        if (semValue <= 0) {
            semValue++;

            while (stepsTaken != bridgeSteps) {
                stepsTaken += bridgeSteps / 3;
                try {
                    Thread.sleep(bridgeSteps / 3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bridge.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (stepsTaken != bridgeSteps) {
                    System.out.println(readyCrossing.get(0).getName()
                            + ": Crossing bridge Step " + stepsTaken / 100);
                }

            }

            System.out.println(readyCrossing.get(0).getName() + ": Across the bridge");

            readyCrossing.add(readyCrossing.remove());

            numCrossed++;
            System.out.println("NEON = " + numCrossed);
            sem.release();

        }

    }

}
