/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import renderPackage.DisplayManager;
import org.lwjgl.opengl.*;

/**
 *
 * @author Mateusz
 */
public class SNAKE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DisplayManager.createDisplay();
        while(!Display.isCloseRequested()) {
           // DisplayManager.updateDisplay();
        }
        DisplayManager.closeDisplay();
    }
    
}
