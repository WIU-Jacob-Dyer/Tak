class CircleTest extends Scene {

    public CircleTest(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }

    @Override
    public void draw(){
        circle(windowWidth / 2, windowHeight / 2, 50);
        
        if (mousePressed){
          swapTo("menu");
    }

  }
}
