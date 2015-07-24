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
public class HagridBullet implements Runnable {
    
    public int x_coord;
    public int y_coord;
    public int x_min_limit = -Settings.bullet_width;
    public int x_max_limit = Settings.window_length;
    public boolean goingRight = false;
    int reversed_bullet_speed = 2;

    
    
    public HagridBullet(int x, int y, boolean forward){
        x_coord = x;
        y_coord = y;
        goingRight = forward;        
    }

    @Override
    public void run() {
        if(goingRight){
            while(x_coord < x_max_limit){
                x_coord++;
      //          System.out.println(x_coord);

                try {
                    Thread.sleep(reversed_bullet_speed);
                } catch (InterruptedException ex) {
                    System.out.println("interrupted");
                }
            }
        }else{
            while(x_coord > x_min_limit){
                x_coord--;
   //             System.out.println(x_coord);

                try {
                    Thread.sleep(reversed_bullet_speed);
                } catch (InterruptedException ex) {
                    System.out.println("interrupted");
                }
            }
        }
    }
    
    
}
