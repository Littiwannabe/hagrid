/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jorma Kikkelsson
 */
public class KeyboardHandler {
    public boolean up_pressed = false;
    public boolean down_pressed = false;
    public boolean right_pressed = false;
    public boolean left_pressed = false;
    public boolean x_pressed = false;
    public boolean z_pressed = false;
    public boolean c_pressed = false;
    
    public JPanel panel = null;
    
    public KeyboardHandler (JPanel panel){
        this.panel = panel;
        InputMap input = this.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = this.panel.getActionMap();
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        actions.put("up", up);
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "up_release");
        actions.put("up_release", up_release);

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        actions.put("right", right);        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right_release");
        actions.put("right_release", right_release);
        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        actions.put("left", left);        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left_release");
        actions.put("left_release", left_release);

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        actions.put("down", down);        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "down_release");
        actions.put("down_release", down_release);

//        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0, false), "x");
//        actions.put("x", x_press);        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0, true), "x_release");
        actions.put("x_release", x_release);

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "z_release");
        actions.put("z_release", z_release);
        
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, true), "c_release");
        actions.put("c_release", c_release);

        
    }
    
    Action up = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                up_pressed = true;
                System.out.println("ylös");
        }
    };

    Action up_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                up_pressed = false;
                System.out.println("hammertime");
        }
    };
    
    Action down = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                down_pressed = true;
        }
    };

    Action down_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                down_pressed = false;
        }
    };
    
    Action right = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                right_pressed = true;
        }
    };

    Action right_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                right_pressed = false;
        }
    };

    
    Action left = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                left_pressed = true;
        }
    };

    Action left_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                left_pressed = false;
        }
    };    

    Action x_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                x_pressed = true;
        }
    };
    
    Action z_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                z_pressed = true;
        }
    };
    
    Action c_release = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
                c_pressed = true;
        }
    };
    
    
}
