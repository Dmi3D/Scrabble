package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PlaceWordController
{
    private boolean placed;
    private boolean clicked;

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
            placed = BoardController.Board.placeWord( getWordInput(), getDirection(), getRowInput(), getColumnInput(), BoardController.players[BoardController.currentPlayer] );

            if( placed )
            {
                BoardController.players[BoardController.currentPlayer].getPlayerFrame().fillFrame( BoardController.Pool );
            }

            wordInputField.clear();
            handleKeyReleased();

            clicked = true;
            notifyAll();
        }

        clicked = false;
    }

    public boolean isClicked()
    {
        return clicked;
    }

    public boolean isPlaced()
    {
        return placed;
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
