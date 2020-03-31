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

    private char blankLetterReplacement;

    public char getBlankLetterReplacement()
    {
        return blankLetterReplacement;
    }

    @FXML
    public void handleButtonClicked( ActionEvent event )
    {
        if ( event.getSource().equals( replaceButton ) )
        {
            blankLetterReplacement = letterReplaceTextField.getText().toUpperCase().charAt( 0 );
        }
    }
}
