/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Jorma Kikkelsson
 */
public class Game {
    public static int x_coord = 150;
    public static int y_coord = 100;
    public static double orientation = 0;
    public static boolean goingRight = true;
    public static boolean isCrouching = false;
    
    JFrame ikkuna;
    
    JPanel card_LA = new JPanel(new CardLayout());
    
    JPanel opt_scr;
    JPanel main_scr;
    
    Thread jump_thread;

    GameSound sounds = new GameSound();
    
    public static ArrayList<HagridBullet> bullets = new ArrayList<>();
    
    private void init() throws Exception{
        //check if ikkuna already exists?
        Action doNothing = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JPanel paneeli = (JPanel)e.getSource();                        
                Color col = paneeli.getBackground();
                //paneeli.setBackground(Color.black);
                if(col == Color.yellow){
                    paneeli.setBackground(Color.blue);
                }else{
                    paneeli.setBackground(Color.yellow);
                }
                x_coord++; 
                //do nothing
            }
        };
        
        String MAIN = "main window";
        String OPT = "options screen";
        
        sounds.loadSounds();

        this.ikkuna = new JFrame();
        ikkuna.setVisible(true);
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setBackground(Color.yellow);
        //ikkuna.setBounds(20, 20, 500, 300);
        ikkuna.setSize(Settings.window_length, Settings.window_height);
        OptionsScreen opt = new OptionsScreen();
        InputMap input = opt.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    //    input.put(KeyStroke.getKeyStroke('g'), "g");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0 , false), "derp");
    //    input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0 , false), "up");
    //    input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0 , false), "right");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0 , false), "left");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0 , false), "down");
        
      //  input.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0 , false), 
        opt.getActionMap().put("derp", doNothing);
      //  opt.getActionMap().put("up", goUp);
      //  opt.getActionMap().put("right", goRight);
      //  opt.getActionMap().put("left", goLeft);
      //  opt.getActionMap().put("down", goDown);

        opt.init();
        
        this.opt_scr = opt;
        
        ikkuna.add(card_LA);
        card_LA.add(opt, OPT);
        
        //ikkuna.add(opt_scr);
     //   opt_scr.init(ikkuna.getGraphics());
        ikkuna.validate();
        ikkuna.repaint();
        long now = System.currentTimeMillis();
        int update_counter = 0; //used to control FPS
        main_scr = new SomeOtherScreen();
    //    this.ikkuna.add(main_scr);
        card_LA.add(main_scr, MAIN);
        ikkuna.validate();
        ikkuna.repaint();
        boolean is_opt = true;
        //sounds.playBGM();
     //   KeyboardHandler kb = new KeyboardHandler(card_LA);
        KeyboardHandler kb;
   //     KeyboardHandler kb_main = new KeyboardHandler(main_scr, false);
        KeyboardHandler kb_opt = new KeyboardHandler(card_LA);
        
        kb = kb_opt;
        
        
        //Game loop starts here
        while(true){
            update_counter++;
            //System.out.println(laskuri / FPS);
            if(kb.up_pressed && is_opt){
                //y_coord--;
                JumpAnimation jump = new JumpAnimation();
                //sounds.playJump();
                Thread run_jump = new Thread(jump);
                Thread run_jump_sounds = new Thread(sounds);
                run_jump.start();
                run_jump_sounds.start();
            }
            if(kb.right_pressed && is_opt){
                x_coord++;
                goingRight = true;
            }
            if(kb.down_pressed && is_opt){
                isCrouching = true;
            }else{
                isCrouching = false;
            }
            if(kb.left_pressed && is_opt){
                x_coord--;
                goingRight = false;
            }
            if(kb.x_pressed){
//                main_scr.setVisible(true);
                CardLayout layout = (CardLayout)card_LA.getLayout();
                kb.x_pressed = false;
                if(is_opt){
                    layout.show(card_LA, MAIN);
                    sounds.pauseBGM();
                    
                }else{
                    layout.show(card_LA, OPT);
                    sounds.playBGM();
                }
                is_opt = !is_opt; //reverse boolean
                
            }
            if(kb.z_pressed && is_opt){
//                orientation = orientation + 0.1;
                RotationAnimation rotation = new RotationAnimation();
                Thread rot_thread = new Thread(rotation);
                rot_thread.start();
                kb.z_pressed = false;
            }
            if(kb.c_pressed && is_opt){
                kb.c_pressed = false;
                HagridBullet new_bullet;
                if(isCrouching){
                    new_bullet = new HagridBullet(x_coord, y_coord + Settings.hagrid_height / 2, goingRight);  
                }else{
                    new_bullet = new HagridBullet(x_coord, y_coord, goingRight);
                }
                bullets.add(new_bullet);
                Thread bullet_thread = new Thread(new_bullet);
                bullet_thread.start();
            }

            card_LA.repaint();  

            Thread.sleep(1000 / Settings.FPS); //assumes execution doesn't take ANY time
        }
        //TODO :    get rid of saved bulled intances after max_distance or hit
        //          bullets go too much to the right, why?
        //          fix FPS to take into account the execution time
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Game game = new Game();
        game.init();
        
    }
    
}
