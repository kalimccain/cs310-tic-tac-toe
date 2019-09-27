package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {
    
    private final Scanner keyboard;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView() {
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);
        
    }
	
    public TicTacToeMove getNextMove(boolean isXTurn) {
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */
        
        // INSERT YOUR CODE HERE
        if(isXTurn){
            System.out.println("Player 1 (X) turn");
        }
        else 
            System.out.println("Player 2(O) turn");
        
        int row=0;
        int col=0;
        
        System.out.println("Please enter your desired row and column separated by a space. Player X goes first!");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
        TicTacToeMove move = new TicTacToeMove(row, col);
        return move;
    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "wins!");

    }
    
    public void showBoard(String board) {
        
        System.out.println("\n\n" + board);
        
    }
	
}
