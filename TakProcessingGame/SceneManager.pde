import java.util.Map;

class SceneManager{
  private Map<String, Scene> sceneMap = new HashMap<String, Scene>();
  
  public SceneManager(){
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

  public void setSceneActive(String sceneName){
    sceneMap.get(sceneName).active = true;
  }
  
  public void setActive(String name){
    sceneMap.get(name).active = true;
  }
}
