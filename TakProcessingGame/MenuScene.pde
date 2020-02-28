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
            
    float buttonWidth = windowWidth * 0.26;
    float buttonHeight = windowHeight * 0.093;
    float xPos = windowWidth / 2;
    
    float buttonTextYOffSet = buttonHeight/3.5;
    
    PFont titleFont = createFont("Georgia Bold", fontSize);
    PFont buttonFont = createFont("Arial", fontSize);
    

    @Override
    public void draw(){
        background(200);
        
                if (mousePressed){
          swapTo("test");
                }
        

      
        /*
        //testing lines for alignment
        strokeWeight(1);
        line(windowWidth/2, 0, windowWidth/2, windowHeight);
        line(0, windowHeight/2, windowWidth, windowHeight/2);
        */

        
        textAlign(CENTER);
        rectMode(CENTER);
        textFont(buttonFont);
        strokeWeight(2);
        textSize(fontSize/1.5);
        
        
        //buttons
        fill(#0341fc); //blue
        rect(xPos, topPadding + textPadding*2, buttonWidth, buttonHeight);       
        fill(0);
        text("Play", xPos, topPadding + textPadding*2 + buttonTextYOffSet);
        
        fill(#808080); //gray    
        rect(xPos, topPadding + textPadding*3, buttonWidth, buttonHeight);
        fill(0);
        text("Play vs AI", xPos, topPadding + textPadding*3 + buttonTextYOffSet);
        
        
        fill(#808080); //gray 
        rect(xPos, topPadding + textPadding*4, buttonWidth, buttonHeight);
        fill(0);
        text("Play Online", xPos, topPadding + textPadding*4 + buttonTextYOffSet);
        
        fill(#808080); //gray 
        rect(xPos, topPadding + textPadding*5, buttonWidth, buttonHeight);
        fill(0);
        text("Settings", xPos, topPadding + textPadding*5 + buttonTextYOffSet);
        
        fill(#0341fc); //blue
        rect(xPos, topPadding + textPadding*6, buttonWidth, buttonHeight);
        fill(0);
        text("Quit", xPos, topPadding + textPadding*6 + buttonTextYOffSet);
        

        //"Tak The Game" Text
        textFont(titleFont);
        textSize(fontSize);
        fill(0);
        text("Tak", xPos, topPadding);
        text("The Game", xPos, topPadding + textPadding);
        
    
        


        
    }
}
