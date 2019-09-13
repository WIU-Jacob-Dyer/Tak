import java.util.Random;
import java.util.ArrayList;

class BOGOAI{

    private static final double chanceInc = 0.04;

    private boolean isWhite;
    private static Random rand = new Random(System.currentTimeMillis()); 
    private  double chanceToMove = 0.0;
    private Board board;

    public BOGOAI(Board board, boolean isWhite){
        this.board = board;
        this.isWhite = isWhite;
    }

    public void makeMove(){
        if(rand.nextDouble() > chanceToMove) {
            placePiece();
        } else {
            movePiece();
        }

        if(chanceToMove < 60){
            chanceToMove += chanceInc;
        }
    }

    private void placePiece(){
        boolean piecePlaced = false;
        if(boardIsNOTFull() && ((isWhite && board.getWhitePool().x > 0) || (!isWhite && board.getBlackPool().x > 0))){
            board.place(isWhite, false, false, pickRandomPoint(emptySpots()));
        } else {
            movePiece();
        }
    }

    private void movePiece(){
        if(hasPieceOnBoard()){
            ArrayList<Point> ownedList = spotsIOwn();
            while(ownedList.size() > 0){
                Point toTry = pickRandomPoint(ownedList);
                ArrayList<Point> validMoves = validMovePoints(toTry);
                if(validMoves.size() > 0){
                    board.move(toTry, pickRandomPoint(validMoves), 1);
                    return;
                }
            }
        } else{
            placePiece();
            return;
        }

        placePiece();
    }

    private ArrayList<Point> validMovePoints(Point from){
        ArrayList<Point> validMoves = new ArrayList<>();
        if(onBoardandValid(from.up(), from)){
            validMoves.add(from.up());
        }

        if(onBoardandValid(from.down(), from)){
            validMoves.add(from.down());
        }

        if(onBoardandValid(from.left(), from)){
            validMoves.add(from.left());
        }

        if(onBoardandValid(from.right(), from)){
            validMoves.add(from.right());
        }

        return validMoves;
    }

    private boolean onBoardandValid(Point to, Point from){
        if(to.x > 0 && to.y > 0 && to.x < board.size() && to.y < board.size()){
            if(board.getStack(from).size() == 0){
                return false;
            }
            
            return true;
        }
        return false;
    }

    private Point pickRandomPoint(ArrayList<Point> list){
        int size = list.size();
        int index = (int)Math.floor(rand.nextDouble() * size);

        Point toReturn = list.get(index);
        list.remove(index);
        return toReturn;
    }

    private boolean hasPieceOnBoard(){
        for(int x = 0; x < board.size(); x++){
            for(int y = 0; y < board.size(); y++){
                if(!board.getStack(new Point(x, y)).isEmpty()){
                    if(board.getStack(new Point(x, y)).top().isWhite() == this.isWhite){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean boardIsNOTFull(){
        for(int x = 0; x < board.size(); x++){
            for(int y = 0; y < board.size(); y++){
                if(board.getStack(new Point(x, y)).isEmpty()){
                    return true;
                }
            }
        }

        return false;
    }

    private ArrayList<Point> emptySpots(){
        ArrayList<Point> emptys = new ArrayList<>();

        for(int x = 0; x < board.size(); x++){
            for(int y = 0; y < board.size(); y++){
                if(board.getStack(new Point(x, y)).isEmpty()){
                    emptys.add(new Point(x, y));
                }
            }
        }

        return emptys;
    }

    private ArrayList<Point> spotsIOwn(){
        ArrayList<Point> thingsIOwn = new ArrayList<>();

        for(int x = 0; x < board.size(); x++){
            for(int y = 0; y < board.size(); y++){
                TakStack stack = board.getStack(new Point(x, y));
                if(!stack.isEmpty()){
                    if(stack.top().isWhite() == this.isWhite){
                        thingsIOwn.add(new Point(x,y));
                    }
                }
            }
        }

        return thingsIOwn;
    }
}