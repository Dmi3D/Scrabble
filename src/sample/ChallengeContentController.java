package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Class that controls the buttons 'Yes' and 'No' that load
 * during fxml content switching when rendering 'challengeContent.fxml'
 */
public class ChallengeContentController
{
    /**
     * 1) Calls method to remove the last word placed on the board.
     * 2) Calls method to refill the frame of the opponent (player whose word was
     *    removed when challenge is successful), with the letters of that word
     *    from the Board.
     * 3) Calls method to reset the opponent's score back to number prior
     *    placing the new word.
     */
    @FXML
    public void handleYesButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Yes button clicked" ); // debug

        // resets the pass back to 0 when they broke the succession of passes by challenging during their move
        OpeningWindowController.bController.resetPass();

        // allowing the challenger to make a move after successfully challenging their opponent's play
        int otherPlayerNumber = BoardController.currentPlayer;

        if ( otherPlayerNumber == 0 )
        {
            otherPlayerNumber = 1;
        }

        else if ( otherPlayerNumber == 1 )
        {
            otherPlayerNumber = 0;
        }

        Player otherPlayer = BoardController.players[otherPlayerNumber];

        BoardController.Board.removeLastWordPlaced();
        BoardController.players[otherPlayerNumber].getPlayerFrame().fillFrameWithWord( BoardController.Board.getLastTilesPlaced() );

        OpeningWindowController.bController.displayBoard();
        OpeningWindowController.bController.challengeButton.setDisable(true);
        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
        OpeningWindowController.bController.scrollLabel.setText( "" );
        otherPlayer.decreaseScore( BoardController.Board.getScoreFromLastMove( BoardController.Pool ) );
    }

    /**
     * Calls method to switch player as the challenge was unsuccessful.
     * Sets the bottom right-hand-side of the window to empty upon switching to next player.
     */
    @FXML
    public void handleNoButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "No button clicked" ); // debug
        OpeningWindowController.bController.switchPlayer();
        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
    }
}
