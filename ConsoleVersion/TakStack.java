import java.util.ArrayList;

class TakStack{
    private ArrayList<TakPiece> stack;

    /**
     * Creates new TakStack with empty stack.
     */
    public TakStack(){
        stack = new ArrayList<>();
    }

    /**
     * Creates new stack starting with the passed stack as foundation.
     */
    public TakStack(ArrayList<TakPiece> stack){
        this.stack = stack;
    }

    /**
     * Adds a stack to the top of this stack
     * @param toAdd The stack we want to push ontop of our stack
     */

    public void add(TakStack toAdd){
        for (TakPiece piece : toAdd.stack) {
            this.stack.add(piece);
        }
    }

    // private void add(ArrayList<TakPiece> toAdd){
    //     for (TakPiece piece : toAdd) {
    //         this.stack.add(piece);
    //     }
    // }

    /**
     * Place piece ontop of stack
     * @param piece The piece that will occupy this otherwise empty space
     */

    public void add(TakPiece piece){
        stack.add(piece);
    }

    /**
     * Removes a chunk of the stack and returns it as a new stack in proper order.
     * @param depth The depth at which we will be removing pieces from this stack
     * @return The stack that was removed
     */

    public TakStack sub(int depth){
        ArrayList<TakPiece> temp = new ArrayList<>();
        ArrayList<TakPiece> toReturn = new ArrayList<>();

        for(int i = 0; i < depth; i++){
            temp.add(this.stack.remove(this.stack.size() - 1));
        }

        // Have to do operation twice so we can flip the list back around so it 
        // preserves our stack structure
        while(temp.size() > 0){
            toReturn.add(temp.remove(temp.size() - 1));
        }

        return new TakStack(toReturn);
    }

    /**
     * Peek at the top of the stack.
     * @return Returns the top piece of this stack - null if empty
     */
    public TakPiece top(){
        return stack.get(stack.size() - 1);
    }

    /**
     * Returns the ArrayList stack
     * @return This stack
     */
    public ArrayList<TakPiece> getStack(){
        return stack;
    }

    /**
     * Is the stack empty?
     * @return empty?
     */
    public boolean isEmpty(){
        return stack.size() == 0;
    }

    /**
     * How tall is the stack?
     * @return Size of stack
     */
    public int size(){
        return stack.size();
    }

}