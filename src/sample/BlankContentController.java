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
            BoardController.Board.changeBlankOnBoard( blankLetterReplacement );
            BoardController.Board.changeBlankInWordsCreated( blankLetterReplacement );

            OpeningWindowController.bController.switchPlayer();

            ArrayList<String> lastWordsList = BoardController.Board.wordsCreatedLastMove;

            StringBuilder lastWords = new StringBuilder();

            // Getting a string of the word/s placed
            for ( String s : lastWordsList )
            {
                lastWords.append( s ).append( "\n" );
            }

            // Displaying the word/s created during the last play in the window
            OpeningWindowController.bController.scrollLabel.setText( lastWords.toString() );
        }
    }
}
