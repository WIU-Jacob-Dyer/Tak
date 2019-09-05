package ConsoleVersion;

import java.util.Scanner;

class TakTheGame {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        clearScreen();
        System.out.print("Enter Size of Board (3 - 8): ");
        int size = scanner.nextInt();
        clearScreen();

        Board board = new Board(size);


        int pos[] = {0,0};
        board.place(false, false, false, pos);
        board.place(true, false, false, pos);
        System.out.println(board);
    }



    static void clearScreen(){
        for(int i = 0; i < 60; i++){
            System.out.println();
        }
    }
}