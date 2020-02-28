class MenuScene extends Scene {
  
    int numButtons = 5;
    
    //dynamic values that change depending on the window size (with a 16:9 ratio in mind)
    float fontSize;
    float textOffSet1;    
    float textOffSet2;
    float textPadding;
    float topPadding;
            
    float buttonWidth;
    float buttonHeight;
    float xPos;
    
    float buttonTextYOffSet;
    float[] buttonYPos;

    //PFont objects to hold 
    PFont titleFont;
    PFont buttonFont;
    
    //booleans for checking if the mouse is over a button
    boolean overPlay = false;
    boolean overPlayAI = false;
    boolean overPlayOnline = false;
    boolean overSettings = false;
    boolean overQuit = false;
  
  
    //contructor. This loads all of the global variables that were previously initialize
    public MenuScene(int windowWidth, int windowHeight, String name, boolean active, SceneManager parent){
        super(name, windowWidth, windowHeight, active, parent);
        
        numButtons = 5;
    
       //dynamic values that change depending on the window size (with a 16:9 ratio in mind)
       fontSize = windowWidth * .0583;
       textOffSet1 = fontSize * 0.916;    
       textOffSet2 = fontSize * 2.333;
       textPadding = fontSize * 1.167;
       topPadding = windowHeight/8;
            
       buttonWidth = windowWidth * 0.26;
       buttonHeight = windowHeight * 0.093;
       xPos = windowWidth / 2;
    
       buttonTextYOffSet = buttonHeight/3.5;
       
       //initializes the buttonYPos array size to the number of buttons specified
       buttonYPos = new float[numButtons];
       
       //loads the YPos of the buttons in an array
       for (int i = 0; i < numButtons; i++){
          buttonYPos[i] = topPadding + textPadding*(2+i);
       }
    
       titleFont = createFont("Georgia Bold", fontSize);
       buttonFont = createFont("Arial", fontSize);
       
        textAlign(CENTER);
        rectMode(CENTER);
        background(200);
    }
    
    private void drawButtons(){
        
        textFont(buttonFont);
        strokeWeight(2);
        textSize(fontSize/1.5);
      
        //buttons
        fill(#0341fc); //blue
        rect(xPos, buttonYPos[0], buttonWidth, buttonHeight);       
        fill(0);
        text("Play", xPos, buttonYPos[0] + buttonTextYOffSet);
        
        
        fill(#808080); //gray    
        rect(xPos, buttonYPos[1], buttonWidth, buttonHeight);
        fill(0);
        text("Play vs AI", xPos, buttonYPos[1] + buttonTextYOffSet);
        
        fill(#808080); //gray 
        rect(xPos, buttonYPos[2], buttonWidth, buttonHeight);
        fill(0);
        text("Play Online", xPos, buttonYPos[2] + buttonTextYOffSet);
        
        fill(#808080); //gray 
        rect(xPos, buttonYPos[3], buttonWidth, buttonHeight);
        fill(0);
        text("Settings", xPos, buttonYPos[3] + buttonTextYOffSet);
        
        fill(#0341fc); //blue
        rect(xPos, buttonYPos[4], buttonWidth, buttonHeight);
        fill(0);
        text("Quit", xPos, buttonYPos[4] + buttonTextYOffSet);
    }
    
    private void drawTitle(){
        //"Tak The Game" Text
        textFont(titleFont);
        textSize(fontSize);
        fill(0);
        text("Tak", xPos, topPadding);
        text("The Game", xPos, topPadding + textPadding);
    }
    
    private void testMouse(){
        
        overQuit = false;
        overPlayAI = false;
        overPlayOnline = false;
        overSettings = false;
        overPlay = false;        
      
      //checks each button via a for loop and sets the flag accordingly
      for (int i = 0; i < numButtons; i++){
        if(
          mouseX > xPos - (buttonWidth/2) 
          && mouseX < xPos + (buttonWidth/2) 
          && mouseY > buttonYPos[i] - (buttonHeight/2) 
          && mouseY < buttonYPos[i] + (buttonHeight/2)
          ){
            
            switch(i) {
             case 0:
               overPlay = true;           
               break;
             case 1:
               overPlayAI = true;
               break;
             case 2:
               overPlayOnline = true;
               break;
             case 3:
               overSettings = true;
               break;
             case 4:
               overQuit = true;
               break;
            }
          }
     }
   }
   
   private void mousePress(){
        if (mousePressed && overPlay){
          swapTo("test");
        }
        if (mousePressed && overQuit){
          exit(); 
        }
   }
    

    @Override
    public void draw(){       
        background(200);
        drawButtons();
        drawTitle(); 
        testMouse();
        mousePress();
    }
}
