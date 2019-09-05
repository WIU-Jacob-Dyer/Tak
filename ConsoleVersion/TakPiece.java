package ConsoleVersion;

class TakPiece{

    private boolean white;
    private boolean capstone;
    private boolean wall;

    public TakPiece(boolean white, boolean wall, boolean capstone){
        this.white = white;
        this.capstone = capstone;
        this.wall = wall;
    }

    public boolean isWhite()  {return white;}
    public boolean isBlack()  {return !white;}
    public boolean isWall()   {return wall;}
    public boolean isCapstone()   {return capstone;}
}