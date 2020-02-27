////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  size(400,400,P3D);
  manager = new SceneManager();
  manager.addScene(new CircleTest(width, height, "test", true, manager));
  
}

void draw() {
  manager.draw();
}
