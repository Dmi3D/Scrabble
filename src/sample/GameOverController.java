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

public class GameOverController implements Initializable
{
    Player Winner;
    Player Loser;

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

    @FXML
    public void onButtonClicked( ActionEvent event ) throws IOException
    {
        if ( event.getSource().equals( restartGameButton ) )
        {
            // switch to opening window
            Parent openingViewParent = FXMLLoader.load( getClass().getResource( "openingWindow.fxml" ) );
            Scene openingViewScene = new Scene(openingViewParent);

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

    @FXML
    public void onMouseHoverIn( ActionEvent event )
    {
        if ( event.getSource().equals( restartGameButton ) )
        {
            // make animation of button color change
        }
    }

    @FXML
    public void onMouseHoverOut( ActionEvent event )
    {
        // make animation of button color change when hover out
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        if(BoardController.players[0].getScore() == BoardController.players[1].getScore())
        {
            winnerNameLabel.setText( BoardController.players[0].getName() + "\n" + BoardController.players[1].getName());
            winnerScoreLabel.setText( String.valueOf( BoardController.players[0].getScore() ) );
        }
        else if(BoardController.players[0].getScore() > BoardController.players[1].getScore())
        {
            Winner = BoardController.players[0];
            Loser = BoardController.players[1];

            winnerNameLabel.setText( Winner.getName() );
            winnerScoreLabel.setText( String.valueOf( Winner.getScore() ) );

            loserNameLabel.setText( Loser.getName() );
            loserScoreLabel.setText( String.valueOf( Loser.getScore() ) );
        }

        else
        {
            Winner = BoardController.players[1];
            Loser = BoardController.players[0];

            winnerNameLabel.setText( Winner.getName() );
            winnerScoreLabel.setText( String.valueOf( Winner.getScore() ) );

            loserNameLabel.setText( Loser.getName() );
            loserScoreLabel.setText( String.valueOf( Loser.getScore() ) );
        }

    }
}
