/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Jorma Kikkelsson
 */
public class SomeOtherScreen extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawLine(10, 100, 600, 400);
    }
}
