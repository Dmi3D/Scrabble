package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;

public class PlaceWordController
{

    @FXML
    private Button menuButton;

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
            System.out.println("Word: " + getWordInput() + "\nRow: " + getRowInput() + "\nColumn: " + getColumnInput() + "\nDirection: " + getDirection() +".");
            wordInputField.clear();
            handleKeyReleased();
        }
        else if ( event.getSource().equals( menuButton ) )
        {
            System.out.println("Loading choice buttons content"); // debug
            /*ChoiceButtonsContent content = new ChoiceButtonsContent();
            MainController controller = new MainController();
            controller.mainPane.setRight( content.getChoiceButtonsContent() );
            BorderPane.setMargin( controller.mainPane.getCenter(), new Insets( 0, 0, 10, 0 ) );
            BorderPane.setMargin( controller.mainPane.getRight(), new Insets( 0, 0, 0, 10 ) );*/
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

    //disabling the buttons when there is no text typed into the text field
    //to avoid user making any mistakes
    @FXML
    public void handleKeyReleased()
    {
        boolean disableButtons = getWordInput().isEmpty() || getWordInput().trim().isEmpty();
        placeWordButton.setDisable( disableButtons );
    }

}
