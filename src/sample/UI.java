package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UI extends Application {

    @Override
    public void start(Stage window) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource( "sample/placeWordFxml.fxml" ));
        window.setTitle("Scrabble");
        window.setScene(new Scene(root, 850, 500));
        window.setResizable(false);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
