class Board{
    // Our stacks will be laid out with x,y = 0,0 at the top left corner EX:
    //        0   1   2   3   4  x
    //  0     O   O   O   O   O
    //  1     O   O   O   O   O
    //  2     O   O   O   O   O
    //  3     O   O   O   O   O
    //  y

    TakStack stacks[][];

    int winner = 0; // 0 = no winnner, 1 = white wins, 2 = black wins

    final int SIZE;

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
        if(winner == 0){
            return "";
        } else if(winner == 1) {
            return "WHITE";
        } else {
            return "BLACK";
        }
    }

    // ONLY SEARCHES TOP DOWN ATM
    public boolean determineWinner(){
        // search top down
        for(int x = 0; x < SIZE; x++){
            if(!stacks[x][0].isEmpty()){
                int[] startingPos = {x, 0};
                int[] previousPos = {-1, -1}; // Bogus points
                TrinityTree<TakPiece> tree = new TrinityTree<>();
                treeBuilderTopDown(tree, startingPos, previousPos);
            }

            if(winner > 0){
                return true;
            }
        }

        // search top down on rotated board
        for(int x = 0; x < SIZE; x++){
            if(!stacks[x][0].isEmpty()){
                int[] startingPos = {x, 0};
                int[] previousPos = {-1, -1}; // Bogus points
                TrinityTree<TakPiece> tree = new TrinityTree<>();
                Board rotatedBoard = this.rotateBoard();
                rotatedBoard.treeBuilderTopDown(tree, startingPos, previousPos);
                winner = rotatedBoard.winner;
            }

            if(winner > 0){
                return true;
            }
        }
        // no winnner found
        return false;
    }

    // MUST FEED A BOGUS VALUE FOR PREVIOUS TO AVOID NULL REF on initial call
    private TrinityTree<TakPiece> treeBuilderTopDown(TrinityTree<TakPiece> tree, int[] startingPos, int[] previousPos){
        
        // Are we at a winning position?
        if(startingPos[1] == (SIZE - 1)){
            // Did white or black win?
            if(stacks[startingPos[0]][startingPos[1]].top().isWhite()){
                winner = 1;
            } else {
                winner = 2;
            }

            // return our tree because this is certainly a leaf node
            return tree;
        }

        int[] right = {startingPos[0] + 1, startingPos[1]}; // right 1
        int[] left = {startingPos[0] - 1, startingPos[1]}; // left 1
        int[] middle = {startingPos[0], startingPos[1] + 1}; // down 1

        // Check for backtracking right
        if(!(right[0] == previousPos[0] && right[1] == previousPos[1])){
            // Check if the move will be valid
            if(isValidAndSimilar(right, startingPos)){
                // Create new subtree with our right as root
                TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
                treeToAttach.addRoot(stacks[right[0]][right[1]].top());

                // Attach subtree to right position
                tree.attachRight(treeBuilderTopDown(treeToAttach, right, startingPos));
            }  
        }
        
        // Check for backtracking left
        if(!(left[0] == previousPos[0] && left[1] == previousPos[1])){
            // Check if the move will be valid
            if(isValidAndSimilar(left, startingPos)){
                // Create new subtree with our left as root
                TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
                treeToAttach.addRoot(stacks[left[0]][left[1]].top());

                // Attach subtree to left position
                tree.attachLeft(treeBuilderTopDown(treeToAttach, left, startingPos));
            }  
        }

        // Check for backtracking middle
        if(!(middle[0] == previousPos[0] && middle[1] == previousPos[1])){
            // Check if the move will be valid
            if(isValidAndSimilar(middle, startingPos)){
                // Create new subtree with our middle as root
                TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
                treeToAttach.addRoot(stacks[middle[0]][middle[1]].top());

                // Attach subtree to middle position
                tree.attachMiddle(treeBuilderTopDown(treeToAttach, middle, startingPos));
            }  
        }

        return tree;    
    }

    // // MUST FEED A BOGUS VALUE FOR PREVIOUS TO AVOID NULL REF on initial call
    // private TrinityTree<TakPiece> treeBuilderLeftRight(TrinityTree<TakPiece> tree, int[] startingPos, int[] previousPos){

    //     // Are we at a winning position?
    //     if(startingPos[0] == (SIZE - 1)){
    //         // Did white or black win?
    //         if(stacks[startingPos[0]][startingPos[1]].top().isWhite()){
    //             winner = 1;
    //         } else {
    //             winner = 2;
    //         }

    //         // return our tree because this is certainly a leaf node
    //         return tree;
    //     }

    //     int[] up = {startingPos[0], startingPos[1] - 1}; // up 1 formerly RIGHT
    //     int[] right = {startingPos[0] + 1, startingPos[1]}; // right 1 formerly LEFT
    //     int[] down = {startingPos[0], startingPos[1] + 1}; // down 1 formerly MIDDLE

    //     // Going up attach RIGHT
    //     // Check for backtracking up
    //     if(!(up[0] == previousPos[0] && up[1] == previousPos[1])){
    //         // Check if the move will be valid
    //         if(isValidAndSimilar(up, startingPos)){
    //             // Create new subtree with our up as root
    //             TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
    //             treeToAttach.addRoot(stacks[up[0]][up[1]].top());

    //             // Attach subtree to right position
    //             tree.attachRight(treeBuilderTopDown(treeToAttach, up, startingPos));
    //         }  
    //     }
        
    //     // Goint right attach LEFT
    //     // Check for backtracking right
    //     if(!(right[0] == previousPos[0] && right[1] == previousPos[1])){
    //         // Check if the move will be valid
    //         if(isValidAndSimilar(right, startingPos)){
    //             // Create new subtree with our right as root
    //             TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
    //             treeToAttach.addRoot(stacks[right[0]][right[1]].top());

    //             // Attach subtree to left position
    //             tree.attachLeft(treeBuilderTopDown(treeToAttach, right, startingPos));
    //         }  
    //     }

    //     // Going down attach middle
    //     // Check for backtracking down
    //     if(!(down[0] == previousPos[0] && down[1] == previousPos[1])){
    //         // Check if the move will be valid
    //         if(isValidAndSimilar(down, startingPos)){
    //             // Create new subtree with our down as root
    //             TrinityTree<TakPiece> treeToAttach = new TrinityTree<>();
    //             treeToAttach.addRoot(stacks[down[0]][down[1]].top());

    //             // Attach subtree to middle position
    //             tree.attachMiddle(treeBuilderTopDown(treeToAttach, down, startingPos));
    //         }  
    //     }

    //     return tree;    
    // }

    private boolean isValidAndSimilar(int[] toPoint, int[] fromPoint){

        // Check that our new point is on the board
        if(toPoint[0] < 0 || toPoint[0] > (SIZE - 1) || toPoint[1] < 0 || toPoint[1] > (SIZE - 1)){
            return false;
        }

        TakStack toStack = stacks[toPoint[0]][toPoint[1]];
        TakStack fromStack = stacks[fromPoint[0]][fromPoint[1]];

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

    public Board rotateBoard(){
        Board temp = new Board(SIZE);

        for(int x = 0; x < SIZE; x++){
            for(int y = 0; y < SIZE; y++){
                if(!stacks[x][y].isEmpty()){
                    // place piece
                    temp.placeStack(stacks[x][y], new int[] {y, x});
                }
            }
        }


        return temp;
    }
}