
import java.util.ArrayList;
import java.util.List;



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

    private final int SIZE;

    /**
     * 
     * @param size This determines the dimensions of the board
     */

    public Board(int size){
        if(size > 8 || size < 3){
            SIZE = 5;
        } else {
            SIZE = size;
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

    /**
     * 
     * @param white Is this piece white?
     * @param pos Array containing the position at which a piece is to be placed
     * @return Will return false if this move is not valid
     */

    public boolean place(boolean isWhite, boolean isWall, boolean isCapstone, int[] pos){
        // START CONDITIONS
        //----------------
        // Is the space empty that we are trying to place
        if(stacks[pos[0]][pos[1]].size() > 0) return false;
        //----------------
        // END CONDITIONS

        // Valid - move start operation
        // Add new piece to the stack
        stacks[pos[0]][pos[1]].add(new TakPiece(isWhite, isWall, isCapstone));

        // RETURN FALSE WHILE NOT IMPLEMENTED
        return true;
    }

    /**
     * 
     * @param piece piece to place
     * @param pos Array containing the position at which a piece is to be placed
     * @return Will return false if this move is not valid
     */

    public boolean placeStack(TakStack stack, int[] pos){
        // START CONDITIONS
        //----------------
        // Is the space empty that we are trying to place
        if(stacks[pos[0]][pos[1]].size() > 0) return false;
        //----------------
        // END CONDITIONS

        // Valid - move start operation
        // Add new piece to the stack
        stacks[pos[0]][pos[1]].add(stack);

        // RETURN FALSE WHILE NOT IMPLEMENTED
        return true;
    }

    /**
     * 
     * @param posFrom The position we are moving from
     * @param posTo The position we should move the piece to
     * @param depth The number of pieces we are grabbing to move
     * @return Will return false if this move is not valid
     */
    public boolean move(int[] startPos, int[] desPos, int depth){
        // START CONDITIONS
        //-----------------
        TakStack startStack = stacks[startPos[0]][startPos[1]];
        TakStack desStack = stacks[desPos[0]][desPos[1]];

        // are we grabbing a valid number given our board size
        if(depth > SIZE) return false;

        // is this move directly up,down,left, or right
        double xchange = Math.pow(startPos[0] - desPos[0], 2);
        double ychange = Math.pow(startPos[1] - desPos[1], 2);
        if(ychange > 1 ||  xchange > 1) return false;
        if(ychange >= 1 &&  xchange >= 1) return false;

        // are there enough pieces to grab
        if(depth > startStack.size()) return false;

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
        //---------------
        // END CONDITIONS
        
        // Valid move - start move operation
        // Add onto our position we would like to move to the difference of what we grab from the postion we move from**
        desStack.add(startStack.sub(depth));

        return true;
    }

   

    public String toString(){
        //KEY --------
        // WHITE WALL = !
        // WHITE ROAD = @
        // WHITE CAPSTONE = #
        // BLACK WALL = $
        // BLACK ROAD = %
        // BLACK CAPSTONE = &
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
                return "#";
            } else if(piece.isWall()){
                return "!";
            } else {
                return "@";
            }
        } else {
            if(piece.isCapstone()){
                return "&";
            } else if(piece.isWall()){
                return "$";
            } else {
                return "%";
            }
        }
    }


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

    public TakPiece getTop(Point p){
        return stacks[p.x][p.y].top();
    }

    public TakStack getStack(Point p){
        return stacks[p.x][p.y];
    }
    
    public boolean determineWinner(){
        // search top down
        for(int x = 0; x < SIZE; x++){
            if(!stacks[x][0].isEmpty()){
                Point startingPoint = new Point(x, 0);
                ArrayList<Point> visited = new ArrayList<>();
                visited.add(startingPoint);
                TakTree<TakPiece> tree = new TakTree<>();
                tree.addRoot(getTop(startingPoint));
                System.out.println(treeBuilder(tree, startingPoint, visited).size());
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
                System.out.println(treeBuilder(tree, startingPoint, visited).size());
            }

            if(whiteWins && blackWins){ // Only exit if we have found that both win
                return true;
            }
        }

        // did we find a winner?
        return whiteWins || blackWins;
    }

    // MUST FEED A BOGUS VALUE FOR PREVIOUS TO AVOID NULL REF on initial call
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
            tree.attachRight(treeBuilder(treeToAttach, startingPoint, visited));
        }

        // Try to build left subtree
        if(isValidAndSimilar(left, startingPoint) && !visitedContains(visited, left)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(left));
            //Add this position to the visited array
            visited.add(left);
            // Attach subtree to right position
            tree.attachLeft(treeBuilder(treeToAttach, startingPoint, visited));
        }

        // Try to build up subtree
        if(isValidAndSimilar(up, startingPoint) && !visitedContains(visited, up)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(up));
            //Add this position to the visited array
            visited.add(up);
            // Attach subtree to right position
            tree.attachUp(treeBuilder(treeToAttach, startingPoint, visited));
        }

        // Try to build down subtree
        if(isValidAndSimilar(down, startingPoint) && !visitedContains(visited, down)){
            // Create new subtree with our right as root
            TakTree<TakPiece> treeToAttach = new TakTree<>();
            treeToAttach.addRoot(getTop(down));
            //Add this position to the visited array
            visited.add(down);
            // Attach subtree to right position
            tree.attachDown(treeBuilder(treeToAttach, startingPoint, visited));
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