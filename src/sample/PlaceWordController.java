package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    @FXML
    public void onButtonClicked( ActionEvent event) throws IOException
    {
        if ( event.getSource().equals( placeWordButton ) )
        {
            boolean placed = BoardController.Board.placeWord( getWordInput(), getDirection(), getRowInput(), getColumnInput(), BoardController.players[BoardController.currentPlayer] );

            if( placed )
            {
                System.out.println("Words created: ");

                for(int i = 0; i < BoardController.Board.wordsCreatedLastMove.size(); i++)
                {
                    System.out.println("Word " + i + ": " + BoardController.Board.wordsCreatedLastMove.get( i ));
                }

                System.out.println("Score: " + BoardController.Board.getScoreFromLastMove( BoardController.Pool ));

                OpeningWindowController.bController.switchPlayer();
                wordInputField.clear();
                handleKeyReleased();

                OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
            }
            else
            {
                System.out.println("Error code is: " + BoardController.Board.getErrorCode());
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

    // disabling the buttons when there is no text typed into the text field
    // to avoid user submitting empty field
    @FXML
    public void handleKeyReleased()
    {
        boolean disableButtons = getWordInput().isEmpty() || getWordInput().trim().isEmpty();
        placeWordButton.setDisable( disableButtons );
    }

}
