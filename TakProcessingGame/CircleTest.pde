class CircleTest extends Scene {

    private Button b = new Button(new PVector(width/2,height/2), new PVector(200, 0, 0), 1, "To Menu");
  
    public CircleTest(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }

    @Override
    public void draw(){
<<<<<<< Updated upstream
        circle(windowWidth / 2, windowHeight / 2, 50);
    }
}
=======
      background(200);
      b.draw();
      checkClicks();

    }

    void checkClicks(){
      if(b.isClicked){
        this.active = false;
        parent.setActive("test2");
      }
    }
  }
>>>>>>> Stashed changes
