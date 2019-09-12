import java.util.Random;

class BOGOAI{

    private static final double chanceInc = 0.05;

    private boolean iswhite;
    private static Random rand = new Random(System.currentTimeMillis()); 
    private  double chanceToMove = 0.0;
    private Board board;

    public BOGOAI(Board board, boolean isWhite){
        this.board = board;
        this.iswhite = isWhite;
    }

    public void makeMove(){
        if(rand.nextDouble() > chanceToMove) {
            placePiece();
        } else {
            movePiece();
        }

        chanceToMove += chanceInc;
    }

    private void placePiece(){
        boolean piecePlaced = false;
        if(boardIsNOTFull()){

        } else {
            movePiece();
        }
        
    }

    private void movePiece(){

    }

    private boolean boardIsNOTFull(){
        
    }

    private ArrayList<Point> emptySpots(){

    }

    private ArrayList<Point> spotsIOwn(){

    }
}