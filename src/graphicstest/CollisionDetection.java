/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.util.ArrayList;

/**
 *
 * @author Jorma Kikkelsson
 */
//IF MADE INTO RUNNABLE; there needs to be a lock for hit_enemies variable
public class CollisionDetection {
    
    public ArrayList<Enemy> enemies;
    public ArrayList<HagridBullet> bullets;
    public ArrayList<Integer> hit_enemies;
    
    public CollisionDetection(){
        enemies = Game.enemies;
        bullets = Game.bullets;
        hit_enemies = new ArrayList<Integer>();
    }
    
    public boolean isHitByEnemy(){
        for(Enemy enemy : enemies){
        //    System.out.println("char: x = " + Game.x_coord + ", x + width: " + (Game.x_coord + Settings.hagrid_width) + ", enemy: x = " + enemy.x_coord + ", x + width: " + (enemy.x_coord + enemy.width));
        //    System.out.println("char: y = " + Game.y_coord + ", y + height: " + (Game.y_coord + Settings.hagrid_height) + ", enemy: y = " + enemy.y_coord + ", y + height: " + (enemy.y_coord + enemy.height));
            if( (enemy.x_coord < Game.x_coord + Settings.hagrid_width && enemy.x_coord > Game.x_coord ) // from the right
                    || (enemy.x_coord + enemy.width > Game.x_coord && enemy.x_coord < Game.x_coord) //from the left
            ){
               // System.out.println(true);
                if( (enemy.y_coord <= Game.y_coord + Settings.hagrid_height && enemy.y_coord >= Game.y_coord ) // from the sky
                    || (enemy.y_coord + enemy.height >= Game.y_coord && enemy.y_coord <= Game.y_coord) //from below
                ){
                    return true;
                }
            }
        }
        return false;
    }
    
    //if run in a thread, need to implement a lock for the hit_enemies variable
    public int whichEnemyHit(){
        enemies = Game.enemies;
        for(HagridBullet bullet : bullets){
            for(int i = 0; i< enemies.size(); i++){
                Enemy enemy = enemies.get(i);
                if( (enemy.x_coord < bullet.x_coord + Settings.bullet_width && enemy.x_coord > bullet.x_coord ) // from the right
                        || (enemy.x_coord + enemy.width > bullet.x_coord && enemy.x_coord < bullet.x_coord) //from the left
                ){
                     System.out.println(true);
                    if( (enemy.y_coord <= bullet.y_coord + Settings.bullet_height && enemy.y_coord >= bullet.y_coord ) // from the sky
                        || (enemy.y_coord + enemy.height >= bullet.y_coord && enemy.y_coord <= bullet.y_coord) //from below
                    ){
                        if(!hit_enemies.contains(i)){
                            hit_enemies.add(i);                        
                        }
                    }
                }
            }
        }
        return 0;
    }
}
