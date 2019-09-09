class test{
    public static void main(String[] args){
        Board board = new Board(5);
        int[][] points = new int[11][2];

        points[0] = new int[] {0, 0};
        points[1] = new int[] {0, 1};
        points[2] = new int[] {0, 2};
        points[3] = new int[] {1, 2};
        points[4] = new int[] {2, 2};
        points[5] = new int[] {2, 1};
        points[6] = new int[] {3, 1};
        points[7] = new int[] {4, 1};
        points[8] = new int[] {4, 2};
        points[9] = new int[] {4, 3};
        points[10] = new int[] {4, 4};

        for (int[] point : points) {
            board.place(true, false, false, point);
        }
        System.out.println(board);

        if(board.determineWinner()){
            System.out.println(board.getWinner() + " WINS!!!");
        }
    }
}