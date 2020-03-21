package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningWindowController
{
    private Board Board;
    private Pool Pool;
    private Player PlayerOne;
    private Player PlayerTwo;

    public OpeningWindowController( Board Board, Pool Pool, Player PlayerOne, Player PlayerTwo)
    {
        this.Board = Board;
        this.Pool = Pool;
        this.PlayerOne = PlayerOne;
        this.PlayerTwo = PlayerTwo;
    }

    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    @FXML
    private Button startGameButton;



    public void initialize()
    {
        startGameButton.setDisable( true );
    }


    public String getPlayerOneName()
    {
        return player1NameField.getText();
    }

    public String getPlayerTwoName()
    {
        return player2NameField.getText();
    }

    /** Method that changes the Scene to the boardGraphic scene
     * */
    public void startGameButtonClicked( ActionEvent event) throws IOException
    {
        if ( event.getSource().equals( startGameButton ) )
        {
            System.out.println( "Player 1 name: " + getPlayerOneName() + "\nPlayer 2 name: " + getPlayerTwoName() );

            PlayerOne.setName( getPlayerOneName() );
            PlayerTwo.setName( getPlayerTwoName() );

           /* System.out.println("PlayerOne: " + PlayerOne.getName());
            System.out.println("PlayerTwo: " + PlayerTwo.getName());
*/
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "boardGraphic.fxml" ) );
            Parent boardViewParent = (Parent) loader.load();

            BoardController mController = loader.getController();
            mController.setPlayers( PlayerOne, PlayerTwo );
            mController.setBoard( Board );
            mController.setPool( Pool );

            Scene boardViewScene = new Scene(boardViewParent);

            // Getting information about the stage i.e. window to access it
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // changing the window's scene
            window.setScene( boardViewScene );
            window.show();
        }
    }

    /**
     * Disables the start game button if player's names are not typed into the text fields
     * */
    @FXML
    private void handleKeyReleased()
    {
        boolean disableButton = player1NameField.getText().isEmpty() || player1NameField.getText().trim().isEmpty()
                            ||  player2NameField.getText().isEmpty() || player2NameField.getText().trim().isEmpty();
        startGameButton.setDisable( disableButton );
    }
}
