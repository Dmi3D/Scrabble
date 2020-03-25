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
       return lettersInputField.getText().toUpperCase();
    }

    /**Takes the String of letters player wishes to exchange and passes it to back-end
     * exchange method.
     * Fail: exchange doesn't work and text field informs player of their invalid input.
     * Success: letters in player's frame are exchanged and player switch is triggered.*/
    @FXML
    public void onButtonClicked()
    {
        String lettrs = getLettersToExchangeAsString();

        char[] letters = new char[lettrs.length()];

        for ( int i = 0; i < letters.length; i++ )
        {
            letters[i] = lettrs.charAt( i );
        }

        boolean exchanged = BoardController.players[BoardController.currentPlayer].getPlayerFrame().exchangeTiles( letters, BoardController.Pool );

        if(!exchanged)
        {
            lettersInputField.setText( "INVALID INPUT" );
        }
        else
        {
            OpeningWindowController.bController.switchPlayer();

            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
            OpeningWindowController.bController.displayName();
            OpeningWindowController.bController.displayFrame();

            lettersInputField.clear();
        }
    }

    /** Disables exchange button when no letter is input into the text field */
    @FXML
    private void handleKeyReleased()
    {
        boolean disable = lettersInputField.getText().isEmpty() || lettersInputField.getText().trim().isEmpty();
        exchangeButton.setDisable( disable );
    }

}
