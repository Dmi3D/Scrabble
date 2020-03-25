package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ChallengeContentController
{
    @FXML
    public void handleYesButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Yes button clicked" ); // debug

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

    @FXML
    public void handleNoButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "No button clicked" ); // debug
        OpeningWindowController.bController.switchPlayer();
        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
    }
}
