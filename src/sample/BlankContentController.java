package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class BlankContentController
{
    @FXML
    private TextField letterReplaceTextField;

    @FXML
    private Button replaceButton;

    @FXML
    public void handleButtonClicked( ActionEvent event )
    {
        if ( event.getSource().equals( replaceButton ) )
        {
            // Getting the letter input by player and returning to method call in PlaceWordController
            char blankLetterReplacement = letterReplaceTextField.getText().toUpperCase().charAt( 0 );
            BoardController.Board.changeBlankOnBoard( blankLetterReplacement );     // displaying blank tile as the letter specified by player

            BoardController.Board.changeBlankInWordsCreated( blankLetterReplacement );  // changing the stored value of blank tile in
                                                                                        // array of words created to facilitate challenge functionality

            OpeningWindowController.bController.switchPlayer();

            OpeningWindowController.bController.displayWordsCreatedLastMove();

            // Making the bottom right-hand-side of window invisible in preparation for next player's move
            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
        }
    }
}
