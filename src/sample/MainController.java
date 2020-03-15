package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController
{
    @FXML
    private BorderPane mainPane;

    @FXML
    private BorderPane rightPanel;

    @FXML
    public void handleExchangeButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Exchange Button Clicked"); // debug
        ExchangeContent content = new ExchangeContent();
        rightPanel.setBottom( content.getExchangeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );

    }

    @FXML
    public void handlePlaceWordButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Place Word Button Clicked"); // debug
        PlaceWordContent content = new PlaceWordContent();
        rightPanel.setBottom( content.getPlaceWordContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }








}
