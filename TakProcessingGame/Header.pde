
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
  
  void draw(){
    textAlign(CENTER, CENTER);
    textFont(font);
    fill(textColor.x, textColor.y, textColor.z);
    text(text, pos.x, pos.y);
  }


}
