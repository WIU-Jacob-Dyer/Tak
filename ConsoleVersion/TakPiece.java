class TakPiece{

    private boolean white;
    private boolean capstone;
    private boolean wall;
    private boolean road;

    public TakPiece(boolean white, boolean wall, boolean capstone){
        this.white = white;

        if(capstone){
            this.capstone = true;
            this.wall = true;
            this.road = true;
        } else {
            this.capstone = true;
            this.wall = wall;
            this.road = !wall;
        }
    }

    public void crush(){
        this.wall = false;
    }

    public boolean isWhite()  {return white;}
    public boolean isBlack()  {return !white;}
    public boolean isWall()   {return wall;}
    public boolean isRoad()   {return road;}
    public boolean isCapstone()   {return capstone;}
}