float scale = 1.0;

void setup() {
  size(640, 360, P3D);
}

void draw() {
  scale += 0.1;
  background(0);
  drawP();
}

void drawP(){
  translate(width/2, height/2, 0);
  stroke(255);
  rotateX(PI/2 * scale);
  rotateZ(-PI/6);
  //noFill();
  
  beginShape();
  vertex(-100, -100, -100);
  vertex( 100, -100, -100);
  vertex(   0,    0,  100);
  
  vertex( 100, -100, -100);
  vertex( 100,  100, -100);
  vertex(   0,    0,  100);
  
  vertex( 100, 100, -100);
  vertex(-100, 100, -100);
  vertex(   0,   0,  100);
  
  vertex(-100,  100, -100);
  vertex(-100, -100, -100);
  vertex(   0,    0,  100);
  endShape();
}
