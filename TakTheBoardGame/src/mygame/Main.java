package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.util.SkyFactory;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    static final int BOARD_SIZE = 5;
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static final String TITLE = "Tak";
    static final boolean VSYNC = false;
    static final int SAMPLES = 2; // anti-aliasing
    

    public static void main(String[] args) {
        Main app = new Main();
        setVideoSettings(app);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        // Create Board
        Board board = new Board(BOARD_SIZE, assetManager);
        rootNode.attachChild(board.geom);
        
        // Attach Camera to board center
        flyCam.setEnabled(false);
        ChaseCamera chaseCam = new ChaseCamera(cam, board.geom, inputManager);
        chaseCam.setHideCursorOnRotate(false);
        //chaseCam.setSmoothMotion(true);
        
        // Sun
        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal());
        rootNode.addLight(sun);
        
        // Skybox
        rootNode.attachChild(SkyFactory.createSky(getAssetManager(), "Textures/Skybox/sky.jpg", 
                SkyFactory.EnvMapType.SphereMap));
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    //HELPERS
    public static void setVideoSettings(Main app){
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", WIDTH);
        settings.put("Height", HEIGHT);
        settings.put("Title", TITLE);
        settings.put("VSync", VSYNC);
        settings.put("Samples", SAMPLES);
        app.setSettings(settings);
    }
}
