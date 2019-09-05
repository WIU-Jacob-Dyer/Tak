package ConsoleVersion;

import java.util.ArrayList;

class TakStack{
    private ArrayList<TakPiece> stack;

    public TakStack(){
        stack = new ArrayList<>();
    }

    /**
     * 
     * @param toAdd The stack we want to push ontop of our stack
     */

    public void add(ArrayList<TakPiece> toAdd){
        for (TakPiece piece : toAdd) {
            this.stack.add(piece);
        }
    }

    /**
     * 
     * @param piece The piece that will occupy this otherwise empty space
     */

    public void add(TakPiece piece){
        stack.add(piece);
    }

    /**
     * 
     * @param depth The depth at which we will be removing pieces from this stack
     * @return The stack that was removed
     */

    public ArrayList<TakPiece> sub(int depth){
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

        return toReturn;
    }

    /**
     * 
     * @return Returns the top piece of this stack - null if empty
     */
    public TakPiece top(){
        return stack.get(stack.size() - 1);
    }

    /**
     * 
     * @return This stack
     */
    public ArrayList<TakPiece> getStack(){
        return stack;
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }

    public int size(){
        return stack.size();
    }

}