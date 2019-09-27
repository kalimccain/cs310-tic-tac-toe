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
    
    public TicTacToeModel(int width) { //initialize game
        
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
        if (isValidSquare(row, col)){ //if is a valid square 
            if(!isSquareMarked(row, col)){ //if square is not already marked
                    if (xTurn){ //if it is x's turn
                        board[row][col]=Mark.X; //mark square with x
                    }
                    else { //if it is not x's turn
                        board[row][col]=Mark.O; //mark square with o
                    }
                    xTurn=!xTurn;
                    return true;
            }
            else
                return false;
        }
        return false;
    }
        
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        //insert your code here
        
        if ((row<0 || col<0) || (row>=width || col>=width)){
            System.out.println("Move is not a valid square!");
            return false;
        }

        else
            return true;

        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE
        if (board[row][col]==Mark.EMPTY){
            return false;
        }
        else {
            System.out.println("Square is already marked!");
            return true;
        }
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if (isMarkWin(Mark.X)==true){
            return Result.X;
        }
        else if(isMarkWin(Mark.O)==true){
            return Result.O;
        }
        else if (isTie()){
            return Result.TIE;
        }
        else 
            return Result.NONE;
 
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        boolean result=false;
        //check rows
        for (int i=0;i<width;i++){
            result=true;
            for(int j=0;j<width;j++){
                if (board[i][j] != mark)
                    result= false;
            }
        if (result){
            break;
            }
        }
        if (!result){
            //check col
            for (int j=0;j<width;j++){ //iterate through rows
                result=true;
                for (int i=0;i<width;i++){ //iterate through col
                    if (board[i][j] != mark) //if 0,0 does not equal 0,1
                    result=  false;
                }
                if (result)
                    break;  
            }
        }
        if (!result){
            //check l-r diagonal
            result=true;
            for (int i=0;i<width;i++){ //iterate through rows
                    if (board[i][i] != mark) //if 00 does not equal 11
                    result= false;

                    
                }
        }
        if (!result){
            //chec r-l diagonal
            result=true;  
                for (int j=0;j<width;j++){ //iterate through columns
                    if (board[(width-1)-j][j] != mark)
                        result= false;
                }
        }
    return result;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE
        boolean isEmpty =false;

        for (int i=0;i<width;++i){
            for (int j=0;j<width;++j){
                if (board[i][j]==Mark.EMPTY){
                    isEmpty= true;
                }
            }
        }
        if(isEmpty){
            return false;
        }
        else
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
        int size=0;
        for (int i=0;i<width;i++){
            output.append(i);
        }
        output.append("\n");

        for (int j=0;j<width;j++){
            output.append(size + " ");

            for (int n=0;n<width;n++){
                output.append(board[j][n]);
            }

            output.append("\n");
            size++;
        }
       return output.toString();
    }
    
}

