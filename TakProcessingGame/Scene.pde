class Scene {
    public String name;
    public int windowWidth;
    public int windowHeight;
    public boolean active;
    public SceneManager parent;
    
    public Scene(String name, int windowWidth, int windowHeight, boolean active, SceneManager parent){
      this.windowHeight = windowHeight;
      this.windowWidth = windowWidth;
      this.name = name;
      this.active = active;
      this.parent = parent;
    }
    
    public void draw(){}
    
    public void setup(){}
    
    public void swapTo(String sceneName){
      this.active = false;
      parent.setActive(sceneName);
    }
}
