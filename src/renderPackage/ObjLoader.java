/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderPackage;

import org.lwjgl.opengl.*;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import java.util.*;

/**
 *
 * @author Mateusz
 */
public class ObjLoader {
    
    private List<Integer> vaos = new ArrayList <> ();
    private List<Integer> vbos = new ArrayList <> ();
    
    public RawModel loadToVAO(float [] positions) {
        int vaoID = createVAO();
        storeDataInAttributeList(0,positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length/3);
    }
    
    
    
    public void cleanUp() {
        for(int vbo:vbos) {
            GL15.glDeleteBuffers(vbo);
        }
        
        for(int vao:vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
    }
    
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, float [] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
    }
    
    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }
    
    private FloatBuffer storeDataInFloatBuffer(float [] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
