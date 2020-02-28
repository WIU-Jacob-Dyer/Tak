class Scene {
    public String name;
    public boolean active;
    public SceneManager parent;
    
    public Scene(String name, boolean active, SceneManager parent){
      this.name = name;
      this.active = active;
      this.parent = parent;
    }
    
    public void draw(){}
    
    public void swapTo(String sceneName){
      parent.enable(sceneName);
      parent.disable(this.name);
    }
}
