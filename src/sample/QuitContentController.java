package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class QuitContentController
{

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    public void onButtonClicked( ActionEvent event ) throws IOException
    {
        if ( event.getSource().equals( yesButton ) )
        {
            Parent openingViewParent = FXMLLoader.load( getClass().getResource( "openingWindow.fxml" ) );
            Scene openingViewScene = new Scene(openingViewParent);

            // Getting information about the stage i.e. window to access it
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // changing the window's scene
            window.setScene( openingViewScene );
            window.show();
        }
        else if ( event.getSource().equals( noButton ) )
        {
            yesButton.setDisable( true );
        }
    }
}
