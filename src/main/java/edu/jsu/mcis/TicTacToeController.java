package edu.jsu.mcis;

import edu.jsu.mcis.TicTacToeModel.Mark;

public class TicTacToeController {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView();
        
    }

    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */

        // INSERT YOUR CODE HERE
        boolean gameover=false;

        while(!gameover){
            view.showBoard(model.toString());
            TicTacToeMove thismove = view.getNextMove(model.isXTurn());
            model.makeMark(thismove.getRow(), thismove.getCol());
            
            if(model.isGameover()){
                gameover=true;
            }
        }
        
        
        /* After the game is over, show the final board an the winner */

        view.showBoard(model.toString());

        view.showResult(model.getResult().toString());
        
    }

}
