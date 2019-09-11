import java.util.ArrayList;

class test{
    public static void main(String[] args){
        Board board = new Board(6);
        
        board.testPiece(new Point(0,0));
        board.testPiece(new Point(0,1));
        board.testPiece(new Point(0,2));
        board.testPiece(new Point(1,2));
        board.testPiece(new Point(2,2));
        board.testPiece(new Point(2,1));
        board.testPiece(new Point(3,1));
        board.testPiece(new Point(4,1));
        board.testPiece(new Point(4,2));
        board.testPiece(new Point(4,3));
        board.testPiece(new Point(4,4));
        board.testPiece(new Point(4,5));

        System.out.println(board);
        board.determineWinner();
        System.out.println(board.getWinner());
    }
}