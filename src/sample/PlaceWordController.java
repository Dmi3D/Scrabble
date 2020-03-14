package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;

public class PlaceWordController
{

    @FXML
    private Button menuButton;

    @FXML
    private TextField wordInputField;

    @FXML
    private ChoiceBox<Integer> rowChoiceBox;

    @FXML
    private ChoiceBox<Character> columnChoiceBox;

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
    public void onButtonClicked( ActionEvent event)
    {
        if ( event.getSource().equals( placeWordButton ) )
        {
            System.out.println("Word: " + getWordInput() + "\nRow: " + getRowInput() + "\nColumn: " + getColumnInput() + "\nDirection: " + getDirection() +".");
            wordInputField.clear();
            handleKeyReleased();
        }
        else if ( event.getSource().equals( menuButton ) )
        {
            //call menu content
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
        return columnChoiceBox.getValue();
    }

    @FXML
    private int getRowInput()
    {
        return rowChoiceBox.getValue();
    }

    @FXML
    private char getDirection()
    {
        String chosenDirection = directionChoiceBox.getValue();
        if ( chosenDirection.equals( "Across" ) )
            return 'A';
        return 'D';
    }

    //disabling the buttons when there is no text typed into the text field
    //to avoid user making any mistakes
    @FXML
    public void handleKeyReleased()
    {
        boolean disableButtons = getWordInput().isEmpty() || getWordInput().trim().isEmpty();
        placeWordButton.setDisable( disableButtons );
    }




}
