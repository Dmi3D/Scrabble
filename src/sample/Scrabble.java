package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 1) Instantiates all game objects needed to link the back and front-end
 * 2) Opens the window with openingWindow.fxml scene
 * 3) Passes references of the game objects to the OpeningWindowController
 */
public class Scrabble extends Application
{
    private Board Board = new Board();
    private Pool Pool = new Pool();
    private Frame FrameOne = new Frame( Pool );
    private Frame FrameTwo = new Frame( Pool );
    private Player PlayerOne = new Player( FrameOne );
    private Player PlayerTwo = new Player( FrameTwo );

    /**
     * Loads openingWindow.fxml and passes objects to its controller
     */
    @Override
    public void start( Stage window ) throws Exception
    {
        FXMLLoader loader = new FXMLLoader( Scrabble.class.getResource( "/openingWindow.fxml" ) );
        // Creating an instance of the controller of openingWindow.fxml to pass the object references
        OpeningWindowController OWController = new OpeningWindowController( Board, Pool, PlayerOne, PlayerTwo );
        loader.setController( OWController );       // setting the controller for openingWindow.fxml
        Parent root = (Parent) loader.load();
        window.setTitle( "Scrabble" );
        window.setScene( new Scene( root, 1028, 500 ) );
        window.setResizable( false );     // making the window fixed in size and not-resizable
        window.show();
    }

    public static void main( String[] args ) throws Exception
    {
        launch( args );
    }

}
