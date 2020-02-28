////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  size(720,480,P3D);
  manager = new SceneManager();
  manager.addScene(new CircleTest(width, height, "test", false, manager));
  manager.addScene(new MenuScene(width, height, "menu", true, manager));
  
}

void draw() {
  
  manager.draw();
}
