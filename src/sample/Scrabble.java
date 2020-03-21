package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Wrapper class
// Instantiating everything
public class Scrabble extends Application
{
    public Board Board = new Board();
    public Pool Pool = new Pool();
    public Frame FrameOne = new Frame( Pool );
    public Frame FrameTwo = new Frame( Pool );
    public Player PlayerOne = new Player( FrameOne );
    public Player PlayerTwo = new Player( FrameTwo );

    @Override
    public void start(Stage window) throws Exception
    {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource( "openingWindow.fxml" ));

        // Creating an instance of the controller of openingWindow.fxml
        OpeningWindowController OWController = new OpeningWindowController( Board, Pool, PlayerOne, PlayerTwo );
        loader.setController( OWController );

        Parent root = loader.load();
        window.setTitle("Scrabble");
        window.setScene(new Scene(root, 1028, 500));
        window.setResizable(false);
        window.show();
    }

    public static void main( String[] args ) throws Exception
    {
        launch( args );
    }

}
