package sample;

import javafx.application.Application;
import javafx.stage.Stage;

// Wrapper class
// Instantiating everything
public class Scrabble extends Application
{
    public static Pool Pool = new Pool();
    public static Frame FrameOne = new Frame( Pool );
    public static Frame FrameTwo = new Frame( Pool );
    public static Player PlayerOne = new Player( FrameOne );
    public static Player PlayerTwo = new Player( FrameTwo );
    public static UI UserInterface = new UI( PlayerOne, PlayerTwo );

    @Override
    public void start(Stage window) throws Exception
    {
        UserInterface.start( window );
    }


    public static void main( String[] args ) throws Exception
    {
        launch( args );
    }

}
