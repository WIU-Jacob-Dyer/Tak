import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 
import java.util.Map; 
import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TakProcessingGame extends PApplet {



SceneManager manager;
PeasyCam cam;

final int w = 1280;
final int h = 720;
final int frames = 60;

public void settings(){
  size(w,h,P3D);
}

public void setup() {
  surface.setResizable(true);
  cam = new PeasyCam(this, 100);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(500);
  
  manager = new SceneManager(cam);
  manager.addScene(new MenuScene("Menu", true, manager));
  manager.addScene(new CubeScene("Cube", false, manager));
  manager.addScene(new SettingsScene("Settings", false, manager));
}

public void draw() {
  manager.draw();
}


class Button {
  private PVector pos;
  private PVector buttonColor;
  private final float alphaOnHover = 100;
  
  private float scale;
  private final float xScale = 0.26f;
  private final float yScale = 0.093f;
  
  public boolean isClicked = false;
  
  private String text;
  private PFont buttonFont;
  private final float fontSize = width * .0583f;
  
  private float buttonWidth, buttonHeight;
  private boolean hover = false;

  public Button(PVector pos, PVector buttonColor, float scale, String text){
    this.pos = pos;
    this.buttonColor = buttonColor;
    this.scale = scale;
    this.text = text;
    this.buttonFont = createFont("Arial", fontSize * scale);
    
    buttonWidth = width * scale * xScale;
    buttonHeight = height * scale * yScale;
  }
  
  public void draw(){
    
    textAlign(CENTER, CENTER);
    rectMode(CENTER);
    
    textFont(buttonFont);
    strokeWeight(2*scale);
    textSize(fontSize/1.5f*scale);
    
    checkHover();
    
    if(hover){
      fill(buttonColor.x, buttonColor.y, buttonColor.z);
    } else {
      fill(buttonColor.x, buttonColor.y, buttonColor.z, alphaOnHover);
    }
    rect(pos.x, pos.y, buttonWidth, buttonHeight);
    
    fill(0);
    text(text, pos.x, pos.y - scale * 8);
  }
  
  private void checkHover(){
    if(mouseX > pos.x - (buttonWidth/2) 
          && mouseX < pos.x + (buttonWidth/2) 
          && mouseY > pos.y - (buttonHeight/2) 
          && mouseY < pos.y + (buttonHeight/2)){
      if(mousePressed){
        this.isClicked = true;
      } else {
        this.isClicked = false;
      }
      this.hover = true;
    } else {
      this.isClicked = false;
      this.hover = false;
    }
  }
  
  public void setText(String text){
    this.text = text;
  }

}
class CircleTest extends Scene {
  
    private Button b = new Button(new PVector(width/4,height/2), new PVector(200, 0, 0), 1, "To Test");

    public CircleTest(String name, boolean active, SceneManager parent){
        super(name, active, parent);
    }

    @Override
    public void draw(){
      background(200);
      b.draw();
      checkClicks();

    }

    public void checkClicks(){
      if(b.isClicked){
        super.swapTo("TestScene");
      }
    }
}
class CubeScene extends Scene {
  
  private Button backToMenuButton;
  
  public CubeScene(String name, boolean active, SceneManager parent){
    super(name, active, parent);
    backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5f, "Menu");
  }
  
  public void draw() {
    cube();
    hud();
  }
  
  public void hud(){
    this.parent.cam.beginHUD();
    backToMenuButton.draw();
    checkButtons();
    this.parent.cam.endHUD();
  }
  
  public void checkButtons(){
    if(backToMenuButton.isClicked){
      super.swapTo("Menu");
    }
  }
  
  public void cube(){
    rotateX(-.5f);
    rotateY(-.5f);
    background(255);
    fill(255,0,0);
    box(30);
    pushMatrix();
    translate(0,0,20);
    fill(0,0,255);
    box(5);
    popMatrix();
  }
}

class Header{
  
  private String text;
  private PFont font;
  private PVector pos;
  private PVector textColor;
  
  public Header(String text, String font, PVector pos, PVector textColor, float scale){
    this.text = text;
    this.font = createFont(font, scale);
    this.pos = pos;
    this.textColor = textColor;
  
  }
  
  public void draw(){
    textAlign(CENTER, CENTER);
    textFont(font);
    fill(textColor.x, textColor.y, textColor.z);
    text(text, pos.x, pos.y);
  }


}
class MenuScene extends Scene {

    Header title; 
    Button playButton, settingsButton, exitButton;
  
  
    //contructor. This loads all of the global variables that were previously initialize
    public MenuScene(String name, boolean active, SceneManager parent){
        super(name, active, parent);
        
      playButton = new Button(new PVector(width/2, height/32 * 12), new PVector(0, 0, 255), 1, "Play");
      settingsButton = new Button(new PVector(width/2, height/32 * 16), new PVector(0, 100, 0), 1, "Settings");
      exitButton = new Button(new PVector(width/32 * 30, height/32 * 31), new PVector(200, 0, 0), 0.4f, "Exit");
      title = new Header("Tak The Game", "Arial", new PVector(width/2, height/8), new PVector(0,0,0), 70);
    }
    

