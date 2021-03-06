
import java.util.ArrayList;

class Board{
    // Our stacks will be laid out with x,y = 0,0 at the top left corner EX:
    //        0   1   2   3   4  x
    //  0     O   O   O   O   O
    //  1     O   O   O   O   O
    //  2     O   O   O   O   O
    //  3     O   O   O   O   O
    //  y

    private TakStack stacks[][];

    private boolean whiteWins = false;
    private boolean blackWins = false;

    private boolean whiteTurn = true;
    //true = player1 (white) turn
    //false = player2 (black turn

    private int firstTwoTurnCounter = 0;
    private int whitePool;
    private int whiteCapPool;
    private int blackPool;
    private int blackCapPool;
    private int turn = 0;

    private final int SIZE;

    /**
     * Builds a new Board object which will store all of our board information.
     * @param size This determines the dimensions of the board.
     */

    public Board(int size){
        if(size > 8 || size < 3){
            SIZE = 5;
        } else {
            SIZE = size;
        }

        switch(SIZE) {
            case 3:
                whitePool = blackPool = 10;
                whiteCapPool = blackCapPool = 0;
                break;
            case 4:
                whitePool = blackPool = 15;
                whiteCapPool = blackCapPool = 0;
                break;
            case 5:
                whitePool = blackPool = 21;
                whiteCapPool = blackCapPool = 1;
                break;
            case 6:
                whitePool = blackPool = 30;
                whiteCapPool = blackCapPool = 1;
                break;
            case 7:
                whitePool = blackPool = 40;
                whiteCapPool = blackCapPool = 2;
                break;
            case 8:
                whitePool = blackPool = 50;
                whiteCapPool = blackCapPool = 2;
                break;
        }

        initStacks();
    }

    private void initStacks(){
        stacks = new TakStack[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++){
            for(int y = 0; y < SIZE; y++){
                stacks[x][y] = new TakStack();
            }
        }
    }

    public void switchPlayer(){whiteTurn = !whiteTurn;}

    /**
     * Creates a new TakPiece and attempts to place it on the board.
     * @param isWhite Is this piece white?
     * @param isWall Is this a wall being placed?
     * @param isCapstone It this a Capstone?
     * @param point Point containing the position at which a piece is to be placed
     * @return Will return false if this move is not valid
     */

    public boolean place(boolean isWhite, boolean isWall, boolean isCapstone, Point point){
        // START CONDITIONS
        //----------------
        // Is the space empty that we are trying to place
        if(getStack(point).size() > 0) return false;
        // Is the player trying to place the opponet's piece post turn 2
        // if(isWhite != whiteTurn) return false;
        // // Is the player trying to move their piece during the first 2 turns
        // if(firstTwoTurnCounter < 2){
        //     if(isWhite != !whiteTurn) return false;
        // }

        // Checks to see if the player has the piece to place
        if(whiteTurn && isCapstone && isWhite && (whiteCapPool == 0)){return false;}
        if(whiteTurn && isWhite && (whitePool == 0)){return false;}
        if(!whiteTurn && isCapstone && !isWhite && (blackCapPool == 0)){return false;}
        if(!whiteTurn && !isWhite && (blackCapPool == 0)){return false;}

        //----------------
        // END CONDITIONS

        // Valid - move start operation
        // Add new piece to the stack
        getStack(point).add(new TakPiece(isWhite, isWall, isCapstone));
        // Switch the player's turn
        switchPlayer();
        // Increment the firstTwoTurnCounter if it's the first 2 turns
        if(firstTwoTurnCounter < 2) firstTwoTurnCounter++;
        // Remove the piece placed from the pool
        if(isWhite && isCapstone){whiteCapPool--;}
        else if(isWhite){whitePool--;}

        if(!isWhite & isCapstone){blackCapPool--;}
        else if(!isWhite){blackPool--;}

        turn++;
        return true;
    }

    // FOR TESTING ONYL ******************
    public void testPiece(Point p){
        getStack(p).add(new TakPiece(true, false, false));
        turn++;
    }

    /**
     * A helper function to move stacks around
     * @param stack TakStack to place at position p
     * @param p Point containing the position at which a piece is to be placed
     * @return Will return false if this move is not valid
     */

    public boolean placeStack(TakStack stack, Point p){
        // START CONDITIONS
        //----------------
        // Is the space empty that we are trying to place
        if(stacks[p.x][p.y].size() > 0) return false;
        //----------------
        // END CONDITIONS

        // Valid - move start operation
        // Add new piece to the stack
        stacks[p.x][p.y].add(stack);

        // RETURN FALSE WHILE NOT IMPLEMENTED
        return true;
    }

