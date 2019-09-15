import java.util.ArrayList;

class test{
    public static void main(String[] args){

        ArrayList<String> s = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            final int SIZE = 8;
            Board board = new Board(SIZE);
            BOGOAI player1 = new BOGOAI(board, true);
            BOGOAI player2 = new BOGOAI(board, false);

            while(!board.determineWinner()){
                player1.makeMove();
                player2.makeMove();
            }

            System.out.println(board);
            System.out.println(board.getWinner());
        }

        // for (String string : s) {
            
        // }
    }
}