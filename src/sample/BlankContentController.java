package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BlankContentController
{
    @FXML
    private TextField letterReplaceTextField;

    @FXML
    private Button replaceButton;

    /**
     * Responsible for getting the replacement letter for the blank tile in player's word.
     * 1) The word input is overridden with the replacement tile in the array storing the
     * last words placed on the board.
     * Reasoning:  to facilitate the challenge functionality (which requires checking of words
     * against a dictionary)
     * 2) The player is switched and the last placed word/s are displayed in the top right corner.
     * 3) The bottom right panel is hidden upon switching to next player
     */
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
