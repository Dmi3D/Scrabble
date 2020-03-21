package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable
{
    private Board Board;
    private Pool Pool;
    private Player[] players;

    private int currentPlayer;
    private int[] amountOfPass;

    @FXML
    private BorderPane rightPanel;

    @FXML
    private TextArea wordsPlacedDisplay;

    @FXML
    private Label playerTurnDisplayLabel;

    @FXML
    private GridPane playerTilesDisplayOnTurn;

    @FXML
    public void handleChallengeButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Challenge Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getChallengeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleExchangeButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Exchange Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getExchangeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );

    }

    @FXML
    public void handlePlaceWordButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Place Word Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getPlaceWordContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleHelpButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Help Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getHelpContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handleQuitButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println("Quit Button Clicked"); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getQuitContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 )  );
    }

    @FXML
    public void handlePassButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Pass Button Clicked" ); // debug
       /* amountOfPass[currentPlayer]++;
        if(amountOfPass[currentPlayer] >= 2)
        {
            System.out.println("This should stop game");
        }*/

        switchPlayer();
        // keep a counter for number of times pass is selected
        // if selected twice in succession, game is over
        // also make this button trigger next turn
    }


    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {

    }

    public void switchPlayer()
    {
        if(currentPlayer == 0)
        {
            currentPlayer = 1;
        }

        else if(currentPlayer == 1)
        {
            currentPlayer = 0;
        }

        displayName();
        displayFrame();
    }

    public void setPlayers(Player PlayerOne, Player PlayerTwo)
    {
        players = new Player[2];
        amountOfPass = new int[2];
        players[0] = PlayerOne;
        players[1] = PlayerTwo;
        currentPlayer = 0;

        displayName();
        displayFrame();
    }

    public void setBoard(Board Board)
    {
        this.Board = Board;
    }

    public void setPool(Pool Pool)
    {
        this.Pool = Pool;
    }

    private void displayName()
    {
        playerTurnDisplayLabel.setText( players[currentPlayer].getName() + "'s turn." );
    }

    private void displayFrame()
    {
        ObservableList<Node> children = playerTilesDisplayOnTurn.getChildren();

        Player player = players[currentPlayer];

        for ( int i = 0; i < children.size(); i++ )
        {
            Node node = children.get( i );

            if(node instanceof Label)
            {
                ( (Label) node ).setText( String.valueOf( player.getPlayerFrame().getFrame()[i] ) );
            }
        }
    }
}
