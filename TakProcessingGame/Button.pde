

class Button {
  private PVector pos;
  private PVector buttonColor;
  private final float alphaOnHover = 100;
  
  private float scale;
  private final float xScale = 0.26;
  private final float yScale = 0.093;
  
  private boolean isClicked = false;
  private boolean isHovered = false;
  public boolean performAction = false;
  
  private String text;
  private PFont buttonFont;
  private final float fontSize = width * .0583;
  
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
    textSize(fontSize/1.5*scale);
    
    checkHover();
    
    if(isHovered){
      fill(buttonColor.x, buttonColor.y, buttonColor.z);
    } else {
      fill(buttonColor.x, buttonColor.y, buttonColor.z, alphaOnHover);
    }
    rect(pos.x, pos.y, buttonWidth, buttonHeight);
    
    fill(0);
    text(text, pos.x, pos.y - scale * 8);
  }
  
  private void checkHover(){
    
    this.isHovered = mouseX > pos.x - (buttonWidth/2) 
          && mouseX < pos.x + (buttonWidth/2) 
          && mouseY > pos.y - (buttonHeight/2) 
          && mouseY < pos.y + (buttonHeight/2);
          
    if(this.isClicked && !mousePressed){
      this.performAction = true;
    }
          
    
    if(this.isHovered && mousePressed){
      this.isClicked = true;
    } else {
      this.isClicked = false;
    }
  }
  
  public void setText(String text){
    this.text = text;
  }

}
