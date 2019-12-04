class Point{

    /**
     * Represent the coordinates of a position on the board.
     */
    public int x, y;

    /**
     * Creates a new Point with the gievn starting position.
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if two points are at the same position.
     */
    public boolean equals(Point toCheck){
        return toCheck.x == x && toCheck.y == y;
    }

    /**
     * Returns a string representation of the Point -> '{ X, Y}'.
     */
    public String toString(){
        return "{ " + x + ", " + y + "} ";
    }

    /**
     * Returns a new Point above this Point.
     */
    public Point up(){
        return new Point(x, y - 1);
    }

    /**
     * Returns a new Point below this Point.
     */
    public Point down(){
        return new Point(x, y + 1);
    }

    /**
     * Returns a new Point to the left of this Point.
     */
    public Point left(){
        return new Point(x - 1, y);
    }

    /**
     * Returns a new Point to the right of this Point.
     */
    public Point right(){
        return new Point(x + 1, y);
    }
}