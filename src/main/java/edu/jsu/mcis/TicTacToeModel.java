package edu.jsu.mcis;

import javax.lang.model.util.ElementScanner6;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        // INSERT YOUR CODE HERE 

        for (int i=0; i<width ;i++){    //loop through rows
            for (int j=0; j<width ;j++){ //loop through cols
                board[i][j]=Mark.EMPTY; //fill each square with empty
            }
        }
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        if (isValidSquare(row, col)){
            if (getMark(row, col)==Mark.EMPTY){
                if (xTurn=true){
                    board[row][col]=Mark.X;
                    xTurn=false;
                    return true;
                }
                else if (xTurn=false){
                    board[row][col]=Mark.O;
                    xTurn=true;
                    return true; 
                }
            }
            else
                return false;
        }
        return false;
    }
        
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        //insert your code here
        if (row>=0 && row<=width && col>=0 &&
            col<=width && board[row][col]==Mark.EMPTY)
            return true;

        else
            return false;

        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE
        if (board[row][col]==Mark.X){
            return true;
        }
        else if (board[row][col]==Mark.O){
            return true;
        }
        else {
            return false;
        }
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        if (board[row][col]==Mark.X){
            return Mark.X;
        }
        else if (board[row][col]==Mark.O){
            return Mark.O;
        }
        else if (board[row][col]==Mark.EMPTY){
            return Mark.EMPTY;
        }
        else return null;
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if (isMarkWin(Mark.X)){
            return Result.X;
        }
        else if(isMarkWin(Mark.O)){
            return Result.O;
        }
        else if (isTie()){
            return Result.TIE;
        }
        else {
            return Result.NONE;
        }
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        boolean result=false;;
        //is square marked
        for (int i=0;i<width;i++){
            result=true;
            for(int j=0;j<width;j++){
                if (board[i][j] != mark)
                    return false;
            }
        if (result){
            break;
            }
        }
        
        if (!result){
            //check rows
            for (int i=0;i<width;i++){
                result=true;
                for(int j=0;j<width;j++){
                    if (board[i][j] != board[i+1][j])
                    return false;
                }
                if(result)
                    break;
            }
        }

        if (!result){
            //check col
            for (int i=0;i<width;i++){
                result=true;
                for (int j=0;j<width;j++){
                    if (board[i][j] != board[i-1][j])
                    return false;
                }
                if (result)
                    break;  
            }
        }
        if (!result){
            //check l-r diagonal
            for (int i=0;i<width-1;i++){
                result=true;
                if (board[i][i] != board[i-1][i-1]){
                    return false;
                }
            if (result)
                break;   
            }
        }
        if (!result){
            //chec r-l diagonal
            for (int i=0;i<width-1;i++){
                if (board[width-i-1][i] != board[width-i][i-1])
                    return false;
            if (result)
               break;
            }
        }
    return result;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE
        for (int i=0;i<width;++i){
            for (int j=0;j<width;++j){
                if (board[i][j]==Mark.EMPTY){
                    return false;
                }
            }
        }
        return true;
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE
        System.out.println("  012");
        int j=0;
        for (int rows=0; rows<width;rows++){
            for (int i=0;i<width-2;i++){
                    System.out.print(rows+" "+board[i][j]);
                    for (int k=1;k<width;k++){
                        System.out.print(board[i][k]);
                    }
                System.out.print("\n");
            } 
        }
        return output.toString();
        
    }
    
}
