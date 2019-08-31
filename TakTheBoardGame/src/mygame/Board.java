/**
 * Board Contains all the logic associated with the physical playing board such as size and node points.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author jld166
 */
public class Board{
    private final int SIZE;
    private final float BOARD_WIDTH = 0.3f;
    private final AssetManager assetManager;
    public Geometry geom;
    
    public Board(int SIZE, AssetManager assetManager){
        this.SIZE = SIZE;
        this.assetManager = assetManager;
        simpleInitApp();
    }
    
    private void simpleInitApp(){
        Box b = new Box(SIZE, BOARD_WIDTH, SIZE);
        geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Wood.jpg"));
        geom.setMaterial(mat);
    }
    
    public int size(){
        return SIZE;
    }
    
    
    
}
