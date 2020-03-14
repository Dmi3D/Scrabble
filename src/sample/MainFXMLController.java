package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;

public class MainFXMLController
{

    @FXML
    private BorderPane mainPane;

   /* @FXML
    private void handlePassButton( ActionEvent event )
    {
        // would make the other user's turn
    }*/

   @FXML
    private void handlePlaceWordButton( ActionEvent event )
   {
       System.out.println("Place Word Button Clicked"); // debug
       FxmlLoader loader = new FxmlLoader();
       VBox placeWordContent = loader.getPage( "placeWordFxml" );
       mainPane.setRight( placeWordContent );
   }


}
