/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Mateusz
 */
public class Camera {
    private Vector3f position = new Vector3f(100,50,10);
    private float pitch;
    private float yaw;
    private float roll;
    
    public Camera(){
        
    }

    public void move() {
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            position.z -= 1.0f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_K)){
            position.z += 1.0f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_J)){
            position.x -= 1.0f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
           position.x += 1.0f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            position.y+=1.0f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            position.y-=1.0f;
        }
    }
    
    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
    
    
}
