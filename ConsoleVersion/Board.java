package ConsoleVersion;

class Board{
    // Our stacks will be laid out with x,y = 0,0 at the top left corner EX:
    //        0   1   2   3   4  x
    //  0     O   O   O   O   O
    //  1     O   O   O   O   O
    //  2     O   O   O   O   O
    //  3     O   O   O   O   O
    //  y

    TakStack stacks[][];

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
                TakStack current = stacks[x][y];    
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
}