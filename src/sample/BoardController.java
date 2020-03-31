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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that is responsible for the following:
 * <p>
 * 1) Holds references to the objects responsible for game logic e.g. Pool
 * <p>
 * 2) Prompts the player whose turn it is on display
 * <p>
 * 3) Prompts the player's frame and score during their turn
 * <p>
 * 4) Holds the options a player has during their play
 * <p>
 * 5) Handles input from the user when making their play
 * <p>
 * 6) Holds valuable methods linking display of letters placed on the board,
 * display of tiles in a frame in the window, set and reset of data
 * to the back-end logic of the game
 * <p>
 * 7) Prompts error message in the window during an invalid word placement
 * <p>
 * 8) Handles fxml content switching during specific button presses
 */
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


    /**
     * Loads fxml content switching in the window when 'CHALLENGE' button is pressed.
     * This changes the content on the bottom right-hand-side of the window to
     * allow the player to challenge their opponent's last play.
     */
    @FXML
    public void handleChallengeButton( ActionEvent actionEvent ) throws IOException
    {
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getChallengeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    /**
     * Loads fxml content switching in the window when 'EXCHANGE' button is pressed.
     * This changes the content on the bottom right-hand-side of the window to
     * allow the player to input the tiles they wish to change in their frame.
     */
    @FXML
    public void handleExchangeButton( ActionEvent actionEvent ) throws IOException
    {
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getExchangeContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );

    }

    /**
     * Loads fxml content switching in the window when 'PLACE WORD' button is pressed.
     * This changes the content on the bottom right-hand-side of the window to allow
     * the player to input the word that they wish to place starting at certain position
     * on the board and going in a specific direction.
     */
    @FXML
    public void handlePlaceWordButton( ActionEvent actionEvent ) throws IOException, InterruptedException
    {
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getPlaceWordContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    /**
     * Loads fxml content switching in the window when 'HELP' button is pressed.
     * This changes the content on the bottom right-hand-side of the window to
     * display information about the different choices a player has during their turn.
     */
    @FXML
    public void handleHelpButton( ActionEvent actionEvent ) throws IOException
    {
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getHelpContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }

    /**
     * Loads fxml content switching in the window when 'HELP' button is pressed.
     * This changes the content on the bottom right-hand-side of the window and
     * prompts the user to decide whether they truly want to quit the game at that stage.
     */
    @FXML
    public void handleQuitButton( ActionEvent actionEvent ) throws IOException
    {
        FxmlLoader content = new FxmlLoader();
        rightPanel.setBottom( content.getQuitContent() );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
    }


    /**
     * Switches to next player when 'PASS' button is pressed.
     * Ends the game when the button is pressed twice in succession by a player.
     * When the game ends: 1) it decreases the player's scores by the sum of the scores of the
     * tiles int their correspondent frame
     * 2) it triggers switching of the scene to the gameOverWindow.fxml
     */
    @FXML
    public void handlePassButton( ActionEvent actionEvent ) throws IOException
    {
        scrollLabel.setText( "" );
        amountOfPass[currentPlayer]++;

        if ( passedTwice() )
        {
            // Game end situation 2: When Pool is empty and Board is full, only move possible is Pass
            if ( Pool.isEmpty() )
            {
                // Decreasing player's scores
                getCurrentPlayer().decreaseScore( getCurrentPlayer().getPlayerFrame().getScoreOnFrame( Pool ) );
                getOtherPlayer().decreaseScore( getOtherPlayer().getPlayerFrame().getScoreOnFrame( Pool ) );
            }

            FXMLLoader loader = new FXMLLoader( BoardController.class.getResource( "/gameOverWindow.fxml" ) );
            Parent gameOverWindow = loader.load();
            Scene gameOverScene = new Scene( gameOverWindow );
            Stage window = (Stage) ( (Node) actionEvent.getSource() ).getScene().getWindow();   // Accessing stage
            window.setScene( gameOverScene );   // changing the window's scene
            window.show();
        }

        switchPlayer();
        rightPanel.getBottom().setVisible( false );
        challengeButton.setDisable( true );
    }

    /**
     * Loads fxml content switching which displays specific error regarding invalid
     * word placement on the board.
     */
    public void loadErrorContent( int errorCode ) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader( BoardController.class.getResource( "/placeError.fxml" ) );
        AnchorPane content = fxmlLoader.load();
        fxmlLoader.setController( this );
        rightPanel.setBottom( content );
        BorderPane.setMargin( rightPanel.getBottom(), new Insets( 0, 10, 10, 10 ) );
        ObservableList<Node> children = content.getChildren();
        Label errorLabel = (Label) children.get( 0 );
        errorLabel.setText( getErrorCodeText( errorCode ) );
    }

    /**
     * Getting the error code to determine the text returned during word placement. This is then set as a text into the
     * label that calls it
     *
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

    /**
     * Handles player switch when called.
     * Enables Challenge button for the next player after switch
     * Disables Exchange button when the Pool is empty
     */
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

        // disable the exchange button when there are less than 7 tiles in the pool
        exchangeButton.setDisable( Pool.getTilesInPool() < 7 );
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

    public void displayAll()
    {
        displayName();
        displayFrame();
        displayBoard();
        displayScore();
    }


    /**
     * Setting the score of player in the label associated with score display
     */
    public void displayScore()
    {
        playerScoreDisplay.setText( String.valueOf( getCurrentPlayer().getScore() ) );
    }

    /**
     * Setting the name of the player in the label associated with name display
     */
    public void displayName()
    {
        playerTurnDisplayLabel.setText( players[currentPlayer].getName() + "'s turn." );
    }

    /**
     * Setting the letters from player's frame in the labels associated with
     * GridPane display of frame in boardGraphic.fxml
     */
    public void displayFrame()
    {
        ObservableList<Node> children = playerTilesDisplayOnTurn.getChildren();

        Player player = players[currentPlayer];

        if ( player.getPlayerFrame().hasEmpty() )
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

    /**
     * Getting the letters in the back-end Board to display in the front-end board after
     * placement.
     */
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

                else
                {
                    label.setText( "" );
                }
            }
        }
    }

    /**
     * Getting the node at certain row and column index in the GridPane representing the
     * board display in boardGraphic.fxml
     *
     * @return the node in the GridPane at position specified
     */
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

    /**
     * Determines whether both players pressed the 'PASS' button twice in succession.
     */
    private static boolean passedTwice()
    {
        return amountOfPass[currentPlayer] >= 2 && amountOfPass[getOtherPlayer( currentPlayer )] >= 2;
    }

    public static Player getCurrentPlayer()
    {
        return players[currentPlayer];
    }

    public static Player getOtherPlayer()
    {
        if ( currentPlayer == 0 )
        {
            return players[1];
        }
        return players[0];
    }

    private static int getOtherPlayer( int currentPlayer )
    {
        if ( currentPlayer == 0 )
        {
            return 1;
        }
        return 0;
    }

    /**
     * Resets the pass counter of back to 0
     */
    public void resetPass()
    {
        amountOfPass[currentPlayer] = 0;
    }

    /*** Method that resets the information in the board, pool, and players upon calling*/
    public void resetGame()
    {
        Board.reset();
        Pool.reset();
        players[0].reset( Pool );
        players[1].reset( Pool );
    }


}
