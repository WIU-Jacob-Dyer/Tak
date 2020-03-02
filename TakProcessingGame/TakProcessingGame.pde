import peasy.*;

SceneManager manager;
PeasyCam cam;

final int w = 1280;
final int h = 720;
final int frames = 60;

void settings(){
  size(w,h,P3D);
}

void setup() {
  surface.setResizable(true);
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(500);
  
  manager = new SceneManager(cam);
  manager.addScene(new MenuScene("Menu", true, manager));
  manager.addScene(new CubeScene("Cube", false, manager));
  manager.addScene(new SettingsScene("Settings", false, manager));
}

void draw() {
  manager.draw();
}
