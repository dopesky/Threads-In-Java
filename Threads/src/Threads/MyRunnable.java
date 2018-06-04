/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author Kevin
 */
public abstract class MyRunnable implements Runnable{
        protected boolean isAlive=false;
        @Override
        public abstract void run();
        public boolean isAlive(){
                return isAlive;
        }
}
