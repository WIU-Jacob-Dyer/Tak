import java.util.Scanner;

class TakTheGame {

    static Scanner scanner = new Scanner(System.in);
    static Board board;

    public static void main(String[] args){
        
        initBoard();
        clearScreen();

        boolean quit = false;
        while(!quit){
            quit = mainMenu();
            if(board.determineWinner()){
                System.out.println(board.getWinner() + " WINS!!!!!!");
            }
        }
    }

    static boolean mainMenu(){
        System.out.println(board);
        System.out.println("OPTIONS");
        System.out.println("-------");
        System.out.println("Place: 1");
        System.out.println("Move: 2");
        System.out.println("EXIT GAME: 9");
        System.out.print("Selection:");
        int option = scanner.nextInt();
        clearScreen();
        switch(option){
            case 1:
                placeMenu();
                break;
            
            case 2:
                moveMenu();
                break;
            
            case 9:
                return true;

            default:
                return false;
        }

        clearScreen();
        return false;
    }

    static void placeMenu(){
        boolean isWhite = false;
        boolean isWall = false;
        boolean isCapstone = false;
        
        System.out.print("White Token: 1\nBlack Token: 2\nEnter Token: ");
        if(scanner.nextInt() == 1){
            isWhite = true;
        }

        clearScreen();
        System.out.print("Road: 1\nWall: 2\nEnter Selection: ");
        if(scanner.nextInt() == 1){
            isWall = true;
        }

        clearScreen();
        System.out.print("Non-Capstone: 1\nCapstone: 2\nEnter Token: ");
        if(scanner.nextInt() == 2){
            isCapstone = true;
        }



        clearScreen();
        System.out.println(board);
        System.out.print("Enter position (x y): ");
        int pos[] = {scanner.nextInt(), scanner.nextInt()};

        board.place(isWhite, isWall, isCapstone, pos);

        //UNDER CONSTRUCTION
    }

    static void moveMenu(){
        //UNDER CONSTRUCTION
    }

    static void initBoard(){
        clearScreen();
        System.out.print("Enter Size of Board (3 - 8): ");
        board = new Board(scanner.nextInt());
    }



    static void clearScreen(){
        for(int i = 0; i < 60; i++){
            System.out.println();
        }
    }
}