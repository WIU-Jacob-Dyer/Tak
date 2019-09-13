class Point{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point toCheck){
        return toCheck.x == x && toCheck.y == y;
    }

    public String toString(){
        return "{ " + x + ", " + y + "} ";
    }

    public Point up(){
        return new Point(x, y - 1);
    }

    public Point down(){
        return new Point(x, y + 1);
    }

    public Point left(){
        return new Point(x - 1, y);
    }

    public Point right(){
        return new Point(x + 1, y);
    }
}