    @Override
    public void draw(){       
        background(244, 236, 228);
        this.parent.cam.beginHUD();
        playButton.draw();
        settingsButton.draw();
        exitButton.draw();
        title.draw();
        this.parent.cam.endHUD();
        checkButtons();
    }
    
    public void checkButtons(){
      if(playButton.isClicked){
        super.swapTo("Cube");
      }
      if(settingsButton.isClicked){
        super.swapTo("Settings");
      }
      if(exitButton.isClicked){
        exit();
      }
    }
}
class Resolution{
  
  public int screenWidth, screenHeight;
  
  public Resolution(int screenWidth, int screenHeight){
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }
  
  @Override
  public String toString(){
    return screenWidth + "x" + screenHeight;
  }
}
class ResolutionList{

  private ArrayList<Resolution> resolutions = new ArrayList<Resolution>();
  private int current = 3;
  
  public ResolutionList(){
    resolutions.add(new Resolution(640, 480));
    resolutions.add(new Resolution(800, 600));
    resolutions.add(new Resolution(1280, 800));
    resolutions.add(new Resolution(1280, 720));
  }
  
  public Resolution next(){
    current++;
    if(current == resolutions.size()){
      current = 0;
    }
    
    return resolutions.get(current);
  }
  
  public Resolution current(){
    return resolutions.get(current);
  }
  
  public int currentWidth(){
    return current().screenWidth;
  }
  
  public int currentHeight(){
    return current().screenHeight;
  }
}
class Scene {
    public String name;
    public boolean active;
    public SceneManager parent;
    
    public Scene(String name, boolean active, SceneManager parent){
      this.name = name;
      this.active = active;
      this.parent = parent;
    }
    
    public void draw(){}
    
    public void swapTo(String sceneName){
      parent.enable(sceneName);
      parent.disable(this.name);
    }
}



class SceneManager{
  private Map<String, Scene> sceneMap = new HashMap<String, Scene>();
  public PeasyCam cam;
  
  public SceneManager(PeasyCam cam){
    this.cam = cam;
  }
  
  public void addScene(Scene scene) {
    sceneMap.put(scene.name, scene);
  }

  public void draw(){
    for (Scene scene : sceneMap.values()){
      if(scene.active){
        scene.draw();
      }
    }
  }

  public void enable(String sceneName){
    sceneMap.get(sceneName).active = true;
  }
  
  public void disable(String sceneName){
    sceneMap.get(sceneName).active = false;
  }
}


class SettingsScene extends Scene {
  
  private Button resolutionButton, backToMenuButton;
  private ResolutionList resolutionsList;

  public SettingsScene(String name, boolean active, SceneManager parent){
    super(name, active, parent);
    resolutionsList = new ResolutionList();
    resolutionButton = new Button(new PVector(width/10, height/16 * 2), new PVector(0, 0, 200), 0.5f, resolutionsList.current().toString());
    backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5f, "Main Menu");
  }
  
  public @Override
  void draw(){
    background(244, 236, 228);
    this.parent.cam.beginHUD();
    resolutionButton.draw();
    backToMenuButton.draw();
    this.parent.cam.endHUD();
    checkClicks();
  }
  
  public void checkClicks(){
    if(backToMenuButton.isClicked){
      super.swapTo("Menu");
    }
    if(resolutionButton.isClicked){
      resolutionsList.next();
      surface.setSize(resolutionsList.currentWidth(), resolutionsList.currentHeight());
      resolutionButton = new Button(new PVector(width/10, height/16 * 2), new PVector(0, 0, 200), 0.5f, resolutionsList.current().toString());
      backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5f, "Menu");
    }
  }


}
class TestScene extends Scene {
  
  private Button backToMenuButton;
  
  public TestScene(String name, boolean active, SceneManager parent){
    super(name, active, parent);
    backToMenuButton = new Button(new PVector(width/10, height/16), new PVector(0, 0, 200), 0.5f, "Menu");
  }
  
  public void draw() {
    cube();
    hud();
  }
  
  public void hud(){
    this.parent.cam.beginHUD();
    backToMenuButton.draw();
    checkButtons();
    this.parent.cam.endHUD();
  }
  
  public void checkButtons(){
    if(backToMenuButton.isClicked){
      super.swapTo("Menu");
    }
  }
  
  public void cube(){
    rotateX(-.5f);
    rotateY(-.5f);
    background(255);
    fill(255,0,0);
    box(30);
    pushMatrix();
    translate(0,0,20);
    fill(0,0,255);
    box(5);
    popMatrix();
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TakProcessingGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
