/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorma Kikkelsson
 */
public class JumpAnimation implements Runnable {
    int moved = 0;
    
    @Override
    public void run() {
        int TO_MOVE = 50;
        //int moved = 0;
        System.out.println("run");
        while(moved < TO_MOVE){
            System.out.println(moved);            
            moved++;
            if(moved > TO_MOVE / 2){
                Game.y_coord++;            
            }else{
                Game.y_coord--;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JumpAnimation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("jump: emmävaantiiä");
            }
        }
    }
    
}
