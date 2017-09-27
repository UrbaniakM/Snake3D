/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderPackage;

import java.util.List;
import models.RawModel;
import models.Surface;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import shaders.SurfaceShader;
import textures.ModelTexture;
import toolbox.Maths;

/**
 *
 * @author Mateusz
 */
public class SurfaceRenderer {
 
    private SurfaceShader shader;
 
    public SurfaceRenderer(SurfaceShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
 
    public void render(List<Surface> surfaces) {
        for (Surface surface : surfaces) {
            prepareSurface(surface);
            loadModelMatrix(surface);
            GL11.glDrawElements(GL11.GL_TRIANGLES, surface.getModel().getVertexCount(),
                    GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }
 
    private void prepareSurface(Surface surface) {
        RawModel rawModel = surface.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = surface.getTexture();
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
    }
 
    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
 
    private void loadModelMatrix(Surface surface) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(
                new Vector3f(surface.getX(), 0, surface.getZ()), 0, 0, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);
    }   
}
