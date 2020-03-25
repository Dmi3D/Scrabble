package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/** Class that handles pressing of buttons 'Yes' and 'No' in quitContent.fxml*/
public class QuitContentController
{
    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    /** 'YES' button pressed: closing the window i.e terminating game/program
     *  'NO' button pressed: disabling 'YES' button to prevent closing the window by mistake */
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
            OpeningWindowController.bController.rightPanel.getBottom().setVisible( false );
        }
    }
}
