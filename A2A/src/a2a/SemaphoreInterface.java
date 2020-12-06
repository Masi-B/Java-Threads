/*
Masimba Banda c3059877 COMP2240 Assignment 3 Semaphore Interface
To be implements by multiple programs that use semaphores hence an interface 
 */
package a2a;

/**
 *
 * @author Masi
 */
public interface SemaphoreInterface {
    
    
     void initialise();
     void semWait();
     void semSignal();
    
}
