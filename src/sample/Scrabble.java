package sample;

import javafx.application.Application;
import javafx.stage.Stage;

// Wrapper class
// Instantiating everything
public class Scrabble extends Application
{
    public Pool Pool = new Pool();
    public Frame FrameOne = new Frame( Pool );
    public Frame FrameTwo = new Frame( Pool );
    public Player PlayerOne = new Player( FrameOne );
    public Player PlayerTwo = new Player( FrameTwo );
    public UI UserInterface = new UI( PlayerOne, PlayerTwo );

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
