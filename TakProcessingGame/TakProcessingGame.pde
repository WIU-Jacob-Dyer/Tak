////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  size(720,480,P3D);
  manager = new SceneManager();
  manager.addScene(new CircleTest(width, height, "test", true, manager));
  manager.addScene(new MenuScene(width, height, "menu", false, manager));
  
}

void draw() {
  
  manager.draw();
}
