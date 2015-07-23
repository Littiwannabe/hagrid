package graphicstest;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorma Kikkelsson
 */
public class OptionsScreen extends JPanel {
    private BufferedImage hagridPic = null;
    private BufferedImage bulletPic = null;
    
    private void loadShit(){
        File pic_file = new File("kyrpa.jpg");
        try{
            hagridPic = ImageIO.read(pic_file);
        }catch(Exception e){
            System.out.println("Could not find file");
        }
        pic_file = new File("bullet.png");
        try{
            bulletPic = ImageIO.read(pic_file);
        }catch(Exception e){
            System.out.println("Could not find file");
        }

    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.loadShit();
        AffineTransform at;
        if(Game.goingRight){
            at = new AffineTransform();
            if(Game.isCrouching){
                at.scale(0.5, 0.25);
                at.translate(0, hagridPic.getHeight());
            }else{
                at.scale(0.5, 0.5);
            }
            at.rotate(Game.orientation, hagridPic.getWidth() / 2, hagridPic.getHeight() / 2);
        }else{
            at = AffineTransform.getScaleInstance(-1, 1);
            if(Game.isCrouching){
                at.scale(0.5, 0.25);
                at.translate(0, hagridPic.getHeight());
            }else{
                at.scale(0.5, 0.5);
            }
            at.translate(-hagridPic.getWidth(), 0);
            at.rotate(Game.orientation, hagridPic.getWidth() / 2, hagridPic.getHeight() / 2);
        }
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(hagridPic, op, Game.x_coord, Game.y_coord);
        if(Game.bullets.size() > 0){
            HagridBullet bullet;
            for(int i = 0; i < Game.bullets.size(); i++){
                bullet = Game.bullets.get(i);
                AffineTransform bullet_at = new AffineTransform();
                AffineTransformOp bullet_op;
                if(!bullet.goingRight){
                    bullet_at = AffineTransform.getScaleInstance(-1, 1);
                }
                bullet_at.scale(0.2, 0.2);
                if(bullet.goingRight){
                    bullet_at.translate(hagridPic.getWidth() + bulletPic.getWidth(), 0); //what the hell
                }

                bullet_op = new AffineTransformOp(bullet_at, AffineTransformOp.TYPE_BILINEAR);
                g2.drawImage(bulletPic, bullet_op, bullet.x_coord, bullet.y_coord);
            }
        }

    }
    
    public void init(){
        
        //loadShit();
        
     //   this.setBounds(30, 30, 400, 250);
     //   this.setVisible(true);
        //this.setVisible(false);
        //Graphics g = this.getGraphics();
        //this.drawComponent(g);
        
    }
    
}
