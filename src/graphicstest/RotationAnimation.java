/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

/**
 *
 * @author Jorma Kikkelsson
 */
public class RotationAnimation implements Runnable{
    double rotated = 0;
    
    @Override
    public void run() {
        double single_step = 0.1;
        if(!Game.goingRight){
            single_step = -0.1;
        }
        double TO_MOVE = 2 * Math.PI;
        //int moved = 0;
        while(rotated < TO_MOVE){
            rotated = rotated + 0.1;
            Game.orientation = Game.orientation + single_step;
            System.out.println(rotated);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.print("rotate: emmävaantiiä");
            }
        }
        Game.orientation = 0;
    }

}
