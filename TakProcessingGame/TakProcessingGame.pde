////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  frameRate(30);
  size(1280,720,P3D);
  manager = new SceneManager();
  manager.addScene(new CircleTest(width, height, "CircleTest", false, manager));
  manager.addScene(new TestScene(width, height, "TestScene", true, manager));  
}

void draw() {
  manager.draw();
}
