package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController
{
    @FXML
    private BorderPane rightPanel;

    @FXML
    private TextArea wordsPlacedDisplay;

    @FXML
    private DialogPane playerInformationDisplayOnTurn;

    @FXML
    private Label playerTurnDisplayLabel;

    @FXML
    private GridPane playerTilesDisplayOnTurn;

    @FXML
    public void handleChallengeButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Challenge Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getChallengeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleExchangeButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Exchange Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getExchangeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );

    }

    @FXML
    public void handlePlaceWordButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Place Word Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getPlaceWordContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleHelpButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Help Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getHelpContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleQuitButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Quit Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getQuitContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handlePassButton( javafx.event.ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Pass Button Clicked" ); // debug
        // keep a counter for number of times pass is selected
        // if selected twice in succession, game is over
        // also make this button trigger next turn
    }






}
