package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 1) Parses input from placeWordContent.fxml.
 * 2) Calls method to place word input by user on the board.
 */
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
        placeWordButton.setDisable( true );
    }

    /**
     * Calls method from back-end to place the word on the board
     * <p>
     * Success: 1) Prompts the words created during that play.
     * 2) Calls method to make word placed on board in back-end show in the front-end
     * 3) Calls method to increase the player's score
     * <p>
     * Fail:    1) Calls method to retrieve the error code of the error associated with the invalid word placement
     * 2) Calls method in BoardController to display the error in the window.
     */
    @FXML
    public void onButtonClicked( ActionEvent event ) throws IOException
    {
        if ( event.getSource().equals( placeWordButton ) )
        {
            int numberOfBlanks = getNumberOfBlanks( getWordInput() );
            if ( numberOfBlanks >= 2 )
            {
                // Calling Board Controller to load the fxml file which displays error
                OpeningWindowController.bController.loadErrorContent( 6 );
            }
            else // Avoiding word passing to back-end when it contains more than one '*'
            {
                boolean placed = BoardController.Board.placeWord( getWordInput(), getDirection(), getRowInput(), getColumnInput(), BoardController.players[BoardController.currentPlayer] );

                if ( placed )
                {
                    // Resets the pass back to 0 when they broke the succession of passes by making a valid word placement
                    OpeningWindowController.bController.resetPass();

                    // Situation 1 when game ends: Pool is empty and the player has used all of their tiles from the frame
                    if ( BoardController.Pool.isEmpty() && BoardController.getCurrentPlayer().getPlayerFrame().isEmpty() )
                    {
                        // Adding to player's score the sum of scores of the tiles on their opponent's frame
                        int scoreFromFrame = BoardController.getCurrentPlayer().getPlayerFrame().getScoreOnFrame( BoardController.Pool );
                        BoardController.getCurrentPlayer().increaseScore( scoreFromFrame );
                        // Subtracting opponent's score the sum of scores of the tiles on their frame
                        BoardController.getOtherPlayer().decreaseScore( scoreFromFrame );

                        // Switching to gameOverWindow.fxml
                        FXMLLoader loader = new FXMLLoader( PlaceWordController.class.getResource( "/gameOverWindow.fxml" ) );
                        Parent gameOverWindow = (Parent) loader.load();
                        Scene gameOverScene = new Scene( gameOverWindow );

                        // Getting information about the stage i.e. window to access it
                        Stage window = (Stage) ( (Node) event.getSource() ).getScene().getWindow();

                        window.setScene( gameOverScene );
                        // Changing the window's scene
                        window.show();
                    }

                    // Increasing the player's score based on word they places and position on board
                    BoardController.getCurrentPlayer().increaseScore( BoardController.Board.getScoreFromLastMove( BoardController.Pool ) );

                    if ( numberOfBlanks == 1 )
                    {
                        FXMLLoader loader = new FXMLLoader( PlaceWordController.class.getResource( "/blankContent.fxml" ) );

                        BlankContentController blankContentController = new BlankContentController();
                        loader.setController( blankContentController );

                        AnchorPane content = loader.load();

                        OpeningWindowController.bController.rightPanel.setBottom( content );
                        BorderPane.setMargin( OpeningWindowController.bController.rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );

                        char blankReplacement = blankContentController.getBlankLetterReplacement();

                        System.out.println("Blank Replacement: " + blankReplacement);
                        BoardController.Board.changeBlankOnBoard( blankReplacement );
                        BoardController.Board.changeBlankInWordsCreated( blankReplacement );
                    }

                    // Switching player
                    OpeningWindowController.bController.switchPlayer();
                    wordInputField.clear();
                    handleKeyReleased();

                    ArrayList<String> lastWordsList = BoardController.Board.wordsCreatedLastMove;

                    StringBuilder lastWords = new StringBuilder();

                    // Getting a string of the word/s placed
                    for ( String s : lastWordsList )
                    {
                        lastWords.append( s ).append( "\n" );
                    }

                    // Displaying the word/s created during the last play in the window
                    OpeningWindowController.bController.scrollLabel.setText( lastWords.toString() );

                    // Making the bottom right-hand-side of window invisible in preparation for next player's move
                    OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
                }
                else
                {
                    // Getting error code for invalid word placement
                    int errorCode = BoardController.Board.getErrorCode();
                    // Calling Board Controller to load the fxml file which displays error
                    OpeningWindowController.bController.loadErrorContent( errorCode );
                }
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
        return Integer.parseInt( rowChoiceBox.getValue() );
    }

    @FXML
    private char getDirection()
    {
        String chosenDirection = directionChoiceBox.getValue();
        if ( chosenDirection.equals( "Across" ) )
            return 'A';
        return 'D';
    }

    private int getNumberOfBlanks( String word )
    {
        int blankCounter = 0;

        for ( int i = 0; i < word.length(); i++ )
        {
            if ( word.charAt( i ) == '*' )
            {
                blankCounter++;
            }
        }

        return blankCounter;
    }

    /**
     * Disabling the button 'PLACE' when there is no text typed into the text field
     * to avoid user submitting empty field
     */
    @FXML
    public void handleKeyReleased()
    {
        boolean disableButtons = getWordInput().isEmpty() || getWordInput().trim().isEmpty();
        placeWordButton.setDisable( disableButtons );
    }

}
