

class TestScene extends Scene {

    public TestScene(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }

    @Override
    public void draw(){
        translate(windowHeight / 2, windowWidth / 2, 0);
        rectMode(CENTER);
        rect(0,0,100,100);
    }
}
