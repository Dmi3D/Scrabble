package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that checks the validity of the opponent's last word/s placed.
 * Outputs correct message according to the outcome of the challenge
 */
public class ChallengeContentController implements Initializable
{

    @FXML
    private Label challengeLabel;

    @Override
    public void initialize( URL location, ResourceBundle resources )
    {
        handleChallenge();
    }

    /**
     * 1) Checks if challenger's opponent placed a word not found in the dictionary.
     * <p>
     * 2) Challenge Successful:
     * Calls method to remove the last word/s placed on the board.
     * Calls method to refill the frame of the opponent (player whose word was
     * removed when challenge is successful), with the letters of that word
     * from the Board.
     * Calls method to reset the opponent's score back to number prior
     * placing the new word.
     * Displays the invalid words on the screen.
     * <p>
     * 3) Challenge Unsuccessful:
     * Switches player to the challenger's opponent
     * Displays message regarding unsuccessful challenge
     */
    @FXML
    public void handleChallenge()
    {
        // Resets the pass back to 0 when they broke the succession of passes by challenging during their move
        OpeningWindowController.bController.resetPass();

        ArrayList<String> wordsCreated = BoardController.Board.wordsCreatedLastMove;
        ArrayList<String> invalidWords = new ArrayList<>();

        for ( String word : wordsCreated )
        {
            if ( !BoardController.dictionary.contains( word.toLowerCase() ) )
            {
                System.out.println( "Word not contained in dictionary: " + word );
                invalidWords.add( word );
            }
        }

        // CHALLENGE SUCCESSFUL
        if ( !invalidWords.isEmpty() )
        {
            StringBuilder invalidWordString = new StringBuilder();

            // Getting a string of the invalid word/s placed
            for ( int i = 0; i < invalidWords.size(); i++ )
            {
                invalidWordString.append( invalidWords.get( i ) );
                if ( invalidWords.size() > 1 && i != invalidWords.size() - 1 )
                {
                    invalidWordString.append( ", " );
                }
            }

            // Success state message display on the screen
            challengeLabel.setText( "CHALLENGE SUCCESSFUL!\n THE FOLLOWING WERE INVALID WORDS:\n" + invalidWordString.toString() );

            Player otherPlayer = BoardController.getOtherPlayer();

            BoardController.Board.removeLastTilesPlaced();   // Removing the last word placed from the board
            otherPlayer.getPlayerFrame().fillFrameWithWord( BoardController.Board.getLastTilesPlaced() );   // Refilling opponent's frame with letters from invalid word

            OpeningWindowController.bController.displayBoard();
            OpeningWindowController.bController.challengeButton.setDisable( true );
            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
            OpeningWindowController.bController.scrollLabel.setText( "" );
            otherPlayer.decreaseScore( BoardController.Board.getScoreFromLastMove() );
        }
        // CHALLENGE UNSUCCESSFUL
        else
        {
            challengeLabel.setText( "CHALLENGE UNSUCCESSFUL!\nALL WORDS ARE VALID. THE CHALLENGER LOSES THEIR TURN." );
            OpeningWindowController.bController.switchPlayer();
            OpeningWindowController.bController.challengeButton.setDisable( true );
            OpeningWindowController.bController.scrollLabel.setText( "" );
            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
        }
    }
}
