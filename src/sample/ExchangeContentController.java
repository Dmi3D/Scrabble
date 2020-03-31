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
        exchangeButton.setDisable( true );
    }

    public String getLettersToExchangeAsString()
    {
        return lettersInputField.getText().toUpperCase();
    }

    /**
     * Takes the String of letters player wishes to exchange and passes it to back-end
     * exchange method.
     * Fail: exchange doesn't work and text field informs player of their invalid input.
     * Success: 1) letters in player's frame are exchanged and player switch is triggered.
     * 2) Resets the pass counter back to 0 as they didn't successively pass twice
     */
    @FXML
    public void onButtonClicked()
    {
        char[] letters = getLettersToExchangeAsString().toCharArray();

        boolean exchanged = BoardController.players[BoardController.currentPlayer].getPlayerFrame().exchangeTiles( letters, BoardController.Pool );

        if ( !exchanged )
        {
            lettersInputField.setText( "INVALID INPUT" );
        }
        else
        {
            // resets the pass back to 0 when they broke the succession of passes by making a valid exchange
            OpeningWindowController.bController.resetPass();
            OpeningWindowController.bController.switchPlayer();
            OpeningWindowController.bController.challengeButton.setDisable( true );
            OpeningWindowController.bController.scrollLabel.setText( "" );


            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
            OpeningWindowController.bController.displayName();
            OpeningWindowController.bController.displayFrame();

            lettersInputField.clear();
        }
    }

    /**
     * Disables exchange button when no letter is input into the text field
     */
    @FXML
    private void handleKeyReleased()
    {
        boolean disable = lettersInputField.getText().isEmpty() || lettersInputField.getText().trim().isEmpty();
        exchangeButton.setDisable( disable );
    }

}
