class MenuScene extends Scene {

    Header title; 
      
    Button playButton;
  
  
    //contructor. This loads all of the global variables that were previously initialize
    public MenuScene(String name, boolean active, SceneManager parent){
        super(name, active, parent);
        
      playButton = new Button(new PVector(width/2, height/8 * 3), new PVector(200, 0, 0), 1, "Play Game");
      title = new Header("Tak The Game", "Arial", new PVector(width/2, height/8), new PVector(0,0,0), 50);
    }
    

    @Override
    public void draw(){       
        background(200);
        playButton.draw();
        title.draw();
    }
}
