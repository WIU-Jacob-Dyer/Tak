import java.util.Map;
import peasy.*;

class SceneManager{
  private Map<String, Scene> sceneMap = new HashMap<String, Scene>();
  public PeasyCam cam;
  
  public SceneManager(PeasyCam cam){
    this.cam = cam;
  }
  
  public void addScene(Scene scene) {
    sceneMap.put(scene.name, scene);
  }

  public void draw(){
    for (Scene scene : sceneMap.values()){
      if(scene.active){
        scene.draw();
      }
    }
  }

  public void enable(String sceneName){
    sceneMap.get(sceneName).active = true;
  }
  
  public void disable(String sceneName){
    sceneMap.get(sceneName).active = false;
  }
}
