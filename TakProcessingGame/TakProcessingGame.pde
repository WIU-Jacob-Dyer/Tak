import peasy.*;

SceneManager manager;

PeasyCam cam;

void setup() {
  frameRate(60);
  size(1280,720,P3D);
  
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(500);
  
  manager = new SceneManager(cam);
  manager.addScene(new MenuScene("Menu", true, manager));
  manager.addScene(new TestScene("Cube", false, manager));
}

void draw() {
  manager.draw();
}
