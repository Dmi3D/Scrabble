package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

/**1) Parses input from placeWordContent.fxml.
 * 2) Calls method to place word input by user on the board.*/
public class PlaceWordController
{
    @FXML
    private TextField wordInputField;

    @FXML
    private ChoiceBox<String> rowChoiceBox;

    @FXML
    private ChoiceBox<String> columnChoiceBox;

    @FXML
    private ChoiceBox<String> directionChoiceBox;

    @FXML
    private Button placeWordButton;

    @FXML
    public void initialize()
    {
        placeWordButton.setDisable(true);
    }

    /** Calls method from back-end to place the word on the board
     *
     *  Success: 1) Prompts the words created during that play.
     *           2) Calls method to make word placed on board in back-end show in the front-end
     *           3) Calls method to increase the player's score
     *
     *  Fail:    1) Calls method to retrieve the error code of the error associated with the invalid word placement
     *           2) Calls method in BoardController to display the error in the window.
     */
    @FXML
    public void onButtonClicked( ActionEvent event) throws IOException
    {
        if ( event.getSource().equals( placeWordButton ) )
        {
            boolean placed = BoardController.Board.placeWord( getWordInput(), getDirection(), getRowInput(), getColumnInput(), BoardController.players[BoardController.currentPlayer] );

            if( placed )
            {
                // resets the pass back to 0 when they broke the succession of passes by making a valid word placement
                OpeningWindowController.bController.resetPass();

                // debug
                System.out.println("Words created: ");

                for(int i = 0; i < BoardController.Board.wordsCreatedLastMove.size(); i++)
                {
                    System.out.println("Word " + i + ": " + BoardController.Board.wordsCreatedLastMove.get( i ));
                }

                System.out.println("Score: " + BoardController.Board.getScoreFromLastMove( BoardController.Pool ));
                // increasing the player's score based on word they places and position on board
                BoardController.getCurrentPlayer().increaseScore( BoardController.Board.getScoreFromLastMove( BoardController.Pool ) );

                // switching player
                OpeningWindowController.bController.switchPlayer();
                wordInputField.clear();
                handleKeyReleased();

                ArrayList<String> lastWordsList = BoardController.Board.wordsCreatedLastMove;

                StringBuilder lastWords = new StringBuilder();

                // getting a string of the word/s placed
                for ( String s : lastWordsList )
                {
                    lastWords.append( s ).append( "\n" );
                }

                // displaying the word/s created during the last play in the window
                OpeningWindowController.bController.scrollLabel.setText( lastWords.toString() );

                // making the bottom right-hand-side of window invisible in preparation for next player's move
                OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
            }
            else
            {
                System.out.println("Error code is: " + BoardController.Board.getErrorCode());
                // getting error code for invalid word placement
                int errorCode = BoardController.Board.getErrorCode();
                // calling Board Controller to load the fxml file which displays error
                OpeningWindowController.bController.loadErrorContent(errorCode);
            }
        }
    }

    @FXML
    private String getWordInput()
    {
        return wordInputField.getText();
    }

    @FXML
    private char getColumnInput()
    {
        return columnChoiceBox.getValue().charAt( 0 );
    }

    @FXML
    private int getRowInput()
    {
        return Integer.parseInt(rowChoiceBox.getValue());
    }

    @FXML
    private char getDirection()
    {
        String chosenDirection = directionChoiceBox.getValue();
        if ( chosenDirection.equals( "Across" ) )
            return 'A';
        return 'D';
    }

    /** Disabling the button 'PLACE' when there is no text typed into the text field
     * to avoid user submitting empty field*/
    @FXML
    public void handleKeyReleased()
    {
        boolean disableButtons = getWordInput().isEmpty() || getWordInput().trim().isEmpty();
        placeWordButton.setDisable( disableButtons );
    }

}
