package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController
{

    @FXML
    private BorderPane mainPane;

   /* @FXML
    private void handlePassButton( ActionEvent event )
    {
        // would make the other user's turn
    }*/

   @FXML
    public void handlePlaceWordButton( javafx.event.ActionEvent actionEvent ) throws IOException
   {
        System.out.println("Place Word Button Clicked"); // debug
        PlaceWordContent content = new PlaceWordContent();
        mainPane.setRight( content.getPlaceWordContent() );
    }


}
