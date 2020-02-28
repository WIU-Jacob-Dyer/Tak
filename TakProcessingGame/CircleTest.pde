class CircleTest extends Scene {

    public CircleTest(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }

    @Override
    public void draw(){
      
      background(200);
      
      textAlign(CENTER);
        text("Butts", windowWidth / 2, windowHeight / 2, 64);
        

    }

  }
