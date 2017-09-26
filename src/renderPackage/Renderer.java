/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderPackage;
import org.lwjgl.opengl.*;

/**
 *
 * @author Mateusz
 */
public class Renderer {
    public void prepare() {
        GL11.glClearColor(1,0,0,1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
    
    public void render(RawModel model) {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
