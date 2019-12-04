class TakPiece{

    private boolean white;
    private boolean capstone;
    private boolean wall;
    private boolean road;

    /**
     * Creates new TakPiece.
     * @param white Is this piece white?
     * @param wall Is this a wall?
     * @param capstone Is this a capstone?
     */
    public TakPiece(boolean white, boolean wall, boolean capstone){
        this.white = white;

        if(capstone){
            this.capstone = true;
            this.wall = true;
            this.road = true;
        } else {
            this.capstone = false;
            this.wall = wall;
            this.road = !wall;
        }
    }

    /**
     * Demolish wall into road.
     */
    public void crush(){
        this.wall = false;
    }

    /**
     * @return isWhite
     */
    public boolean isWhite()  {return white;}
    /**
     * @return isBlack
     */
    public boolean isBlack()  {return !white;}
    /**
     * @return isWall
     */
    public boolean isWall()   {return wall;}
    /**
     * @return isRoad
     */
    public boolean isRoad()   {return road;}
    /**
     * @return isCapstone
     */
    public boolean isCapstone()   {return capstone;}
}