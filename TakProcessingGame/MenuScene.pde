class MenuScene extends Scene {

    Header title; 
    Button playButton, settingsButton, exitButton;
  
  
    //contructor. This loads all of the global variables that were previously initialize
    public MenuScene(String name, boolean active, SceneManager parent){
        super(name, active, parent);
        
      playButton = new Button(new PVector(width/2, height/32 * 12), new PVector(0, 0, 255), 1, "Play");
      settingsButton = new Button(new PVector(width/2, height/32 * 16), new PVector(0, 100, 0), 1, "Settings");
      exitButton = new Button(new PVector(width/2, height/32 * 20), new PVector(200, 0, 0), 1, "Exit");
      title = new Header("Tak The Game", "Arial", new PVector(width/2, height/8), new PVector(0,0,0), 70);
    }
    

    @Override
    public void draw(){       
        background(200);
        this.parent.cam.beginHUD();
        playButton.draw();
        settingsButton.draw();
        exitButton.draw();
        title.draw();
        this.parent.cam.endHUD();
        checkButtons();
    }
    
    void checkButtons(){
      if(playButton.isClicked){
        super.swapTo("Cube");
      }
      if(exitButton.isClicked){
        exit();
      }
    }
}
