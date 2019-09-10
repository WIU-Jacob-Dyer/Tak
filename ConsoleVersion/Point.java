class Point{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point toCheck){
        return toCheck.x == x && toCheck.y == y;
    }
}