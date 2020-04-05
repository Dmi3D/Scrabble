package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that handles button presses in gameOverWindow.fxml
 */
public class GameOverController implements Initializable
{
    private Player Winner;
    private Player Loser;

    @FXML
    Button restartGameButton;

    @FXML
    Button exitButton;

    @FXML
    Label winnerNameLabel;

    @FXML
    Label winnerScoreLabel;

    @FXML
    Label loserNameLabel;

    @FXML
    Label loserScoreLabel;

    /**
     * Press of button 'RESTART':
     * 1) calls method that resets the game
     * 2) triggers scene change in window to openingWindow.fxml
     * <p>
     * Press of button 'EXIT':
     * 1) closes the window i.e. terminates the game/program
     */
    @FXML
    public void onButtonClicked( ActionEvent event ) throws IOException
    {
        if ( event.getSource().equals( restartGameButton ) )
        {
            // resetting the game
            OpeningWindowController.bController.resetGame();

            // switch to opening window
            FXMLLoader loader = new FXMLLoader( GameOverController.class.getResource( "/openingWindow.fxml" ) );
            OpeningWindowController openingWindowController = new OpeningWindowController( BoardController.Board, BoardController.Pool, BoardController.players[0], BoardController.players[1] );
            loader.setController( openingWindowController );
            Parent openingViewParent = loader.load();
            Scene openingViewScene = new Scene( openingViewParent );

            // Getting information about the stage i.e. window to access it
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // changing the window's scene
            window.setScene( openingViewScene );
            window.show();
        }
        else if ( event.getSource().equals( exitButton ) )
        {
            // end game
            Stage window = (Stage) exitButton.getScene().getWindow();
            window.close();
        }
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // When there is a tie/draw in the game i.e. players have the same score
        if ( BoardController.players[0].getScore() == BoardController.players[1].getScore() )
        {
            winnerNameLabel.setText( BoardController.players[0].getName() + "\n" + BoardController.players[1].getName() );
            winnerScoreLabel.setText( String.valueOf( BoardController.players[0].getScore() ) );
        }
        // When Player 1 is the winner
        else if ( BoardController.players[0].getScore() > BoardController.players[1].getScore() )
        {
            Winner = BoardController.players[0];
            Loser = BoardController.players[1];

            winnerNameLabel.setText( Winner.getName() );                        // Displaying name of winner i.e. Player 1
            winnerScoreLabel.setText( String.valueOf( Winner.getScore() ) );    // Displaying score of winner i.e. Player 1

            loserNameLabel.setText( Loser.getName() );                          // Displaying name of loser i.e. Player 2
            loserScoreLabel.setText( String.valueOf( Loser.getScore() ) );      // Displaying score of loser i.e. Player 2
        }
        // When Player 2 is the winner
        else
        {
            Winner = BoardController.players[1];
            Loser = BoardController.players[0];

            winnerNameLabel.setText( Winner.getName() );                        // Displaying name of winner i.e. Player 2
            winnerScoreLabel.setText( String.valueOf( Winner.getScore() ) );    // Displaying score of winner i.e. Player 2

            loserNameLabel.setText( Loser.getName() );                          // Displaying name of loser i.e. Player 1
            loserScoreLabel.setText( String.valueOf( Loser.getScore() ) );      // Displaying score of loser i.e. Player 1
        }

    }
}
