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
        BoardController.Board.removeLastWordPlaced();
        OpeningWindowController.bController.displayBoard();
        OpeningWindowController.bController.challengeButton.setDisable(true);
        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
    }

    @FXML
    public void handleNoButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "No button clicked" ); // debug
        OpeningWindowController.bController.switchPlayer();
        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
    }
}
