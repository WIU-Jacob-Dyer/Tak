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

    public boolean white()  {return white;}
    public boolean black()  {return !white;}
    public boolean wall()   {return wall;}
    public boolean capstone()   {return capstone;}
}