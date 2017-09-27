/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderPackage;

import models.Surface;
import entities.Camera;
import entities.Entity;
import entities.Light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.TexturedModel;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import shaders.StaticShader;
import shaders.SurfaceShader;

/**
 *
 * @author Mateusz
 */
public class MasterRenderer {
     
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
     
    private Matrix4f projectionMatrix;
     
    private StaticShader shader = new StaticShader();
    private EntityRenderer renderer;
     
    private SurfaceRenderer surfaceRenderer;
    private SurfaceShader surfaceShader = new SurfaceShader();
     
     
    private Map<TexturedModel,List<Entity>> entities = new HashMap<>();
    private List<Surface> surfaces = new ArrayList<>();
     
    public MasterRenderer(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        renderer = new EntityRenderer(shader,projectionMatrix);
        surfaceRenderer = new SurfaceRenderer(surfaceShader,projectionMatrix);
    }
     
    public void render(Light sun,Camera camera){
        prepare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        surfaceShader.start();
        surfaceShader.loadLight(sun);
        surfaceShader.loadViewMatrix(camera);
        surfaceRenderer.render(surfaces);
        surfaceShader.stop();
        surfaces.clear();
        entities.clear();
    }
     
    public void processTerrain(Surface surface){
        surfaces.add(surface);
    }
     
    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);        
        }
    }
     
    public void cleanUp(){
        shader.cleanUp();
        surfaceShader.cleanUp();
    }
     
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.49f, 89f, 0.98f, 1);
    }
     
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
     
 
}