    /**
     * Moves up to 5 pieces as a stack from startP to desP
     * @param startingP The position we are moving from
     * @param desP The position we should move the piece to
     * @param depth The number of pieces we are grabbing to move
     * @return Will return false if this move is not valid
     */
    public boolean move(Point startingP, Point desP, int depth){
        // START CONDITIONS
        //-----------------
        TakStack startStack = stacks[startingP.x][startingP.y];
        TakStack desStack = stacks[desP.x][desP.y];

        // are we grabbing a valid number given our board size
        if(depth > SIZE) return false;

        // is this move directly up,down,left, or right
        double xchange = Math.pow(startingP.x - desP.x, 2);
        double ychange = Math.pow(startingP.y - desP.y, 2);
        if(ychange > 1 ||  xchange > 1) return false;
        if(ychange >= 1 &&  xchange >= 1) return false;

        // are there enough pieces to grab
        if(depth > startStack.size()) return false;

        // Is the target des empty
        if(!desStack.isEmpty()){
            // Can this piece move ontop of destination piece
            TakPiece startTop = startStack.top();
            TakPiece desTop = desStack.top();

            if(desTop.isCapstone()){return false;} // Nothing we can do about this

            if(startTop.isCapstone()){
                if(desTop.isWall()){
                    desTop.crush();
                }
            } else {
                if(desTop.isWall()){
                    return false;
                }
            }
        }

        // Is the player grabbing a stack in their control
        if(startStack.top().isWhite() != whiteTurn) return false;
        //---------------
        // END CONDITIONS

        // Valid move - start move operation
        // Add onto our position we would like to move to the difference of what we grab from the postion we move from**
        desStack.add(startStack.sub(depth));
        // switch turns only if the player places a stack of depth = 1 (no possible additional moves)
        if (depth == 1) switchPlayer();

        turn++;
        return true;
    }

    /**
     * return a point with x as the number of roads and y is the number of capstones
     */
    public Point getWhitePool(){
        return new Point(whitePool, whiteCapPool);
    }

    /**
     * return a point with x as the number of roads and y is the number of capstones
     */
    public Point getBlackPool(){
        return new Point(blackPool, blackCapPool);
    }

    /**
     * Print out the board with the given key
     * KEY --------
     * WHITE WALL = WW
     * WHITE ROAD = WR
     * WHITE CAPSTONE = WC
     * BLACK WALL = BW
     * BLACK ROAD = BR
     * BLACK CAPSTONE = BC
     * EMPTY = "blank"
     */
    public String toString(){
        //KEY --------
        // WHITE WALL = WW
        // WHITE ROAD = WR
        // WHITE CAPSTONE = WC
        // BLACK WALL = BW
        // BLACK ROAD = BR
        // BLACK CAPSTONE = BC
        // EMPTY = "blank"

        StringBuilder builder = new StringBuilder();

        /*TOP ROW*/ builder.append("\t"); for(int i = 0; i < SIZE; i++){ builder.append(i + "\t");} builder.append("\n\n");
        /*REST OF THE ROWS*/ 
        for(int x = 0; x < SIZE; x++){
            builder.append(x + "\t");
            for(int y = 0; y < SIZE; y++){
                TakStack current = stacks[y][x];    
                if(!current.isEmpty()){
                    builder.append(getPieceString(current.top()));
                }
                builder.append("\t");
            }
            builder.append("\n\n");
        }


        //BUILDER RETURN
        return builder.toString();
    }

    private String getPieceString(TakPiece piece){
        if(piece.isWhite()){
            if(piece.isCapstone()){
                return "WC";
            } else if(piece.isWall()){
                return "WW";
            } else {
                return "WR";
            }
        } else {
            if(piece.isCapstone()){
                return "BC";
            } else if(piece.isWall()){
                return "BW";
            } else {
                return "BR";
            }
        }
    }

    /**
     * @return the size of the board
     */
    public int size(){
        return SIZE;
    }

    /**
     * @return the current turn count
     */
    public int turn(){
        return turn;
    }

    /**
     * Returns the currently stored winner values as a string.
     */
    public String getWinner(){
        if(whiteWins && blackWins){
            return "ITS A DRAW";
        } else if(whiteWins){
            return "WHITE WINS";
        } else if(blackWins){
            return "BLACK WINS";
        } else {
            return "NO WINNER";
        }
    }

    /**
     * Return the top TakPiece given a Point p
     */
    public TakPiece getTop(Point p){
        return stacks[p.x][p.y].top();
    }

    /**
     * Return the stack at Point p
     */
    public TakStack getStack(Point p){
        return stacks[p.x][p.y];
    }
    
