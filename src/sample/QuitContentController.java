package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.close();
        }
        else if ( event.getSource().equals( noButton ) )
        {
            yesButton.setDisable( true );
        }
    }
}
