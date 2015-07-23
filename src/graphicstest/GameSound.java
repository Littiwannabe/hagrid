/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import javax.sound.sampled.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorma Kikkelsson
 */
public class GameSound implements Runnable{
    private AudioInputStream audio = null;
    private Clip jump_player = null;
    private Clip bgm_player = null;
    
    private int bgm_frame_position;
    
    public void loadSounds() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        
        AudioInputStream aio = AudioSystem.getAudioInputStream(new File("sfx_jump.wav"));
        jump_player = AudioSystem.getClip();
        jump_player.open(aio);
//        aio = AudioSystem.getAudioInputStream(new File("bgm_1.mp3"));
        aio = AudioSystem.getAudioInputStream(new File("bgm_2.wav"));
        bgm_player = AudioSystem.getClip();
        bgm_player.open(aio);
        
        
   /*     FileInputStream audio_file = null;
        try{
            audio_file = new FileInputStream("sfx_jump.wav");
        }catch(FileNotFoundException e){
            System.out.println("file not found");
        }
        AudioInputStream audio_stream = null;
        try {
            audio_stream = AudioSystem.getAudioInputStream(audio_file);
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("File not supported");
        }
        Clip player = null;
        try {
            player = AudioSystem.getClip();
            player.open(audio_stream);
        } catch (LineUnavailableException ex) {
            System.out.println("LineUnavailableException");
        } catch (IOException ex) {
            System.out.println("IOException 2");
        }
        System.out.println("taalla");
        jump_player = player;
           */
    }
    
    public void playJump(){
        jump_player.start();
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            System.out.println("interrupted");
        }
        jump_player.setFramePosition(0);
    }
    
    public void playBGM(){
        bgm_player.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pauseBGM(){
        bgm_player.stop();
    }
    
    
    @Override
    public void run() {
        playJump();
    }
    
}
