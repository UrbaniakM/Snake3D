/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Snake;
import java.util.ArrayList;
import java.util.List;
import renderPackage.DisplayManager;
import org.lwjgl.opengl.*;
import renderPackage.Loader;
import models.RawModel;
import models.Surface;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderPackage.OBJLoader;
import renderPackage.EntityRenderer;
import renderPackage.MasterRenderer;
import shaders.StaticShader;
import textures.ModelTexture;

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
        Loader loader = new Loader();
         
         
        RawModel model = OBJLoader.loadObjModel("snake", loader);
         
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("snake")));
         
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity(staticModel, new Vector3f(300,20,3),0,0,0,25));
        entities.add(new Entity(staticModel, new Vector3f(650,20,56),0,0,0,5));
         
        Snake snake = new Snake(staticModel, new Vector3f(900,20,250),0,0,0,5);
        
        Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
         
        Surface surface = new Surface(0,0,loader,new ModelTexture(loader.loadTexture("brick")));
         
        Camera camera = new Camera();   
        MasterRenderer renderer = new MasterRenderer();
        
       while(!Display.isCloseRequested()){
            camera.move();
            snake.move();
            renderer.processEntity(snake);
            renderer.processTerrain(surface);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }
        
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
    
}
