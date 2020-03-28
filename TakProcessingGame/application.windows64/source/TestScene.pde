class TestScene extends Scene {
  
  private Button backToMenuButton;
  
  public TestScene(String name, boolean active, SceneManager parent){
    super(name, active, parent);
    backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5, "Menu");
  }
  
  void draw() {
    cube();
    hud();
  }
  
  void hud(){
    this.parent.cam.beginHUD();
    backToMenuButton.draw();
    checkButtons();
    this.parent.cam.endHUD();
  }
  
  void checkButtons(){
    if(backToMenuButton.isClicked){
      super.swapTo("Menu");
    }
  }
  
  void cube(){
    rotateX(-.5);
    rotateY(-.5);
    background(255);
    fill(255,0,0);
    box(30);
    pushMatrix();
    translate(0,0,20);
    fill(0,0,255);
    box(5);
    popMatrix();
  }
}
