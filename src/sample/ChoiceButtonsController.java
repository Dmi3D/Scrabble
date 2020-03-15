package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ChoiceButtonsController
{

    @FXML
    public void handlePlaceWordButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Place Word Button Clicked"); // debug
        PlaceWordContent content = new PlaceWordContent();
        mainPane.setRight( content.getPlaceWordContent() );
        BorderPane.setMargin( mainPane.getCenter(), new Insets( 0, 0, 10, 0 ) );
        BorderPane.setMargin( mainPane.getRight(), new Insets( 0, 0, 0, 10 ) );
    }
}
