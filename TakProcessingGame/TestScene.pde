

class TestScene extends Scene {
  
    private Button b = new Button(new PVector(width/4*3,height/2), new PVector(200, 0, 0), 1, "To Circle");

    public TestScene(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }

    @Override
    public void draw(){
      background(200);
      b.draw();
      checkClicks();

    }

    void checkClicks(){
      if(b.isClicked){
        super.swapTo("CircleTest");
      }
    }
}
