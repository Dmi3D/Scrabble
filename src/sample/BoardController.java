package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable
{
    public static Board Board;
    public static Pool Pool;
    public static Player[] players;

    public static int currentPlayer;
    public static int[] amountOfPass;

    @FXML
    private BorderPane rightPanel;

    @FXML
    private GridPane boardDisplay;

    @FXML
    private Label playerTurnDisplayLabel;

    @FXML
    private GridPane playerTilesDisplayOnTurn;

    @FXML
    public void handleChallengeButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Challenge Button Clicked" ); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getChallengeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    @FXML
    public void handleExchangeButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Exchange Button Clicked" ); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getExchangeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );

    }

    @FXML
    public void handlePlaceWordButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Place Word Button Clicked" ); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getPlaceWordContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    @FXML
    public void handleHelpButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Help Button Clicked" ); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getHelpContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    @FXML
    public void handleQuitButton( ActionEvent actionEvent ) throws IOException
    {
        System.out.println( "Quit Button Clicked" ); // debug
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getQuitContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
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
        if ( currentPlayer == 0 )
        {
            currentPlayer = 1;
        }

        else if ( currentPlayer == 1 )
        {
            currentPlayer = 0;
        }

        displayName();
        displayFrame();
        displayBoard();
    }

    public void setPlayers( Player PlayerOne, Player PlayerTwo )
    {
        BoardController.players = new Player[2];
        BoardController.amountOfPass = new int[2];
        BoardController.players[0] = PlayerOne;
        BoardController.players[1] = PlayerTwo;
        BoardController.currentPlayer = 0;

        displayName();
        displayFrame();
    }

    public void setBoard( Board Board )
    {
        BoardController.Board = Board;
    }

    public void setPool( Pool Pool )
    {
        BoardController.Pool = Pool;
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

            if ( node instanceof Label )
            {
                ( (Label) node ).setText( String.valueOf( player.getPlayerFrame().getFrame()[i] ) );
            }
        }
    }

    public void displayBoard()
    {
        for ( int row = 1; row <= 15; row++ )
        {
            for ( int col = 1; col <= 15; col++ )
            {
                Pane pane = (Pane) getNodeByRowColumnIndex( row, col, boardDisplay );

                ObservableList<Node> labelList = pane.getChildren();

                Label label = (Label) labelList.get( 0 );

                if ( Board.getSquareAt( row - 1, col - 1 ).getTile() != '\u0000' )
                {
                    label.setText( String.valueOf( Board.getSquareAt( row - 1, col - 1 ).getTile() ) );
                }
            }
        }
    }

    public Node getNodeByRowColumnIndex( final int row, final int column, GridPane Board )
    {
        Node result = null;
        ObservableList<Node> children = Board.getChildren();

        int rowOfGridPane;
        int columnOfGridPane;

        for ( Node node : children )
        {

            if ( GridPane.getRowIndex( node ) != null )
            {
                rowOfGridPane = GridPane.getRowIndex( node );
            }

            else
            {
                rowOfGridPane = 0;
            }

            if ( GridPane.getColumnIndex( node ) != null )
            {
                columnOfGridPane = GridPane.getColumnIndex( node );
            }

            else
            {
                columnOfGridPane = 0;
            }

            if ( rowOfGridPane == row && columnOfGridPane == column )
            {
                result = node;
                break;
            }
        }

        return result;
    }
}