    /**
     * Returns true if a winner has been determined.
     */
    public boolean determineWinner(){
        // search top down
        for(int x = 0; x < SIZE; x++){
            if(!stacks[x][0].isEmpty()){
                Point startingPoint = new Point(x, 0);
                ArrayList<Point> visited = new ArrayList<>();
                visited.add(startingPoint);
                TakTree<TakPiece> tree = new TakTree<>();
                tree.addRoot(getTop(startingPoint));
                treeBuilder(tree, startingPoint, visited);
            }

            if(whiteWins && blackWins){ // Only exit if we have found that both win
                return true;
            }
        }

        for(int y = 1; y < SIZE; y++){
            if(!stacks[0][y].isEmpty()){
                Point startingPoint = new Point(0, y);
                ArrayList<Point> visited = new ArrayList<>();
                visited.add(startingPoint);
                TakTree<TakPiece> tree = new TakTree<>();
                tree.addRoot(getTop(startingPoint));
                treeBuilder(tree, startingPoint, visited);
            }

            if(whiteWins && blackWins){ // Only exit if we have found that both win
                return true;
            }
        }

        // did we find a winner?
        return whiteWins || blackWins;
    }

    private TakTree<TakPiece> treeBuilder(TakTree<TakPiece> tree, Point startingPoint, ArrayList<Point> visited){
        
        // JUST FINISHED ADDING NEW POINT CLASS

        // Are we at a winning position?
        if(containsWinningPath(visited)){
            // Did white or black win?
            if(getTop(startingPoint).isWhite()){
                whiteWins = true;
            } else {
                blackWins = true;
            }

            // return our tree because this is certainly a leaf node
            return tree;
        }

        Point right = new Point(startingPoint.x + 1, startingPoint.y);
        Point left = new Point(startingPoint.x - 1, startingPoint.y);
        Point up = new Point(startingPoint.x, startingPoint.y - 1);
        Point down = new Point(startingPoint.x, startingPoint.y + 1);


        // Check for backtracking right
        if(isValidAndSimilar(right, startingPoint) && !visitedContains(visited, right)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(right));
            //Add this position to the visited array
            visited.add(right);
            // Attach subtree to right position
            tree.attachRight(treeBuilder(treeToAttach, right, visited));
        }

        // Try to build left subtree
        if(isValidAndSimilar(left, startingPoint) && !visitedContains(visited, left)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(left));
            //Add this position to the visited array
            visited.add(left);
            // Attach subtree to right position
            tree.attachLeft(treeBuilder(treeToAttach, left, visited));
        }

        // Try to build up subtree
        if(isValidAndSimilar(up, startingPoint) && !visitedContains(visited, up)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(up));
            //Add this position to the visited array
            visited.add(up);
            // Attach subtree to right position
            tree.attachUp(treeBuilder(treeToAttach, up, visited));
        }

        // Try to build down subtree
        if(isValidAndSimilar(down, startingPoint) && !visitedContains(visited, down)){

            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(down));
            //Add this position to the visited array
            visited.add(down);
            // Attach subtree to right position
            tree.attachDown(treeBuilder(treeToAttach, down, visited));
        }

        return tree;
    }

    private boolean containsWinningPath(ArrayList<Point> points){

        boolean startFoundY = false;
        boolean endFoundY = false;
        boolean startFoundX = false;
        boolean endFoundX = false;

        //Check top down
        for (Point point : points) {
            if(point.x == 0){
                startFoundX = true;
            }

            if(point.x == SIZE - 1){
                endFoundX = true;
            }

            if(point.y == 0){
                startFoundY = true;
            }

            if(point.y == SIZE - 1){
                endFoundY = true;
            }

            if(startFoundX && endFoundX){
                return true;
            }

            if(startFoundY && endFoundY){
                return true;
            }
        }

        return false;
    }

    private boolean visitedContains(ArrayList<Point> points, Point toCheck){
        for (Point point : points) {
            if(point.equals(toCheck)){
                return true;
            }
        }

        return false;
    }

    private boolean isValidAndSimilar(Point toPoint, Point fromPoint){

        // Check that our new point is on the board
        if(toPoint.x < 0 || toPoint.x > (SIZE - 1) || toPoint.y < 0 || toPoint.y > (SIZE - 1)){
            return false;
        }

        TakStack toStack = getStack(toPoint);
        TakStack fromStack = getStack(fromPoint);

        // Check if the position has a piece on it
        if(toStack.isEmpty() || fromStack.isEmpty()){
            return false;
        }

        // Chack if the piece is a road
        if(!toStack.top().isRoad()){
            return false;
        }

        // check if road is of the correct color
        if(fromStack.top().isWhite() != toStack.top().isWhite()){
            return false;
        }

        return true;
    }
}