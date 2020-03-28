

class SettingsScene extends Scene {
  
  private Button resolutionButton, backToMenuButton;
  private ResolutionList resolutionsList;

  public SettingsScene(String name, boolean active, SceneManager parent){
    super(name, active, parent);
    resolutionsList = new ResolutionList();
    resolutionButton = new Button(new PVector(width/10, height/16 * 2), new PVector(0, 0, 200), 0.5, resolutionsList.current().toString());
    backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5, "Main Menu");
  }
  
  @Override
  void draw(){
    background(244, 236, 228);
    this.parent.cam.beginHUD();
    resolutionButton.draw();
    backToMenuButton.draw();
    this.parent.cam.endHUD();
    checkClicks();
  }
  
  void checkClicks(){
    if(backToMenuButton.performAction){
      super.swapTo("Menu");
    }
    if(resolutionButton.performAction){
      resolutionsList.next();
      surface.setSize(resolutionsList.currentWidth(), resolutionsList.currentHeight());
      resolutionButton = new Button(new PVector(width/10, height/16 * 2), new PVector(0, 0, 200), 0.5, resolutionsList.current().toString());
      backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5, "Menu");
    }
  }


}
