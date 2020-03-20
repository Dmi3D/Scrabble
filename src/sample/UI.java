package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

    public class UI extends Application {

        public Player PlayerOne;
        public Player PlayerTwo;

        public UI ( Player PlayerOne, Player PlayerTwo)
        {
            this.PlayerOne = PlayerOne;
            this.PlayerTwo = PlayerTwo;
        }

        @Override
        public void start(Stage window) throws Exception{
            FXMLLoader loader =  new FXMLLoader(getClass().getResource( "openingWindow.fxml" ));

            // Creating an instance of the controller of openingWindow.fxml
            OpeningWindowController OWController = new OpeningWindowController( PlayerOne, PlayerTwo );
            loader.setController( OWController );

            Parent root = loader.load();
            window.setTitle("Scrabble");
            window.setScene(new Scene(root, 1028, 500));
            window.setResizable(false);
            window.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}
