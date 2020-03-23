package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController
{
    @FXML
    Button restartGameButton;

    @FXML
    Button exitButton;

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

}
