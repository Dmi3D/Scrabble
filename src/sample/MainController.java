package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController
{

    @FXML
    public BorderPane mainPane;


    /*public void loadChoiceButtonsContent( javafx.event.ActionEvent actionEvent ) throws IOException
    {

        //mainPane.getRight().

        System.out.println("Loading choice buttons content"); // debug
        ChoiceButtonsContent content = new ChoiceButtonsContent();
        mainPane.setRight( content.getChoiceButtonsContent() );
        BorderPane.setMargin( mainPane.getCenter(), new Insets( 0, 0, 10, 0 ) );
        BorderPane.setMargin( mainPane.getRight(), new Insets( 0, 0, 0, 10 ) );
    }*/

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
