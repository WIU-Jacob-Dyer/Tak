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
