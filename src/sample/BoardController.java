package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    public BorderPane rightPanel;

    @FXML
    private GridPane boardDisplay;

    @FXML
    private Label playerTurnDisplayLabel;

    @FXML
    private GridPane playerTilesDisplayOnTurn;

    @FXML
    public Button challengeButton;

    @FXML
    public Button exchangeButton;

    @FXML
    public Label scrollLabel;

    @FXML
    private Label playerScoreDisplay;

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
    public void handlePlaceWordButton( ActionEvent actionEvent ) throws IOException, InterruptedException
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
        amountOfPass[currentPlayer]++;

        if ( passedTwice() )
        {
            FXMLLoader loader = new FXMLLoader( getClass().getResource( "gameOverWindow.fxml" ) );
            Parent gameOverWindow = (Parent) loader.load();
            Scene gameOverScene = new Scene(gameOverWindow);

            // Getting information about the stage i.e. window to access it
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // changing the window's scene
            window.setScene( gameOverScene );
            window.show();
        }

        switchPlayer();
        rightPanel.getBottom().setVisible( false );
        challengeButton.setDisable( true );
    }

    public void loadErrorContent( int errorCode ) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "placeError.fxml" ) );
        AnchorPane content = fxmlLoader.load();
        fxmlLoader.setController( this );
        System.out.println("Error content loaded"); // debug
        rightPanel.setBottom( content );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
        ObservableList<Node> children = content.getChildren();
        Label errorLabel = (Label) children.get( 0 );
        errorLabel.setText( getErrorCodeText( errorCode ) );
    }

    /**
     * Getting the error code to determine the text returned during word placement. This is then set as a text into the
     * label that calls it
     * @param errorCode integer that holds value associated with specific error during word placement
     * @return string representing error text
     */
    private String getErrorCodeText( int errorCode )
    {
        switch ( errorCode )
        {
            case 0:
                return "INVALID PLACEMENT OF FIRST WORD. MAKE SURE WORD GOES THROUGH CENTRE OF THE BOARD!";
            case 1:
                return "INVALID WORD PLACEMENT OUTSIDE OF BOUNDS. MAKE SURE THE WORD DOESN'T GO OUT OF BOUNDS OF THE BOARD!";
            case 2:
                return "INVALID WORD. MAKE SURE YOU HAVE THE LETTERS IN YOUR FRAME, AND/OR THAT THEY ARE FOUND ON THE BOARD IF OVERLAPPING!";
            case 3:
                return "INVALID DUE TO LETTER CLASH. MAKE SURE THAT PLACEMENT IS SAFE AND THAT LETTERS DON'T CLASH!";
            case 4:
                return "INVALID WORD. MAKE SURE TO PLACE AT LEAST ONE TILE FROM YOUR FRAME!";
            default:
                return "INVALID WORD PLACEMENT. MAKE SURE WORD IS CONNECTED TO OTHER LETTERS ON THE BOARD!";
        }
    }


    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        playerScoreDisplay.setText( "0" );
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

        displayAll();
        challengeButton.setDisable( false );

        if(players[currentPlayer].getPlayerFrame().hasEmpty())
        {
            exchangeButton.setDisable( true );
        }

        else
        {
            exchangeButton.setDisable( false );
        }
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

    public void displayScore()
    {
        playerScoreDisplay.setText( String.valueOf(getCurrentPlayer().getScore()) );
    }

    public void displayName()
    {
        playerTurnDisplayLabel.setText( players[currentPlayer].getName() + "'s turn." );
    }

    public void displayFrame()
    {
        ObservableList<Node> children = playerTilesDisplayOnTurn.getChildren();

        Player player = players[currentPlayer];

        if (player.getPlayerFrame().hasEmpty())
        {
            players[currentPlayer].getPlayerFrame().fillFrame( Pool );
        }

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
                    System.out.println( "Should set label to: " + Board.getSquareAt( row - 1, col - 1 ).getTile() );
                }

                else
                {
                    label.setText( "" );
                }
            }
        }
    }

    private Node getNodeByRowColumnIndex( final int row, final int column, GridPane Board )
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

    public void displayAll()
    {
        displayName();
        displayFrame();
        displayBoard();
        displayScore();
    }

    private static boolean passedTwice()
    {
        if ( amountOfPass[currentPlayer] >= 2 )
        {
            return true;
        }

        return false;
    }

    public static Player getCurrentPlayer()
    {
        return players[currentPlayer];
    }


    /*** Method that resets the information in the board, pool, and players upon calling*/
    public void resetGame()
    {
        Board.reset();
        Pool.reset();
        players[0].reset( Pool );
        players[1].reset( Pool );
    }

    public static Player getOtherPlayer()
    {
        if ( currentPlayer == 0 )
        {
            return players[1];
        }

        return players[0];

    }
}
