package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ExchangeContentController
{

    @FXML
    private TextField lettersInputField;

    @FXML
    private Button exchangeButton;

    public void initialize()
    {
        exchangeButton.setDisable(true);
    }

    public String getLettersToExchangeAsString()
    {
        String letters = lettersInputField.getText();
        if ( validateLetterInput( letters ) )
            return letters;
        return " ";
    }

    private boolean validateLetterInput(String letters)
    {
        boolean isValid = true;

        for ( int i = 0; i < letters.length(); i++ )
        {
            if ( Character.isDigit( letters.charAt( i ) ) ||
                !(Character.isLetter( letters.charAt( i ) ) &&
                    ((letters.charAt( i ) >= 'a' && letters.charAt( i ) <= 'z' )
                                    || (letters.charAt( i ) >= 'A' && letters.charAt( i ) <= 'Z') || letters.charAt( i ) == '*')))
            {
                isValid = false;
                break;
            }
        }
        return isValid;
    }


    // debugging
    @FXML
    public void onButtonClicked()
    {
        String lettrs = getLettersToExchangeAsString();
        char[] letters = new char[lettrs.length()];
        for ( int i = 0; i < letters.length; i++ )
        {
            letters[i] = lettrs.charAt( i );
        }

        BoardController.players[BoardController.currentPlayer].getPlayerFrame().exchangeTiles( letters, BoardController.Pool );

        OpeningWindowController.bController.switchPlayer();

        OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
        OpeningWindowController.bController.displayName();
        OpeningWindowController.bController.displayFrame();

        lettersInputField.clear();
    }

    @FXML
    private void handleKeyReleased()
    {
        boolean disable = lettersInputField.getText().isEmpty() || lettersInputField.getText().trim().isEmpty();
        exchangeButton.setDisable( disable );
    }

}
