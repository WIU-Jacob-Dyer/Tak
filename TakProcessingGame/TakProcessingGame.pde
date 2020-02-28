////////////////////
// Init all scenes
SceneManager manager;

void setup() {
  size(400,400,P3D);
  manager = new SceneManager();
<<<<<<< Updated upstream
  manager.addScene(new CircleTest(width, height, "test", true, manager));
=======
  manager.addScene(new CircleTest(width, height, "test", false, manager));
  manager.addScene(new MenuScene(width, height, "menu", true, manager));
  manager.addScene(new TestScene(width, height, "test2", false, manager));
>>>>>>> Stashed changes
  
}

void draw() {
  manager.draw();
}
