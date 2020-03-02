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
