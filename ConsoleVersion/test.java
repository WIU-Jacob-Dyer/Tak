class test{
    public static void main(String[] args){

        final int SIZE = 6;

        for(int i = 0; i < 10000; i++){
            Board board = new Board(SIZE);
        
            for(int j = 0; j < 20; j++){
                int x = (int)Math.floor(Math.random() * SIZE);
                int y = (int)Math.floor(Math.random() * SIZE);

                board.testPiece(new Point(x, y));
            }

            System.out.println(board);
            board.determineWinner();
            System.out.println(board.getWinner());
        }
    }
}