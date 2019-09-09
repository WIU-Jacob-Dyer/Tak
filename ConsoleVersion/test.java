class test{
    public static void main(String[] args){
        Board board = new Board(3);

        int[] y0 = {0, 0};
        int[] y1 = {0, 1};
        int[] y2 = {0, 2};
        board.place(true, false, false, y0);
        board.place(true, false, false, y1);
        board.place(true, false, false, y2);
        System.out.println(board);

        System.out.println(board.determineWinner());
    }
}