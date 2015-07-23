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
    private BufferedImage crouching_hagridPic = null;
    private BufferedImage rev_hagridPic = null;
    private BufferedImage rev_crouching_hagridPic = null;

    private BufferedImage hagridImage = null;
    
    private BufferedImage bulletPic = null;
    private BufferedImage rev_bulletPic = null;
    
    private BufferedImage bulletImage = null;
    
    
    private void loadShit(){
        File pic_file = new File("kyrpa.jpg");
        try{
            hagridPic = ImageIO.read(pic_file);
        }catch(Exception e){
            System.out.println("Could not find file");
        }
        hagridPic = resize(hagridPic, Settings.hagrid_width, Settings.hagrid_height);
        crouching_hagridPic = resize(hagridPic, Settings.hagrid_width, Settings.hagrid_height / 2);
        rev_hagridPic = flip(hagridPic);
        rev_crouching_hagridPic = flip(crouching_hagridPic);
        pic_file = new File("bullet.png");
        try{
            bulletPic = ImageIO.read(pic_file);
            bulletPic = resize(bulletPic, Settings.bullet_width, Settings.bullet_height);
            rev_bulletPic = flip(bulletPic);
        }catch(Exception e){
            System.out.println("Could not find file");
        }

    }

    public BufferedImage resize(BufferedImage image, double newW, double newH){
        int picHeight = image.getHeight();
        int picWidth = image.getWidth();
        AffineTransform resize = new AffineTransform();
        resize.scale(newW / picWidth, newH / picHeight);
        AffineTransformOp op = new AffineTransformOp(resize, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
    
    public BufferedImage flip(BufferedImage image){
        AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
        flip.translate(-image.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(flip, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
        return image;
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.loadShit();
        int y_coord = Game.y_coord;
        if(Game.goingRight){
            if(Game.isCrouching){
                hagridImage = crouching_hagridPic;
                y_coord = y_coord + hagridImage.getHeight();
            }else{
                hagridImage = hagridPic;
            }
        }else{
            if(Game.isCrouching){
                hagridImage = rev_crouching_hagridPic;
                y_coord = y_coord + hagridImage.getHeight();
            }else{
                hagridImage = rev_hagridPic;
            }
        }
        Graphics2D g2 = (Graphics2D)g;

        if(Game.orientation != 0){
            AffineTransform at = new AffineTransform();
            at.rotate(Game.orientation, hagridImage.getWidth() / 2, hagridImage.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            g2.drawImage(hagridImage, op, Game.x_coord, y_coord);        
        }else{
            g2.drawImage(hagridImage, null, Game.x_coord, y_coord);
        }
        
        for(HagridBullet bullet : Game.bullets){
            if(!bullet.goingRight){
                bulletImage = rev_bulletPic;
            }else{
                bulletImage = bulletPic;
            }
            g2.drawImage(bulletImage, null, bullet.x_coord, bullet.y_coord);
        }
        ///////////
        
   /*     if(Game.goingRight){
            at = new AffineTransform();
            if(Game.isCrouching){
                at.translate(0, hagridPic.getHeight());
            }else{
            }
            at.rotate(Game.orientation, hagridPic.getWidth() / 2, hagridPic.getHeight() / 2);
        }else{
            at = AffineTransform.getScaleInstance(-1, 1);
            if(Game.isCrouching){
                at.translate(0, hagridPic.getHeight());
            }else{
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
           */
    }
    

    
}
