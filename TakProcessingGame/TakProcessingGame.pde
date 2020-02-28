////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  frameRate(30);
  size(1280,720,P3D);
  manager = new SceneManager();
  manager.addScene(new MenuScene("Menu", true, manager));
}

void draw() {
  manager.draw();
}
