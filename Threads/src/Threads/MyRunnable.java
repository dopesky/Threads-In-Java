/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 * An abstract class that implements the Runnable
 * class and adds some methods and variables to it.
 * @author Kevin
 */
public abstract class MyRunnable implements Runnable{
        /**
         * A variable that is true if a thread is currently executing and false if a thread terminates.
         */
        protected boolean isAlive=false;
        
        /**
         * Overrides the method in the superclass.
         */
        @Override
        public abstract void run();
        
        /**
         * Reads the protected variable isAlive in this class and returns it.
         * @return 
         */
        public boolean isAlive(){
                return isAlive;
        }
        
}
