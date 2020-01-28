float x,y,z;

void setup() {
  size(200,200);
  x = width/2;
  y = height/2;
  z = 0;
  background(100);
}

void draw() {
  translate(x,y,z);
  rectMode(CENTER);
  rect(0,0,100,100);

  z++; // The rectangle moves forward as z increments.
}
