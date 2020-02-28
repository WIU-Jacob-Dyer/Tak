class MenuScene extends Scene {

    public MenuScene(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
    }
    
    
    int numButtons = 5;
    
    //dynamic values that change depending on the window size (with a 16:9 ratio in mind)
    float fontSize = windowWidth * .0583;
    float textOffSet1 = fontSize * 0.916;    
    float textOffSet2 = fontSize * 2.333;
    float textPadding = fontSize * 1.167;
    float topPadding = windowHeight/8;
            
    float buttonWidth = windowWidth * 0.156;
    float buttonHeight = windowHeight * 0.083;
    float buttonOffSet = windowWidth * 0.0797;
    float xPosText = windowWidth / 2;
      
    float[] yPos = new float[numButtons];

    @Override
    void setup(){
      System.out.println("setup");
       for (int i = 0; i < numButtons; i++){
         yPos[i] = topPadding + (textPadding*2) + textPadding*i;
         System.out.println(yPos[i]);
       }
    }
        

    @Override
    public void draw(){
        background(200);
      
        //testing lines for alignment
        strokeWeight(1);
        line(windowWidth/2, 0, windowWidth/2, windowHeight);
        line(0, windowHeight/2, windowWidth, windowHeight/2);
        //
        
        //buttons
        fill(#0341fc);
        
        for (int i = 0; i < numButtons; i++){
          rect(xPosText - buttonOffSet, yPos[i], buttonWidth, buttonHeight);
        }


        
        textSize(fontSize);
        fill(0);
        text("Tak", xPosText - textOffSet1, topPadding);
        text("The Game", xPosText - textOffSet2, topPadding + textPadding);
        
    
        


        
    }
}
