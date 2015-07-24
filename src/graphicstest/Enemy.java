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
public class Enemy implements Runnable{
    
    public int x_coord;
    public int y_coord;
    public int width;
    public int height;
    
    public Enemy(int x, int y){
        x_coord = x;
        y_coord = y;
        width = Settings.def_enemy_width;
        height = Settings.def_enemy_height;
    }
    
    public void Attack(){
        
    }

    
    @Override
    public void run() {
        while(true){
            int targetx = Game.x_coord;
            int targety = Game.y_coord;
            int to_move = 0;
            int x_step = 0;  //default version only moves along the ground ignoring the terrain
            if(targetx + Settings.hagrid_width < this.x_coord){
                x_step = -1;
                to_move = this.x_coord - targetx - Settings.hagrid_width;
            }else if(targetx > this.x_coord + this.width){
                x_step = 1;
                to_move = targetx - this.width - this.x_coord;
            }
            int counter = 0;
            while(counter < to_move){
                counter++;
                this.x_coord += x_step;
                try {
                    Thread.sleep(Settings.def_reverse_enemy_speed);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted");
                }
            }
        }
    }
}
