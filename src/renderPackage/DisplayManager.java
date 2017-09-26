/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderPackage;

import org.lwjgl.opengl.*;
import org.lwjgl.LWJGLException;


/**
 *
 * @author Mateusz
 */
public class DisplayManager {
    
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;
    
    public static void createDisplay(){
        
        ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
        
        try{
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("Mateusz Urbaniak - SNAKE 3D");
        } catch (LWJGLException e){
            e.printStackTrace();
        }
        
        GL11.glViewport(0,0,WIDTH,HEIGHT);
    }
    
    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
    }
    
    public static void closeDisplay(){
        Display.destroy();
    }
}
